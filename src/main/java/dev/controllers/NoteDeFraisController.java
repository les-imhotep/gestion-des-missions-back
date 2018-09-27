package dev.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.services.NoteDeFraisService;

@RestController
public class NoteDeFraisController {

	NoteDeFraisService noteDeFraisService;

	public NoteDeFraisController(NoteDeFraisService noteDeFraisService) {
		super();
		this.noteDeFraisService = noteDeFraisService;
	}

}
