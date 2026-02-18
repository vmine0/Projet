package Vue;

import Modele.Desert;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class PiocheTempete extends JPanel {

    private JLabel label;

    public PiocheTempete(Desert d) {
        // Configuration du JPanel
        setBackground(new Color(50, 50, 50));
        setPreferredSize(new Dimension(100, 150));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Ajout du label "Tempête"
        double a = d.getNiveauTempête();
        label = new JLabel("Tempête \n" + a, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        Desert d = new Desert();
        PiocheTempete piochePanel = new PiocheTempete(d);
        JFrame frame = new JFrame();
        frame.getContentPane().add(piochePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
