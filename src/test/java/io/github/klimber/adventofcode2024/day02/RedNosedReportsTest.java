package io.github.klimber.adventofcode2024.day02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RedNosedReportsTest {

    @Test
    void testUsingExample() {
        String example = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;
        List<List<Long>> reports = this.parseReports(example);
        long noDampenerCount = RedNosedReports.countSafeReports(reports, 0);
        Assertions.assertEquals(2L, noDampenerCount);
        long oneDampenerCount = RedNosedReports.countSafeReports(reports, 1);
        Assertions.assertEquals(4L, oneDampenerCount);
    }

    @Test
    void testUsingInput() throws IOException {
        File inputFile = new File("src/main/resources/day02/input.txt");
        String input = Files.readString(inputFile.toPath());
        List<List<Long>> reports = this.parseReports(input);
        long noDampenerCount = RedNosedReports.countSafeReports(reports, 0);
        System.out.println("Safe reports (no dampener): " + noDampenerCount);
        long oneDampenerCount = RedNosedReports.countSafeReports(reports, 1);
        System.out.println("Safe reports (1 level dampener): " + oneDampenerCount);
    }

    private List<List<Long>> parseReports(String input) {
        return input.lines()
                    .map(line -> Arrays.stream(line.split(" "))
                                       .map(Long::parseLong)
                                       .toList())
                    .toList();
    }
}
