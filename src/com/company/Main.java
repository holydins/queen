package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int size = setSize();
        int[] queenPosition = getStartPositionOfQueen(size);

        Problem problem = new Problem();

        State state;

        Scanner scanner = new Scanner(System.in);
        int value;

        System.out.println("A játék menete során meg kell adjon minden egyes körben egy irányt, illetve annak mértékét");

        System.out.println("Ha ön szeretne kezdeni, kérem adja meg a 0 értéket.");
        System.out.println("Egyébként bármely különböző értéket megadhatja.");
        System.out.print("Kérem nyomjon meg egy billentyűt: ");

        try{
            value = scanner.nextInt();
        }catch(Exception e){
            value = 1;
        }

        if(value == 0){
            state = new State(size, queenPosition, Turn.Player);
            System.out.println("A játékot a játékos kezdi.");
        }else{
            state = new State(size, queenPosition, Turn.Computer);
            System.out.println("A játékot a számítógép kezdi.");
        }
        System.out.println("A kezdő állapot");
        state.toString();

        while(true){
            Operator operator = null;
            do{
                if(size == 1){
                    break;
                }
                switch(state.turn){
                    case Computer:
                        operator = MiniMax.step(new State(state), problem, 1);
                        if(operator.applicable(state)){
                            System.out.println("A gép által választott operátor: " + operator.toString() + ".");
                        }
                        System.out.println("######################################");
                        System.out.println(state.turn);
                        System.out.println("######################################");
                        state.turn = Turn.Computer;
                        break;
                    case Player:
                        System.out.println("Állapot:");

                        String direction;
                        while(true){
                            System.out.print("Kérem adja meg az irányt (bal, le, átló): ");
                            direction = String.valueOf(scanner.next());
                            if(direction.compareTo("bal") == 0 || direction.compareTo("le") == 0 || direction.compareTo("átló") == 0){
                                break;
                            }else{
                                System.out.println("Rossz irányt adott meg, a felvehető értékek: bal, le és átló");
                            }
                        }
                        int valueOfStep;
                        while(true){
                            System.out.print("Adja meg a lépés nagyságát: ");
                            try{
                                valueOfStep = scanner.nextInt();
                            }catch (Exception e){
                                System.out.println("Rossz értéket adott meg, így alapértelmezetten az 1 mértéket vettem fel.");
                                valueOfStep = 1;
                            }
                            operator = new Operator(setDirectionValue(direction), valueOfStep);

                            if(!operator.applicable(state) || valueOfStep < 1){
                                System.out.println("Nem alkalmazható, mert nem megfelelő méretű lépést adott meg.");
                            }else{
                                break;
                            }
                        }


                        System.out.println("######################################");
                        System.out.println(state.turn);
                        System.out.println("######################################");
                        break;
                }
            }while(!operator.applicable(state));

            if(size == 1){
                break;
            }

            state = operator.apply(state);

            if(state.isGoal()){
                break;
            }

            state.swapTurn();


            state.toString();
        }

        System.out.println("A GYŐZTES " + state.turn);
        state.toString();

    }

    public static int setSize(){

        return (int) (Math.random() * 10) + 1;
    }

    /**
     * It return the exact position of quuen as a pair.
     * @return [x, y] pair.
     */
    public static int[] getStartPositionOfQueen(int N){
        int x,y;
        boolean isFirstLine = Math.random() < 0.5;
        if(isFirstLine){
            System.out.println("első sor");
            x = 0;
            y = (int) (Math.random() * N);
        }else{
            System.out.println("oldalsó sor");
            x = (int) (Math.random() * N);
            y = N - 1;
        }
        return new int[]{x, y};
    }

    /**/



    public static int setDirectionValue(String direction){
        switch (direction){
            case "bal":
                return -1;
            case "átló":
                return 0;
            case"le":
                return 1;
            default:
                return -2;
        }
    }
}
