//package com.robert.ddd.setup;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.headers().frameOptions().disable().and()
////                .authorizeRequests()
////                //.antMatchers("/", "/").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                //.loginPage("/login")
////                .permitAll()
////                .and()
////                .csrf().disable()
////                .logout()
////                .permitAll();
////    }
////
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
////        UserDetails user = User.withUsername("user")
////                        .password("password")
////                        .roles("USER")
////                        .build();
////
////        return new InMemoryUserDetailsManager(Collections.singleton(user));
////    }
//
//
//}
