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
            if(op.apply(state).isGoal()){
                value -= 1000;
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

}
