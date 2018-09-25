package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.NatureMission;

public interface NatureMissionRepo extends JpaRepository<NatureMission, Long> {

}
