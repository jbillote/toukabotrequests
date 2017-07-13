package com.jbillote.toukabotrequests.config;

import com.jbillote.toukabotrequests.security.UserDetailsService;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers( "/index.html", "/images", "/api/*", "/**/public/bundle.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Require login to manage requests, including approving and declining
                .authorizeRequests()
                    .antMatchers("/manageRequests", "/api/requests/approve", "/api/requests/decline")
                    .authenticated()
                .and()
                // Use custom login page
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/manageRequests")
                        .failureUrl("/login?error=true")
                        .permitAll()
                .and()
                // TODO: Implement CSRF
                    .csrf()
                        .disable()
                // Keep most of site not requiring authentication
                .authorizeRequests()
                    .antMatchers("/index.html", "/images", "/api/*", "/**/public/bundle.js")
                    .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Use custom DynamoDB authentication backend
        auth
                .userDetailsService(new UserDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
