package com.huihe.sensitive.dao.jpa;

import com.huihe.sensitive.core.annotation.Sensitive;
import com.huihe.sensitive.pojo.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用户
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 10:45
 */
@Sensitive
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    User save(User user);

    @Override
    List<User> findAll();

    @Override
    List<User> findAll(Sort sort);

    @Override
    List<User> findAllById(Iterable<Integer> iterable);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> iterable);

    @Override
    void flush();

    @Override
    <S extends User> S saveAndFlush(S s);

    @Override
    void deleteInBatch(Iterable<User> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    User getOne(Integer integer);

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(User user);

    @Override
    void deleteAll(Iterable<? extends User> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends User> long count(Example<S> example);

    @Override
    <S extends User> boolean exists(Example<S> example);
}
