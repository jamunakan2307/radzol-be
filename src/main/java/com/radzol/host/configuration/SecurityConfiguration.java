/**
 * 
 */
package com.radzol.host.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.radzol.host.security.Permission;
import com.radzol.host.security.WebUserAuthenticationProvider;

/**
 * @author pradeepan
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${application.build}")
	private String buildProfile;

	private List<String> corsAllowedOrigins;
	
	@Autowired
	private WebUserAuthenticationProvider webUserAuthenticationProvider;

	@Autowired
	public void setCorsAllowedOrigins(@Value("${cors.allowed-origins.whitelist}") final String str) {
		corsAllowedOrigins = Arrays.asList(str.split(","));
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// nothing in here
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable(); // play around with this

		http.authorizeRequests()
			.mvcMatchers(HttpMethod.OPTIONS, "/*/**")
				.permitAll()
			.mvcMatchers("/v1/authentication/**")
				.permitAll()
			.mvcMatchers("/v1/companies")
				.hasAuthority(Permission.SYSTEM_COMPANY.name())
			.anyRequest().authenticated()
		.and()
			.logout()
				.deleteCookies("JSESSIONID")
					.permitAll()
			.and()
				.sessionManagement()
					.maximumSessions(1);
		
		//@formatter:on
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(webUserAuthenticationProvider);
	}
}
