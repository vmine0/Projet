package Vue;
import javax.swing.*;
import java.awt.event.*;

public class BoutonJeu extends JFrame implements ActionListener {

    private JButton finDeTourButton;
    private JButton boireEauButton;
    private JButton explorerButton;
    private JButton prendrePieceButton;

    public BoutonJeu() {
        // Créer la fenêtre
        super("Boutons de jeu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer les boutons
        finDeTourButton = new JButton("Fin de tour");
        finDeTourButton.addActionListener(this);

        boireEauButton = new JButton("Boire eau");
        boireEauButton.addActionListener(this);

        explorerButton = new JButton("Explorer");
        explorerButton.addActionListener(this);

        prendrePieceButton = new JButton("Prendre pièce");
        prendrePieceButton.addActionListener(this);

        // Ajouter les boutons à la fenêtre
        JPanel panel = new JPanel();
        panel.add(finDeTourButton);
        panel.add(boireEauButton);
        panel.add(explorerButton);
        panel.add(prendrePieceButton);
        add(panel);

        // Afficher la fenêtre
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finDeTourButton) {
            System.out.println("Fin de tour");
        } else if (e.getSource() == boireEauButton) {
            System.out.println("Boire eau");
        } else if (e.getSource() == explorerButton) {
            System.out.println("Explorer");
        } else if (e.getSource() == prendrePieceButton) {
            System.out.println("Prendre pièce");
        }
    }

    public static void main(String[] args) {
        BoutonJeu boutonJeu = new BoutonJeu();
    }
}
