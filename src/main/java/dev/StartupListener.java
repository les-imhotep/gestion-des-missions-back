package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;
import dev.entities.LigneDeFrais;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.NoteDeFrais;
import dev.entities.RoleCollegue;
import dev.entities.Version;
import dev.entities.enumerations.Facturation;
import dev.entities.enumerations.NatureLigne;
import dev.entities.enumerations.Role;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;
import dev.repositories.CollegueRepo;
import dev.repositories.LigneDeFraisRepo;
import dev.repositories.MissionRepo;
import dev.repositories.NatureMissionRepo;
import dev.repositories.NoteDeFraisRepo;
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
	private NoteDeFraisRepo noteDeFraisRepo;
	private LigneDeFraisRepo ligneDeFraisRepo;
	private NatureMissionRepo natureMissionRepo;
	private MissionRepo missionRepo;
	private NoteDeFraisRepo noteDeFrais;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionRepo missionRepo,
			NatureMissionRepo natureMissionRepo, NoteDeFraisRepo noteDeFrais, LigneDeFraisRepo ligneDeFraisRepo) {

		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;

		this.natureMissionRepo = natureMissionRepo;
		this.missionRepo = missionRepo;
		this.ligneDeFraisRepo = ligneDeFraisRepo;
		this.noteDeFrais = noteDeFrais;

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

		Collegue col3 = new Collegue();
		col3.setNom("User");
		col3.setPrenom("DEV");
		col3.setEmail("manager@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_MANAGER)));
		this.collegueRepo.save(col3);

		// Nature1 nature de mission fictive
		NatureMission nature1 = new NatureMission();
		nature1.setName("Expertise de Djamel");
		nature1.setFacturation(Facturation.FACTUREE);
		nature1.setPourcentage(10.00);
		nature1.setPrime(true);
		nature1.setTjm(2.50);
		this.natureMissionRepo.save(nature1);

		NatureMission conseil = new NatureMission();
		conseil.setPourcentage(3.5);
		conseil.setPrime(true);
		conseil.setTjm(750);
		conseil.setFacturation(Facturation.FACTUREE);
		conseil.setName("Conseil");
		this.natureMissionRepo.save(conseil);

		NatureMission formation = new NatureMission();
		formation.setPrime(false);
		formation.setFacturation(Facturation.NON_FACTUREE);
		formation.setName("Formation");
		formation.setDateFin(LocalDate.of(2018, 9, 20));
		this.natureMissionRepo.save(formation);

		NatureMission formation1 = new NatureMission();
		formation1.setPrime(true);
		formation1.setFacturation(Facturation.NON_FACTUREE);
		formation1.setName("Jean");
		this.natureMissionRepo.save(formation1);

		NatureMission expertiseTechnique = new NatureMission();
		expertiseTechnique.setPourcentage(4);
		expertiseTechnique.setPrime(true);
		expertiseTechnique.setTjm(1000);
		expertiseTechnique.setFacturation(Facturation.FACTUREE);
		expertiseTechnique.setName("Expertise technique");
		this.natureMissionRepo.save(expertiseTechnique);

		// ajout de misssions
		Mission mission = new Mission();
		mission.setCollegue(col1);
		mission.setDateDebut(LocalDate.now());
		mission.setDateFin(LocalDate.of(2019, 01, 21));
		mission.setNatureMission(conseil);
		mission.setNatureMission(expertiseTechnique);
		mission.setPrime(0);
		mission.setTransport(Transport.AVION);
		mission.setStatut(Statut.INITIALE);
		mission.setVilleArrivee("Paris");
		mission.setVilleDepart("Nantes");
		missionRepo.save(mission);

		Mission mission1 = new Mission();
		mission1.setCollegue(col1);
		mission1.setDateDebut(LocalDate.now());
		mission1.setDateFin(LocalDate.of(2019, 01, 21));
		mission1.setNatureMission(expertiseTechnique);
		mission1.setTransport(Transport.COVOITURAGE);
		mission1.setStatut(Statut.INITIALE);
		mission1.setVilleArrivee("Dakar");
		mission1.setVilleDepart("Marseille");
		missionRepo.save(mission1);

		Mission mission2 = new Mission();
		mission2.setCollegue(col2);
		mission2.setDateDebut(LocalDate.now());
		mission2.setDateFin(LocalDate.of(2020, 01, 21));
		mission2.setNatureMission(formation1);
		mission2.setTransport(Transport.VOITURE_SERVICE);
		mission2.setStatut(Statut.EN_ATTENTE_VALIDATION);
		mission2.setVilleArrivee("Oui");
		mission2.setVilleDepart("Perle");
		missionRepo.save(mission2);

		// Création de lignes de frais
		LigneDeFrais ligneDeFrais = new LigneDeFrais();
		ligneDeFrais.setDate(LocalDate.of(2018, 8, 17));
		ligneDeFrais.setNatureLigne(NatureLigne.HOTEL);
		ligneDeFrais.setFrais(150);
		this.ligneDeFraisRepo.save(ligneDeFrais);

		LigneDeFrais ligneDeFrais1 = new LigneDeFrais();
		ligneDeFrais1.setDate(LocalDate.of(2017, 10, 17));
		ligneDeFrais1.setNatureLigne(NatureLigne.RESTAURANT);
		ligneDeFrais1.setFrais(1500);
		this.ligneDeFraisRepo.save(ligneDeFrais1);

		LigneDeFrais ligneDeFrais2 = new LigneDeFrais();
		ligneDeFrais2.setDate(LocalDate.of(2015, 1, 17));
		ligneDeFrais2.setNatureLigne(NatureLigne.TAXI);
		ligneDeFrais2.setFrais(300);
		this.ligneDeFraisRepo.save(ligneDeFrais2);

		// notes de frais
		NoteDeFrais notedefrais = new NoteDeFrais();
		notedefrais.getLignesDeFrais().add(ligneDeFrais);
		notedefrais.setMission(mission2);
		noteDeFrais.save(notedefrais);

		NoteDeFrais notedefrais1 = new NoteDeFrais();
		notedefrais1.getLignesDeFrais().add(ligneDeFrais1);
		notedefrais1.getLignesDeFrais().add(ligneDeFrais2);
		notedefrais1.setMission(mission1);
		noteDeFrais.save(notedefrais1);

	}

}
