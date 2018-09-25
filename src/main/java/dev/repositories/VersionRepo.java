package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Version;

public interface VersionRepo extends JpaRepository<Version, Integer> {
}
