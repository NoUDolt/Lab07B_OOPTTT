import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTFrame extends JFrame {
    private Game game;
    private TTTTileButton[][] buttons;

    public TTTFrame(Player player1, Player player2) {
        super("Tic Tac Toe");
        game = new Game(player1, player2);
        buttons = new TTTTileButton[3][3];

        // Create a panel for the game board
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Initialize buttons and add them to the panel
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TTTTileButton(row, col);
                buttons[row][col].setText(" ");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonClickListener());
                panel.add(buttons[row][col]);
            }
        }

        this.add(panel);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TTTTileButton button = (TTTTileButton) e.getSource();
            if (button.getText().equals(" ")) { // Valid move check
                button.setText(game.getCurrentPlayer().getSymbol());
                game.makeMove(button.getRow(), button.getCol());

                if (game.getBoard().checkWin(game.getCurrentPlayer())) {
                    JOptionPane.showMessageDialog(null, "Player " + game.getCurrentPlayer().getName() + " wins!");
                    resetGame();
                } else if (game.getBoard().isTie()) {
                    JOptionPane.showMessageDialog(null, "It's a tie!");
                    resetGame();
                } else {
                    // Debugging statement to check the current player
                    System.out.println("Current player after switch: " + game.getCurrentPlayer().getName());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
            }
        }
    }

    private void resetGame() {
        game.resetGame();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(" ");
            }
        }
    }

    public static void main(String[] args) {
        // Create two players
        Player player1 = new Player("Player 1", "X");
        Player player2 = new Player("Player 2", "O");

        // Launch the Tic Tac Toe game
        SwingUtilities.invokeLater(() -> new TTTFrame(player1, player2));
    }
}