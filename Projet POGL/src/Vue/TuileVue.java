package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import static Modele.TypeTuile.*;

public class TuileVue extends JPanel implements MouseListener {
    private Tuile tuile;
    private JLabel labelCase;

    public TuileVue(Tuile tuile) {
        this.tuile = tuile;
        this.setPreferredSize(new Dimension(100, 100));
        this.addMouseListener(this);
        this.labelCase = new JLabel(this.tuile.toString());
        if (this.tuile.getTypeTuile() == Oeil) {
            this.labelCase.setText("");
        }

        this.add(this.labelCase);
    }


    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }


    public void mouseClicked(MouseEvent mouseEvent) {

    }

    public void mousePressed(MouseEvent mouseEvent) {
    }

    public void mouseReleased(MouseEvent mouseEvent) {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.tuile.getTypeTuile() == Oeil ){
            this.setBackground(new Color(255, 255, 255));
        } else {
            int x = 10;
            int y = 75;
            int i = 0;


            this.setBackground(new Color(250, 242, 25));
            if(this.tuile.getTypeTuile() == Oasis || this.tuile.getTypeTuile() == Mirage){
                g.setColor(Color.BLUE);
                g.fillOval(5, 70, 40, 40);
            }

            this.drawJoueur(g, this.tuile.getJoueurs());
        }

    }

    public void drawJoueur(Graphics g, ArrayList<Joueur> joueurs) {
        int i = 2;

        for(Iterator var4 = joueurs.iterator(); var4.hasNext(); ++i) {
            Joueur j = (Joueur)var4.next();
            g.setColor(Color.gray);
            g.fillRect(i * 11, 60, 10, 10);
            g.setColor(new Color(0, 0, 0));
        }

    }
}
