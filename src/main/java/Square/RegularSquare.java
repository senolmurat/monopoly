package Square;

public class RegularSquare extends Square {

    public RegularSquare(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }

    @Override
    public String landedOn() {
        return "(Regular Square)";
    }
}
