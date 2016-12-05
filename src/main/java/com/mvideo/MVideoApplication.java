package com.mvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mybatis.admin on 16/12/3.
 */
@SpringBootApplication
@RestController
public class MVideoApplication {

    @RequestMapping("/")
    public String home(){
        return "hello world !";
    }

    public static void main(String[] args) {
        SpringApplication.run(MVideoApplication.class,args);
    }

}
