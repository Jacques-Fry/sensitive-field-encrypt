package com.jacques.sensitive.serivice.impl;

import com.jacques.sensitive.dao.jpa.UserRepository;
import com.jacques.sensitive.utils.PageResult;
import com.jacques.sensitive.pojo.User;
import com.jacques.sensitive.pojo.where.WhereUser;
import com.jacques.sensitive.serivice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务-JPA
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 10:34
 */
@Service
@Slf4j
public class UserJpaServiceImpl implements UserService {

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
        log.info("添加用户");
        return userRepository.save(user);
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
        log.info("根据ID查询用户");
        return userRepository.findById(id);
    }

    /**
     * 查询所有用户
     *
     * @return {@link List<User> }
     * @author Jacques·Fry
     * @since 2021/02/01 16:18
     */
    @Override
    public List<User> findAll() {
        log.info("查询所有用户");
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
    public PageResult<User> queryUser(WhereUser where) {
        log.info("分页查询用户");
        Pageable pageable = PageRequest.of(where.getPageNo() - 1, where.getPageSize());
        Page<User> page = userRepository.findAll(pageable);
        return new PageResult<>(page.getTotalElements(), page.getContent());
    }
}
