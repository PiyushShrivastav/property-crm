package com.propertycrm.app.config;

import com.propertycrm.app.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                	    .requestMatchers(
                	            "/api/auth/**"
                	    ).permitAll()

                	    // ADMIN + DIRECTOR
                	    .requestMatchers(
                	            "/api/dashboard/**"
                	    ).hasAnyRole(
                	            "ADMIN",
                	            "DIRECTOR"
                	    )

                	    // ADMIN ONLY
                	    .requestMatchers(
                	            "/api/employees/**",
                	            "/api/projects/**",
                	            "/api/units/**"
                	    ).hasRole("ADMIN")

                	    // SALES + MANAGER + ADMIN
                	    .requestMatchers(
                	            "/api/bookings/**",
                	            "/api/unit-holds/**"
                	    ).hasAnyRole(
                	            "ADMIN",
                	            "MANAGER",
                	            "SALES"
                	    )

                	    // LEADS
                	    .requestMatchers(
                	            "/api/leads/**"
                	    ).hasAnyRole(
                	            "ADMIN",
                	            "MANAGER",
                	            "SALES",
                	            "TELECALLER",
                	            "CRM_EXECUTIVE"
                	    )

                	    // ACCOUNTS
                	    .requestMatchers(
                	            "/api/payments/**",
                	            "/api/receipts/**"
                	    ).hasAnyRole(
                	            "ADMIN",
                	            "ACCOUNTANT"
                	    )

                	    // CRM
                	    .requestMatchers(
                	            "/api/customers/**"
                	    ).hasAnyRole(
                	            "ADMIN",
                	            "CRM_EXECUTIVE",
                	            "MANAGER"
                	    )

                	    .anyRequest()
                	    .authenticated()
                	)
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}