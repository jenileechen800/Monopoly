package MainGameObjects;

import BoardSquares.*;

import java.util.*;

public class GameBoard {
    public static ArrayList<BoardSquare> gameBoard;
    private Dice dice;
    private ArrayList<Pawn> pawns;
    HashMap<String, Integer> orderedWinners;

    private Scanner scanner;

    private String nextStringScan = "";
    private String continuePlaying;

    private Go go;
    private int numGameRounds;

    public static void main(String[] args) {
        new GameBoard();
    }

    public GameBoard() {
        dice = new Dice();
        pawns = new ArrayList<>();
        scanner = new Scanner(System.in);
        gameBoard = new ArrayList<>();
        go = new Go();
        orderedWinners = new HashMap<>();

        play_game();
    }


    private void text_introduction() {

        init_game_board();

        System.out.println("\nWelcome to Monopoly! Here you can play one of man-kind's favorite games.");

        instructions();

        choose_num_players();
    }
    private void instructions() {
        line_break();
        System.out.println("Would you like to view the instructions? (y/n)");

        if (scanner.next().equals("y")) {
            line_break();
            System.out.println("Instructions: " +
                    "\n\nEach player takes turns rolling the dice. \nAt each space, you can either: " +
                    "\n 1) buy the property (if unowned)" +
                    "\n 2) pay rent (if owned by another player)" +
                    "\n 3) do nothing, or further options (if owned by you)" +
                    "\n\n At the end of the game>>> The player with the most money wins!");
        }
    }
    private void choose_num_players() {
        line_break();
        System.out.println("To begin, choose a number of players.");

        int numPlayers = scanner.nextInt();

        while (numPlayers <= 0) {
            line_break();
            System.out.println("Please enter an integer number of players greater than 0.");
            numPlayers = scanner.nextInt();
        }

        generatePawns(numPlayers);
    }
    private void choose_num_rounds() {
        line_break();
        System.out.println("How many rounds would you like to play? (Recommended 3 rounds)");

        numGameRounds = scanner.nextInt();

        while (numGameRounds <= 0) {
            line_break();
            System.out.println("Please enter an integer number of players greater than 0.");
            numGameRounds = scanner.nextInt();
        }
    }
    private void generatePawns(int numPawns) {
        for (int i=0; i < numPawns; i++) {
            line_break();

            System.out.println("Enter the player" + i + " name: ");

            nextStringScan = scanner.next();

            pawns.add(new Pawn(nextStringScan, gameBoard.get(0)));

        }
    }
    private void init_game_board() {

        gameBoard.add(go);
        gameBoard.add(new BoardSquare("Mediterranean Avenue", 2, 60, "purple"));
        gameBoard.add(new Community_Chest());
        gameBoard.add(new BoardSquare("Baltic Avenue", 4, 60, "purple"));
        gameBoard.add(new BoardSquare("Income Tax", 200, 0, "none"));
        gameBoard.add(new BoardSquare("Reading Railroad", 25, 200, "black"));
        gameBoard.add(new BoardSquare("Oriental Avenue", 6, 100, "blue"));
        gameBoard.add(new Chance());
        gameBoard.add(new BoardSquare("Vermont Avenue", 6, 100, "blue"));
        gameBoard.add(new BoardSquare("Connecticut Avenue", 8, 120, "blue"));
        gameBoard.add(new Jail());
        gameBoard.add(new BoardSquare("St. Charles Place", 10, 140, "pink"));
        gameBoard.add(new BoardSquare("States Avenue", 10, 140, "pink"));
        gameBoard.add(new BoardSquare("Virginia Avenue", 12, 160, "pink"));
        gameBoard.add(new BoardSquare("Pennsylvania Railroad", 25, 20, "black"));
        gameBoard.add(new BoardSquare("St. James Place", 14, 180, "orange"));
        gameBoard.add(new Community_Chest());
        gameBoard.add(new BoardSquare("Tennessee Avenue", 14, 180, "orange"));
        gameBoard.add(new BoardSquare("New York Avenue", 16, 200, "orange"));
        gameBoard.add(new BoardSquare("Kentucky Avenue", 18, 220, "red"));
        gameBoard.add(new Chance());
        gameBoard.add(new BoardSquare("Indiana Avenue", 18, 220,"red"));
        gameBoard.add(new BoardSquare("Illinois Avenue", 20, 240, "red"));
        gameBoard.add(new BoardSquare("B. & O. Railroad", 25, 200, "black"));
        gameBoard.add(new BoardSquare("Atlantic Avenue", 22, 260, "yellow"));
        gameBoard.add(new BoardSquare("Ventor Avenue", 22, 260, "yellow"));
        gameBoard.add(new BoardSquare("Marvin Gardens", 24, 280, "yellow"));
        gameBoard.add(new BoardSquare("Pacific Avenue", 26, 300, "green"));
        gameBoard.add(new BoardSquare("North Carolina Avenue", 26, 300, "green"));
        gameBoard.add(new Community_Chest());
        gameBoard.add(new BoardSquare("Pennsylvania Avenue", 28, 320, "green"));
        gameBoard.add(new BoardSquare("Short Line Railroad", 25, 200, "black"));
        gameBoard.add(new Chance());
        gameBoard.add(new BoardSquare("Park Place", 35, 350, "dark blue"));
        gameBoard.add(new BoardSquare("Luxury Tax", 100, 0, "none"));
        gameBoard.add(new BoardSquare("Boardwalk", 50, 400, "dark blue"));
    }

