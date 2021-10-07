package com.hs.meetme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hs.meetme.useraccess.service.AccountServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountServiceImpl accountService;
	
	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
    @Override
//  security를 적용하지 않을 url 경로
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/slick/**", "/font-awesome-4.7.0/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//        		.antMatchers("/createCourse").authenticated()
                .antMatchers("/**").permitAll()
                ;

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(new LoginSuccess())
                ;
        
        http.logout()
        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
                ;

//        재호가 함
        http.csrf()
        	.ignoringAntMatchers("/post/ckeditor/fileUpload")
        	.ignoringAntMatchers("/api/course/register")
//        http.csrf().disable()
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(accountService)
    			.passwordEncoder(new BCryptPasswordEncoder())
    			;
    }

}
