package square;

import lombok.Getter;
import lombok.Setter;
import player.Player;
import io.Display;
import properties.Property;

import java.util.ArrayList;

@Getter
@Setter
public class PropertySquare extends Square implements Purchasable{

    private int landValue;
    private int rent;
    private Player owner;//player index in players array
    private String type;//this one stands for colour
    private ArrayList<Property> properties = new ArrayList<>();

    private PropertySquare() {
    }



    public PropertySquare(String name, int position, String colour, int landValue, int rent) {
        super.setName(name);
        super.setPosition(position);
        this.type = colour;
        this.landValue = landValue;
        this.rent = rent;
        this.owner = null;//default , no owner
    }

    @Override
    public void squareAction(Player player) {

        Display display = new Display();

        if(owner == null) {

            if(player.decidingToBuy() && (player.getMoney().getMoney() - landValue > 0) ) {
                owner = player;
                player.getMoney().subtractMoney(landValue);
                player.addProperty(this);
                display.infoMessageBuying(player, this);
            }
        }

        else if(!player.equals(owner)) {
            int count = owner.howManyOfSameColour(type);
            //TODO we need to increase the rent based on this count

            player.getMoney().subtractMoney(rent);
            if(!owner.isBankrupt()){
                owner.getMoney().addMoney(rent);
                display.infoMessagePayingRent(player, owner, rent);
            }
        }

    }


    @Override
    public String landedOn() {
        String landInfo = "";
        landInfo = landInfo.concat(getName());
        if (getOwner() != null)
            landInfo = landInfo + " (Owner: " + getOwner().getName()+ ")";
        return landInfo;
    }

    public void buildNewProperty(Player player) {

        Property newProperty = player.canIBuildPropertie(this);
        if(newProperty != null && player.getMoney().getMoney() - newProperty.prize > 0) {
            player.getMoney().subtractMoney(newProperty.prize);
            properties.add(newProperty);
        }
    }
}
