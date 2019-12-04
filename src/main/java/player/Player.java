package player;

import game.elements.Die;
import square.Square;

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
    private ArrayList<Square> properties = new ArrayList<>();

    private Player() {

    }

    public Player(int id, String name, int startingMoney, Die die) {
        this.id = id;
        this.name = name;
        this.money = new Money(startingMoney);
        piece = new Piece();
        firstRoll = tossDie(die);
    }

    public int tossDie(Die die) {
        die.setDouble(false); //Set the isDouble value to "false" for every dice before tossing
        tossedFaces = die.getFaces();

        return die.getTotalFaces();
    }

    public int getNumberOfTurn() {
        return numberOfTurn;
    }

    public void setNumberOfTurn(int numberOfTurn) {
        this.numberOfTurn = numberOfTurn;
    }

    public void setPosition(Square position) {
        piece.setPosition(position);
    }

    public Square getPosition() {
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

    public void goToJail(Square position) {
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

        return value > 80;
    }

    public void addProperty(Square property) {
        properties.add(property);
    }
    public ArrayList<Square> getProperties() { return properties;}
}
