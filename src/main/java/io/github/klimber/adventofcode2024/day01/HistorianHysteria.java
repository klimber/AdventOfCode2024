package io.github.klimber.adventofcode2024.day01;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HistorianHysteria {

    private static List<Long> left;
    private static List<Long> right;

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            useInputLists();
        } else {
            useExampleLists();
        }

        long distance = calculateDistance(left, right);
        System.out.println("Distance: " + distance);
        long similarityScore = calculateSimilarityScore(left, right);
        System.out.println("SimilarityScore: " + similarityScore);
    }

    private static long calculateDistance(List<Long> left, List<Long> right) {
        PriorityQueue<Long> leftHeap = new PriorityQueue<>(left);
        PriorityQueue<Long> rightHeap = new PriorityQueue<>(right);
        long sum = 0;
        while (!leftHeap.isEmpty() && !rightHeap.isEmpty()) {
            Long leftElement = leftHeap.poll();
            Long rightElement = rightHeap.poll();
            sum += Math.abs(leftElement - rightElement);
        }
        return sum;
    }

    private static long calculateSimilarityScore(List<Long> left, List<Long> right) {
        Map<Long, Long> rightFreq = right.stream()
                                         .collect(Collectors.groupingBy(Function.identity(),
                                                                        Collectors.counting()));
        return left.stream()
                   .map(l -> l * rightFreq.getOrDefault(l, 0L))
                   .mapToLong(Long::longValue)
                   .sum();
    }

    private static void useExampleLists() {
        left = Arrays.asList(3L, 4L, 2L, 1L, 3L, 3L);
        right = Arrays.asList(4L, 3L, 5L, 3L, 9L, 3L);
    }

    private static void useInputLists() throws Exception {
        left = new ArrayList<>();
        right = new ArrayList<>();
        File inputFile = new File("src/main/resources/day01/input.txt");
        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNext()) {
            left.add(scanner.nextLong());
            right.add(scanner.nextLong());
        }
    }
}
