package com.example.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 스도쿠 보드 생성기
 * 9x9 보드를 3x3 크기의 9개 블록으로 나누어 행렬 곱셈을 이용해 스도쿠 규칙을 만족하는 맵을 생성합니다.
 */
public class BoardGenerator {
    private static final int BOARD_SIZE = 9;
    private static final int BLOCK_SIZE = 3;

    private final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    // 행렬 변환 기법
    // shiftDown (기존 x1): 3x3 행렬에 곱해지면 행(row)을 아래로 1칸씩 밀어냄
    private static final int[][] shiftDown = {
            {0, 0, 1},
            {1, 0, 0},
            {0, 1, 0}
    };

    // shiftRight (기존 x2): 3x3 행렬에 곱해지면 열(col)을 오른쪽으로 1칸씩 밀어냄
    private static final int[][] shiftRight = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 0, 0}
    };

    public BoardGenerator() {
        int[][][] blocks = new int[BOARD_SIZE][BLOCK_SIZE][BLOCK_SIZE];

        // 랜덤 순서로 1~9의 숫자 생성
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= BOARD_SIZE; i++) {
            list.add(i);
        }

        Collections.shuffle(list); // 랜덤, 이 줄을 없애면 언제나 같은 보드가 나옴

        // 첫 번째 3x3 블록(blocks[0]) 초기화
        for (int i = 0; i < BOARD_SIZE; i++) {
            blocks[0][i / BLOCK_SIZE][i % BLOCK_SIZE] = list.get(i);
        }

        // 행렬 곱셈을 이용하여 나머지 8개 블록 생성
        blocks[1] = mul(shiftRight, blocks[0]);
        blocks[2] = mul(shiftDown, blocks[0]);
        blocks[3] = mul(blocks[0], shiftDown);
        blocks[4] = mul(blocks[1], shiftDown);
        blocks[5] = mul(blocks[2], shiftDown);
        blocks[6] = mul(blocks[0], shiftRight);
        blocks[7] = mul(blocks[1], shiftRight);
        blocks[8] = mul(blocks[2], shiftRight);

        // 9개의 3x3 블록을 9x9 전체 보드에 병합
        for (int i = 0; i < BLOCK_SIZE; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                board[i][j] = blocks[0][i][j];
                board[i][j + BLOCK_SIZE] = blocks[1][i][j];
                board[i][j + (BLOCK_SIZE * 2)] = blocks[2][i][j];
                
                board[i + BLOCK_SIZE][j] = blocks[3][i][j];
                board[i + BLOCK_SIZE][j + BLOCK_SIZE] = blocks[4][i][j];
                board[i + BLOCK_SIZE][j + (BLOCK_SIZE * 2)] = blocks[5][i][j];
                
                board[i + (BLOCK_SIZE * 2)][j] = blocks[6][i][j];
                board[i + (BLOCK_SIZE * 2)][j + BLOCK_SIZE] = blocks[7][i][j];
                board[i + (BLOCK_SIZE * 2)][j + (BLOCK_SIZE * 2)] = blocks[8][i][j];
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    /**
     * 두 3x3 행렬을 곱합니다.
     * @param x 첫 번째 행렬
     * @param y 두 번째 행렬
     * @return 곱해진 결과 행렬 (3x3)
     */
    private int[][] mul(int[][] x, int[][] y) {
        int[][] result = new int[BLOCK_SIZE][BLOCK_SIZE];
        for (int i = 0; i < BLOCK_SIZE; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                for (int k = 0; k < BLOCK_SIZE; k++) {
                    result[i][j] += x[i][k] * y[k][j];
                }
            }
        }
        return result;
    }
}
