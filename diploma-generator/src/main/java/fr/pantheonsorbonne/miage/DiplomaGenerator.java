package fr.pantheonsorbonne.miage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class DiplomaGenerator {

	private String name;

	public DiplomaGenerator(String name) {
		this.name = name;
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

			{
				ColumnText ct = new ColumnText(writer.getDirectContent());
				ct.setSimpleColumn(200, 50, 550, 350);
				Font font = FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK);
				Chunk chunk = new Chunk(name, font);
				ct.addElement(chunk);
				ct.go();
			}

			{
				ColumnText ct = new ColumnText(writer.getDirectContent());
				ct.setSimpleColumn(200, 140, 350, 0);
				Font font = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);
				String timeStamp = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).format(new Date());
				Chunk chunk = new Chunk(timeStamp, font);
				ct.addElement(chunk);
				ct.go();
			}

			document.add(Image.getInstance(image.toAbsolutePath().toString()));

		} catch (DocumentException | IOException e) {
			throw new RuntimeException("failed to generate Document", e);
		} finally {
			document.close();
		}
	}
}
