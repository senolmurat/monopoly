public class RegularSquare extends Square{

    RegularSquare(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }

    @Override
    public String landedOn() {
        return "(Regular Square)";
    }
}
