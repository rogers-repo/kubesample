package com.roger.gateway.config;

import com.roger.gateway.filter.JwtValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.GenericFilterBean;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class GatewayBeanConfig {

    public static final String DELIVERY_API_URL = "/api/delivery/orders/*";
    public static final String FLIPPY_API_URL = "/api/orders/*";
    public static final String SHOP_API_URL = "/api/shop/orders/*";


    @Bean
    public FilterRegistrationBean<GenericFilterBean> jwtFilter() {
        FilterRegistrationBean<GenericFilterBean> filter = new FilterRegistrationBean<>();
        filter.setFilter(new JwtValidationFilter());

        filter.addUrlPatterns(DELIVERY_API_URL,
                FLIPPY_API_URL,
                SHOP_API_URL);
        return filter;
    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}