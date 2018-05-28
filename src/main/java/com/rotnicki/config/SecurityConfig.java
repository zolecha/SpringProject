package com.rotnicki.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	public void configure(HttpSecurity security) throws Exception{
		
		security.authorizeRequests()
		.antMatchers("/CreateAccount", "/add", "/login", "/m.jpg", "/loginStyle.css", "/CreateAccountStyle.css").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").failureUrl("/login?error")
		.usernameParameter("login")
		.passwordParameter("pass")
		.and().logout().permitAll();

	}
	
	@Autowired
	public void securityUsers(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.usersByUsernameQuery("SELECT login, pass, enabled FROM user WHERE login=?")
		.authoritiesByUsernameQuery("SELECT login, pass, enabled FROM user WHERE login=?")
		.dataSource(dataSource);
	}

	
	@Override
	 public void configure(WebSecurity web) throws Exception {
	 web
	    .ignoring()
	    .antMatchers("/resources/static/**", "/resources/templates/**");
	 }
	
}
