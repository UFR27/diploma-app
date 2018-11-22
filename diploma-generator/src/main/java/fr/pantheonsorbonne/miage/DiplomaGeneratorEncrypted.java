package fr.pantheonsorbonne.miage;

import java.io.File;
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
	void generateFile(String outputFile) {

		File tempOutputFile = new File("temp_" + outputFile);
		super.generateFile(tempOutputFile.getAbsolutePath());
		try {
			PdfReader pdfReader = new PdfReader(tempOutputFile.getAbsolutePath());
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("encrypted_" + outputFile));

			pdfStamper.setEncryption(this.password.getBytes(), "".getBytes(), 0, PdfWriter.ENCRYPTION_AES_256);
			pdfStamper.close();

		} catch (IOException | DocumentException e) {
			try {
				Files.delete(tempOutputFile.toPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException("failed to generate Encrypted File");
		}

	}

}
