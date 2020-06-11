package com.stone.manage.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.stone.manage.admin.dao")
@SpringBootApplication
public class ManageAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageAdminApplication.class, args);
    }

}
