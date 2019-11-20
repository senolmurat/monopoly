package Square;

public class JailSquare extends Square {
    public JailSquare(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }

    @Override
    public String landedOn() {
        return "(Jail Square)";
    }
}
