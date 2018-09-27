package dev.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.NatureMission;

public interface NatureMissionRepo extends JpaRepository<NatureMission, Long> {
	boolean existsByName(String name);

	public Optional<NatureMission> findByDateFin(LocalDate date);

	public Optional<NatureMission> findByName(String name);

}
