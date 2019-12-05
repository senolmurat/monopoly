package game_elements.cards.community.chest;

import game_elements.cards.Card;
import player.Player;

public class commCard11 extends Card {

    public commCard11() {
        super.setName("Chest11");
        super.setDescription("Receive $25 consultancy fee.");
        super.setAltDescription("As Justice of the Peace, a stern Mr. M holds out his hand for fee from an embarrassed groom whose bride hold a bouquet behind him");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(25);
    }

}
