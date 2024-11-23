package com.hms.anikdv.code.configuration;


import com.hms.anikdv.code.security.CustomUserDetailsService;
import com.hms.anikdv.code.security.JwtAuthenticationEntryPoint;
import com.hms.anikdv.code.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;


/**
 * This is Security Configuration
 *
 * @author anikdv
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private static final String[] PUBLIC_URL = {
            "/api/v1/auth/doctor/login",
            "/api/v1/auth/patient/signin",
            "/api/v1/auth/patient/login",
            "/api/v1/auth/admin/login",
            "/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/api/v1/patient/",
            "/api/v1/patient/{patientId}",
            "/api/v1/doctor/",
            "/api/v1/doctor/{doctorId}"
    };


    /**
     * @param security
     * @throws Exception
     * @return security
     */
    @Bean
    @Order(1)
    public SecurityFilterChain configuration(HttpSecurity security) throws Exception {
        security.cors(Customizer.withDefaults());
        security.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(PUBLIC_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/doctor/**").hasRole("DOCTOR")
                                .requestMatchers("/api/v1/patient/**").hasRole("PATIENT")
                                .anyRequest()
                                .authenticated());
        security.exceptionHandling(e -> e.authenticationEntryPoint(this.point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        //corsConfiguration.setAllowedHeaders(List.of("Content-Type", "Accept"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


    /**
     * @param builder
     * @throws Exception
     * @return authenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(this.passwordEncoder());
        return new ProviderManager(provider);
    }

    /**
     * @return BCryptPasswordEncoder;
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
