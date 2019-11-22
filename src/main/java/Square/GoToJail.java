package Square;

import Player.Player;

public class GoToJail extends Square {
    public GoToJail(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }


    @Override
    public void squareAction(Player player) {
        player.setPosition(10);
    }

    @Override
    public String landedOn() {
        return "(Go To Jail)";
    }
}
