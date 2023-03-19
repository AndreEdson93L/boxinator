package no.accelerate.springwebpreswagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthenticationInterceptor())
                .addPathPatterns("/api/v1/user/**") // Adjust the path pattern based on user endpoints
                .excludePathPatterns("/api/v1/auth/**");

        registry.addInterceptor(new AdminAuthenticationInterceptor())
                .addPathPatterns("/api/v1/admin/**"); // Adjust the path pattern based on admin endpoints
    }
}
