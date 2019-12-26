package square;

import io.Display;
import lombok.Getter;
import lombok.Setter;
import player.Player;

@Getter
@Setter
public class UtilitySquare extends Square implements Purchasable {

    private String type;
    private int landValue;
    private int baseRent;
    private Player owner;//player index in players array
    private String color;
    private int twoStation;
    private int threeStation;
    private int fourStation;


    private UtilitySquare() {
    }

    @Override
    public void squareAction(Player player) {

        Display display = new Display();

        if (owner == null) {

            if (player.decidingToBuy() && (player.getMoney().getMoney() - landValue > 0)) {
                owner = player;
                player.getMoney().subtractMoney(landValue);
                player.addProperty(this);
                //display.infoMessageBuying(player, this);
            }
        } else if (!player.equals(owner)) {
            int count = owner.howManyOfSameColour(type);
            //TODO we need to increase the rent based on this count

            player.getMoney().subtractMoney(baseRent);
            if (!owner.isBankrupt()) {
                owner.getMoney().addMoney(baseRent);
                display.infoMessagePayingRent(player, owner, baseRent);
            }
        }

    }


    public UtilitySquare(String name, int position, String type, int landValue, int baseRent, String color, int twoStation, int threeStation, int fourStation) {
        super.setName(name);
        super.setPosition(position);
        this.type = type;
        this.landValue = landValue;
        this.baseRent = baseRent;
        this.owner = null;//default , no owner
        this.color = color;
        this.twoStation = twoStation;
        this.threeStation = threeStation;
        this.fourStation = fourStation;
    }

    @Override
    public String landedOn() {
        String landInfo = "";
        landInfo = landInfo.concat(getName());
        if (getOwner() != null)
            landInfo = landInfo + " (Owner: " + getOwner().getName() + ")";
        return landInfo;
    }
}
