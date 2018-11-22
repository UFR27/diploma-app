package fr.pantheonsorbonne.miage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

import fr.pantheonsorbonne.miage.diploma.NameSnippet;
import fr.pantheonsorbonne.miage.diploma.DiplomaSnippet;
import fr.pantheonsorbonne.miage.diploma.DateSnippet;

public class DiplomaGenerator {

	private Collection<DiplomaSnippet> snippets = new HashSet<>();

	public DiplomaGenerator(String name) {

		this.snippets.add(new DateSnippet());
		this.snippets.add(new NameSnippet(name));
	}

	public InputStream generateStream() {
		Path tempFile;
		try {
			tempFile = java.nio.file.Files.createTempFile("sdfsdfggs", "sdfsdfgsdfg");
			this.generateFile(tempFile.toString());
			return new FileInputStream(tempFile.toFile());

		} catch (IOException e) {

			throw new RuntimeException("failed to generate the file to stream to", e);
		}

	}

	public void generateFile(String outputFile) {
		Document document = new Document();
		try {

			Path image = new File("src/main/resources/diploma.png").toPath();
			Rectangle rect = new Rectangle(800f, 600f);
			document.setPageSize(rect);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
			document.open();
			
			
			for (DiplomaSnippet snippet : this.snippets) {
				snippet.write(writer);
			}

			document.add(Image.getInstance(image.toAbsolutePath().toString()));

		} catch (DocumentException | IOException e) {
			throw new RuntimeException("failed to generate Document", e);
		} finally {
			document.close();
		}
	}

}
