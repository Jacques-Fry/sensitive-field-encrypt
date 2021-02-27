package com.huihe.sensitive.serivice;


import com.huihe.sensitive.pojo.where.WhereUser;
import com.huihe.sensitive.utils.PageResult;
import com.huihe.sensitive.pojo.User;
import org.springframework.data.domain.Page;

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
     * 根据ID查询用户
     *
     * @param id id
     * @return {@link User }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    User get(int id);

    /**
     * 查询所有用户
     *
     * @return {@link List<User> }
     * @author Jacques·Fry
     * @since 2021/02/01 16:17
     */
    List<User> findAll();

    /**
     * 分页查询用户
     *
     * @param where 查询条件
     * @return {@link Page<User> }
     * @author Jacques·Fry
     * @since 2021/02/03 13:38
     */
    PageResult<User> queryUser(WhereUser where);
}
