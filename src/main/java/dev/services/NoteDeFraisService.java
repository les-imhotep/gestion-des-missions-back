package dev.services;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dev.Converters;
import dev.entities.NoteDeFrais;
import dev.repositories.NoteDeFraisRepo;

@Service
public class NoteDeFraisService {

	private NoteDeFraisRepo noteDeFraisRepo;

	public NoteDeFraisService(NoteDeFraisRepo noteDeFraisRepo) {
		super();
		this.noteDeFraisRepo = noteDeFraisRepo;
	}

	public void addNoteDefraisMission(NoteDeFrais noteDeFraisRepo) {

		this.noteDeFraisRepo.save(noteDeFraisRepo);

	}

	public String getUserDetails() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public List<NoteDeFrais> findAllNoteDeFraisByUser() {

		return this.noteDeFraisRepo.findAllByMissionCollegueEmail(getUserDetails());

	}

	public Document ddlNoteDeFrais(ByteArrayOutputStream baos) throws FileNotFoundException, DocumentException {

		Document document = new Document();

		PdfWriter.getInstance(document, baos);

		document.open();
		document.addTitle("Notes de frais");
		Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
		Chunk chunk = new Chunk("Notes de frais", chapterFont);
		Chapter chapter = new Chapter(new Paragraph(chunk), 1);
		chapter.setNumberDepth(0);
		chapter.add(new Paragraph(" ", chapterFont));
		document.add(chapter);

		PdfPTable table = new PdfPTable(new float[] { 1, (float) 1.1, 1, 1, 1, (float) 1.5, 1 }); // columns.
		// table.setWidthPercentage(100);
		table.setTotalWidth(500);
		table.setLockedWidth(true);
		table.addCell(new PdfPCell(new Paragraph("Date de début : ")));
		table.addCell(new PdfPCell(new Paragraph("Date de fin : ")));
		table.addCell(new PdfPCell(new Paragraph("Frais : ")));
		table.addCell(new PdfPCell(new Paragraph("Ville d'arrivée : ")));
		table.addCell(new PdfPCell(new Paragraph("Ville de départ : ")));
		table.addCell(new PdfPCell(new Paragraph("Transport : ")));
		table.addCell(new PdfPCell(new Paragraph("Nature de mission : ")));

		for (NoteDeFrais noteDeFrais : findAllNoteDeFraisByUser()) {
			noteDeFrais.getLignesDeFrais().stream()
					.map(ligne -> Converters.LIGNEDEFRAIS_TO_LIGNEDEFRAIS_DTO.convert(ligne))
					.collect(Collectors.toList()).forEach(element -> {

						table.addCell(new PdfPCell(new Paragraph(element.getDateDebut())));
						;
						table.addCell(new PdfPCell(new Paragraph(element.getDateFin())));
						table.addCell(new PdfPCell(new Paragraph("" + element.getFrais())));
						table.addCell(new PdfPCell(new Paragraph(element.getVilleArrivee())));
						table.addCell(new PdfPCell(new Paragraph(element.getVilleDepart())));
						table.addCell(new PdfPCell(new Paragraph("" + element.getTransport())));
						table.addCell(new PdfPCell(new Paragraph(element.getNatureMission().getName())));

					});

		}
		try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		return document;

	}

}
