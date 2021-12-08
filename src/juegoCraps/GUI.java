package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as View Craps Class
 * @author Alejandro Tapiero Triana 202043737 alejandro.tapiero@correounivalle.edu.co
 * @version v.1.0.0 date:29/11/2021
 */
public class GUI extends JFrame {
    private static final String MENSAJE_INICIO = " ¡Bienvenido a Craps! \n"
            + " Para iniciar, sólo oprime el botón de lanzar."
            + "\n Presta atención, porque de salida lanzas un par de dados."
            + "\n Si tu tiro de salida es 7 u 11, será un Natural victorioso."
            + "\n Mas si tiras un 2, 3 o 12, será una derrota de Craps."
            + "\n Cualquier otro valor de salida te establecerá en un Punto."
            + "\n Mientras estés en Punto, podrás seguir tirando sin problema."
            + "\n ¡Es más! Saca el mismo valor del Punto establecido, ¡y ganarás!"
            + "\n Sólo no saques 7 mientras estés en Punto, o habrás perdido."
            + "\n Si estás listo, ¡ve y da tu mejor tiro!";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private JSeparator separator;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private int flag;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("The Title app");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flag = 0;
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH);

        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados, BorderLayout.CENTER);

        mensajeSalida = new JTextArea(7, 31);
        mensajeSalida.setText(MENSAJE_INICIO);
        //mensajeSalida.setBorder(BorderFactory.createTitledBorder("Qué debes hacer: "));
        JScrollPane scroll = new JScrollPane(mensajeSalida);

        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Qué debes hacer: "));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370, 180));

        this.add(panelResultados, BorderLayout.EAST);

        resultadosDados = new JTextArea(4,31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(320,7));
        separator.setBackground(Color.YELLOW);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imageDado);
            modelCraps.determinarJuego();

            if (flag == 0){
                panelResultados.removeAll();
                panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados: "));
                panelResultados.add(resultadosDados);
                panelResultados.add(separator);
                panelResultados.add(mensajeSalida);
                flag = 1;
            }
            resultadosDados.setText(modelCraps.getEstadotoString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelCraps.getEstadotoString()[1]);

            revalidate();
            repaint();
        }
    }
}
