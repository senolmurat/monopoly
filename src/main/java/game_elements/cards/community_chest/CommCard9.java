package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard9 extends Card {

    public CommCard9() {
        super.setName("Chest9");
        super.setDescription("Receive $25 consultancy fee.");
        super.setAltDescription("As Justice of the Peace, a stern Mr. M holds out his hand for fee from an embarrassed groom whose bride hold a bouquet behind him");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(25);
    }

}
