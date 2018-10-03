package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Input arguments must be not null.");
        }

        int n = x.size();
        int m = y.size();

        // Y contains less elements - return false immediately.
        if (m < n) {
            return false;
        }

        int idx = 0;

        for (int i = 0; i < n ; i++) {
            Object o1 = x.get(i);

            boolean found = false;

            for (int j = idx; j < m; j++) {
                Object o2 = y.get(j);

                if (o1.equals(o2)) {
                    found = true;

                    // Store index of found element in j to continue from next element.
                    idx = j + 1;
                }
            }

            // Element o1 was not found in y, return false skipping next iterations.
            if (!found) {
                return false;
            }
        }

        return true;
    }
}


