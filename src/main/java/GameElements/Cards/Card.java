package GameElements.Cards;
import Player.Player;

public abstract class Card {
    private String name;
    private String description;



    private String altDescription;

    public Card(){
    }

    public abstract void action(Player player, Player[] playerArray);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAltDescription() {
        return altDescription;
    }

    public void setAltDescription(String altDescription) {
        this.altDescription = altDescription;
    }
}
