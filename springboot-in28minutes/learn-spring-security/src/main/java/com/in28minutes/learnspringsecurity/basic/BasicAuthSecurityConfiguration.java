package com.in28minutes.learnspringsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration
public class BasicAuthSecurityConfiguration {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> {
                            auth.anyRequest().authenticated();
                        });
        // http.formLogin(withDefaults());
        http.sessionManagement(
                        session -> {
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        });
        http.httpBasic(withDefaults());
        http.csrf(csrf -> csrf.disable());
        // http.headers().frameOptions().sameOrigin();
        http.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
        return http.build();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        UserDetails user = User.withUsername("in28minutes")
                // .password("{noop}dummy")
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                // .password("{noop}dummy")
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("test","young","admin")
                .build();
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);

        return userDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("in28minutes")
                .password("{noop}dummy")
                .roles("USER")
                .passwordEncoder(String::strip)
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}dummy")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/
}
