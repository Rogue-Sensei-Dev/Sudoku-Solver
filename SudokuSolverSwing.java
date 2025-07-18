import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuSolverSwing extends JFrame implements ActionListener {
    private JTextField[][] grid = new JTextField[9][9];
    private JButton solveButton, clearButton;
    private JLabel statusLabel;

    public SudokuSolverSwing() {
        setTitle("Sudoku Solver © by Aman Sahu");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));

        // Creating Sudoku grid with alternating colors for 3x3 boxes
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final int r = row, c = col; // Ensure final variables for inner class
                grid[r][c] = new JTextField(1);
                grid[r][c].setFont(new Font("Arial", Font.BOLD, 20));
                grid[r][c].setHorizontalAlignment(JTextField.CENTER);

                // Apply background color for alternating 3x3 boxes
                if (((row / 3) + (col / 3)) % 2 == 1) {
                    grid[r][c].setBackground(Color.LIGHT_GRAY);
                } else {
                    grid[r][c].setBackground(Color.WHITE);
                }

                // Real-time validation: Allow only 1-9, no letters or duplicates in row/col/box
                grid[r][c].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char inputChar = e.getKeyChar();
                        String currentText = grid[r][c].getText();

                        if (!Character.isDigit(inputChar) || inputChar == '0' || currentText.length() >= 1) {
                            e.consume();
                            return;
                        }

                        int value = inputChar - '0';

                        for (int i = 0; i < 9; i++) {
                            if (i != c && grid[r][i].getText().equals(String.valueOf(value))) {
                                e.consume();
                                statusLabel.setText("Duplicate in row!");
                                return;
                            }
                            if (i != r && grid[i][c].getText().equals(String.valueOf(value))) {
                                e.consume();
                                statusLabel.setText("Duplicate in column!");
                                return;
                            }
                        }

                        int boxRowStart = r - r % 3;
                        int boxColStart = c - c % 3;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                int rowToCheck = boxRowStart + i;
                                int colToCheck = boxColStart + j;
                                if ((rowToCheck != r || colToCheck != c) &&
                                    grid[rowToCheck][colToCheck].getText().equals(String.valueOf(value))) {
                                    e.consume();
                                    statusLabel.setText("Duplicate in 3x3 box!");
                                    return;
                                }
                            }
                        }

                        statusLabel.setText("Valid input");
                    }
                });

                gridPanel.add(grid[r][c]);
            }
        }

        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        statusLabel = new JLabel("Enter numbers and press Solve", SwingConstants.CENTER);

        solveButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) {
            int[][] board = new int[9][9];

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    String text = grid[row][col].getText();
                    if (!text.isEmpty()) {
                        try {
                            int value = Integer.parseInt(text);
                            if (value >= 1 && value <= 9) {
                                board[row][col] = value;
                            } else {
                                statusLabel.setText("Invalid Input! Use numbers 1-9.");
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            statusLabel.setText("Invalid Input! Use numbers only.");
                            return;
                        }
                    } else {
                        board[row][col] = 0;
                    }
                }
            }

            if (solveSudoku(board)) {
                updateGrid(board);
                statusLabel.setText("Solved Successfully!");
            } else {
                statusLabel.setText("No solution exists.");
            }
        } else if (e.getSource() == clearButton) {
            clearGrid();
        }
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num ||
                board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private void updateGrid(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setText(String.valueOf(board[row][col]));
            }
        }
    }

    private void clearGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setText("");
            }
        }
        statusLabel.setText("Grid Cleared!");
    }

    public static void main(String[] args) {
        new SudokuSolverSwing();
    }
}