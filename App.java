import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; //50px for the text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JMenuBar menuBar=new JMenuBar();
    JMenu menu=new JMenu("Game Options");
    JMenuItem menu1Item,menu3Item =new JMenuItem();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        menu1Item=new JMenuItem("New Game");
        menu3Item=new JMenuItem("Exit Game");
        menu.add(menu1Item);
        menu.add(menu3Item);
        menuBar.add(menu);

        menu1Item.addActionListener(e -> startNewGame());
        menu3Item.addActionListener(e -> System.exit(0)); // Exit game

        frame.setJMenuBar(menuBar);


        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener((ActionEvent e) -> {
                    if (gameOver)
                    {
                        textLabel.setText("Game Over");
                        return;
                    }
                    JButton tile1 = (JButton) e.getSource();
                    if ("".equals(tile1.getText())) {
                        tile1.setText(currentPlayer);
                        turns++;
                        checkWinner();
                        if (!gameOver) {
                            currentPlayer = (currentPlayer == null ? playerX == null : currentPlayer.equals(playerX)) ? playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn.");
                        }
                    }
                });
            }
        }
    }

    // Starts a new game by resetting the board and resetting the game state
    void startNewGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText(""); // Clear all tiles
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
            }
        }
        currentPlayer = playerX; // Reset to player X
        gameOver = false;
        turns = 0;
        textLabel.setText("Tic-Tac-Toe"); // Reset label
    }

    
    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if ("".equals(board[r][0].getText())) continue;

            if ((board[r][0].getText() == null ? board[r][1].getText() == null : board[r][0].getText().equals(board[r][1].getText())) &&
                (board[r][1].getText() == null ? board[r][2].getText() == null : board[r][1].getText().equals(board[r][2].getText()))) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) {
            if ("".equals(board[0][c].getText())) continue;
            
            if ((board[0][c].getText() == null ? board[1][c].getText() == null : board[0][c].getText().equals(board[1][c].getText())) &&
                (board[1][c].getText() == null ? board[2][c].getText() == null : board[1][c].getText().equals(board[2][c].getText()))) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonally
        if ((board[0][0].getText() == null ? board[1][1].getText() == null : board[0][0].getText().equals(board[1][1].getText())) &&
            (board[1][1].getText() == null ? board[2][2].getText() == null : board[1][1].getText().equals(board[2][2].getText())) &&
            !"".equals(board[0][0].getText())) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally
        if ((board[0][2].getText() == null ? board[1][1].getText() == null : board[0][2].getText().equals(board[1][1].getText())) &&
            (board[1][1].getText() == null ? board[2][0].getText() == null : board[1][1].getText().equals(board[2][0].getText())) &&
            !"".equals(board[0][2].getText())) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
public class App {
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("unused")
        TicTacToe ticTacToe = new TicTacToe();
    }
}