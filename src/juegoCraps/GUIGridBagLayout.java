package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
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
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("The Title app");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        initGUI();
        //Set up JFrame Container's Layout
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);

        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda, constraints);

        salir = new JButton(" X ");
        ayuda.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir, constraints);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

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
