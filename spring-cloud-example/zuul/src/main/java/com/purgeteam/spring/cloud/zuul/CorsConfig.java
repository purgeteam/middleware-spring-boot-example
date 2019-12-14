//package com.purgeteam.spring.cloud.zuul;//package com.deepblueai.gateway.config.cors;
//
//import java.util.Arrays;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * 跨域配置 C - Cross  O - Origin  R - Resource  S - Sharing
// *
// * @author purgeyao
// * @since 1.0
// */
//@Configuration
////@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsConfig {
//
//  @Bean
//  public FilterRegistrationBean filterRegistrationBean() {
//    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    final CorsConfiguration config = new CorsConfiguration();
//
//    config.setAllowCredentials(true);
//    config.setAllowedOrigins(Arrays.asList("*"));
//    config.setAllowedHeaders(Arrays.asList("*"));
//    config.setAllowedMethods(Arrays.asList("*"));
//    config.setMaxAge(300L);
//
//    source.registerCorsConfiguration("/**", config);
//    CorsFilter corsFilter = new CorsFilter(source);
//    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(corsFilter);
//    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    return filterRegistrationBean;
//  }
//}
