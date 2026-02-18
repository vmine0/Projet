package Modele;

import Obs.Observable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

public class Desert extends Observable {
    private Tuile[][] grille = new Tuile[5][5];
    private ArrayList<Tuile> caseDesert = new ArrayList();

    private Emplacement emplacementOeil;
    private double niveauTempête = 2.0;
    private ArrayList<Joueur> joueurs = new ArrayList();
    private int joueurCourant = 0;
    private static Joueur srcAction;

    public Desert() {
        //On ajoute les differentes tuile au jeu
        //on commence par les 4 piece a recuperer
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.BoiteVitesse));
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.CristalEnergie));
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.SystemeNaviagtion));
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.Helice));

        //2 oasis
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.Oasis));
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.Oasis));

        //1 mirage
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.Mirage));

        //3 tunnels
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.tunnel));
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.tunnel));
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.tunnel));

        //La piste de decollage
        this.caseDesert.add(new Tuile(this, 0, TypeTuile.decollage));

        for(int i = 0; i < 13; ++i) {
            this.caseDesert.add(new Tuile(this, 0, TypeTuile.Cite));
        }

        this.shuffleArrayList();
        this.shuffleArrayList();
        Tuile tmpSwap = (Tuile)this.caseDesert.get(12);
        this.caseDesert.set(12, new Tuile(this, 0, TypeTuile.Oeil));
        this.emplacementOeil = new Emplacement(2, 2);
        this.caseDesert.add(tmpSwap);

        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                Tuile c = (Tuile)this.caseDesert.get(i * 5 + j);
                Emplacement tmpCoord = new Emplacement(i, j);
                this.grille[i][j] = c;
                c.setEmplacementTuile(i, j);
            }
        }
        this.grille[0][0] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[0][1] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[0][2] = new Tuile(this, 0, TypeTuile.tunnel);
        this.grille[0][3] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[0][4] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[1][0] = new Tuile(this, 0, TypeTuile.Oasis);
        this.grille[1][1] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[1][2] = new Tuile(this, 0, TypeTuile.CristalEnergie);
        this.grille[1][3] = new Tuile(this, 0, TypeTuile.tunnel);
        this.grille[1][4] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[2][0] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[2][1] = new Tuile(this, 0, TypeTuile.BoiteVitesse);
        this.grille[2][2] = new Tuile(this, 0, TypeTuile.Oeil);
        this.grille[2][3] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[2][4] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[3][0] = new Tuile(this, 0, TypeTuile.Helice);
        this.grille[3][1] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[3][2] = new Tuile(this, 0, TypeTuile.decollage);
        this.grille[3][3] = new Tuile(this, 0, TypeTuile.Cite   );
        this.grille[3][4] = new Tuile(this, 0, TypeTuile.Oasis);
        this.grille[4][0] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[4][1] = new Tuile(this, 0, TypeTuile.Cite);
        this.grille[4][2] = new Tuile(this, 0, TypeTuile.Mirage);
        this.grille[4][3] = new Tuile(this, 0, TypeTuile.SystemeNaviagtion);
        this.grille[4][4] = new Tuile(this, 0, TypeTuile.tunnel);




        //Permet de commencer avec les tuiles sur la le losange a 1 de nv de sable
        this.grille[0][2].incrementeNv_sable();
        this.grille[1][1].incrementeNv_sable();
        this.grille[1][3].incrementeNv_sable();
        this.grille[2][0].incrementeNv_sable();
        this.grille[2][4].incrementeNv_sable();
        this.grille[3][1].incrementeNv_sable();
        this.grille[3][3].incrementeNv_sable();
        this.grille[4][2].incrementeNv_sable();
    }


    public static Joueur getSrcAction() {
        return srcAction;
    }

    public static void setSrcAction(Joueur j) {
        srcAction = j;
    }

    private void swapArrayList(int i, int j) {
        Tuile tmp = (Tuile)this.caseDesert.get(i);
        this.caseDesert.set(i, (Tuile)this.caseDesert.get(j));
        this.caseDesert.set(j, tmp);
    }

    private void shuffleArrayList() {
        for(int i = this.caseDesert.size() - 1; i > 1; --i) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, i);
            this.swapArrayList(i, randomNum);
        }

    }

    private HashSet<Emplacement> randomListCoord(int nbCoord, int min, int max) {
        HashSet<Emplacement> liste_nombre = new HashSet();

        while(true) {
            Emplacement c;
            do {
                if (liste_nombre.size() >= nbCoord) {
                    return liste_nombre;
                }

                c = new Emplacement(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(min, max));
            } while(c.get_ligne() == 2 && c.get_colonne() == 2);

            liste_nombre.add(c);
        }
    }



    public void incrementeNiveauTempête() {
        this.niveauTempête = this.niveauTempête + 0.5;
    }


    public double getNiveauTempête() {
        return this.niveauTempête;
    }

    public Emplacement getCoordOeil() {
        return this.emplacementOeil;
    }

    public Tuile getCase(int i, int j) {
        return this.grille[i][j];
    }

    public Tuile getCase(Emplacement c) {
        return this.grille[c.get_ligne()][c.get_colonne()];
    }

    public void addJoueur(Joueur j) {
        this.joueurs.add(j);
    }

    public void changeCurrentPlayer() {
        this.getJoueurCourant().resetActions();

        if (this.joueurCourant == this.joueurs.size() - 1) {
            this.joueurCourant = 0;
        } else {
            ++this.joueurCourant;
        }

    }

    public Joueur getJoueurCourant() {
        return this.joueurs.size() > 0 ? (Joueur)this.joueurs.get(this.joueurCourant) : null;
    }

    public ArrayList<Joueur> getJoueurs() {
        return this.joueurs;
    }



    public Tuile[][] getGrilleDesert() {
        return this.grille;
    }


    //recuperer chez un camarade
    public String toString() {
        String s = "";
        s = s + "+-----+\n";

        for(int i = 0; i < 5; ++i) {
            s = s + "|";

            for(int j = 0; j < 5; ++j) {
                s = s + this.grille[i][j].getTypeTuile();
            }

            s = s + "|\n";
        }

        s = s + "+-----+\n";
        return s;
    }


}
