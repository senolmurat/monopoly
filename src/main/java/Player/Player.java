package Player;

import GameElements.Board;
import GameElements.Dice;
import Square.Square;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private int numberOfTurn; //Number of turns each player played
    private int id;
    private String name;
    private boolean bankrupt = false; //Is player bankrupt or not.
    private Money money;
    private int doubleDiceCounter = 0; //Number of double dices in a row
    private int[] tossedFaces;
    private Piece piece;
    private int firstRoll;
    private boolean inJail = false;
    private int jailCounter = 0;
    private ArrayList<Square> properties = new ArrayList<Square>();

    private Player() {

    }

    public Player(int id, String name, int startingMoney, Dice dice) {
        this.id = id;
        this.name = name;
        this.money = new Money(startingMoney);
        piece = new Piece();
        firstRoll = tossDie(dice);
    }

    public int tossDie(Dice dice) {
        dice.setDouble(false); //Set the isDouble value to "false" for every dice before tossing
        tossedFaces = dice.getFaces();

        if (dice.isDouble()) {
            doubleDiceCounter++;
        }

        int sumOfFaces = dice.getTotalFaces();

        return sumOfFaces;
    }

    public int getNumberOfTurn() {
        return numberOfTurn;
    }

    public void setNumberOfTurn(int numberOfTurn) {
        this.numberOfTurn = numberOfTurn;
    }

    public void setPosition(int position) {
        piece.setPosition(position);
    }

    public int getPosition() {
        return piece.getPosition();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    public boolean isBankrupt() {
        return money.getMoney() <= 0;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public int getDoubleDiceCounter() {
        return doubleDiceCounter;
    }

    public void setDoubleDiceCounter(int doubleDiceCounter) {
        this.doubleDiceCounter = doubleDiceCounter;
    }

    public int[] getTossedFaces() {
        return tossedFaces;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getJailCounter() {
        return jailCounter;
    }

    public void setJailCounter(int jailCounter) {
        this.jailCounter = jailCounter;
    }

    public void goToJail(int position) {
        setPosition(position);
        setInJail(true);
        setJailCounter(0);
    }

    public void freeToGo() {
        setInJail(false);
        setJailCounter(0);
        setDoubleDiceCounter(0);
    }

    public boolean decidingToBuy() {
        Random rand = new Random();
        int value = 1 + rand.nextInt(100);

        if(value > 80) {
            return true;
        }
        else
            return false;
    }

    public void addPropertie(Square propertie) {
        properties.add(propertie);
    }
    public ArrayList<Square> getProperties() { return properties;}
}
