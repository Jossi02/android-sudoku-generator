package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table;
        table = (TableLayout) findViewById(R.id.tablelayout);
        BoardGenerator board = new BoardGenerator();

        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new Button(this);

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                int margin = 2;
                layoutParams.setMargins(margin, margin, margin, margin);

                buttons[i][j].setLayoutParams(layoutParams);

                int number = board.get(i, j);

                if (Math.random() < 0.7) {
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