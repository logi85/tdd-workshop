package de.hec.tddworkshop.tddworkshop;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import de.hec.tddworkshop.tddworkshop.parser.CsvParser;
import de.hec.tddworkshop.tddworkshop.printer.ConsolePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TddWorkshopApplication {

	private final CsvParser parser;
	private final ConsolePrinter printer;

	private static String csvFile;

	@PostConstruct
	public void process() {
			List<Todo> todos = parser.readFile(TddWorkshopApplication.csvFile);
			printer.print(todos);
	}


	public static void main(String[] args) {
		if(args==null || args.length!=1) {
			System.out.println("please provide input filename.");
			return;
		}
		TddWorkshopApplication.csvFile = args[0];

		SpringApplication.run(TddWorkshopApplication.class, args);
	}

}
