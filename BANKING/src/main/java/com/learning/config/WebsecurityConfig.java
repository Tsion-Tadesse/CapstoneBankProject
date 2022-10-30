package com.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@SuppressWarnings("deprecation")

@Configuration
public class WebsecurityConfig  extends WebSecurityConfigurerAdapter{
	
	
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
				
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().antMatchers(HttpMethod.POST).hasAnyRole("STAFF","ADMIN")
		.antMatchers(HttpMethod.PUT).hasAnyRole("STAFF","ADMIN")
		.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/account").hasAnyRole("ADMIN","MANAGER","USER")
			
		.antMatchers(HttpMethod.GET,"/api/user").hasAnyRole("STAFF","ADMIN")
		.antMatchers(HttpMethod.GET,"/api/users/{id}").access("@userSecurity.hasUserId(authentication,#id)")
		;
		
		
	    
		
		
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		super.configure(http);
		
		
		
	}
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
	
	
	
	
	
	
	

}
