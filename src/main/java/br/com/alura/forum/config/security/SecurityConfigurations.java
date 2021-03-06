package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private AtenticacaoService autenticacaoService;
		
		@Override
		@Bean
		protected AuthenticationManager authenticationManager() throws Exception {
		    return super.authenticationManager();
		}
	
		//configurações de autenticaçõe
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService( autenticacaoService ).passwordEncoder(new BCryptPasswordEncoder());
		}
		
		//configurações de autorizações
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll()
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			//.and().formLogin();
			
		}
		
		
		//configurações de recursos estátios, javascript, css, imagens , etc...
		@Override
		public void configure(WebSecurity web) throws Exception {
		
		}
		
		/*
		 * public static void main(String[] args) {
		 * 
		 * System.out.println(new BCryptPasswordEncoder().encode("123456"));
		 * 
		 * }
		 */
}
