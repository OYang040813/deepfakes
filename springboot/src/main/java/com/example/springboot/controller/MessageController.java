package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Message;
import com.example.springboot.request.MessagePageRequest;
import com.example.springboot.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author oy
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    IMessageService messageService;

    @PostMapping("/save")
    public Result save(@RequestBody Message message) {
        messageService.save(message);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        messageService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Message message) {
        messageService.update(message);
        return Result.success();
    }

    @PutMapping("/mark/{id}")
    public Result mark(@PathVariable Integer id) {
        Message byId = messageService.getById(id);
        byId.setIsread("1");
        messageService.update(byId);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Message message = messageService.getById(id);
        return Result.success(message);
    }

    @GetMapping("/list")
    public Result list() {
        List<Message> messages = messageService.list();
        return Result.success(messages);
    }

    @GetMapping("/page")
    public Result page(MessagePageRequest messagePageRequest) {
        return Result.success(messageService.page(messagePageRequest));
    }
}

