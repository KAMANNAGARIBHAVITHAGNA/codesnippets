import java.util.Scanner;

public class MazeGame {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char PLAYER = 'P';
    private static final char EXIT = 'E';

    private static char[][] maze = {
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
            { '#', 'P', ' ', '#', ' ', ' ', ' ', '#', 'E', '#' },
            { '#', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#' },
            { '#', ' ', '#', '#', ' ', '#', ' ', '#', ' ', '#' },
            { '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', '#', '#', '#', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', '#' },
            { '#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' }
    };

    private static int playerX = 1;
    private static int playerY = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            printMaze();
            System.out.println("Move (WASD): ");
            char move = scanner.next().toUpperCase().charAt(0);

            switch (move) {
                case 'W':
                    movePlayer(playerX - 1, playerY);
                    break;
                case 'A':
                    movePlayer(playerX, playerY - 1);
                    break;
                case 'S':
                    movePlayer(playerX + 1, playerY);
                    break;
                case 'D':
                    movePlayer(playerX, playerY + 1);
                    break;
                default:
                    System.out.println("Invalid move. Use W, A, S, or D.");
            }

            if (maze[playerX][playerY] == EXIT) {
                printMaze();
                System.out.println("Congratulations! You reached the exit!");
                gameRunning = false;
            }
        }

        scanner.close();
    }

    private static void movePlayer(int newX, int newY) {
        if (maze[newX][newY] == WALL) {
            System.out.println("You hit a wall! Try a different direction.");
        } else {
            maze[playerX][playerY] = PATH;
            playerX = newX;
            playerY = newY;
            maze[playerX][playerY] = PLAYER;
        }
    }

    private static void printMaze() {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
