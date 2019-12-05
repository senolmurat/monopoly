package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard5 extends Card {

    public CommCard5() {
        super.setName("Chest5");
        super.setDescription("Go to Jail. Go directly to Jail. Do not pass GO");
        super.setAltDescription("A truncheon-carrying policeman in a dark-colored uniform hauls a surprised Mr Monopoly along by the feet");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //player.goToJail(9);
    }
}
