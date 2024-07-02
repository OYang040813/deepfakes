package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Announcement;
import com.example.springboot.request.AnnouncementPageRequest;
import com.example.springboot.request.MessagePageRequest;
import com.example.springboot.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author oy
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    IAnnouncementService announcementService;

    @PostMapping("/save")
    public Result save(@RequestBody Announcement announcement) {
        announcementService.save(announcement);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        announcementService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Announcement announcement) {
        announcementService.update(announcement);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Announcement announcement = announcementService.getById(id);
        return Result.success(announcement);
    }

    @GetMapping("/list")
    public Result list() {
        List<Announcement> messages = announcementService.list();
        return Result.success(messages);
    }

    @GetMapping("/page")
    public Result page(AnnouncementPageRequest announcementPageRequest) {
        return Result.success(announcementService.page(announcementPageRequest));
    }
}

