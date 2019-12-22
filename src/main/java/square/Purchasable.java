package square;

import player.Player;

public interface Purchasable {

    int landValue = 0;
    int rent = 0;
    Player owner = null;
    String type = "";

    int getLandValue();
    void setLandValue(int landValue);
    int getRent();
    void setRent(int rent);
    Player getOwner();
    void setOwner(Player owner);
    String getType();
    void setType(String type);
}