public class Game {
    private TTTBoard board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean isGameOver;

    public Game(Player player1, Player player2) {
        this.board = new TTTBoard();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Start with player1
        this.isGameOver = false;
    }

    public void makeMove(int row, int col) {
        if (!isGameOver && board.isValidMove(row, col)) {
            board.placeMove(row, col, currentPlayer);
            if (board.checkWin(currentPlayer)) {
                System.out.println("Player " + currentPlayer.getName() + " wins!");
                isGameOver = true;
            } else if (board.isTie()) {
                System.out.println("It's a tie!");
                isGameOver = true;
            } else {
                switchPlayer(); // Switch to the other player
            }
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    // Corrected switchPlayer() method
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        System.out.println("Switched player to: " + currentPlayer.getName());
    }

    public void resetGame() {
        board.clearBoard();
        currentPlayer = player1; // Reset to start with player1
        isGameOver = false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public TTTBoard getBoard() {
        return board;
    }
}