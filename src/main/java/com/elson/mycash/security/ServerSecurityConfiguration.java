package com.elson.mycash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class ServerSecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager()
    throws Exception{
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http)
            throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .authorizeRequests()
                .antMatchers("/mycash/api/lancamento/**")
                    .hasAnyAuthority("ADMIN","USER")
                //   .hasAnyRole("ADMIN","USER")
                //.antMatchers("/mycash/api/usuario/**")
                //    .hasRole("ADMIN")
                .anyRequest().authenticated()
        .and()
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint);

        ;


        //super.configure(http);
    }
}
