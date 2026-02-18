import Modele.Desert;
import Vue.DesertVue;
import Vue.Fenetre;
import Vue.TuileVue;

import java.awt.*;

public class Main {
    public Main(){}
    public static void main(String[] args) {
        Desert d = new Desert();
        DesertVue desertVue = new DesertVue(d, 5, 5);
        Fenetre fenetre = new Fenetre(desertVue);
    }
}