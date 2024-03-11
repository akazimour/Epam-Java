package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] results = new int[rows][columns];
        int startColumn = 0;
        int endColumn = columns - 1;
        int startRow = 0;
        int endRow = rows - 1;
        int counter = 1;

        while (startRow <= endRow && startColumn <= endColumn) {

            //top row
            for (int i = startColumn; i <= endColumn; i++) {
                results[startRow][i] = counter;
                counter++;
            }
            //right side
            startRow++;
            for (int i = startRow; i <= endRow; i++) {
                results[i][endColumn] = counter;
                counter++;
            }
            //bottom row
            endColumn--;
            if (startRow <= endRow) {
                for (int i = endColumn; i >= startColumn; i--) {
                    results[endRow][i] = counter;
                    counter++;
                }
            }
            //left side
            endRow--;
            if (startColumn <= endColumn) {
                for (int i = endRow; i >= startRow; i--) {
                    results[i][startColumn] = counter;
                    counter++;
                }
            }
            startColumn++;
        }
        return results;
    }
}
