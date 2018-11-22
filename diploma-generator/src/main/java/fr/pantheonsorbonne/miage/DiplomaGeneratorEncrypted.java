package fr.pantheonsorbonne.miage;

import java.io.File;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class DiplomaGeneratorEncrypted extends DiplomaGenerator {

	private String password;

	public DiplomaGeneratorEncrypted(String name, String password) {
		super(name);
		this.password = password;

	}

	@Override
	public void generateFile(String outputFile) {

		File tempOutputFile = new File(outputFile + "_tmp");
		super.generateFile(tempOutputFile.getPath());
		try {
			PdfReader pdfReader = new PdfReader(tempOutputFile.getAbsolutePath());
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(outputFile));

			pdfStamper.setEncryption(this.password.getBytes(), "".getBytes(), 0, PdfWriter.ENCRYPTION_AES_256);
			pdfStamper.close();

		} catch (IOException | DocumentException e) {

			e.printStackTrace();
			throw new RuntimeException("failed to generate Encrypted File");
		} finally {
			try {
				Files.delete(tempOutputFile.toPath());
			} catch (IOException e1) {
				Logger.getGlobal()
						.warning("failed to delete temporary file, clear-text diploma will remain on the server!");
			}
		}

	}

}
