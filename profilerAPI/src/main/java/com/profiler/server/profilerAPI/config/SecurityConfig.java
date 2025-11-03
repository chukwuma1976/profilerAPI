package com.profiler.server.profilerAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/index.html", "/favicon.ico", "/error").permitAll()
                    .requestMatchers("/*.js", "/*.css", "/assets/**").permitAll()
                    .requestMatchers("/{path:[^\\.]*}", "/**/{path:[^\\.]*}").permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(HttpMethod.HEAD, "/**").permitAll()
            	    .requestMatchers("/api/v1/profiler/**").permitAll()
            	    .anyRequest().authenticated()
            	);
            // .sessionManagement(session -> session.maximumSessions(1));
        return http.build();
    }

}
