public class TTTBoard {
    private String[][] grid;
    private static final int SIZE = 3;

    public TTTBoard() {
        grid = new String[SIZE][SIZE];
        clearBoard();
    }

    public void clearBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = " ";
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        return grid[row][col].equals(" ");
    }

    public void placeMove(int row, int col, Player player) {
        grid[row][col] = player.getSymbol();
    }

    public boolean checkWin(Player player) {
        String symbol = player.getSymbol();
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((grid[i][0].equals(symbol) && grid[i][1].equals(symbol) && grid[i][2].equals(symbol)) ||
                    (grid[0][i].equals(symbol) && grid[1][i].equals(symbol) && grid[2][i].equals(symbol))) {
                return true;
            }
        }
        // Check diagonals
        if ((grid[0][0].equals(symbol) && grid[1][1].equals(symbol) && grid[2][2].equals(symbol)) ||
                (grid[0][2].equals(symbol) && grid[1][1].equals(symbol) && grid[2][0].equals(symbol))) {
            return true;
        }
        return false;
    }

    public boolean isTie() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}