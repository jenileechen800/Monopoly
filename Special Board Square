package BoardSquares;

import MainGameObjects.GameBoard;
import MainGameObjects.Pawn;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class SpecialBoardSquares extends BoardSquare {
    protected String name;
    protected String cardDescription;
    protected Integer cardAmt;
    protected Integer cardMovement;

    protected ArrayList<String> positiveMessages;
    protected ArrayList<String> negativeMessages;
    protected ArrayList<Pair<String, Integer>> moveMessages;

    protected Random random;
    protected Pair<String, Integer> drawnCard;

    public SpecialBoardSquares() {
        random = new Random();
        positiveMessages = new ArrayList<>();
        negativeMessages = new ArrayList<>();

        initMessages();
    }

    public void drawRandomCard(Pawn pawn) {
        if (Math.random() < 0.3) {
            drawPositiveCard(pawn);
        } else if (Math.random() < 0.6) {
            drawNegativeCard(pawn);
        } else {
            drawMoveCard(pawn);
        }
    }

    public void initMessages() {
        initPositiveMessages();
        initNegativeMessages();
        initMoveMessages();
    }

    public void initPositiveMessages() {
        positiveMessages.add("\"You inherit:\" $");
        positiveMessages.add("\"Receive from services:\" $");
        positiveMessages.add("\"Xmas fund matures. Collect\" $");
        positiveMessages.add("\"From sale of stock you get:\"  $");
        positiveMessages.add("\"Income tax refund. Collect:\"  $");
    }

    public void initNegativeMessages() {
        negativeMessages.add("\"Pay school tax of:\" $");
        negativeMessages.add("\"Doctor's Fee. Pay:\" $");
        negativeMessages.add("\"Pay hospital:\" $");
        negativeMessages.add("\"Pay poor tax of:\" $");
        negativeMessages.add("\"Pay county tax of:\" $");
    }

    public void initMoveMessages() {
//        moveMessages.add(new Pair<>("Move forward 4 spaces", 0));
//        moveMessages.add(new Pair<>("Pass Go!", 0));
//        moveMessages.add(new Pair<>("Advance to the nearest railroad", 0));
    }


    public void drawPositiveCard(Pawn pawn) {
        cardDescription = positiveMessages.get(random.nextInt(5));
        cardAmt = random.nextInt(10) * 100;
        drawnCard = new Pair(cardDescription, cardAmt);

        pawn.addToBankAccount(cardAmt, "Community Chest/Chance Card");

        System.out.println("You've drawn>>> " + cardDescription + cardAmt);
    }

    public void drawNegativeCard(Pawn pawn) {
        cardDescription = negativeMessages.get(random.nextInt(5));
        cardAmt = -random.nextInt(10) * 100;
        drawnCard = new Pair(cardDescription, cardAmt);

        pawn.subtractFromBankAccount(cardAmt, "Community Chest/Chance Card");

        System.out.println("You've drawn>>> " + cardDescription + cardAmt);
    }

    public void drawMoveCard(Pawn pawn) {
        drawnCard = moveMessages.get(random.nextInt(3));
        cardDescription = drawnCard.getKey();
        cardMovement = drawnCard.getValue();
        pawn.setCurrentPosition(GameBoard.gameBoard.get(cardMovement));

        System.out.println("You've drawn>>> " + cardDescription);
    }

    public static void main(String[] args) {
        SpecialBoardSquares specialBoardSquares = new SpecialBoardSquares();
        specialBoardSquares.drawMoveCard(new Pawn("name", new BoardSquare()));
    }
}
