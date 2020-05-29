package com.company;

import java.util.List;

public class MiniMax {

    public static Operator step(State state, Problem p, int rail){
        int max = -1;

        List<Operator> operators = Operator.getAllApplicableOperators(state);

        Operator operator = operators.get(0);
        System.out.println(operators.size());

        for(Operator op: operators){
            state.turn = Turn.Computer;
            if(op.applicable(state)){
                State newState = op.apply(state);

                int val = value(newState, p, rail - 1);

                if(val > max){
                    max = val;
                    operator = new Operator(op);

                }
            }
        }

        return operator;
    }

    public static int value(State state, Problem p, int rail){
        state.swapTurn();
        if(state.isGoal() || rail == 0 ){
            return Heuristica.h(state);
        }else if(state.turn == Turn.Computer){
            int max = Integer.MIN_VALUE;
            for(Operator op: Operator.getAllApplicableOperators(state)){
                if(op.applicable(state)){
                    State newState = op.apply(state);
                    int v = value(newState, p, rail - 1);

                    if(v > max){
                        max = v;
                    }
                }
            }
            return max;
        }else{
            int min = Integer.MAX_VALUE;

            for(Operator op: Operator.getAllApplicableOperators(state)){
                if(op.applicable(state)){
                    State newState = op.apply(state);
                    int v = value(newState, p, rail - 1);

                    if(v < min){
                        min = v;
                    }
                }
            }
            return min;
        }
    }

}
