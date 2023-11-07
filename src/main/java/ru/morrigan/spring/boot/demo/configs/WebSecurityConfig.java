package ru.morrigan.spring.boot.demo.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Config config;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        // пробуем
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    // пробуем
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(config.passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/").permitAll()
            .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
            .antMatchers("/user/**").access("hasAnyAuthority('ADMIN', 'USER')")
            .anyRequest().authenticated()
            .and().formLogin()
                .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("j_email")
            .passwordParameter("j_password")
            .permitAll()
            .successHandler(successUserHandler);

        http.logout()
            .permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .and().csrf().disable();
    }
}