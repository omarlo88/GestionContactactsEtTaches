package omar.lo.myConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//On désactive le système d'authentification basé sur les sessions
		//http.formLogin();
		http.authorizeRequests().antMatchers("/login/**", "/AccountRestController/register/**").permitAll();
		
		http.authorizeRequests().antMatchers(
				HttpMethod.POST, "/TachesRestController/taches/**", "/AccountRestController/roles/**")
		.hasAuthority("ADMIN");
		
		http.authorizeRequests().antMatchers(
				HttpMethod.GET, "/AccountRestController/users/**", "/AccountRestController/roles/**")
		.hasAuthority("ADMIN");
		
		http.authorizeRequests().antMatchers(
				HttpMethod.PUT, "/AccountRestController/users/**", "/TachesRestController/taches/**",
				"/AccountRestController/roles/**", "/AccountRestController/addRoleToUser/**")
		.hasAuthority("ADMIN");
		
		http.authorizeRequests().antMatchers(
				HttpMethod.DELETE, "/AccountRestController/users/**", "/TachesRestController/taches/**",
				"/AccountRestController/roles/**", "/AccountRestController/removeRoleToUser/**")
		.hasAuthority("ADMIN");
		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), objectMapper))
		.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}// SecurityConfig
