package juegoCraps;

/**
 * ModelCraps apply craps rules.
 * estado 1 = Natural winner
 * estado 2 = Craps loser
 * estado 3 = Stablish Punto
 * estado 4 = Win on Punto
 * estado 5 = Lose on Punto
 * @author Alejandro Tapiero Triana 202043737 alejandro.tapiero@correounivalle.edu.co
 * @version v.1.0.0 date:07/12/2021
 */


public class modelCraps {
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String estadotoString;
    private int[] caras;

    /**
     * Class Constructor
     */

    public modelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
    }

    /**
     * Establishment of Tiro value, according to each Dado value.
     */

    public void calcularTiro(){
        caras[0] = dado1.getCara();
        caras[1] = dado1.getCara();
        tiro = caras[0] + caras[1];
    }

    /**
     * Establishment of Tiro value, according to Estado atribute value.
     *  estado 1 = Natural winner
     *  estado 2 = Craps loser
     *  estado 3 = Stablish Punto
     */

    public void determinarJuego(){
        if (flag == 0){
            if (tiro == 7 || tiro == 11){
                estado = 1;
            }
            else {
                if (tiro == 2 || tiro == 3){
                    estado = 2;
                }
                else{
                    estado = 3;
                    punto = tiro;
                    flag = 1;
                }
            }
        }
        else{
            //Ronda Punto
            rondaPunto();
        }
    }

    /**
     * Establishment of Tiro value, according to Estado atribute value after an else condition
     *  estado 4 = Win on Punto
     *  estado 5 = Lose on Punto
     */

    private void rondaPunto() {
        if (tiro == punto){
            estado = 4;
            flag = 0;
        }
        if (tiro == 7){
            estado = 5;
            flag = 0;
        }
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establishment of Tiro value, according to Estado atribute value.
     * @return message for the View class
     */

    public String getEstadotoString() {
        switch(estado){
            case 1: estadotoString = "¡Un natural! ¡Felicidades! Has ganado.";
            break;
            case 2: estadotoString = "Es un craps. Has perdido, suerte para la próxima.";
                break;
            case 3: estadotoString = "Has establecido un punto en " +punto+ ". ¡Sigue lanzando!" +
                "\n Cuidado, no saques un 7 antes que " +punto+ ". No querrás perder, ¿eh?";
                break;
            case 4: estadotoString = "Parece que volviste a sacar un " +punto+ "¡Felicidades! Has ganado.";
                break;
            case 5: estadotoString = "¿Eso es un 7 antes que " +punto+ "? Te lo advertí."+
                    "\n Lo siento, reglas son reglas. Has perdido, suerte para la próxima.";
                break;
        }
        return estadotoString;
    }

    public int[] getCaras() {
        return caras;
    }
}
