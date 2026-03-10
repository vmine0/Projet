package Vue;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class DesertPanel extends JPanel {

    private Image backgroundImage;

    public DesertPanel() {
        try {
            // Chargement de l'image du désert avec un oasis
            backgroundImage = ImageIO.read(new File("/Macintosh HD/Utilisateurs/macbook/Téléchargements/oasis.jpg"));
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de l'image du désert.");
        }

        // Création des boutons "Jouer" et "Quitter"
        JButton jouerButton = new JButton("Jouer");
        jouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectionJoueurVue select = new SelectionJoueurVue();
                select.setVisible(true);
                setVisible(false);
            }
        });
        JButton quitterButton = new JButton("Quitter");

        // Ajout des boutons au JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jouerButton);
        buttonPanel.add(quitterButton);

        // Ajout du panel de boutons au bas du panel principal
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessin de l'image de fond
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        // Création de la fenêtre
        JFrame frame = new JFrame("Jeu du désert");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout du JPanel au centre de la fenêtre
        DesertPanel panel = new DesertPanel();

        frame.setContentPane(panel);

        // Redimensionnement de la fenêtre et affichage
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
