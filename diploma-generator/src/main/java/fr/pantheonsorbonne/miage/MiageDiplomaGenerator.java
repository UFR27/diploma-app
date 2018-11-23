package fr.pantheonsorbonne.miage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import com.google.common.collect.Collections2;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.ColumnText;

import fr.pantheonsorbonne.miage.diploma.NameSnippet;
import fr.pantheonsorbonne.miage.diploma.DateSnippet;
import fr.pantheonsorbonne.miage.diploma.DiplomaSnippet;

public class MiageDiplomaGenerator extends AbstractDiplomaGenerator {

	private String name;

	/**
	 * Create the generator using a student name
	 * 
	 * @param name
	 */
	public MiageDiplomaGenerator(String name) {
		this.name = name;
	}

	@Override
	protected Collection<DiplomaSnippet> getDiplomaSnippets() {
		return Arrays.asList(new DateSnippet(), new NameSnippet(name));
	}

}
