package com.bobo.demo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.demo.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2021-12-08
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
