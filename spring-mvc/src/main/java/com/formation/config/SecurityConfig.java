package com.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity

                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/webjars/**",
                                                                "/resources/**")
                                                .permitAll()
                                                .requestMatchers("/login").permitAll()
                                                .requestMatchers("/api/**").authenticated()
                                                .requestMatchers("/books/list/**").authenticated()
                                                .requestMatchers("/books/add/**").hasRole("ADMIN")
                                                .anyRequest().permitAll())

                                .formLogin(form -> form
                                                .loginPage("/login").permitAll()
                                                .loginProcessingUrl("/login")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login?error"))
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout"))
                                .httpBasic(Customizer.withDefaults())
                                .sessionManagement(sm -> sm.sessionCreationPolicy(
                                                SessionCreationPolicy.IF_REQUIRED))
                                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

                return httpSecurity.build();
        }
}
