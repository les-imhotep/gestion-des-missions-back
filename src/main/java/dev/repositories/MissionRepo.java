package dev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Mission;
import dev.entities.enumerations.Statut;

public interface MissionRepo extends JpaRepository<Mission, Long> {
	List<Mission> findAllByCollegueEmail(String username);

	List<Mission> findAllByStatut(Statut statut);

	boolean existsById(Long id);
}
