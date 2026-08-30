# Android Sudoku Board Coursework

## Overview

This Android Java project was completed for a university mobile-programming course. It uses an instructor-provided `BoardGenerator` to create a completed 9×9 Sudoku board, then builds the board UI dynamically by following the course instructions.

The repository covers the board-generation and display stage of the coursework. It is not a full playable Sudoku game.

## Coursework Scope

Instructor-provided coursework material:

- The original `BoardGenerator` implementation
- The Sudoku board-generation algorithm
- The shift-matrix, matrix-multiplication, and 3×3-block construction approach

Implemented as guided coursework:

- Android project and UI wiring
- A `TableLayout` declared in XML
- Nine `TableRow`s and 81 `Button`s created in Java
- Display of values read from `BoardGenerator`
- Independent display of each value with approximately 70% probability

Later portfolio maintenance:

- Constants, naming, and block-array organization
- Method extraction, comments, and documentation
- Build repair and regression tests

## How the Board Is Displayed

`BoardGenerator` supplies a completed 9×9 board. `MainActivity` creates 81 buttons and displays each board value with approximately 70% probability; the remaining buttons have blank text.

The blank cells are display-only and do not accept Sudoku input.

## Validation

The following commands were run successfully on Windows with Java 21.0.10 and Gradle 8.11.1:

- `gradlew.bat test --no-daemon --console=plain`
- `gradlew.bat assembleDebug --no-daemon --console=plain`

The JVM regression test checks 100 generated boards for 9×9 dimensions, values from 1 through 9, and row, column, and 3×3-block uniqueness. Its purpose is to protect the instructor-provided generator's behavior after later refactoring.

## Limitations

- No number input or cell editing
- No conflict validation
- No win condition
- No memo feature
- No uniqueness check for the displayed puzzle

## Build

Use Android Studio with Android SDK 35 and JDK 17 or later.

Windows:

```text
gradlew.bat test
gradlew.bat assembleDebug
```

Unix-like systems:

```text
./gradlew test
./gradlew assembleDebug
```

The Unix Gradle wrapper is tracked as executable. These Unix commands were not run during the Windows validation above.

A repository-wide open-source license is not specified because the project contains instructor-provided coursework code.
