package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fenetre extends JFrame {
    private DesertVue desertVue;
    private BoutonJeu boutonJeu;

    public Fenetre(DesertVue desertVue) {
        super("Desert Interdit");
        this.desertVue = desertVue;
        this.boutonJeu = new BoutonJeu();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        // Ajout des vues à la fenêtre
        getContentPane().add(desertVue, BorderLayout.CENTER);
        getContentPane().add(boutonJeu, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        setVisible(true);
    }



        public static void main(String[] args) {
            // Création de la fenêtre
            JFrame frame = new JFrame("Jeu du désert");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

            // Redimensionnement de la fenêtre et affichage
            frame.setSize(600, 400);
            frame.setVisible(true);
        }
    }
