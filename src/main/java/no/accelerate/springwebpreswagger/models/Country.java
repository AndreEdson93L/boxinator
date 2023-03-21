package no.accelerate.springwebpreswagger.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {
    @Id
    @Column(nullable = false, unique = true)
    private int id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private double multiplier;
}
