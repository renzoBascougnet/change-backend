//package com.renzoBascougnet.change_backend.config;
//
//import com.renzoBascougnet.change_backend.filter.LoggingFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class FilterConfig {
//
//    private final LoggingFilter loggingFilter;
//
//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(loggingFilter);
//        registrationBean.addUrlPatterns("/api/*");
//        return registrationBean;
//    }
//}
