package com.example.demo.security.config;
import com.example.demo.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfigurer {

        @Autowired
        public CustomUserDetailsService userDetailsService;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
            return http.cors()
                    .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/register").permitAll()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .anyRequest().authenticated()
                    //.anyRequest().permitAll()
                    .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .exceptionHandling()
                    .and()
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod(HttpMethod.DELETE);
            config.addAllowedMethod(HttpMethod.GET);
            config.addAllowedMethod(HttpMethod.OPTIONS);
            config.addAllowedMethod(HttpMethod.HEAD);
            config.addAllowedMethod(HttpMethod.PUT);
            config.addAllowedMethod(HttpMethod.POST);
            config.addAllowedMethod(HttpMethod.PATCH);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);
            return source;
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

    }
