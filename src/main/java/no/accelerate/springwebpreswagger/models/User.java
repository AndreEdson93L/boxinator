package no.accelerate.springwebpreswagger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users") // Use a different table name: "users" because "user" is a reserve keyword in postgres.
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Email
    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 32)
    private String salt;
    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 20)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 20)
    private String countryOfResidence;
    @Column(nullable = false, length = 20)
    private String postalCode;

    @Column(nullable = false, length = 20)
    private String contactNumber;
}
