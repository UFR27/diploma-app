package fr.pantheonsorbonne.miage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import fr.pantheonsorbonne.miage.diploma.DiplomaSnippet;

public class DiplomaFileAdapter extends FileGenerator<DiplomaGenerator> {

	public DiplomaFileAdapter(DiplomaGenerator t) {
		super(t);

	}

	private AbstractDiplomaGenerator diplomaGenerator;

	@Override
	public void generateFile(String outputFile) {
		try (FileOutputStream fos = new FileOutputStream(outputFile)) {
			this.diplomaGenerator.writeToStream(fos);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("failed to write diploma file", e);
		}
	}

}
