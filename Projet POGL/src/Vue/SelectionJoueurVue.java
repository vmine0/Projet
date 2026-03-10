package Vue;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.swing.*;

public class SelectionJoueurVue extends JPanel {
    private JComboBox<Integer> playerComboBox;

    public SelectionJoueurVue() {
        JLabel titleLabel = new JLabel("Nombre de joueurs :");
        add(titleLabel);

        Integer[] playerOptions = {2, 3, 4};
        playerComboBox = new JComboBox<Integer>(playerOptions);
        add(playerComboBox);
    }

    public int getSelectedPlayerCount() {
        return (int) playerComboBox.getSelectedItem();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sélection du nombre de joueurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SelectionJoueurVue playerSelectionPanel = new SelectionJoueurVue();
        frame.getContentPane().add(playerSelectionPanel);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            int selectedPlayerCount = playerSelectionPanel.getSelectedPlayerCount();
            JOptionPane.showMessageDialog(frame, "Nombre de joueurs sélectionné : " + selectedPlayerCount);
        });
        frame.getContentPane().add(okButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}

