package com.purgeteam.spring.cloud.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@EnableZuulProxy
@RestController
@SpringBootApplication(scanBasePackages = {"com.purgeteam.spring.cloud.zuul"})
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @GetMapping("api")
    public String apiGateway(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Stream.of(cookies).forEach(c -> log.info("n:{} v:{}", c.getName(), c.getValue()));
        return "api";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "zuul cloud";
    }

}
