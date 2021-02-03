package com.jacques.sensitive.serivice.impl;

import com.jacques.sensitive.dao.jpa.UserRepository;
import com.jacques.sensitive.pojo.User;
import com.jacques.sensitive.pojo.where.WhereUser;
import com.jacques.sensitive.serivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 10:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        user.verify();
        return userRepository.save(user);
    }

    /**
     * 得到
     *
     * @param id id
     * @return {@link User }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    @Override
    public User get(int id) {
        return userRepository.findById(id);
    }

    /**
     * 找到所有
     *
     * @return {@link List<User> }
     * @author Jacques·Fry
     * @since 2021/02/01 16:18
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 分页查询用户
     *
     * @param where 查询条件
     * @return {@link Page<User> }
     * @author Jacques·Fry
     * @since 2021/02/03 13:38
     */
    @Override
    public Page<User> queryUser(WhereUser where) {
        Pageable page=PageRequest.of(where.getPageNo()-1,where.getPageSize());
        return userRepository.findAll(page);
    }
}
