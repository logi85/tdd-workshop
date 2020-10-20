package de.hec.tddworkshop.tddworkshop.service;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import de.hec.tddworkshop.tddworkshop.parser.CsvParser;
import de.hec.tddworkshop.tddworkshop.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CsvProcessingServiceTest {

  @Mock
  private CsvParser parser;

  @Mock
  private ConsolePrinter printer;

  @InjectMocks
  private CsvProcessingService service;

  @Test
  void processFile() {

    final String givenFile = "myFile";
    final List<Todo> givenTodos = List.of(
            Todo.builder().build()
    );

    when(parser.readFile(givenFile)).thenReturn(givenTodos);
    service.process(givenFile);

    verify(printer).print(givenTodos);
  }
}
