package com.zxw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zxw
 * @date 2019/11/3 17:25
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:config/kaptcha.xml"})
@MapperScan({"com.zxw.jwxt.mapper","com.zxw.monitor.mapper"})
@EnableTransactionManagement
public class JwSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwSystemApplication.class);
    }
}
