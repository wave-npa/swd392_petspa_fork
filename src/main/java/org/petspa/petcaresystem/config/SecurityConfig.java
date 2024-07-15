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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableJpaRepositories(basePackages="org.petspa.petcaresystem")
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request ->
                        request
                                //------------------------ get method---------------------------

                                // GUEST
//                                .requestMatchers(HttpMethod.GET,
//                                        "/swagger-ui/**",
//                                        "/v3/api-docs/**",
//                                        "/actuator/**",
//                                        "/petspa/user/logout",
//                                        "/petspa/user/login",
//                                        "/currentUser/**",
//                                        "/petspa/appointment/getById/{appointmentId}",
//                                        "/petspa/user/vertify")
//                                .permitAll()
//
//                                // ADMIN
//                                .requestMatchers(HttpMethod.GET,
//                                        "/petspa/user/getAllUser",
//                                        "/petspa/user/getUserById/{userId}",
//                                        "/petspa/user/findUserByAge",
//                                        "/petspa/user/searchUserTest",
//                                        "/petspa/appointment/getById/{appointmentId}",
//                                        "/petspa/appointment/getAll")
//                                .hasAuthority("ROLE_ADMIN")
//
//                                // STAFF
//                                .requestMatchers(HttpMethod.GET,
//                                        "/petspa/user/getUserById/{userId}").hasAuthority("ROLE_STAFF")
//
//                                // CUSTOMER
//                                .requestMatchers(HttpMethod.GET,
//                                        "/petspa/appointment/getByUserId").hasAuthority("ROLE_CUSTOMER")
//
//                                //---------------------------------------------------------------
//
//
//                                // ------------------------ post method---------------------------
//                                .requestMatchers(HttpMethod.POST,
//
//                                        "/petspa/user/register",
//                                        "/petspa/appointment/save",
//                                        "/petspa/user/login")
//                                .permitAll()
//
//                                .requestMatchers(HttpMethod.POST,
//
//                                        "/petspa/user/save")
//                                .hasAnyAuthority("ROLE_ADMIN", "ROLE_STAFF")
//                                //---------------------------------------------------------------
//
//
//                                // ------------------------ put method---------------------------
//
//                                // GUEST
//                                .requestMatchers(HttpMethod.PUT,
//
//                                        "/petspa/appointment/save")
//                                .permitAll()
//
//                                // STAFF
//                                .requestMatchers(HttpMethod.PUT,
//                                        "/petspa/appointment/updateStatus",
//                                        "/petspa/appointment/update")
//                                .hasAuthority("ROLE_STAFF")
//
//                                //---------------------------------------------------------------
                                .requestMatchers(HttpMethod.GET,"/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .anyRequest().authenticated());

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // front end port
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
