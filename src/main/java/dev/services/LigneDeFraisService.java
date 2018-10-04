package dev.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.entities.LigneDeFrais;
import dev.entities.NoteDeFrais;
import dev.entities.enumerations.NatureLigne;
import dev.exceptions.AllreadyExistsLigneException;
import dev.exceptions.InvalidDateLigneException;
import dev.exceptions.InvalidFraisException;
import dev.exceptions.NoLigneDeFraisFoundException;
import dev.repositories.LigneDeFraisRepo;
import dev.repositories.NoteDeFraisRepo;

/**
 * 
 * @author Diginamic-02
 *
 */
@Service
public class LigneDeFraisService {

	private LigneDeFraisRepo ligneDeFraisRepo;
	private NoteDeFraisRepo noteDeFraisRepo;

	public LigneDeFraisService(LigneDeFraisRepo ligneDeFraisRepo, NoteDeFraisRepo noteDeFraisRepo) {
		super();
		this.ligneDeFraisRepo = ligneDeFraisRepo;
		this.noteDeFraisRepo = noteDeFraisRepo;
	}

	/**
	 * lister toutes les lignes de frais
	 * 
	 * @return liste de LigneDeFrais
	 */
	public List<LigneDeFrais> listerLigneDeFrais() {
		return this.ligneDeFraisRepo.findAll();
	}

	/**
	 * ajouter une ligne de frais
	 * 
	 * @param ligneDeFraisRepo
	 */
	public void addLigneDefrais(LigneDeFrais ligneDeFrais) {
		System.out.println("ligne6" + ligneDeFrais.getId());

		if (isVerificationLigneDeFraisOk(ligneDeFrais)) {

			this.ligneDeFraisRepo.save(ligneDeFrais);
		}
	}

	/**
	 * supprimer une ligne de frais
	 * 
	 * @param ligneDeFrais
	 */
	public void deleteLigneDeFrais(LigneDeFrais ligneDeFrais) {
		if (isVerificationLigneDeFraisOk(ligneDeFrais)) {
			this.ligneDeFraisRepo.delete(ligneDeFrais);
		}
	}

	/**
	 * modifier une ligne de frais
	 * 
	 * @param ligneDeFraisAModifier
	 * @return ligne de frais modifié
	 */
	public LigneDeFrais updateLigneDeFrais(LigneDeFrais ligneDeFraisAModifier) {
		LigneDeFrais ligneDeFraisModifie = new LigneDeFrais();

		if (isVerificationLigneDeFraisOk(ligneDeFraisAModifier)) {
			this.ligneDeFraisRepo.save(ligneDeFraisModifie);
		}
		return ligneDeFraisModifie;
	}

	/**
	 * Vérifie que la ligne de frais est valide
	 * 
	 * @param ligneDeFrais
	 * @return true or false en fonction de la validité
	 */
	public boolean isVerificationLigneDeFraisOk(LigneDeFrais ligneDeFrais) {
		if (ligneDeFrais.getFrais() < 0) {
			throw new InvalidFraisException();
		}

		if (!ligneDeFrais.getDate().isAfter(findNotesByLigneId(ligneDeFrais.getId()).getMission().getDateDebut())
				&& !ligneDeFrais.getDate()
						.isBefore(findNotesByLigneId(ligneDeFrais.getId()).getMission().getDateFin())) {
			throw new InvalidDateLigneException();
		}

		if (Exists(ligneDeFrais.getDate(), ligneDeFrais.getNatureLigne())) {
			throw new AllreadyExistsLigneException();
		} else {
			return true;
		}
	}

	/**
	 * vérifie qu'une ligne existe déjà avec les paramètres suivant
	 * 
	 * @param date
	 *            la date d'une ligne
	 * @param nature
	 *            la nature d'une ligne
	 * @return true or false en fonction de si une ligne est déjà existante avec
	 *         ces paramètres
	 */
	public boolean Exists(LocalDate date, NatureLigne nature) {
		boolean resultat = false;
		for (LigneDeFrais ligne : this.ligneDeFraisRepo.findAll()) {
			if (ligne.getDate() == date && ligne.getNatureLigne() == nature) {
				resultat = true;
			} else {
				resultat = false;
			}
		}
		return resultat;
	}

	/**
	 * trouve une note de frais en fonction de l'ID d'une ligne
	 * 
	 * @param id
	 *            id d'une ligne de frais
	 * @return la note de frais
	 */
	public NoteDeFrais findNotesByLigneId(Long id) {
		NoteDeFrais resultat = null;
		for (NoteDeFrais noteDeFrais : this.noteDeFraisRepo.findAllByMissionCollegueEmail(
				(String) SecurityContextHolder.getContext().getAuthentication().getName())) {
			for (LigneDeFrais ligne : noteDeFrais.getLignesDeFrais()) {
				System.out.println(id);
				System.out.println("ligne id" + ligne.getId());
				if (ligne.getId() == id) {

					resultat = noteDeFrais;
				} else {
					throw new NoLigneDeFraisFoundException();
				}
			}
		}
		return resultat;

	}

}
