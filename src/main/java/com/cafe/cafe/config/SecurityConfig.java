package com.cafe.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http
        //         .authorizeRequests().antMatchers("/*").permitAll()
        //         .and().formLogin().
        //         loginPage("/appLogin.jsf").
        //         loginProcessingUrl("/login").
        //         usernameParameter("username").
        //         passwordParameter("password").
        //         defaultSuccessUrl("/menu.jsf").
        //         and().logout().
        //         logoutUrl("/logout")
        //         .and().csrf().disable();

        http.csrf().disable();

    }




}
