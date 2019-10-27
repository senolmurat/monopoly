import java.util.Random;

public class Dice {

    private boolean isDouble;

    public int[] getFaces() {

        int[] faces = new int[2];

        Random rand1 = new Random();
        int dice1face = 1+rand1.nextInt(6);
        faces[0] = dice1face;

        Random rand2 = new Random();
        int dice2face = 1+rand2.nextInt(6);
        faces[1] = dice2face;

        if(dice1face == dice2face){
            isDouble = true;
        }
        return faces;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public boolean isDouble() {
        return isDouble;
    }
}
