package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        checkInput(inputNumbers);

        inputNumbers.sort(Comparator.naturalOrder());

        int rows = calcRowsCount(inputNumbers);

        // Number of colums is twice as rows - 1.
        int colums = rows * 2 - 1;

        int[][] result =  new int[rows][colums];

        // Index of central column.
        int centerIdx = colums / 2;

        Iterator<Integer> iter = inputNumbers.iterator();

        for (int i = 0; i < rows; i++) {
            // Index to start filling elements.
            int colStartIdx = centerIdx - i;

            for (int j = colStartIdx; j <= colStartIdx + i * 2 ; j+=2) {
                if (!iter.hasNext()) {
                    // Should not get here, since sizes calculated correctly.
                    throw new CannotBuildPyramidException();
                }

                // Don't check hasNext since we know there are i elements in it.
                result[i][j] = iter.next();
            }
        }

        return result;
    }

    private void checkInput(List<Integer> inputNumbers) {
        if (inputNumbers == null) {
            // Input argument cannot be null.
            throw new CannotBuildPyramidException();
        }

        Set<Integer> s = new HashSet<>();

        for (Integer num : inputNumbers) {
            if (num == null) {
                //Element of list cannot be null.
                throw new CannotBuildPyramidException();
            }

            // Input list contains duplicates.
            if (s.contains(num)) {
                throw new CannotBuildPyramidException();
            }

            s.add(num);
        }
    }

    private int calcRowsCount(List<Integer> inputNumbers) {
        int n = inputNumbers.size();

        // Number of rows of pyramid.
        int i = 1;
        // Count of elements correspoing number of rows.
        int m = 0;

        while (m < n) {
            // No next row add i elements
            m = m + i;

            // If number of elements in input list and in pyramid is exactly the same, return it.
            if (m == n) {
                return i;
            }

            i++;
        }

        throw new CannotBuildPyramidException();
    }
}
