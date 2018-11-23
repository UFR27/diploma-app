package fr.pantheonsorbonne.miage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.junit.jupiter.api.Test;

import com.google.common.io.ByteStreams;

public class DiplomaGeneratorTest {

	@Test
	void compareGeneratedDiploma() {

		try {
			//
			File temp = Files.createTempFile("prefix_", "_suffic").toFile();

			Student stu = new Student(0, "Nicolas", "");
			MiageDiplomaGenerator generator = new MiageDiplomaGenerator(stu);
			new DiplomaFileAdapter(generator).generateFile(temp.getPath());

			FileInputStream tempFileReader = new FileInputStream(temp);
			ByteArrayOutputStream tempFileContent = new ByteArrayOutputStream();
			ByteStreams.copy(tempFileReader, tempFileContent);

			FileInputStream testBaseFileReader = new FileInputStream("src/test/resources/nicolas.pdf");
			ByteArrayOutputStream testBaseFileContent = new ByteArrayOutputStream();
			ByteStreams.copy(testBaseFileReader, testBaseFileContent);

			BufferedImage genetatedbim = new PDFRenderer(PDDocument.load(new File(temp.getPath()))).renderImage(0);
			BufferedImage referencebim = new PDFRenderer(PDDocument.load(new File("src/test/resources/nicolas.pdf")))
					.renderImage(0);

			File generatedImage = Files.createTempFile("prefix_", ".png").toFile();
			File testImage = Files.createTempFile("prefix_", ".png").toFile();
			ImageIOUtil.writeImage(genetatedbim, generatedImage.getPath(), 300);
			ImageIOUtil.writeImage(referencebim, testImage.getPath(), 300);

			FileInputStream generatedImageReader = new FileInputStream(generatedImage);
			FileInputStream testImageReader = new FileInputStream(testImage);

			ByteArrayOutputStream generatedImageData = new ByteArrayOutputStream();
			ByteArrayOutputStream testImageData = new ByteArrayOutputStream();

			ByteStreams.copy(generatedImageReader, generatedImageData);
			ByteStreams.copy(testImageReader, testImageData);

			assertArrayEquals(testImageData.toByteArray(), generatedImageData.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}