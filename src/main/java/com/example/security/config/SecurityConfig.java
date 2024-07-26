package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.service.UserDetailServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity // Habilitar segurdad web
@EnableMethodSecurity // Habilitar seguridad a nivel de método
public class SecurityConfig {

  // @Bean
  // SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws
  // Exception {

  // return httpSecurity
  // .csrf(csrf -> csrf.disable())
  // .httpBasic(Customizer.withDefaults())
  // .sessionManagement(session ->
  // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  // .authorizeHttpRequests(http -> {
  // // configurar endpoinst publicos
  // http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();

  // // configurar endpoints seguros
  // http.requestMatchers(HttpMethod.GET,
  // "/auth/hello-secured").hasAuthority("CREATE");

  // // configurar resto de endpoinrs no especificados
  // // denyAll() -> denegar todo
  // // authenticated() -> solo usuarios autenticados
  // http.anyRequest().denyAll();
  // })
  // .build();

  // }

  // para metodos
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();

  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailService);
    return provider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public static void main(String[] args) {
    System.out.println(new BCryptPasswordEncoder().encode("1234"));
  }

}
