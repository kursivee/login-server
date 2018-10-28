package com.kursivee.resource.config;

import com.kursivee.resource.filter.AdminFilter;
import com.kursivee.resource.filter.GuestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {

    @Autowired
    private AdminFilter adminFilter;

    @Autowired
    private GuestFilter guestFilter;

    @Bean
    public FilterRegistrationBean<AdminFilter> filterAdmin() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(adminFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/admin/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<GuestFilter> filterGuest() {
        FilterRegistrationBean<GuestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(guestFilter);
        registrationBean.addUrlPatterns("/guest/*");
        return registrationBean;
    }
}
