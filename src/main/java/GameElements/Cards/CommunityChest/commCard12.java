package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard12 extends Card {

    public commCard12() {
        super.setName("Chest12");
        super.setDescription("Receive $25 consultancy fee.");
        super.setAltDescription("As Justice of the Peace, a stern Mr. M holds out his hand for fee from an embarrassed groom whose bride hold a bouquet behind him");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //increase player money by 25
    }
}
