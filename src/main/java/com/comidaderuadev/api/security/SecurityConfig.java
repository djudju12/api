package com.comidaderuadev.api.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // Aqui define os endpoints baseados em roles
        // http.authorizeHttpRequests(configurer ->
        //     configurer
        //         .requestMatchers(HttpMethod.GET, "/api/produtos").hasRole("EMPLOYEE")
        // );

        // Tipo da autenticacao
        http.httpBasic(Customizer.withDefaults());

        // As boas linguas dizem que nao precisa de csrf para rest apis
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
