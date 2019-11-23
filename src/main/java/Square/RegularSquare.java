package Square;

import Player.Player;

public class RegularSquare extends Square {

    public RegularSquare(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }


    @Override
    public void squareAction(Player player) {

    }

    @Override
    public String landedOn() {
        return "Regular Square";
    }
}
