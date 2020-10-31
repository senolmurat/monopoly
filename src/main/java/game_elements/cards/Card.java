package game_elements.cards;
import player.Player;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public abstract class Card {
    private String name;
    private String description;
    private String altDescription;

    public Card(){
    }

    public abstract void action(Player player, Player[] playerArray);
}
