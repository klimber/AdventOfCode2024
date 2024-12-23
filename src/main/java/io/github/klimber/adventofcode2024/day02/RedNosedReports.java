package io.github.klimber.adventofcode2024.day02;

import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {

    public static long countSafeReports(List<List<Long>> reports, int dampenerLevels) {
        return reports.stream()
                      .filter(e -> isReportSafe(e, dampenerLevels))
                      .count();
    }

    private static boolean isReportSafe(List<Long> providedReport, long dampenerLevels) {
        long availableRemovals = dampenerLevels;
        List<Long> report = new ArrayList<>(providedReport);
        boolean isAscending = checkAscending(report);
        int i = 1;
        while (i < report.size() && availableRemovals >= 0) {
            long previous = report.get(i - 1);
            long current = report.get(i);
            long difference = isAscending ? current - previous : previous - current;
            if (i == report.size() - 1) { // i is last element
                if (availableRemovals > 0) {
                    // Automatically safe by removing last level
                    return true;
                } else {
                    // Check if last level is safe
                    return difference >= 1 && difference <= 3;
                }
            }
            if (difference < 1 || difference > 3) { // unsafe, need to remove some level
                long next = report.get(i + 1);
                long differenceWithoutCurrent = isAscending ? next - previous : previous - next;
                if (differenceWithoutCurrent >= 1 && differenceWithoutCurrent <= 3) {
                    // current level is the problem
                    report.remove(i);
                } else {
                    // previous level is the problem
                    report.remove(i - 1);
                }
                availableRemovals--;
                i = 1; // restart checks
                continue;
            }
            i++;
        }
        return availableRemovals >= 0;
    }

    private static boolean checkAscending(List<Long> report) {
        long trend = 0;
        for (int i = 1; i < report.size(); i++) {
            long previous = report.get(i - 1);
            long current = report.get(i);
            trend += current >= previous ? 1 : -1;
        }
        return trend >= 0;
    }
}
