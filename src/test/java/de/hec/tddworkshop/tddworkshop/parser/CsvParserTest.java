package de.hec.tddworkshop.tddworkshop.parser;

import de.hec.tddworkshop.tddworkshop.data.Todo;
import de.hec.tddworkshop.tddworkshop.data.Todo.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvParserTest {

    private final CsvParser csvParser = new CsvParser();

    @Test
    public void twoValidLines() {
        // given
        String givenFileName = "/validTestFile.csv";

        // when
        final List<Todo> lines = this.csvParser.readFile(givenFileName);

        // then
        assertThat(lines).hasSize(2);

        assertThat(lines.get(0)).isEqualTo(
                Todo.builder()
                        .id(1)
                        .category(
                                Category.builder()
                                        .name("Haushalt")
                                        .build())
                        .description("Wäsche waschen")
                        .build());

        assertThat(lines.get(1)).isEqualTo(
                Todo.builder()
                        .id(2)
                        .category(
                                Category.builder()
                                        .name("Einkauf")
                                        .build())
                        .description("Brötchen kaufen")
                        .build());

    }

    @Test
    void invalidId() {
        // given
        String givenFileName = "/invalidIdTestFile.csv";

        Assertions.assertThatThrownBy(() -> {

            // when
            this.csvParser.readFile(givenFileName);

            // then ( incl. assertThatThrownBy)
        }).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void missingColumn() {
        // given
        String givenFileName = "/missingColumnTestFile.csv";

        Assertions.assertThatThrownBy(() -> {

            // when
            this.csvParser.readFile(givenFileName);

            // then ( incl. assertThatThrownBy)
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void missingFile() {
        // given
        String givenFileName = "/notExistingFile.csv";

        Assertions.assertThatThrownBy(() -> {

            // when
            this.csvParser.readFile(givenFileName);

        // then ( incl. assertThatThrownBy)
        }).isInstanceOf(FileNotFoundException.class);
    }

    @Test
    public void emptyFile() {
        // given
        String givenFileName = "/emptyTestFile.csv";

        assertThat(

                // when
                this.csvParser.readFile(givenFileName)

        // then
        ).isEmpty();
    }

}