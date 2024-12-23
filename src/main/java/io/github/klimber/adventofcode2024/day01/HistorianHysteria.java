package io.github.klimber.adventofcode2024.day01;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HistorianHysteria {

    public static long calculateDistance(List<Long> left, List<Long> right) {
        Queue<Long> leftHeap = new PriorityQueue<>(left);
        Queue<Long> rightHeap = new PriorityQueue<>(right);
        if (leftHeap.size() != rightHeap.size()) {
            String msg = String.format("Expected queues to have same size (leftHeapSize=%d, rightHeapSize=%d)",
                                       leftHeap.size(), rightHeap.size());
            throw new IllegalArgumentException(msg);
        }
        long sum = 0;
        while (!leftHeap.isEmpty()) {
            long leftElement = Objects.requireNonNull(leftHeap.poll());
            long rightElement = Objects.requireNonNull(rightHeap.poll());
            sum += Math.abs(leftElement - rightElement);
        }
        return sum;
    }

    public static long calculateSimilarityScore(List<Long> left, List<Long> right) {
        Map<Long, Long> rightFreq = right.stream()
                                         .collect(Collectors.groupingBy(Function.identity(),
                                                                        Collectors.counting()));
        return left.stream()
                   .map(l -> l * rightFreq.getOrDefault(l, 0L))
                   .mapToLong(Long::longValue)
                   .sum();
    }
}
