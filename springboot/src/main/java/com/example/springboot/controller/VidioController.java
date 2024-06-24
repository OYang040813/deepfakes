package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.entity.Vidio;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.request.VidioPageRequest;
import com.example.springboot.service.IVidioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author oy
 */
@RestController
@RequestMapping("/vidio")
public class VidioController {

    @Autowired
    IVidioService vidioService;

    @PostMapping("/save")
    public Result save(@RequestBody Vidio vidio) {
        vidioService.save(vidio);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        vidioService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Vidio vidio) {
        vidioService.update(vidio);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Vidio vidio = vidioService.getById(id);
        return Result.success(vidio);
    }

    @GetMapping("/list")
    public Result list() {
        List<Vidio> users = vidioService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(VidioPageRequest vidioPageRequest) {
        return Result.success(vidioService.page(vidioPageRequest));
    }

}

