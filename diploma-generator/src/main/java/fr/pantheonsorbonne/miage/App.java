package fr.pantheonsorbonne.miage;


public class App {
	public static void main(String[] args) {
		new DiplomaGenerator("Nicolas Herbaut").generateFile("test-nicolas.pdf");
		new DiplomaGeneratorEncrypted("Nicolas Herbaut", "nhe").generateFile("test-nicolas-encrypted.pdf");

	}
}
