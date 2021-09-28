package com.hs.meetme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
//    private MemberService memberService;

    @Bean
//  패스워드 인코딩
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }

    @Override
//  security를 적용하지 않을 url 경로
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        	.antMatchers("/css/**", "/js/**", "/img/**", "/slick/**", "/font-awesome-4.7.0/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/s/member").access("hasRole('USER')")
                .antMatchers("/s/admin").access("hasRole('ADMIN')")
                .antMatchers("/s/all").permitAll();

        http.formLogin()
                .loginPage("/s/login")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/s/logout"))
                .logoutSuccessUrl("/s/login")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/s/denied");
        
        http.csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
    			.withUser("admin")
    			.password("{noop}admin")
    			.roles("ADMIN");
    }
    
    public AuthenticationSuccessHandler loginSuccessHandler() {
    	return new LoginSuccess();
    }
}
