package dk.surfstation.easyedit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	// Inspiration: http://disq.us/p/13yhboy
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO: WTF... Learn and fix this!
		http
				.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/posts/filter").permitAll()
				.antMatchers(HttpMethod.GET, "/api/posts/latest").permitAll()
				.antMatchers(HttpMethod.GET, "/api/posts/latest/title").permitAll()
				.antMatchers(HttpMethod.GET, "/api/posts/latest/content").permitAll()
				.antMatchers(HttpMethod.GET, "/api/feed").permitAll()
				.antMatchers(HttpMethod.GET, "/api/edit/*").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/edit/*").permitAll()
				.antMatchers(HttpMethod.GET, "/app/**").permitAll()
				.anyRequest()
				.authenticated();
	}
}
