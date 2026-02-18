package Modele;

import java.util.ArrayList;

import static Modele.TypeTuile.Oeil;

public class Tuile {
    private Emplacement emplacement;
    private int nv_sable;

    private boolean estExplore = false;
    private  TypeTuile typeTuile;
    private ArrayList<Joueur> joueurs;
    private Desert desert;

    public Tuile(Desert d, int nv_sable, TypeTuile typeTuile) {
        this.nv_sable = nv_sable;
        this.typeTuile = typeTuile;
        this.joueurs = new ArrayList();
        this.desert = d;
    }

    public void setEmplacementTuile(int i, int j) {
        this.emplacement = new Emplacement(i, j);
    }

    public Emplacement getEmplacementTuile() {
        return this.emplacement;
    }

    public void addJoueur(Joueur j) {
        this.joueurs.add(j);
        j.setCase(this);
    }

    public Joueur removeJoueur(Joueur j) {
        return (Joueur)this.joueurs.remove(this.joueurs.indexOf(j));
    }

    public ArrayList<Joueur> getJoueurs() {
        return this.joueurs;
    }

    public void incrementeNv_sable() {
        ++this.nv_sable;
    }

    public void decrementeNv_sable() {
        if (this.nv_sable > 0) {
            --this.nv_sable;
        }

    }


    public int get_nvSable() {
        return this.nv_sable;
    }

    public TypeTuile getTypeTuile() {
        return this.typeTuile;
    }

    public Desert getDesert() {
        return this.desert;
    }


    public boolean isPiece() {
        switch (this.typeTuile) {
            case BoiteVitesse:
            case CristalEnergie:
            case Helice:
            case SystemeNaviagtion:

                return true;
            default:
                return false;
        }
    }

    public String toString() {
        String nomCase = "<html>Sable: " + this.get_nvSable();
        if (this.getTypeTuile() ==  Oeil) {
            nomCase = nomCase + "<br/>" + this.getTypeTuile();
        }

        nomCase = nomCase + "</html>";
        return nomCase;
    }
}
