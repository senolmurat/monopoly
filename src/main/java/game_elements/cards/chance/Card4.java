package game_elements.cards.chance;

import game_elements.cards.Card;
import player.Player;

public class Card4 extends Card {

    public Card4(){
        super.setName("Chance4");
        super.setDescription("Pay poor tax of $15");
        super.setAltDescription("His trouser pockets pulled out to show them empty, Mr. Monopoly spreads his hands");
    }


    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().subtractMoney(15);
    }
}
