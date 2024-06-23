package com.example.springboot.gen.controller;

import com.example.springboot.common.Result;
import com.example.springboot.gen.entity.User;
import com.example.springboot.gen.service.IUserService;
import com.example.springboot.request.UserPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @author oy
*/
@RestController
@RequestMapping("/gen/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/list")
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){
        return Result.success(userService.page(userPageRequest));
    }
}

