package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Absence;

public interface AbsenceRepo extends JpaRepository<Absence, Long> {

}
