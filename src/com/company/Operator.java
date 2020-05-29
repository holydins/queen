package com.company;

import java.util.ArrayList;
import java.util.List;

public class Operator {

    private int direction;
    private int value;

    public Operator(Operator operator){
        this.direction = operator.direction;
        this.value = operator.value;
    }

    public Operator(int direction, int value){
        this.direction = direction;
        this.value = value;
    }

    //TODO
    public boolean applicable(State state){
        int[] queenPosition = state.getQueenPosition();
        switch(direction){
            case -1:
                return queenPosition[1] - value >= 0 ? true : false;
            case 0:
                return queenPosition[0] + value < state.board.length && queenPosition[1] - value >= 0 ? true : false;
            case 1:
                return queenPosition[0] + value < state.board.length ? true : false;
            default:
                System.err.println("Egyik előre meghatározott irányt sem vette fel.");
                return false;
        }
    }

    public State apply(State state){

        int[] queenPosition = state.getQueenPosition();
        int[] newPosition = new int[]{0, 1};
        switch(direction){
            case -1:
                newPosition[0] = queenPosition[0];
                newPosition[1] = queenPosition[1] - this.value;
                break;
            case 0:
                newPosition[0] = queenPosition[0] + this.value;
                newPosition[1] = queenPosition[1] - this.value;
                break;
            case 1:
                newPosition[0] = queenPosition[0] + this.value;
                newPosition[1] = queenPosition[1];
                break;
        }

        return new State(state.board.length, newPosition, state.turn);
    }

    @Override
    public String toString(){
        String direc;
        switch (this.direction){
            case -1:
                direc = "bal";
                break;
            case 0:
                direc = "átló";
                break;
            case 1:
                direc = "le";
                break;
            default:
                direc = "WRONG";
        }
        return direc + " " + value;
    }

    public static List<Operator> getAllApplicableOperators(State state){
        int[] queenPosition = state.getQueenPosition();
        List<Operator> operators = new ArrayList<>();

        //horizontal operators
        for(int i = queenPosition[1] - 1; i >= 0 ; i--){
            operators.add(new Operator(-1, queenPosition[1] - i));
        }
        //horizontal operators
        for(int i = queenPosition[0] + 1; i < state.board.length; i++){
            operators.add(new Operator(1, i - queenPosition[0]));
        }
        //diagonal operators
        for(int i = 1; queenPosition[0] + i < state.board.length && queenPosition[1] - i >= 0; i++){
            operators.add(new Operator(0, i));
        }

        return operators;
    }
}
