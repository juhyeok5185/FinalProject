package hotel.management.v1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
	private LoginFailHandler loginFailHandler;
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().loginPage("/hotel/member/login")
				.loginProcessingUrl("/hotel/member/login")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailHandler);
		http.logout().logoutUrl("/hotel/member/logout").logoutSuccessUrl("/");
		return http.build();
	}
}
