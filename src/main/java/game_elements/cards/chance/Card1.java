package game_elements.cards.chance;

import game_elements.cards.Card;
import player.Player;

public class Card1 extends Card {

    public Card1(){
        super.setName("Chance1");
        super.setDescription("Advance to \"GO\"");
        super.setAltDescription("Mr. Monopoly hops on both feet.");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
       //player.setPosition(1);
    }
}
