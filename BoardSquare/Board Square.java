package BoardSquares;

import MainGameObjects.GameBoard;
import MainGameObjects.Pawn;

import java.util.ArrayList;

public class BoardSquare {
    private String color;
    private String name;
    private int rent;
    private boolean owned;
    private int sellPrice;
    private ArrayList<Pawn> pawnsContained;
    private int houses;
    private int hotels;

    public BoardSquare() {}

    public BoardSquare(String name, int rent, int sellPrice, String color) {
        this.color = color;
        this.name = name;
        this.rent = rent;
        this.sellPrice = sellPrice;
        hotels = 0;
        houses = 0;

        owned = false;
        pawnsContained = new ArrayList<>();
    }

    public int getHouses() {
        return houses;
    }

    public void addHouse(Pawn pawn) {
        GameBoard.line_break();
        if (houses < 4) {
            this.houses += 1;
            System.out.println("You've added a house to " + getName());
        } else if (hotels < 4) { // case where houses >= 4
            houses = 0; // reset houses
            hotels++; // add hotel
            System.out.println("You're at max houses, adding another house created a new hotel to " + getName());
        } else {
            System.out.println("You've reached the max (4)" +
                    "houses and hotels you can buy on a give property :)");
        }
    }

    public int getHotels() {
        return hotels;
    }

    public void addHotel(Pawn pawn) {
        GameBoard.line_break();
        if (hotels < 4) {
            this.hotels += 1;
            System.out.println("You've added a hotel to " + getName());
        } else {
            System.out.println("You've reached the max(4) " +
                    "houses and hotels you can buy on a give property :)");
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getRent() {
        //houses is 1/4(#) * rent
        //hotels is (#) * rent
        return rent * (hotels + (.25 * houses));
    }
    public void setRent(int rent) {
        this.rent = rent;
    }

    public boolean isOwned() {
        return owned;
    }
    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public int getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public ArrayList<Pawn> getPawnsContained() {
        return pawnsContained;
    }
    public void setPawnsContained(ArrayList<Pawn> pawnsContained) {
        this.pawnsContained = pawnsContained;
    }
}