    private void play_game() {
        text_introduction();

        roll_and_play();

        while (play_again()) {
            go.setNumTimesPassed(0);
            roll_and_play();
        }
    }

    private boolean play_again() {
        line_break();
        System.out.println("Thank you so much for playing our game. Would you like to play again? (\"y\"/\"n\")");

        continuePlaying = scanner.next();

        if (continuePlaying.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
    private void roll_and_play() {
        choose_num_rounds();

        line_break();
        System.out.println("Ready...Set...Monopoly!");

        while (go.getNumTimesPassed(pawns) < numGameRounds) { //plays game until 'go' has been passed 20 times]

            line_break();
            System.out.println("ROUND " + go.getNumTimesPassed(pawns));
            line_break();

            for (Pawn p : pawns) {
                if (p.isInJail()) {
                    p.jailTurn();
                } else {
                    p.turn(gameBoard, pawns);
                }
            }

            go.passGo();
        }
        declareWinner();
    }


    public static void line_break() {
        System.out.println("------------------------------------------------");
    }

    private void declareWinner() {
        Iterator<Pawn> pawnIterator = pawns.iterator();
        Pawn winner = pawnIterator.next();
        Pawn nextPawn;

        while(pawnIterator.hasNext()) {
            nextPawn = pawnIterator.next();
            if (winner.getBankAccount() < nextPawn.getBankAccount()) {
                winner = nextPawn;
            }
        }
        line_break();
        System.out.println("\n\n**************************************************");
        System.out.println("Your monopoly game has ended, thank you for playing.");
        System.out.println("\"" + winner.getPlayerName() + "\""
                + " has WON THE GAME!" +
                "\n\nThe winning total was: $" + winner.getBankAccount());
        System.out.println("**************************************************\n\n");

        rankPawns();
    }

    private void rankPawns() {
        // dictionary of pawn names(value) + bank accounts (key)
        for (Pawn pawn: pawns) {
            pawnsReference.put(pawn.getBankAccount(), pawn.getPlayerName());
            pawnsBankAccounts.add(pawn.getBankAccount());
        }

        Collections.sort(pawnsBankAccounts); // order from high to low

        for (Integer bankAccount: pawnsBankAccounts) {
            orderedWinningPawns.put(pawnsReference.get(bankAccount), bankAccount);
        }

        printWinningPawns(orderedWinningPawns);
    }

    private void printWinningPawns(HashMap<String, Integer> orderedWinners) {
        int rank = 1;

        System.out.println("The ranking is: ");
        for (String pawn: orderedWinners.keySet()) {
            System.out.println(rank + ". " + pawn + " ($" + orderedWinners.get(pawn) + ")");
        }
    }

}
