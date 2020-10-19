package de.hec.tddworkshop.tddworkshop.parser;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvParser {

    public static final String SEPARATOR = "\\|";

    @SneakyThrows
    public List<Todo> readFile(String fileName) {
        List<String> lines = readLines(fileName);

        return lines.stream()
                .map(this::mapLine)
                .collect(Collectors.toList());
    }

    private List<String> readLines(String fileName) throws URISyntaxException, IOException {
        URL fileResource = this.getClass().getResource(fileName);
        if(fileResource!=null) {
            Path filePath = Path.of(fileResource.toURI());
            if(Files.exists(filePath)) {
                return Files.readAllLines(filePath);
            }
        }
        throw new FileNotFoundException();
    }

    private Todo mapLine(String line) {

        String[] tokens = line.split(SEPARATOR);

        if(tokens.length!=3) {
            throw new IllegalArgumentException("expecting 3 columns in file");
        }

        return Todo.builder()
                .id(Integer.parseInt(tokens[0]))
                .category(Todo.Category.builder()
                        .name(tokens[1])
                        .build())
                .description(tokens[2])
                .build();
    }


}
