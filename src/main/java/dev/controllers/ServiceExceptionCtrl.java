package dev.controllers;

import java.io.IOException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.controller.dto.ErrorCode;
import dev.controller.dto.ErrorDto;
import dev.exceptions.InvalidFacturationException;
import dev.exceptions.InvalidIdException;
import dev.exceptions.InvalidNameException;
import dev.exceptions.NameAllreadyExcistsException;
import dev.exceptions.NoPrimeException;
import dev.exceptions.PourcentageException;

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
}