package Square;

import Player.Player;

public class JailSquare extends Square {
    public JailSquare(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }


    @Override
    public void squareAction(Player player) {
        //just visiting

    }

    @Override
    public String landedOn() {
        return "Jail Square";
    }
}
