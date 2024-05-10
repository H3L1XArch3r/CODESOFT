package com.example.registration.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationSuccessHandler successHandler;


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerUserDetailService();

    }

//	@Bean
//	public BCryptPasswordEncoder bcryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//
//	}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/saveStudent").permitAll()

                .antMatchers("/saveCourse").permitAll()
                .antMatchers("/getStudent").permitAll()
                .antMatchers("/student/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/studentHome")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll().and().csrf().disable();

    }

    }




