package com.stone.manage.oauth.controller;

import io.swagger.annotations.Api;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wjj
 * @date 2020/6/11
 */
@Api(value = "用户验证")
@RestController
public class UserController {

    @GetMapping("user")
    public Principal user(Principal user){
        return user;
    }

    public static void main(String[] args){

        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
