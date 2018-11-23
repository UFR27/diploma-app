package fr.pantheonsorbonne.miage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import fr.pantheonsorbonne.miage.diploma.DiplomaSnippet;

public class DiplomaFileAdapter extends FileGenerator<AbstractDiplomaGenerator> {

	public DiplomaFileAdapter(AbstractDiplomaGenerator t) {
		super(t);

	}


	@Override
	public void generateFile(String outputFile) {
		try (FileOutputStream fos = new FileOutputStream(outputFile)) {
			this.generator.writeToStream(fos);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("failed to write diploma file", e);
		}
	}

}
