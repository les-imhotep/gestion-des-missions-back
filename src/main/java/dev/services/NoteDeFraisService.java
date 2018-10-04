package dev.services;

import java.io.ByteArrayOutputStream;
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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dev.Converters;
import dev.controller.dto.NoteDeFraisDto;
import dev.entities.NoteDeFrais;
import dev.repositories.NoteDeFraisRepo;

@Service
public class NoteDeFraisService {

	private NoteDeFraisRepo noteDeFraisRepo;

	public NoteDeFraisService(NoteDeFraisRepo noteDeFraisRepo) {
		super();
		this.noteDeFraisRepo = noteDeFraisRepo;
	}

	/**
	 * ajoute une note de frais
	 * 
	 * @param noteDeFrais
	 *            la note de frais à ajouter
	 */
	public void addNoteDefraisMission(NoteDeFrais noteDeFrais) {

		this.noteDeFraisRepo.save(noteDeFrais);

	}

	/**
	 * récupère les données de l'utilisateur connecté
	 * 
	 * @return le nom de l'utilisateur connecté
	 */
	public String getUserDetails() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getName();
	}

	/**
	 * trouve toutes les notes de frais en fonction d'un utilisateur
	 * 
	 * @return la liste de toutes les notes de frais de l'utilisateur connecté
	 */
	public List<NoteDeFrais> findAllNoteDeFraisByUser() {

		return this.noteDeFraisRepo.findAllByMissionCollegueEmail(getUserDetails());

	}

	public void addNoteDeFrais(NoteDeFrais noteDeFrais) {
		this.noteDeFraisRepo.save(noteDeFrais);

	}

	public void deleteNoteDeFrais(NoteDeFrais noteDeFrais) {
		this.noteDeFraisRepo.delete(noteDeFrais);
	}

	public NoteDeFraisDto updateNoteDeFrais(NoteDeFrais noteDeFraisAModifier) {
		NoteDeFraisDto noteDeFrais = new NoteDeFraisDto();
		// à faire exception balbla
		this.noteDeFraisRepo.save(noteDeFraisAModifier);
		return noteDeFrais;
	}

	/**
	 * exporte les notes de frais de l'utilisateur dans un format pdf
	 * 
	 * @param baos
	 *            le flux de données
	 * @return le pdf en question
	 * 
	 * @throws DocumentException
	 */
	public Document ddlNoteDeFrais(ByteArrayOutputStream baos) throws DocumentException {

		Document document = new Document();

		PdfWriter.getInstance(document, baos);

		document.open();
		document.addTitle("Notes de frais");
		Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
		Chunk chunk = new Chunk("Notes de frais de " + getUserDetails(), chapterFont);
		Chapter chapter = new Chapter(new Paragraph(chunk), 1);
		chapter.setNumberDepth(0);
		chapter.add(new Paragraph(" ", chapterFont));
		document.add(chapter);
		PdfPTable tableMission = new PdfPTable(new float[] { 1, (float) 1.1, 1 }); // columns.
		tableMission.setTotalWidth(500);
		tableMission.setLockedWidth(true);
		for (NoteDeFrais noteDeFrais : findAllNoteDeFraisByUser()) {

			tableMission.addCell(new PdfPCell(new Paragraph("Date début : " + noteDeFrais.getMission().getDateDebut())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission.addCell(new PdfPCell(new Paragraph("Date fin : " + noteDeFrais.getMission().getDateFin())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission
					.addCell(new PdfPCell(
							new Paragraph("Nature : " + noteDeFrais.getMission().getNatureMission().getName())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission.addCell(new PdfPCell(new Paragraph("Montant prime : " + noteDeFrais.getMission().getPrime())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission.addCell(new PdfPCell(new Paragraph("Statut : " + noteDeFrais.getMission().getStatut())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission.addCell(new PdfPCell(new Paragraph("Transport : " + noteDeFrais.getMission().getTransport())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission
					.addCell(new PdfPCell(
							new Paragraph("Ville d'arrivée: " + noteDeFrais.getMission().getVilleArrivee())))
					.setBorder(Rectangle.NO_BORDER);
			tableMission
					.addCell(new PdfPCell(new Paragraph("Ville départ : " + noteDeFrais.getMission().getVilleDepart())))
					.setBorder(Rectangle.NO_BORDER);

			document.add(tableMission);
			document.add(Chunk.NEWLINE);

		}
		PdfPTable table = new PdfPTable(new float[] { 1, (float) 1.1, 1 }); // columns.
		// table.setWidthPercentage(100);
		table.setTotalWidth(500);
		table.setLockedWidth(true);
		table.addCell(new PdfPCell(new Paragraph("Date: "))).setFixedHeight(20);
		table.addCell(new PdfPCell(new Paragraph("Nature : ")));
		table.addCell(new PdfPCell(new Paragraph("Frais : ")));

		for (NoteDeFrais noteDeFrais : findAllNoteDeFraisByUser()) {
			noteDeFrais.getLignesDeFrais().stream()
					.map(ligne -> Converters.LIGNEDEFRAIS_TO_LIGNEDEFRAIS_DTO.convert(ligne))
					.collect(Collectors.toList()).forEach(element -> {

						table.addCell(new PdfPCell(new Paragraph(element.getDate()))).setFixedHeight(20);
						table.addCell(new PdfPCell(new Paragraph("" + element.getNatureLigne())));
						table.addCell(new PdfPCell(new Paragraph("" + element.getFrais())));

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
