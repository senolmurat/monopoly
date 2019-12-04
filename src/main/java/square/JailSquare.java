package square;

import game.elements.Die;
import player.Player;

public class JailSquare extends Square {

    public JailSquare(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }


    @Override
    public void squareAction(Player player) {
        //just visiting
    }

    public void squareAction(Player player, int passedTurnInJail, Die die) {
        int maxWaitingTime = 3;
        if(passedTurnInJail >= maxWaitingTime - 1) {
            player.setInJail(false);
            player.setJailCounter(0);
        }

        else {
            player.tossDie(die);
            if(player.getDoubleDiceCounter() > 0)
                player.freeToGo();
            else
                player.setJailCounter(passedTurnInJail + 1);
        }
    }

    @Override
    public String landedOn() {
        return "Jail Square";
    }
}
