package com.example.sudoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Regression tests for the instructor-provided board generator after later refactoring.
 */
public class BoardGeneratorTest {
    private static final int SIZE = 9;

    @Test
    public void generatedBoardsMaintainSudokuInvariants() {
        for (int generation = 0; generation < 100; generation++) {
            assertValidBoard(new BoardGenerator().getBoard());
        }
    }

    private void assertValidBoard(int[][] board) {
        assertEquals(SIZE, board.length);

        for (int[] row : board) {
            assertValidUnit(row);
        }

        for (int column = 0; column < SIZE; column++) {
            int[] values = new int[SIZE];
            for (int row = 0; row < SIZE; row++) {
                values[row] = board[row][column];
            }
            assertValidUnit(values);
        }

        for (int blockRow = 0; blockRow < SIZE; blockRow += 3) {
            for (int blockColumn = 0; blockColumn < SIZE; blockColumn += 3) {
                int[] values = new int[SIZE];
                for (int index = 0; index < SIZE; index++) {
                    values[index] = board[blockRow + index / 3][blockColumn + index % 3];
                }
                assertValidUnit(values);
            }
        }
    }

    private void assertValidUnit(int[] values) {
        assertEquals(SIZE, values.length);
        boolean[] seen = new boolean[SIZE + 1];

        for (int value : values) {
            assertTrue(value >= 1 && value <= SIZE);
            assertFalse(seen[value]);
            seen[value] = true;
        }
    }
}
