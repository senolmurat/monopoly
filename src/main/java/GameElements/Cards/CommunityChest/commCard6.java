package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard6 extends Card {

    public commCard6() {
        super.setName("Chest6");
        super.setDescription("Grand Opera Night. Collect $50 from every player for opening night seats.");
        super.setAltDescription("A wall sign near steps reads \"Opera Tonight - 8 PM Sharp\"; Mr. Monopoly leans against it checking his pocket watch in annoyance");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //decrase player money by 50 , ncrease all other players money by 50
    }
}
