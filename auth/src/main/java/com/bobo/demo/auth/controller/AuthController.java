package com.bobo.demo.auth.controller;

import com.bobo.demo.auth.client.UserClient;
import com.bobo.demo.auth.entity.VO.AuthParam;
import com.bobo.demo.common.entity.auth.AuthVO;
import com.bobo.demo.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author bo
 */
@RestController
@Slf4j
public class AuthController {

    private final UserClient userClient;
    private final RedissonClient redissonClient;

    public AuthController(RedissonClient redissonClient, UserClient userClient) {
        this.userClient = userClient;
        this.redissonClient = redissonClient;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseResult<AuthVO> login(@RequestBody AuthParam authParam, HttpSession session, HttpServletResponse httpResponse) {
        ResponseResult<AuthVO> check;
        AuthVO authVO = (AuthVO) session.getAttribute("login_user");
        if (authVO != null) {
            log.info("已登录状态", authVO);
            check = new ResponseResult<>(authVO);
        } else {
            check = userClient.check(authParam);
            if (check.success()) {
                log.info("登录成功", check);
                session.setAttribute("login_user", check.getObject());

                RMap<Object, Object> session_token = redissonClient.getMap("session_id_user");
                session_token.put(session.getId(), check.getObject());
                Cookie cookie = new Cookie("bobo_session_id", session.getId());
                cookie.setPath("/");
                httpResponse.addCookie(cookie);
            }
        }
        return check;
    }
}

