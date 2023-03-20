package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.accelerate.springwebpreswagger.mappers.UserMapper;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.LoginDTO;
import no.accelerate.springwebpreswagger.models.dto.user.RegistrationDTO;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
import no.accelerate.springwebpreswagger.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private final UserRepository userRepository;
    @Autowired
    public AuthenticationController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    @Operation(summary = "Log in a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            )
    })
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, HttpSession session) {

            Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

            User user = userOptional.get();
            String hashedPassword = Utility.hashPassword(loginDTO.getPassword(), user.getSalt());

            if (!hashedPassword.equals(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            } else {
                session.setAttribute("user", user);
            }

            return ResponseEntity.ok().body("User successfully logged in");
    }


    @GetMapping("logout")
    @Operation(summary = "Log out a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged out successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            )
    })
    public ResponseEntity<?> logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        // Return a successful logout response
        return ResponseEntity.ok().body("User logged out successfully!");
    }

    @PostMapping("register")
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
        if(userRepository.findAllByEmail(registrationDTO.getEmail()).size() >= 1){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Convert DTO to User entity
        //User newUser = convertDtoToUser(registrationDTO);

        // Convert DTO to User entity
        User newUser = UserMapper.INSTANCE.convertDtoToUser(registrationDTO);
        newUser.setAdmin(false);

        // Generate a salt for the new user
        String salt = Utility.generateSalt();

        // Encode the password with the salt
        newUser.setPassword(Utility.hashPassword(registrationDTO.getPassword(), salt));
        newUser.setSalt(salt);

        // Save the new user (error I have in the comment below).
        //Inferred type 'S' for type parameter 'S' is not within its bound; should implement 'org.apache.catalina.User'
        userRepository.save(newUser);

        // Return a successful registration response
        // ...
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully!");
    }

    @GetMapping("current-user")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }


        // I might want to return a DTO instead of the full User object, depending on the data I want to expose
        return ResponseEntity.ok(currentUser);
    }
}

