package de.hec.tddworkshop.tddworkshop.printer;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class ConsolePrinterTest {

  private final ConsolePrinter printer = new ConsolePrinter();

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void printTodo() {

    Todo givenTodo = Todo.builder()
            .id(1)
            .category(Todo.Category.builder()
                    .name("Hausaufgaben")
                    .build()
            )
            .description("Mathe")
            .build();


    printer.print(List.of(givenTodo));

    Assertions.assertThat(outContent.toString()).isEqualTo("Aufgabe 1 [Hausaufgaben] - Mathe\n");
  }

  @Test
  void printTwoToDos() {

    Todo givenTodo1 = Todo.builder()
            .id(1)
            .category(Todo.Category.builder()
                    .name("Hausaufgaben")
                    .build()
            )
            .description("Mathe")
            .build();

    Todo givenTodo2 = Todo.builder()
            .id(2)
            .category(Todo.Category.builder()
                    .name("Einkauf")
                    .build()
            )
            .description("Eier kaufen")
            .build();


    printer.print(List.of(givenTodo1, givenTodo2));

    Assertions.assertThat(outContent.toString()).isEqualTo(
            "Aufgabe 1 [Hausaufgaben] - Mathe\n" +
            "Aufgabe 2 [Einkauf] - Eier kaufen\n");


  }
}