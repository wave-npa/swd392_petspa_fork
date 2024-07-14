package org.petspa.petcaresystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaRepositories(basePackages="org.petspa.petcaresystem")
public class SecurityConfig {

     @Autowired
     private JwtRequestFilter jwtRequestFilter;

//     @Bean
//     UserDetailsService userDetailsService(){
//         return new UserDetailsService() {
//             @Override
//             public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                 return null;
//             }
//         };
//     }

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
          httpSecurity
                  .authorizeHttpRequests(request ->
                  request
                          .requestMatchers(HttpMethod.GET,"/swagger-ui/**", "/v3/api-docs/**", "/actuator/**",
                                  "/petspa/user/login").permitAll()
                          .requestMatchers(HttpMethod.GET, "/petspa/user/getAllUser").hasAuthority("ROLE_ADMIN")
//                          .requestMatchers(HttpMethod.POST, "/petspa/user/register", "/petspa/appointment/save").permitAll()
//                          .requestMatchers(HttpMethod.PUT, "/**").permitAll()
//                          .requestMatchers(HttpMethod.GET,"petspa/shelter/viewAllShelter").permitAll()
                          .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                          .requestMatchers(HttpMethod.PATCH, "/**").permitAll()
                          .anyRequest().authenticated());

          httpSecurity.csrf(AbstractHttpConfigurer::disable);
          httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

         return httpSecurity.build();
     }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//     @Bean
//     public AuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//         authenticationProvider.setUserDetailsService(userDetailsService());
//         authenticationProvider.setPasswordEncoder(passwordEncoder());
//         return authenticationProvider;
//
//     }
//
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
}
