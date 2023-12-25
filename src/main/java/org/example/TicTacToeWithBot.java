package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeWithBot {
    private char[][] board;
    private char currentPlayer;
    private static final char PLAYER_X = '❌';
    private static final char PLAYER_O = '⭕';
    private static final char EMPTY = '-';

    public TicTacToeWithBot() {
        board = new char[3][3];
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void printBoard() {
        System.out.println("-------------");
        int cellNumber = 1;
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    System.out.print(cellNumber);
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print(" | ");
                cellNumber++;
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private boolean makeMove(int choseNumber, char player) {
        if (choseNumber < 1 || choseNumber > 9) {
            return false;
        }

        int row = (choseNumber - 1) / 3;
        int col = (choseNumber - 1) % 3;

        if (board[row][col] != EMPTY) {
            return false;
        }

        board[row][col] = player;
        return true;
    }

    private void playHumanTurn() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (choose a number from 1 to 9): ");
            int choseNumber = scanner.nextInt();

            if (makeMove(choseNumber, currentPlayer)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void playBotTurn() {
        Random random = new Random();
        int cellNumber;

        while (true) {
            cellNumber = random.nextInt(9) + 1; // Random number between 1 and 9

            if (makeMove(cellNumber, PLAYER_O)) {
                break;
            }
        }

        System.out.println("Computer played at cell " + cellNumber);
    }

    public void playAgainstFriend() {
        while (true) {
            printBoard();

            playHumanTurn();

            if (isWinner(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                start();
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a tie!");
                break;
            } else {
                switchPlayer();
            }
        }
    }

    public void playWithBot() {
        while (true) {
            printBoard();

            if (currentPlayer == PLAYER_X) {
                playHumanTurn();
            } else {
                playBotTurn();
            }

            if (isWinner(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                initializeBoard();
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a tie!");
                break;
            } else {
                switchPlayer();
            }
        }
    }

    public static void start(){
        TicTacToeWithBot game = new TicTacToeWithBot();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the game mode: ");
        System.out.println("1. Play against a friend");
        System.out.println("2. Play against the computer");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            game.playAgainstFriend();
        } else if (choice == 2) {
            game.playWithBot();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
    }

    public static void main(String[] args) {
        start();
    }
}
