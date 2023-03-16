package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.RegistrationDTO;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    // Autowired UserRepository
    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // POST /login endpoint

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request: Email already in use or invalid data",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            )
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDTO registrationDTO) {
        // Check if the email is already in use
        // ...
        if(userRepository.findByEmail(registrationDTO.getEmail()).size() >= 1){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Convert DTO to User entity
        User newUser = convertDtoToUser(registrationDTO);

        // Encode the password
        newUser.setPassword(newUser.getPassword());

        // Save the new user (error I have in the comment below).
        //Inferred type 'S' for type parameter 'S' is not within its bound; should implement 'org.apache.catalina.User'
        userRepository.save(newUser);

        // Return a successful registration response
        // ...
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully!");
    }

    private User convertDtoToUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());
        user.setDateOfBirth(registrationDTO.getDateOfBirth());
        user.setCountryOfResidence(registrationDTO.getCountryOfResidence());
        user.setPostalCode(registrationDTO.getPostalCode());
        user.setContactNumber(registrationDTO.getContactNumber());

        return user;
    }
}
