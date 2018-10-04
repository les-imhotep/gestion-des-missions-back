package dev.controllers;

import java.io.IOException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.controller.dto.ErrorCode;
import dev.controller.dto.ErrorDto;
import dev.exceptions.AllreadyExistsLigneException;
import dev.exceptions.InvalidDateException;
import dev.exceptions.InvalidDateLigneException;
import dev.exceptions.InvalidDateMissionsException;
import dev.exceptions.InvalidDateTransportMissionException;
import dev.exceptions.InvalidFacturationException;
import dev.exceptions.InvalidIdException;
import dev.exceptions.InvalidIdMissionException;
import dev.exceptions.InvalidNameException;
import dev.exceptions.NameAllreadyExcistsException;
import dev.exceptions.NoLigneDeFraisFoundException;
import dev.exceptions.NoPrimeException;
import dev.exceptions.PourcentageException;

/**
 * classe gérant les exceptions de l'application
 * 
 * @author Diginamic-02
 *
 */
@ControllerAdvice
public class ServiceExceptionCtrl {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> serviceException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.SERVICE, "Erreur côté service"));
	}

	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<?> pseudoException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.ID_INVALID, "La nature n'a pas été trouvé en base de données"));
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<?> invalidFormatException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.JSON_PARSE,
				"Erreur dans la conversion Java <> JSON (vérifier vos paramètres d'entrée)"));
	}

	@ExceptionHandler(InvalidNameException.class)
	public ResponseEntity<?> invalidNameException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.INVALID_NAME, "Le nom est obligatoire"));
	}

	@ExceptionHandler(InvalidFacturationException.class)
	public ResponseEntity<?> invalidFacturationException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.INVALID_FACTURATION, "Le type de facturation est obligatoire"));
	}

	@ExceptionHandler(PourcentageException.class)
	public ResponseEntity<?> pourcentageException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.INVALID_POURCENTAGE, "Le pourcentage doit être inférieur à 10%"));
	}

	@ExceptionHandler(InvalidDateTransportMissionException.class)
	public ResponseEntity<?> invalidDateTransportMissionException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.TO_SHORT_DATE,
				"Une anticipation de 7 jours est exigée pour ce mode de transport"));
	}

	@ExceptionHandler(InvalidDateMissionsException.class)
	public ResponseEntity<?> invalidDateMissionsException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.INVALID_DATE,
				"une mission ne peut pas débuter le jour même, ni dans le passé "));
	}

	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<?> invalidDateException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.INVALID_DATE,
				"Une mission ne peu commencer ou se terminer, un jour non-travaillé"));
	}

	@ExceptionHandler(InvalidIdMissionException.class)
	public ResponseEntity<?> InvalidIdMissionException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.INVALID_ID_MISSION,
				"Mission non trouvé en base,id incorrect ou connexion serveur perdu"));
	}

	@ExceptionHandler(NameAllreadyExcistsException.class)
	public ResponseEntity<?> nameAllreadyExcistsException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.ALLREADY_EXISTS, "La nature existe déjà"));

	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> IOException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.FILE_ERROR, "Problème lors de la création du fichier"));
	}

	@ExceptionHandler(NoPrimeException.class)
	public ResponseEntity<?> NoPrimeException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.NO_PRIME, "Aucune prime n'a été trouvée"));
	}

	@ExceptionHandler(InvalidDateLigneException.class)
	public ResponseEntity<?> InvalidDateLigneException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.INVALID_DATE,
				"La date d'une ligne de frais doit être comprise durant la date de début et la date de fin de la mission"));
	}

	@ExceptionHandler(AllreadyExistsLigneException.class)
	public ResponseEntity<?> AllreadyExistsLigneException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.ALLREADY_EXISTS, "Cette ligne de frais existe déjà"));
	}

	@ExceptionHandler(NoLigneDeFraisFoundException.class)
	public ResponseEntity<?> NoLigneDeFraisFoundException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.NOT_FOUND, "Ligne non existante"));
	}

}