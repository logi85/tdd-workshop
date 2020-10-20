package de.hec.tddworkshop.tddworkshop;

import de.hec.tddworkshop.tddworkshop.service.CsvProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class TddWorkshopApplication {

	private final CsvProcessingService processingService;

	private static String csvFile;

	@PostConstruct
	public void process() {
		processingService.process(TddWorkshopApplication.csvFile);
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
