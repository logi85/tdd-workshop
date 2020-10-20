package de.hec.tddworkshop.tddworkshop.service;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import de.hec.tddworkshop.tddworkshop.parser.CsvParser;
import de.hec.tddworkshop.tddworkshop.printer.ConsolePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvProcessingService {

  private final CsvParser parser;
  private final ConsolePrinter printer;

  public void process(String csvFile) {
    List<Todo> todos = parser.readFile(csvFile);
    printer.print(todos);
  }

}
