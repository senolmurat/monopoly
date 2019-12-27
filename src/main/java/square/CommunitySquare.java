package square;

import lombok.Getter;
import lombok.Setter;
import player.Player;
import main.Game;
import io.Display;

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

        Display display = new Display();

        int faces = player.tossDie(Game.instance().getDie());
        if (owner == null) {

            if (player.decidingToBuy() && (player.getMoney().getMoney() - landValue > 0)) {
                owner = player;
                player.getMoney().subtractMoney(landValue);
                player.addProperty(this);
                //display.infoMessageBuying(player, this);
            }
        } else if (!player.equals(owner)) {
            int count = owner.howManyOfSameColour(type);
            int rent;
            if(count == 1)
                rent = baseRent;
            else
                rent = twoCommunity;

            player.getMoney().subtractMoney(rent * faces);
            if (!owner.isBankrupt()) {
                owner.getMoney().addMoney(rent);
                display.infoMessagePayingRent(player, owner, rent);
            }
        }

    }

    @Override
    public String landedOn() {
        return null;
    }
}
