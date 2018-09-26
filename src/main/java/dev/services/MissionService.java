package dev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.entities.Mission;
import dev.repositories.MissionRepo;

@Service
public class MissionService {
	private MissionRepo missionRepo;

	public MissionService(MissionRepo missionRepo) {
		super();
		this.missionRepo = missionRepo;
	}

	public List<Mission> listerMission() {
		return this.missionRepo.findAll();

	}

	public List<Mission> findAllMission(String username) {

		return this.missionRepo.findAllByCollegueEmail(username);

	}

	public void newMission(Mission mission) {
		this.missionRepo.save(mission);
	}

}
