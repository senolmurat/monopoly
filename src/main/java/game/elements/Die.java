package game.elements;

import java.util.Random;

import io.Reader;

public class Die {

    private Reader reader = new Reader();

    private boolean isDouble;
    private int totalFaces;

    private int[] dieFaces = new int[reader.getNumberOfDices()];

    public int[] getFaces() {

        isDouble = false;

        totalFaces = 0;

        for (int i = 0; i < reader.getNumberOfDices(); i++) {
            Random rand = new Random();
            int dieFace = 1 + rand.nextInt(6);
            dieFaces[i] = dieFace;
            totalFaces += dieFace;

            isDouble = dieFace == dieFaces[0];
        }

        return dieFaces;
    }

    public void setDouble(boolean aDouble) {
        isDouble = aDouble;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public int getTotalFaces() {
        return totalFaces;
    }

    public int[] getDieFaces() {
        return dieFaces;
    }
}
