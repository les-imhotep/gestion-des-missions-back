package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Collegue;

import java.util.Optional;

public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
}
