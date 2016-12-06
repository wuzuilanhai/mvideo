package com.mvideo;

import com.mvideo.config.CacheConfig;
import com.mvideo.config.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 16/12/3.
 */
@SpringBootApplication
@EnableTransactionManagement
@Import({DatabaseConfig.class, CacheConfig.class})
@RestController
public class MVideoApplication {

    @RequestMapping("/")
    public String home() throws Exception {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(MVideoApplication.class, args);
    }

}
