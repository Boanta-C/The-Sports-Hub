package com.itschool.lastproject.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    .mvcMatchers(HttpMethod.GET, "sports/added?sportId=?"); - MVC if we want to use this syntax or {cityId}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        .antMatchers("/cities/add", "neighborhoods/add","/cities/update", "neighborhoods/update")
                        .hasAnyAuthority("OWNER", "MODERATOR")

                        .antMatchers("/sportFacilities/add","/sports/add", "/sportFacilities/update","/sports/update")
                        .hasAnyAuthority("ADMIN", "MODERATOR", "OWNER")

                        .antMatchers("/**", "/register/**", "/activation/**,","/login/**").permitAll())

                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .and()
                .httpBasic();

    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    }
}
