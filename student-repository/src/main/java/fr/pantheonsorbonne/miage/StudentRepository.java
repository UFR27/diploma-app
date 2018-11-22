package fr.pantheonsorbonne.miage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class StudentRepository implements Iterable<Student> {

	private String db;

	private StudentRepository(String db) {
		this.db = db;
	};

	public static StudentRepository withDB(String db) {
		return new StudentRepository(db);
	}

	@Override
	public java.util.Iterator<Student> iterator() {
		try (FileReader reader = new FileReader(this.db)) {
			CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT);
			return parser.getRecords().stream()
					.map((reccord) -> new Student(Integer.parseInt(reccord.get(2)), reccord.get(0), reccord.get(1)))
					.map(c -> (Student) c).iterator();

		} catch (IOException e) {
			Logger.getGlobal().info("IO PB" + e.getMessage());
			return Collections.EMPTY_SET.iterator();
		}
	}

}
