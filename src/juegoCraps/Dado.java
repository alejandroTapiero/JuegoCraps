package juegoCraps;

import java.util.Random;

/**
 * Class Dado that generates a value that rounds between 1 and 6.
 * * @author Alejandro Tapiero Triana 202043737 alejandro.tapiero@correounivalle.edu.co
 * * @version v.1.0.0 date:05/12/2021
 */

public class Dado {
    private int cara;

    public int getCara() {
        /**
         * Method that generates a random value to cara
         * @return number between (1, 6)
         */
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        return cara;
    }
}
