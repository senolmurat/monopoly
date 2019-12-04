package GameElements.Cards.Chance;

import GameElements.Cards.Card;
import Player.Player;

public class Card2 extends Card {


    public Card2(){
        super.setName("Chance2");
        super.setDescription("Go to Jail. Go directly to Jail. Do not pass GO");
        super.setAltDescription("A truncheon-carrying policeman in a dark-colored uniform hauls a surprised Mr Monopoly along by the feet");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //player.goToJail(9);
    }
}
