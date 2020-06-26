package com.shopping.basket.config;

import com.shopping.basket.security.jwt.JwtSecurityConfigurer;
import com.shopping.basket.security.jwt.JwtTokenProvider;
import com.shopping.basket.service.CustomerAuthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private CustomerAuthDetailsService customerAuthDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final String[]	AUTH_WHITELIST	= { "api/v1/**" };
    private static final String[]	AUTH_PUBLIC	= { "api/public/**", "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**" };

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerAuthDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(AUTH_WHITELIST).authenticated()
                .antMatchers(AUTH_PUBLIC).permitAll().anyRequest().authenticated()
                .and().httpBasic()
                .and().formLogin().permitAll()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));

        http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
    }


}
