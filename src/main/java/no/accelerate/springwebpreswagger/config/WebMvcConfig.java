package no.accelerate.springwebpreswagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
                .allowedOrigins("http://localhost:4200/") // Allow any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*") // Allow all headers
                .exposedHeaders("*") // Expose all headers
                .allowCredentials(true).maxAge(3600);
    }
}
