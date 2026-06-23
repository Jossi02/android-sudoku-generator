package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    private static final int BOARD_SIZE = 9;
    private static final float HINT_PROBABILITY = 0.7f;
    private static final int BUTTON_MARGIN = 2;

    private final Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];
    private BoardGenerator boardGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardGenerator = new BoardGenerator();
        createSudokuGrid();
    }

    /**
     * 스도쿠 보드 UI(버튼 그리드)를 생성하고 초기 숫자를 배치합니다.
     */
    private void createSudokuGrid() {
        TableLayout table = findViewById(R.id.tablelayout);

        for (int i = 0; i < BOARD_SIZE; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new Button(this);

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                layoutParams.setMargins(BUTTON_MARGIN, BUTTON_MARGIN, BUTTON_MARGIN, BUTTON_MARGIN);
                buttons[i][j].setLayoutParams(layoutParams);

                // 스도쿠 정답 보드에서 숫자 가져오기
                int number = boardGenerator.get(i, j);

                // 설정된 확률에 따라 일부 숫자만 노출 (나머지는 빈칸)
                if (Math.random() < HINT_PROBABILITY) {
                    buttons[i][j].setText(String.valueOf(number));
                } else {
                    buttons[i][j].setText("");
                }

                tableRow.addView(buttons[i][j]);
            }
            table.addView(tableRow);
        }
    }
}