package com.company;

import java.util.List;

public class Heuristica {

    private static int value;

    public static int h(State state){
        return possible(state);
    }

    public static void calculate(State state){

        List<Operator> applicableOperators = Operator.getAllApplicableOperators(state);

        for(Operator op: applicableOperators){
            State newState = op.apply(state);
            if(isFinishLine(newState)){
                value -= 10;
            }else{
                value += 10;
            }
        }

    }


    private static int possible(State state){
        value = 0;

        List<Operator> applicableOperators = Operator.getAllApplicableOperators(state);

        if(applicableOperators.size() == 0){
            return Integer.MAX_VALUE;
        }

        if(applicableOperators.size() == 4 && isAllBadMove(state.board.length - 1, state.getQueenPosition())){
            return Integer.MAX_VALUE;
        }

        for(Operator op: applicableOperators){
            calculate(op.apply(state));
        }

        return value;

    }

    public static boolean isAllBadMove(int length, int[] position){
        if((position[0] == length - 2 && position[1] == 1) ||
            position[0] == length - 1 && position[1] == 2 ){
            return true;
        }else{
            return false;
        }

    }
    public static boolean isFinishLine(State state){

        for(Operator op: Operator.getAllApplicableOperators(state)){
            State newState = op.apply(state);
            int[] queenPosition = newState.getQueenPosition();

            if(queenPosition[0] == newState.board.length - 1 ||
                queenPosition[1] == 0 ||
                    (Math.abs(queenPosition[0] - newState.board.length - 1) == Math.abs(queenPosition[1] - 0)) ){
                return true;
            }
        }

        return false;
    }

}
