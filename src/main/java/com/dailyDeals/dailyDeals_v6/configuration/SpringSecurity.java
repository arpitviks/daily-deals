package com.dailyDeals.dailyDeals_v6.configuration;

import com.dailyDeals.dailyDeals_v6.filter.JwtFilter;
import com.dailyDeals.dailyDeals_v6.services.CustomUserDetailService;
import com.dailyDeals.dailyDeals_v6.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Products
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.PATCH,"/products/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/products/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.DELETE,"/products/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/products/**").hasAnyRole("Admin","Customer"));
        //Deals
        http
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(HttpMethod.PATCH,"/deals/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/deals/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.DELETE,"/deals/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.GET,"/deals/**").hasAnyRole("Admin","Customer"));
        //Orders
        http
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(HttpMethod.PATCH,"/orders/**").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/orders/**").hasAnyRole("Customer")
                        .requestMatchers(HttpMethod.DELETE,"/orders/**").hasAnyRole("Customer","Admin")
                        .requestMatchers(HttpMethod.GET,"/orders/**").hasAnyRole("Admin","Customer"));
        //Users
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").hasAnyRole("Admin"));

        //Public
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                );
        //http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(csrf -> {
            csrf.disable();
        }).cors(cors -> cors.disable());
        http.authenticationProvider(daoAuthenticationProvider());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception{
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager userDetailsService(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
