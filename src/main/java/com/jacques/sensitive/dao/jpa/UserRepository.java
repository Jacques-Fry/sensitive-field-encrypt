package com.jacques.sensitive.dao.jpa;

import com.jacques.sensitive.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 10:45
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    @Override
    List<User> findAll();

    User findById(int id);

    Page<User> findAll(Pageable pageable);
}
