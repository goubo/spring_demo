package com.bobo.demo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 *
 */
@Component
@Slf4j
public class SessionAuthFilter implements GlobalFilter, Ordered {

    private final RedissonClient redissonClient;

    public SessionAuthFilter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        System.out.println(route);
        RMap<Object, Object> session_token = redissonClient.getMap("session_id_user");

        exchange.getRequest().getCookies().toSingleValueMap().forEach((k, v) -> System.out.println(k + "-->" + v));
        //TODO 从注册中心获取服务真实列表
        HttpCookie bobo_session_id = exchange.getRequest().getCookies().getFirst("bobo_session_id");
        String cookie = bobo_session_id == null ? "" : bobo_session_id.getValue();
        Object o = session_token.get(cookie); // 假定这个对象是实际要转发的服务ip

        URI requestUrl = UriComponentsBuilder.fromUri(exchange.getRequest().getURI()).uri(route.getUri()).build().toUri();
        System.out.println(requestUrl);
        System.out.println(URI.create("http://127.0.0.1:10003" + exchange.getRequest().getURI().getPath()));
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, URI.create("http://127.0.0.1:10003" + exchange.getRequest().getURI().getPath()));

        return chain.filter(exchange).then();

    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
