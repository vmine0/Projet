package Vue;

import Modele.Desert;
import Modele.Joueur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vue extends JFrame {

    private DesertVue desertVue;
    private JPanel boutons;

    private JPanel pioche;

    public Vue() {
        // Création de la grille de cases
        Desert d = new Desert();
        this.desertVue = new DesertVue(d, 5, 5);

        // Création des boutons
        boutons = new JPanel(new GridLayout(1, 2));
        JButton bouton1 = new JButton("Désensabler");
        JButton bouton2 = new JButton("Boire de l'eau");
        JButton bouton3 = new JButton("Ramasser Piece");
        JButton bouton4 = new JButton("Decouvrir");
        JButton bouton5 = new JButton("Fin de Tour");
        boutons.add(bouton1);
        boutons.add(bouton2);
        boutons.add(bouton3);
        boutons.add(bouton4);
        boutons.add(bouton5);

        //Création de la pioche
        pioche = new JPanel();
        PiocheTempete piocheTempete = new PiocheTempete(d);
        JoueurPanel joueurPanel1 = new JoueurPanel(new Joueur("Amine"));
        JoueurPanel joueurPanel2 = new JoueurPanel(new Joueur("Jordy"));
        pioche.add(piocheTempete);
        pioche.add(joueurPanel1);
        pioche.add(joueurPanel2);

        // Ajout des composants à la fenêtre
        add(desertVue, BorderLayout.CENTER);
        add(boutons, BorderLayout.SOUTH);
        add(pioche, BorderLayout.EAST);

        // Configuration de la fenêtre
        setTitle("Desert Interdit");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Vue vue = new Vue();
    }
}

