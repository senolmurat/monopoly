package player;

import game_elements.Board;
import game_elements.Die;
import lombok.Getter;
import lombok.Setter;
import properties.Hotel;
import properties.House;
import properties.Property;
import square.*;
import main.Game;

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

    public void addProperty(Purchasable property) {
        properties.add(property);
    }

    public int howManyOfSameColour(String colour) {
        int count = 0;
        for (Purchasable iter : properties) {
            if (iter.getType().equals(colour))
                count++;
        }
        return count;
    }

    private int totalColour(String color) {
        int count = 0;

        Board board = Game.instance().getBoard();
        for (Square square : board.getBoard()) {
            if (square instanceof Purchasable) {
                if (((Purchasable) square).getType().equals(color))
                    count++;
            }
        }
        return count;
    }

    public Property canIBuildPropertie(PropertySquare square) {
        int currentlyBuilded = square.getProperties().size();
        int count = howManyOfSameColour(square.getType());

        if (currentlyBuilded == 1 && square.getProperties().get(0) instanceof Hotel)
            return null;

        if (count == totalColour(square.getType())) {
            for (Purchasable iter : properties) {
                if (iter.getType().equals(square.getType()))
                    if (!doIHaveEnough(currentlyBuilded, (PropertySquare) iter))
                        return null;
            }
            if (currentlyBuilded != 4)
                return new House();
            else
                return new Hotel();
        } else
            return null;
    }

    public boolean doIHaveEnough(int currentlyBuilded, PropertySquare square) {
        if (currentlyBuilded == 0)
            return true;
        else if (square.getProperties().size() == 0)
            return false;
        else if ((square.getProperties().get(0)) instanceof Hotel)
            return true;
        else if (square.getProperties().size() == currentlyBuilded)
            return true;
        else
            return false;
    }

    public boolean isMortgage(){

        boolean isMortgage = false;

            isMortgage = communitySquareSelling();
            if(isMortgage)
                return true;
            isMortgage = utilitySquareSelling();
            if (isMortgage)
                return true;
            isMortgage = hotelSelling();
            if (isMortgage)
                return true;
            for(Purchasable square : properties){
                if(((PropertySquare) square).getProperties().size() != 0) {
                    isMortgage = houseSelling();
                    if(isMortgage){
                        return true;
                    }
                }
            }
            isMortgage = propertySquareSelling();
            if(isMortgage)
                return true;
            return false;

    }

    private boolean hotelSelling() {
        for (int i = 0; i<properties.size();i++) {
            Purchasable square = properties.get(i);
            if (!(square instanceof PropertySquare))
                continue;
            if (((PropertySquare) square).getProperties().size() == 0)
                return false;
            if (((PropertySquare) square).getProperties().get(0) instanceof Hotel) {
                ((PropertySquare) square).getProperties().clear();
                for (int j = 0; j < 4; j++) {
                    ((PropertySquare) square).getProperties().add(new House());
                }
                money.addMoney(((PropertySquare) square).getBuildingPrice() / 2);
                i--;
                if (!isBankrupt()) {
                    setBankrupt(false);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean houseSelling() {
        for (int i = 0; i<properties.size();i++) {
            Purchasable square = properties.get(i);
            if (!(square instanceof PropertySquare))
                continue;
            if(((PropertySquare) square).getProperties().size() == 0)
                return false;
            for (int j = ((PropertySquare) square).getProperties().size() - 1; j >= ((PropertySquare) square).getProperties().size() - 1; j--) {
                ((PropertySquare) square).getProperties().remove(j);
                money.addMoney(((PropertySquare) square).getBuildingPrice() / 2);
                if (!isBankrupt()) {
                    setBankrupt(false);
                    return true;
                }
                i--;
            }
        }
        return false;
    }

    private boolean communitySquareSelling() {
        for (int i = 0; i<properties.size(); i++) {
            Purchasable square = properties.get(i);
            if (!(square instanceof CommunitySquare))
                continue;
            money.addMoney(square.getLandValue() / 2);
            square.setOwner(null);
            properties.remove(square);
            i--;
            if (!isBankrupt()) {
                setBankrupt(false);
                return true;
            }
        }
        return false;
    }

    private boolean utilitySquareSelling() {
        for(int i= 0; i<properties.size();i++){
            Purchasable square = properties.get(i);
            if (!(square instanceof UtilitySquare))
                continue;
            money.addMoney(square.getLandValue() / 2);
            square.setOwner(null);
            properties.remove(square);
            i--;
            if (!isBankrupt()) {
                setBankrupt(false);
                return true;
            }
        }
        return false;
    }

    private boolean propertySquareSelling() {
        for (int i = 0; i < properties.size(); i++) {
            Purchasable square = properties.get(i);
            if (!(square instanceof PropertySquare))
                continue;
            money.addMoney(square.getLandValue() / 2);
            square.setOwner(null);
            properties.remove(square);
            i--;
            if (!isBankrupt()) {
                setBankrupt(false);
                return true;
            }
        }
        return false;
    }
}
