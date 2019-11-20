package GameElements.Cards.Chance;

import GameElements.Cards.Card;
import Player.Player;

public class Card4 extends Card {

    public Card4(){
        super.setName("Chance4");
        super.setDescription("Pay poor tax of $15");
        super.setAltDescription("His trouser pockets pulled out to show them empty, Mr. Monopoly spreads his hands");
    }


    @Override
    public void action(Player player) {
        //Decrease player money by 15
    }
}
