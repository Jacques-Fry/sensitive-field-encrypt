package com.jacques.sensitive.controller;

import com.jacques.sensitive.pojo.User;
import com.jacques.sensitive.serivice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 9:20
 */
@RestController
@Api(tags = "用户服务")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("给用户对象赋值")
    @GetMapping("/set")
    public User getUser(User user){
        System.out.println(user);
        return user;
    }

    @ApiOperation("保存一个用户对象")
    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @ApiOperation("根据ID获取一个对象")
    @GetMapping("/get/{id}")
    public User get(@PathVariable(name = "id") int id){
        return  userService.get(id);
    }

    @ApiOperation("获取全部对象")
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
}
