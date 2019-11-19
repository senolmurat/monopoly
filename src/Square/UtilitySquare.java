package Square;

public class UtilitySquare extends Square {

    private String type;
    private int landValue;
    private int rent;
    private int owner;//player index in players array

    private UtilitySquare() {
    }

    public UtilitySquare(String name, int position, String type, int landValue, int rent) {
        super.setName(name);
        super.setPosition(position);
        this.type = type;
        this.landValue = landValue;
        this.rent = rent;
        this.owner = -1;//default , no owner
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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
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
        return getName();
    }
}
