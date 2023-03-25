package no.accelerate.springwebpreswagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//implementation 'org.springframework.boot:spring-boot-starter-security'
//implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
//implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthenticationInterceptor())
                .addPathPatterns("/api/v1/user/shipments/**")
                .addPathPatterns("api/v1/settings/countries/**");

        registry.addInterceptor(new AdminAuthenticationInterceptor())
                .addPathPatterns("/api/v1/admin/shipments/**")
                .addPathPatterns("api/v1/settings/countries/**");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("Content-Type", "Accept", "Authorization", "Access-Control-Request-Method")
                .exposedHeaders("Content-Type", "Accept", "Authorization")
                .allowCredentials(true).maxAge(3600);
    }

}
