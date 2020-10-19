package de.hec.tddworkshop.tddworkshop.printer;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

@Component
public class ConsolePrinter {

  public void print(List<Todo> givenTodo) {
    givenTodo.forEach(this::printTodo);
  }

  private void printTodo(Todo todo) {
    final PrintWriter writer = new PrintWriter(System.out, true);
    writer.printf("Aufgabe %d [%s] - %s\n",
            todo.getId(),
            todo.getCategory().getName(),
            todo.getDescription());
  }
}
