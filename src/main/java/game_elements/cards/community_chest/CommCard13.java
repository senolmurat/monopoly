package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard13 extends Card {

    public CommCard13() {
        super.setName("Chest15");
        super.setDescription("You inherit $100.");
        super.setAltDescription("Mr Monopoly. holds his head as unseen people's hands offer brochures titled \"Buy Yacht\", \"World Tour\", and \"Rolls Royce\"");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(100);
    }
}
