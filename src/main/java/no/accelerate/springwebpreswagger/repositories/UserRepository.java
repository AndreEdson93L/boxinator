package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email like %?1%")
    Set<User> findByEmail(String email);
}
