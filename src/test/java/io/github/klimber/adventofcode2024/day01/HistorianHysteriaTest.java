package io.github.klimber.adventofcode2024.day01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HistorianHysteriaTest {
    private final List<Long> left = new ArrayList<>();
    private final List<Long> right = new ArrayList<>();

    @Test
    void testUsingExample() {
        String example = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;
        this.readLists(example);
        long distance = HistorianHysteria.calculateDistance(this.left, this.right);
        Assertions.assertEquals(11L, distance);
        long similarityScore = HistorianHysteria.calculateSimilarityScore(this.left, this.right);
        Assertions.assertEquals(31L, similarityScore);
    }

    @Test
    void testUsingInput() throws IOException {
        File inputFile = new File("src/main/resources/day01/input.txt");
        String input = Files.readString(inputFile.toPath());
        this.readLists(input);
        long distance = HistorianHysteria.calculateDistance(this.left, this.right);
        System.out.println("Distance: " + distance);
        long similarityScore = HistorianHysteria.calculateSimilarityScore(this.left, this.right);
        System.out.println("SimilarityScore: " + similarityScore);
    }

    private void readLists(String input) {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            this.left.add(scanner.nextLong());
            this.right.add(scanner.nextLong());
        }
        scanner.close();
    }
}
