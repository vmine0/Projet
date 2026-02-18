package Vue;

import Modele.Joueur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class JoueurPanel extends JPanel {

    private Joueur joueur;
    private JLabel nomLabel;
    private JLabel niveauLabel;
    private JLabel actionLabel;

    public JoueurPanel(Joueur joueur) {
        this.joueur = joueur;

        // Configuration du JPanel
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setLayout(new BorderLayout());

        // Ajout des labels pour les informations du joueur
        nomLabel = new JLabel(joueur.getNom());
        nomLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(nomLabel, BorderLayout.NORTH);

        niveauLabel = new JLabel("Niveau d'eau : " + joueur.getNiveauEau());
        niveauLabel.setFont(new Font("Arial", Font.BOLD, 10));

        add(niveauLabel, BorderLayout.CENTER);

        actionLabel = new JLabel("Actions restante : " + joueur.getActions());
        actionLabel.setFont(new Font("Arial", Font.BOLD, 10));

        add(actionLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        Joueur joueur = new Joueur("Amine");
        joueur.incrementeNiveauEau();
        joueur.decrActions();
        JoueurPanel joueurPanel = new JoueurPanel(joueur);
        JFrame frame = new JFrame();
        frame.getContentPane().add(joueurPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
