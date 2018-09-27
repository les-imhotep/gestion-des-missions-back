package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.NoteDeFrais;

public interface NoteDeFraisRepo extends JpaRepository<NoteDeFrais, Long> {

}
