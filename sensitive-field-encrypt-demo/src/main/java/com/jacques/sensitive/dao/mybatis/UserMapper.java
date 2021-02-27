package com.jacques.sensitive.dao.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jacques.sensitive.core.annotation.Sensitive;
import com.jacques.sensitive.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 用户访问层
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/27 10:22
 */
@Sensitive
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Override
    List<User> selectList(@Param("ew") Wrapper<User> queryWrapper);

    @Override
    int insert(User entity);

    @Override
    User selectById(Serializable id);

    @Override
    int updateById(User entity);

    @Override
    int update(User entity, @Param("ew") Wrapper<User> updateWrapper);

    @Override
    <E extends IPage<User>> E selectPage(E page, @Param("ew") Wrapper<User> queryWrapper);

}
