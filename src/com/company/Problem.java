package com.company;

import java.util.ArrayList;
import java.util.List;

public class Problem {

    static List<Operator> OPERATORS = new ArrayList<>();

    static{
        //OPERATORS = Main.getAllApplicableOperators();
    }

    public List<Operator> getOperators(){
        return this.OPERATORS;
    }

}
