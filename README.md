# Android Sudoku Board Coursework

이 저장소는 대학교 `모바일프로그래밍` 수업 과제로 작성한 Android Java 프로젝트입니다. Instructor-provided `BoardGenerator`를 사용하고, 수업 안내에 따라 9×9 board UI를 동적으로 구성해 생성된 값을 표시합니다.

현재 범위는 board generation/display stage이며, full playable Sudoku game은 아닙니다.

## Project Overview

- Android Java 기반 coursework
- XML에 선언한 `TableLayout`
- Java에서 생성하는 9개의 `TableRow`와 81개의 `Button`
- `BoardGenerator`의 값을 UI에 연결
- 각 값을 약 70% 확률로 초기 표시
- Later portfolio maintenance에서 build repair와 regression test 추가

## Coursework Scope

### Instructor-provided

- 원본 `BoardGenerator` 구현
- Sudoku board-generation algorithm
- Shift matrix와 matrix multiplication
- 3×3 block construction 방식

위 항목은 instructor-provided coursework material이며, 저장소 소유자가 설계한 알고리즘으로 소개하지 않습니다.

### Implemented as coursework

- 수업 안내에 따른 Android project와 UI wiring
- XML `TableLayout` 구성
- Java에서 `TableRow`와 `Button` 동적 생성
- `BoardGenerator` 값을 화면에 표시
- 각 값을 약 70% 확률로 초기 표시

### Later portfolio maintenance

- Constants와 naming 정리
- `s0`~`s8`을 blocks array로 정리
- Method extraction
- Comments와 documentation 정리
- Build repair
- Regression test 추가

## How It Works

- `BoardGenerator`가 완성된 9×9 board를 제공합니다.
- `MainActivity`가 81개의 `Button`을 생성합니다.
- 각 board 값은 약 70% 확률로 표시됩니다.
- 나머지 cell은 blank text로 남습니다.
- Blank cell은 입력 가능한 game cell이 아닙니다.

## Validation

Portfolio hardening 당시 다음 환경에서 검증했습니다.

- Windows 11
- Java 21.0.10
- Gradle 8.11.1
- Android SDK 35

```text
gradlew.bat test --no-daemon --console=plain
gradlew.bat assembleDebug --no-daemon --console=plain
```

두 명령 모두 PASS했습니다. `BoardGeneratorTest`는 100개의 generated board를 대상으로 9×9 크기, 1부터 9까지의 값, row·column·3×3 block uniqueness를 검증합니다.

이 테스트는 사용자가 설계한 알고리즘을 증명하는 테스트가 아니라, instructor-provided generator가 later refactoring 이후에도 기존 Sudoku invariant를 유지하는지 확인하는 regression test입니다.

## Build & Usage

Android Studio, Android SDK 35, JDK 17 이상이 필요합니다.

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

Unix wrapper는 executable로 추적되어 있습니다. 다만 portfolio hardening 당시 실제 validation은 Windows에서 수행했으며, 위 macOS/Linux 명령은 당시 실행하지 않았습니다.

## Known Limitations

- 숫자 입력 및 cell editing 없음
- Conflict validation 없음
- Win condition 없음
- Memo 기능 없음
- Displayed puzzle의 unique solution 검증 없음

이 기능들은 교수 안내의 후속 단계에 포함되어 있었지만 현재 repository 범위에는 구현되지 않았습니다.

## Rights and Provenance

이 저장소에는 instructor-provided coursework source가 포함되어 있습니다. Repository-wide 재라이선스 권한이 확인되지 않았으므로 별도 `LICENSE` 파일을 두지 않습니다.
