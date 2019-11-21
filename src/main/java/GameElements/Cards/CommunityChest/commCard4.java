package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;


public class commCard4 extends Card {

    public commCard4() {
        super.setName("Chest4");
        super.setDescription("From sale of stock you get $30");
        super.setAltDescription("MMr. Monopoly, happily entangled in the tape of a stock ticker, waves cash");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //add money to player 30
    }


}
