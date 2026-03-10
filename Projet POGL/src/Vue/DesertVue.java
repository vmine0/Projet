package Vue;

import Modele.Desert;
import Obs.Observer;

import javax.management.remote.JMXPrincipal;
import javax.swing.*;
import java.awt.*;

public class DesertVue extends JPanel implements Observer {
    private Desert d;
    private TuileVue[][] vueDesert;

    public DesertVue(Desert d, int hauteur, int largeur) {
        this.d = d;
        this.vueDesert = new TuileVue[5][5];
        this.setLayout(new GridLayout(hauteur, largeur, 2, 2));
        this.setPreferredSize(new Dimension(600, 400));
        d.addObserver(this);

        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                TuileVue tuileVue = new TuileVue(d.getCase(i, j));
                this.vueDesert[i][j] = tuileVue;
                this.add(tuileVue);
            }
        }

    }

    public void update() {
        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                this.vueDesert[i][j].setTuile(this.d.getCase(i, j));
            }
        }

        this.revalidate();
        this.repaint();
    }
}
