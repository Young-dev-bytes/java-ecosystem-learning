package com.in28minutes.microservices.currencyexchangeservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/ {

  /*@Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests().anyRequest().permitAll()
        .and()
        .httpBasic().disable()
        .csrf().disable();
  }*/


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().permitAll()
        );
        http.httpBasic().disable();
        http.csrf().disable();
        return http.build();
    }
}