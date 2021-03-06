package io.pivotal.workshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
	    protected void configureUser(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .inMemoryAuthentication()
	                .passwordEncoder(NoOpPasswordEncoder.getInstance())
	                .withUser("billy").password("bob").roles("USER")
	                .and()
	                .withUser("admin").password("password").roles("ADMIN");
	     
	    }

	    // We do not want the default behavior of form authentication
	    // before HTTP Basic authentication we get
	    // from WebSecurityConfigurerAdapter.
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .anyRequest().fullyAuthenticated()
	                .and()
	                .httpBasic();
	    }
}
