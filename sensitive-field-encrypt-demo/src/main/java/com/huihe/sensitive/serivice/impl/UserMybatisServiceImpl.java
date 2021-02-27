package com.huihe.sensitive.serivice.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huihe.sensitive.dao.mybatis.UserMapper;
import com.huihe.sensitive.utils.PageResult;
import com.huihe.sensitive.pojo.User;
import com.huihe.sensitive.pojo.where.WhereUser;
import com.huihe.sensitive.serivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务-Mybatis
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/27 10:48
 */
@Service
public class UserMybatisServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 保存
     *
     * @param user 用户
     * @return {@link User }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    @Override
    public User save(User user) {
        userMapper.insert(user);
        return user;
    }

    /**
     * 根据ID查询用户
     *
     * @param id id
     * @return {@link User }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    @Override
    public User get(int id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有用户
     *
     * @return {@link List <User> }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    /**
     * 分页查询用户
     *
     * @param where 查询条件
     * @return {@link PageResult <User> }
     * @author Jacques·Fry
     * @since 2021/02/03 13:38
     */
    @Override
    public PageResult<User> queryUser(WhereUser where) {
        IPage<User> page=new Page<>(where.getPageNo(),where.getPageSize());
        userMapper.selectPage(page,null);
        return new PageResult<>(page.getTotal(),page.getRecords());
    }
}
