package fr.pantheonsorbonne.miage;

/**
 * an object able to be serialized as a File
 * 
 * @author nherbaut
 *
 */
public abstract class FileGenerator<T> {

	protected final T t;

	public FileGenerator(T t) {
		this.t = t;
	}

	public abstract void generateFile(String outputFile);
}
