package MainGameObjects;

import BoardSquares.BoardSquare;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
    public static Random random;
    public static int die1;
    public static int die2;

    public Dice() {
        random = new Random();
    }

    public static void roll(ArrayList<BoardSquare> gameBoard, Pawn pawn) {

        GameBoard.line_break();

        die1 = random.nextInt(7);
        die2 = random.nextInt(7);

        int roll = die1 + die2;

        System.out.println("You rolled a: \"" + roll + "\"");
        System.out.println("You've landed on: \"" + gameBoard.get(roll).getName() + "\"!");

        pawn.setCurrentPosition(gameBoard.get(roll));
    }

    public static boolean jailRoll() {

        GameBoard.line_break();

        random = new Random();

        die1 = random.nextInt(7);
        die2 = random.nextInt(7);

        System.out.println("You rolled a \"" + die1 + "\"" + " and " + "\"" + die2 + "\"\n" );


        if (die1 == die2) {
            System.out.println("It's your lucky day! You are getting out of jail free:)");
            return true;
        } else {
            System.out.println("Better luck next time:(");
            return false;
        }
    }
}
