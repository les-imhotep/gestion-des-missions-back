package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;
import dev.entities.Mission;
import dev.entities.RoleCollegue;
import dev.entities.Version;
import dev.entities.enumerations.Role;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;
import dev.repositories.CollegueRepo;
import dev.repositories.MissionRepo;
import dev.repositories.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private MissionRepo missionRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionRepo missionRepo) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.missionRepo = missionRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		// ajout de misssions
		Mission mission = new Mission();
		mission.setCollegue(col1);
		mission.setDateDebut(LocalDate.now());
		mission.setDateFin(LocalDate.of(2019, 01, 21));
		mission.setNatureMission(null);
		mission.setPrime(5000);
		mission.setTransport(Transport.AVION);
		mission.setStatut(Statut.INITIALE);
		mission.setVilleArrivee("Paris");
		mission.setVilleDepart("Nantes");
		missionRepo.save(mission);
	}

}
