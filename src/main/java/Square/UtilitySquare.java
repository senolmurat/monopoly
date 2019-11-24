package Square;

import Player.Player;

public class UtilitySquare extends Square {

    private String type;
    private int landValue;
    private int rent;
    private Player owner;//player index in players array

    private UtilitySquare() {
    }

    @Override
    public void squareAction(Player player) {
        player.getMoney().subtractMoney(this.rent);
    }

    public UtilitySquare(String name, int position, String type, int landValue, int rent) {
        super.setName(name);
        super.setPosition(position);
        this.type = type;
        this.landValue = landValue;
        this.rent = rent;
        this.owner = null;//default , no owner
    }

    public int getLandValue() {
        return landValue;
    }

    public void setLandValue(int landValue) {
        this.landValue = landValue;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String landedOn() {
        String ret = "";
        ret = ret.concat(getName());
        if (getOwner() != null)
            ret = ret + " (Owner: " + getOwner().getName()+ ")";
        return ret;
    }
}
