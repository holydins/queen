package com.company;

public class State {

    int[][] board;
    Turn turn;

    public State(Turn turn) {
        this.turn = turn;
    }

    public State(State state){
        this.board = state.board;
        this.turn = state.turn;
    }

    public State(int size, int[] queenPosition, Turn turn){
        this.board = initState(size, queenPosition);
        this.turn = turn;
    }

    public State(int[][] board, Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public void setTurn(Turn turn){
        this.turn = turn;
    }

    public void swapTurn() {
        if (this.turn == Turn.Computer) {
            this.turn = Turn.Player;
        } else {
            this.turn = Turn.Computer;
        }
    }

    public boolean isGoal() {
        if (getQueenPosition()[0] == board.length - 1 && getQueenPosition()[1] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getQueenPosition() {
        int xPosition = 0, yPosition = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    xPosition = i;
                    yPosition = j;
                    return new int[]{xPosition, yPosition};
                }
            }
        }
        return new int[]{xPosition, yPosition};
    }

    public static int[][] initState(int N, int[] positionOfQueen) {
        int[][] state = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == positionOfQueen[0] && j == positionOfQueen[1]) {
                    state[i][j] = 1;
                } else {
                    state[i][j] = 0;
                }
            }
        }
        return state;
    }

    @Override
    public String toString() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        return null;
    }
}
