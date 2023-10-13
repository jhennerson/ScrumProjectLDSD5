package com.ifsp.scrumProjectLDSD5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.util.AntPathMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {


	@Autowired
	SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize ->
					authorize
						.requestMatchers(HttpMethod.POST,"auth/**").permitAll()
							.requestMatchers("h2-console").permitAll()
							.requestMatchers("h2-console/**").permitAll()
						.requestMatchers("api/**").authenticated()
						.anyRequest().authenticated()


				)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();


	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration ac) throws Exception {
		return ac.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();

	}
}
