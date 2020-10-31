package square;

import player.Player;

public class GoToJail extends Square {

    private Square jail;

    public GoToJail(String name, int position, Square jail) {
        super.setName(name);
        super.setPosition(position);
        this.jail = jail;
    }


    @Override
    public void squareAction(Player player) {
        player.goToJail(jail);
    }

    @Override
    public String landedOn() {
        return "Go To Jail Square";
    }
}
