package com.rotnicki.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.rotnicki.controller.UserController;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	public void configure(HttpSecurity security) throws Exception{
		
		security.authorizeRequests()
		.antMatchers("/CreateAccount").permitAll()
		.antMatchers("/add").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		//.loginPage("/")
		.defaultSuccessUrl("/LangChoice").permitAll()
        .and()
        .logout()
        .permitAll();
	}
	
	@Autowired
	public void securityUsers(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT login, pass, enabled FROM USER WHERE login=?")
		.authoritiesByUsernameQuery("SELECT login, pass FROM USER WHERE login=?");
	}
	
}
