package com.project.restAPI.JobModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig2 {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/myApplications").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/api/myApplications/{id}").hasRole("COMPANY")
                        .requestMatchers(HttpMethod.GET, "/api/myApplications").hasRole("COMPANY")
                        .requestMatchers(HttpMethod.GET, "/api/myApplications/{id}").hasAnyRole("APPLICANT", "COMPANY")
                        .anyRequest().authenticated()
                ))

                        .httpBasic(httpBasic -> {})

                        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        //creating new in-memory user

        UserDetails applicant = User.builder()
                .username("applicant")
                //password needs to be encoded
                .password(passwordEncoder.encode("apppass"))
                .roles("APPLICANT")
                .build();

        UserDetails company = User.builder()
                .username("company")
                //password needs to be encoded
                .password(passwordEncoder.encode("comppass"))
                .roles("COMPANY")
                .build();

        return new InMemoryUserDetailsManager(applicant, company);
    }




}

