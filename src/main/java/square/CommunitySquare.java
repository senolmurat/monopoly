package square;

import lombok.Getter;
import lombok.Setter;
import player.Player;

@Getter
@Setter
public class CommunitySquare extends Square implements Purchasable {
    private String type;
    private int landValue;
    private int baseRent;
    private int twoCommunity;
    private Player owner;//player index in players array
    private String color;

    public CommunitySquare(String name, int position, String colour, int landValue, int baseRent, int twoCommunity) {
        super.setName(name);
        super.setPosition(position);
        this.type = colour;
        this.landValue = landValue;
        this.baseRent = baseRent;
        this.twoCommunity = twoCommunity;
        this.owner = null;//default , no owner
    }

    @Override
    public void squareAction(Player player) {

    }

    @Override
    public String landedOn() {
        return null;
    }
}
