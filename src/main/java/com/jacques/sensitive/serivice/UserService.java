package com.jacques.sensitive.serivice;


import com.jacques.sensitive.pojo.User;

import java.util.List;

/**
 * 用户服务
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 10:28
 */
public interface UserService {

    /**
     * 保存
     *
     * @param user 用户
     * @return {@link User }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    User save(User user);

    /**
     * 得到
     *
     * @param id id
     * @return {@link User }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    User get(int id);

    /**
     * 找到所有
     *
     * @return {@link List<User> }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    List<User> findAll();
}
