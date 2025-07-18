```markdown
# 🧩 Sudoku Solver (Java Swing)

A simple and interactive **Sudoku Solver GUI** built using **Java Swing**. The app allows users to input Sudoku puzzles, validates input in real-time, and solves the puzzle using a backtracking algorithm.

---

## 💡 Features

- 🖱️ Intuitive 9x9 Sudoku grid input
- ✅ Real-time validation of entries:
  - Only allows digits `1-9`
  - Prevents duplicates in rows, columns, and 3x3 boxes
- 🧠 Solves puzzles instantly using backtracking
- 🔄 Clear button to reset the board
- 📋 Status messages for user feedback

---

## 📦 Requirements

- Java 8 or higher
- No external libraries required

---

## 🚀 How to Run

### Option 1: Compile & Run Manually

1. **Compile the source code:**

```bash
javac SudokuSolverSwing.java
```

2. **Run the application:**

```bash
java SudokuSolverSwing
```

---

### Option 2: Run via `run.bat` (Windows)

To simplify launching on Windows, a `run.bat` file is provided.

#### ✅ Steps:

1. Double-click `run.bat`  
   *(or right-click → Run as administrator)*  
2. The Sudoku Solver window will open automatically.

#### 📝 Make sure:

- `run.bat` and `.class` files are in the **same folder**
- Java is installed and added to your system's **PATH**

---

## 🧠 Algorithm

This project uses the **Backtracking** algorithm to solve Sudoku:

1. Find an empty cell.
2. Try filling in numbers `1-9`.
3. Check if the number is valid (no duplicates in row, column, or box).
4. Recursively solve the rest of the board.
5. If stuck, backtrack and try the next number.

---

## 📌 Notes

- Input validation prevents invalid or duplicate numbers.
- The grid highlights alternating 3x3 blocks for better visual clarity.
- Only numeric input is allowed (`1-9`); `0` and letters are blocked.

---

## ✨ Future Enhancements

- Add undo/redo functionality
- Timer and leaderboard
- Dark/light theme toggle
- Save and load puzzles from file

---

## 👨‍💻 Author

**Aman Sahu**  
📧 Connect on GitHub or LinkedIn

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).
```
