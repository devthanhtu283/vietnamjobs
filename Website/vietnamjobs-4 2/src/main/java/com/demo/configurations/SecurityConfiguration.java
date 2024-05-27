package com.demo.configurations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.demo.entities.Account;
import com.demo.services.AccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	@Autowired
	private AccountService accountService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.cors(c -> c.disable()).csrf(c -> c.disable()).authorizeHttpRequests(a -> {
			a.requestMatchers(
					"/",
					"/files/**",
					"/index",
					"/home/**",
					"/assets/**",
					"/api/**",
					"/account/login",
					"/account/register",
					"/account/forgotpassword",
					"/account/accessdenied",
					"/checkmail/**",
					"/ajax/**",
					"/ajaxUser/**"
					)
					.permitAll()
					.requestMatchers("/admin/**").hasAnyRole("ADMIN")
					.requestMatchers("/employer/**").hasAnyRole("EMPLOYER")
					.requestMatchers("/seeker/**").hasAnyRole("SEEKER");
		}).formLogin(f -> {
			f.loginPage("/account/login")
			.loginProcessingUrl("/account/process-login")
			.usernameParameter("username")
			.passwordParameter("password")
			//.defaultSuccessUrl("/account/welcome")
			.failureUrl(("/account/login?error"))
			.successHandler(new AuthenticationSuccessHandler() {
				
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					Map<String, String> redirectUrls = new HashMap<String, String>();
					redirectUrls.put("ROLE_ADMIN", "/admin/dashboard");
					redirectUrls.put("ROLE_EMPLOYER", "/employer/dashboard");
					redirectUrls.put("ROLE_SEEKER", "/home/index");
					String url="";
					for(GrantedAuthority role : authentication.getAuthorities()) {
						if(redirectUrls.keySet().contains(role.getAuthority())) {
							url = redirectUrls.get(role.getAuthority());
							System.out.print(url);
							break;
						}
					}
					response.sendRedirect(url);
				}
			});
		}).logout(log -> {
			log.logoutUrl("/account/logout").logoutSuccessUrl("/account/login");
		}).exceptionHandling(ex->{
			ex.accessDeniedPage("/account/accessdenied");	
		})
				.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(accountService);
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
