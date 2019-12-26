package player;

import game_elements.Die;
import lombok.Getter;
import lombok.Setter;
import properties.Hotel;
import properties.House;
import properties.Property;
import square.PropertySquare;
import square.Purchasable;
import square.Square;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
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
    private ArrayList<Purchasable> properties = new ArrayList<>();

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

    public void setPosition(Square position) {
        piece.setPosition(position);
    }

    public Square getPosition() {
        return piece.getPosition();
    }

    public boolean isBankrupt() {
        return money.getMoney() <= 0;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
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

    public int decidingPropertyStage() {
        Random rand = new Random();
        int value = 1 + rand.nextInt(5);

        return value;
    }

    public void addProperty(Purchasable property) {
        properties.add(property);
    }

    public int howManyOfSameColour(String colour) {
        int count = 0;
        for(Purchasable iter : properties) {
            if(iter.getType().equals(colour))
                count ++;
        }
       return count;
    }

    public Property canIBuildPropertie(PropertySquare square) {
        int currentlyBuilded = square.getProperties().size();
        int count = howManyOfSameColour(square.getType());

        if(count == 3) {
            for(Purchasable iter : properties) {
                if(iter.getType().equals(square.getType()))
                    if(!doIHaveEnough(currentlyBuilded, (PropertySquare)iter))
                        return null;
            }
            if(currentlyBuilded != 4)
                return new House();
            else
                return new Hotel();
        }
        else
            return null;
    }

    public boolean doIHaveEnough(int currentlyBuilded, PropertySquare square) {
        if(currentlyBuilded == 5){
            /*for(int i = 0 ; i < currentlyBuilded ; i++){
                System.out.println("builded : " + currentlyBuilded);
                if( (square.getProperties().get(i)) instanceof Hotel )
                    return true;
            }

             */
            if( (square.getProperties().get(4)) instanceof Hotel )
                return true;
        }
        if(square.getProperties().size() == currentlyBuilded)
            return true;
        else
            return false;
    }
}
