package Square;

public class PropertySquare extends Square {

    private int landValue;
    private int rent;
    private int owner;//player index in players array

    private PropertySquare(){
    }

    public PropertySquare(String name ,int position , int landValue , int rent){
        super.setName(name);
        super.setPosition(position);
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

    @Override
    public String landedOn() {
        return "(Property Square)";
    }
}
