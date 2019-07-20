package MainGameObjects;

import BoardSquares.*;

import java.util.*;

public class Pawn {
    private String playerName;
    private BoardSquare currentPosition;
    private ArrayList<BoardSquare> ownedProperties;
    private int bankAccount;
    private ArrayList<String> transactions;
    private Scanner scanner;

    public static boolean inJail;
    private int getOutOfJailCards;

    public Pawn(String playerName, BoardSquare startingPosition) {
        this.scanner = new Scanner(System.in);
        this.playerName = playerName;
        this.currentPosition = startingPosition;
        inJail = false;
        getOutOfJailCards = 0;

        ownedProperties = new ArrayList<>();
        transactions = new ArrayList<>();
        bankAccount = 1000;

    }

    public void setJailAttribute(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void addJailCard() {
        getOutOfJailCards++;
    }

    //Attributes
    public BoardSquare getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(BoardSquare currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    //Bank Account
    public int getBankAccount() {
        return bankAccount;
    }

    public void addToBankAccount(double amount, String reason) {
        this.bankAccount += amount;
        addTransaction("Added: $" + amount + " | " + reason);
    }

    public void subtractFromBankAccount(double amount, String reason) {
        this.bankAccount -= amount;
        addTransaction("Subtracted: $" + amount + " | " + reason);
    }

    //Properties
    public ArrayList<BoardSquare> getOwnedProperties() {
        return ownedProperties;
    }

    public ArrayList<String> printOwnedProperties() {
        ArrayList<String> ownedPropertyNames = new ArrayList<>();
        for (BoardSquare property : ownedProperties) {
            ownedPropertyNames.add(property.getName());
        }
        return ownedPropertyNames;
    }

    public void addNewOwnedProperty(BoardSquare property) {
        this.ownedProperties.add(property);
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public void getTransactions() {

        System.out.println("\nTransactions: ");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    //Actions
    public void pay_rent(ArrayList<Pawn> pawns) {
        BoardSquare currentPosition = getCurrentPosition();
        double rent = currentPosition.getRent();

        subtractFromBankAccount(rent, "Paying Rent for " + currentPosition.getName());
        for (Pawn p : pawns) {
            if (p.getOwnedProperties().contains(currentPosition)) {
                p.addToBankAccount(currentPosition.getRent(), "Receiving Rent for " + currentPosition.getName());

                System.out.println(this.playerName + " paid $" + rent + " of rent to " + p);
            }
        }
    }

    public void buy_property() {
        BoardSquare currentPosition = getCurrentPosition();

        GameBoard.line_break();
        System.out.println("Would you like to buy this property? (\"y\"/\"n\")");

        if (scanner.next().equals("y")) {
            subtractFromBankAccount(currentPosition.getSellPrice(), "Purchased " + currentPosition.getName());
            this.ownedProperties.add(currentPosition);
            currentPosition.setOwned(true);
            System.out.println("You now own: \"" + currentPosition.getName() + "\"");
        }
    }

    private void tier_2_turn_options() {
        String playerChoice = "";

        while (!playerChoice.equals("x")) {

            GameBoard.line_break();
            System.out.println("Further player options: "
                    + "\n- View bank account(b)?"
                    + "\n- View transaction history(t)?"
                    + "\n- View properties(p)?"
                    + "\n- Manage houses or hotels(h)?"
                    + "\n- End turn(x)?");

            playerChoice = scanner.next();

            switch (playerChoice) {
                case "b":
                    System.out.println("Your current balance is: $" + bankAccount);
                    break;

                case "t":
                    getTransactions();
                    break;

                case "p":
                    System.out.println("Your owned properties are: " + printOwnedProperties());
                    break;

                case "h":
                    manageHousesAndHotels();
                    break;
            }
        }
    }

//    public static void main(String[] args) {
//        Pawn pawn = new Pawn("name", new BoardSquare());
//        ArrayList<Pawn> pawns = new ArrayList<>();
//        pawns.add(pawn);
//
//        pawn.tier_1_turn_options(new ArrayList<>());
//
//    }

    public void tier_1_turn_options(ArrayList<Pawn> pawns) {
        BoardSquare currentPosition = getCurrentPosition();

        if (currentPosition.getName().equals("Community Chest") || currentPosition.getName().equals("Chance")) {
            SpecialBoardSquares specialBoardSquares = (SpecialBoardSquares) currentPosition;

            specialBoardSquares.drawRandomCard(this);
        } else if (currentPosition.getName().equals("Go")) {
            System.out.println("Collect $200");
            addToBankAccount(200, "Passed Go");
        } else if (currentPosition.getName().equals("Jail")) {
            jailTurn();
        } else if (currentPosition.getName().equals("Income Tax") || currentPosition.getName().equals("Luxury Tax")) {
            subtractFromBankAccount(currentPosition.getRent(), "Tax");
            System.out.println("\nSorry:( but you will pay: $-"+ currentPosition.getRent());
        }

        else if (currentPosition.isOwned()) {
            pay_rent(pawns);
        } else if (!getOwnedProperties().contains(currentPosition)) {
            buy_property();
        }

        tier_2_turn_options();
    }

    public void turn(ArrayList<BoardSquare> gameBoard, ArrayList<Pawn> pawns) {
        GameBoard.line_break();
        System.out.println("Player \"" + getPlayerName() + "\" it is your turn! Type \"roll\" to roll the die.");

        if (scanner.next().equals("roll")) {
            Dice.roll(gameBoard, this);
            tier_1_turn_options(pawns);
        }
    }

    public void jailTurn() {
        GameBoard.line_break();
        System.out.println("You are in jail :(\n");


        if (getOutOfJailCards >= 1) {
            System.out.println("Would you like to apply a 'Get Out of Jail Card' or 'roll doubles' this turn?" +
                    "\n(C) Card" +
                    "\n(D) Doubles");

            if (scanner.next().equals("C")) {
                System.out.println("\nYou have applied a 'Get Out of Jail Card'. Enjoy Freedom While It Lasts:)");
                getOutOfJailCards--;
                setJailAttribute(false);
            }
        }

        System.out.println("Roll doubles to get out of jail! \nType \"roll\" to roll -> wish for the best!");

        GameBoard.line_break();
        if (scanner.next().equals("roll")) {
            if (Dice.jailRoll()) {
                setJailAttribute(false);
            } else {
                setJailAttribute(true);
            }
        }
    }

    public void manageHousesAndHotels() {
        BoardSquare focusProperty = displayHousingInfo();
        addHousesAndHotels(focusProperty);

    }

    public void addHousesAndHotels(BoardSquare focusProperty) {
        if (focusProperty.getHotels() < 4 || focusProperty.getHouses() < 4);

        String houseDecision = "";

        while (!houseDecision.equals("x")) {
            GameBoard.line_break();
            System.out.println("Would you like to add another house (hs) or hotel (ht) or quit (x)?");

            houseDecision = scanner.next();

            if (houseDecision.equals("hs")) {
                focusProperty.addHouse(this);
            } else if (houseDecision.equals("ht")) {
                focusProperty.addHotel(this);
            }
        }
    }

    public BoardSquare displayHousingInfo() {
        GameBoard.line_break();
        System.out.println("All owned properties: \n");
        for (int i=0; i<ownedProperties.size(); i++) {
            System.out.println("" + (i + 1) + ". " + ownedProperties.get(i).getName());
        }
        GameBoard.line_break();
        System.out.println("Please write the number of the property you would like to manage hotels and houses on.");

        BoardSquare focusProperty = ownedProperties.get(scanner.nextInt() - 1);

        GameBoard.line_break();
        System.out.println("" + focusProperty.getName() + " contains " + focusProperty.getHouses() + " houses and " + focusProperty.getHotels() + " hotels");

        return focusProperty;
    }
}
