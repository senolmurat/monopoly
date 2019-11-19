package GameElements.Cards.Chance;

import GameElements.Cards.Card;
import Player.Player;

public class Card3 extends Card {

    public Card3(){
        super.setName("Chance3");
        super.setDescription("Make general repairs on all your property: For each house pay $25" /*For each hotel pay $100 */);
        super.setAltDescription("Consulting a \"How to Fix It\" brochure, a hammer-wielding Mr. Monopoly sits astride a house not much larger than he is; it buckles under his weight");
    }

    @Override
    public void action(Player player) {
        //decrease player money for numberOfProperty * 25
    }
}
