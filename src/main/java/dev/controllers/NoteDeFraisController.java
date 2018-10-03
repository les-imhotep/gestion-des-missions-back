package dev.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import dev.Converters;
import dev.controller.dto.NoteDeFraisDto;
import dev.services.NoteDeFraisService;

@CrossOrigin
@RestController()
@RequestMapping("/notesdefrais")
public class NoteDeFraisController {

	NoteDeFraisService noteDeFraisService;

	public NoteDeFraisController(NoteDeFraisService noteDeFraisService) {
		super();
		this.noteDeFraisService = noteDeFraisService;
	}

	// *************************************GET***********************************************
	@GetMapping
	public ResponseEntity<List<NoteDeFraisDto>> findAllNoteDeFrais() {
		return ResponseEntity.ok(this.noteDeFraisService.findAllNoteDeFraisByUser().stream()
				.map(col -> Converters.NOTEDEFRAIS_TO_NOTEDEFRAIS_DTO.convert(col)).collect(Collectors.toList()));
	}

	@GetMapping("/pdf")
	public void ddlPrime(HttpServletResponse response) throws IOException, DocumentException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.noteDeFraisService.ddlNoteDeFrais(baos);

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + "Notes_de_frais.pdf" + "\""));

		// setting the content type
		response.setContentType("application/pdf");
		// the contentlength
		response.setContentLength(baos.size());
		// write ByteArrayOutputStream to the ServletOutputStream
		ServletOutputStream os = response.getOutputStream();
		baos.writeTo(os);
		os.flush();
		os.close();
	}

}
