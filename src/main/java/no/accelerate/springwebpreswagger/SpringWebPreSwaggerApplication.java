package no.accelerate.springwebpreswagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import no.accelerate.springwebpreswagger.config.AdminUserInitializer;
import no.accelerate.springwebpreswagger.models.dto.user.AdminDTO;
import no.accelerate.springwebpreswagger.models.dto.user.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebPreSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebPreSwaggerApplication.class, args);
    }
}





