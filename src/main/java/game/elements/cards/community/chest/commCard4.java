package game.elements.cards.community.chest;

import game.elements.cards.Card;
import player.Player;


public class commCard4 extends Card {

    public commCard4() {
        super.setName("Chest4");
        super.setDescription("From sale of stock you get $30");
        super.setAltDescription("Mr. Monopoly, happily entangled in the tape of a stock ticker, waves cash");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(30);
    }


}
