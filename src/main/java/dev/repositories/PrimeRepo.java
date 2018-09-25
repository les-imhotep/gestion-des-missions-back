package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Prime;

public interface PrimeRepo extends JpaRepository<Prime, Long> {

}
