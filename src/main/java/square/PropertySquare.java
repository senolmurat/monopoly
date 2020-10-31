package square;

import lombok.Getter;
import lombok.Setter;
import player.Player;
import io.Display;
import properties.Hotel;
import properties.Property;

import java.util.ArrayList;

@Getter
@Setter
public class PropertySquare extends Square implements Purchasable {

    private int landValue;
    private Player owner;//player index in players array
    private String type;//this one stands for colour
    private int totalRent;
    private int baseRent;
    private int firstHomeRental;
    private int secondHomeRental;
    private int thirdHomeRental;
    private int fourthHomeRental;
    private int hotelRental;
    private int buildingPrice;

    private ArrayList<Property> properties = new ArrayList<>();
    private Display display = new Display();

    private PropertySquare() {
    }


    public PropertySquare(String name, int position, String colour, int landValue, int baseRent, int firstHomeRental, int secondHomeRental, int thirdHomeRental, int fourthHomeRental, int hotelRental, int buildingPrice) {
        super.setName(name);
        super.setPosition(position);
        this.type = colour;
        this.landValue = landValue;
        this.baseRent = baseRent;
        this.firstHomeRental = firstHomeRental;
        this.secondHomeRental = secondHomeRental;
        this.thirdHomeRental = thirdHomeRental;
        this.fourthHomeRental = fourthHomeRental;
        this.hotelRental = hotelRental;
        this.buildingPrice = buildingPrice;
        this.owner = null;//default , no owner
    }

    @Override
    public void squareAction(Player player) {

        Display display = new Display();

        if (owner == null) {

            if (player.decidingToBuy() && (player.getMoney().getMoney() - landValue > 0)) {
                owner = player;
                player.getMoney().subtractMoney(landValue);
                player.addProperty(this);
                display.infoMessageBuying(player, this);
            }
        } else if (!player.equals(owner)) {
            int count = owner.howManyOfSameColour(type);
            totalRent = baseRent;
            int propertyStage = properties.size();

            if(propertyStage != 0 && properties.get(0) instanceof Hotel)
                totalRent = hotelRental;
            else {
                switch (propertyStage) {
                    case 1:
                        totalRent = firstHomeRental;
                        break;
                    case 2:
                        totalRent = secondHomeRental;
                        break;
                    case 3:
                        totalRent = thirdHomeRental;
                        break;
                    case 4:
                        totalRent = fourthHomeRental;
                        break;
                }
            }

            player.getMoney().subtractMoney(totalRent);
            if (!owner.isBankrupt()) {
                owner.getMoney().addMoney(totalRent);
                display.infoMessagePayingRent(player, owner, totalRent);
            }
        }

        else {
            buildNewProperty(player);

        }

    }


    @Override
    public String landedOn() {
        String landInfo = "";
        landInfo = landInfo.concat(getName());
        if (getOwner() != null)
            landInfo = landInfo + " (Owner: " + getOwner().getName() + ")";
        return landInfo;
    }

    public void buildNewProperty(Player player) {

        Property newProperty = player.canIBuildPropertie(this);
        if (newProperty != null && player.getMoney().getMoney() - buildingPrice > 0) {
            player.getMoney().subtractMoney(buildingPrice);

            if (newProperty instanceof Hotel){
                properties.clear();
                display.infoMessageBuildHotel(player, super.getName(), buildingPrice);
            }
            else{
                display.infoMessageBuildHouse(player, super.getName(), properties.size()+1, buildingPrice);
            }
            properties.add(newProperty);
        }
    }
}