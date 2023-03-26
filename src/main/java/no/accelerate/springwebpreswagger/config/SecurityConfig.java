package no.accelerate.springwebpreswagger.config;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
*/
//@EnableWebSecurity
//@Configuration
public class SecurityConfig {
    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().sessionManagement().disable()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .authenticated()
                ).oauth2ResourceServer()
                .jwt();
        return http.build();
    }*/
}

//public / public
//authenticated / authenticated (user)
//authorized / authorized (admin)

//spring.security.oauth2.resourceserver.jwt.issuer-uri=""
//spring.security.oauth2.resourceserver.jwt.set-uri=""

//implementation 'org.springframework.boot:spring-boot-starter-security'
//implementation 'org.springframework.security.oauth:spring-security-oauth2'