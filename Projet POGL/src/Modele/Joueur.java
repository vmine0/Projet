package Modele;

import Obs.Observable;

import java.util.ArrayList;
import java.util.Objects;

public class Joueur extends Observable {
    private int niveauEau;
    private String nom;
    private int actions;
    private Tuile tuile;

    public Joueur(String nom) {
        this.nom = nom;
        this.actions = 4;
        this.niveauEau = 4;


        this.tuile = null;

    }

    public void decrActions() {
        if (this.actions > 0) {
            --this.actions;
        }

    }

    public void incrActions() {
        ++this.actions;
    }

    public void resetActions() {
        this.actions = 4;
    }

    public int getActions() {
        return this.actions;
    }

    public int getActionRestante(){
        return 4 - getActions();
    }

    public String getNom() {
        return this.nom;
    }

    public int getNiveauEau() {
        return this.niveauEau;
    }

    public void decrementeNiveauEau() {
        if (this.niveauEau > 0) {
            --this.niveauEau;
            this.notifyObservers();
        }

    }

    public void incrementeNiveauEau() {
        if (this.niveauEau < 5) {
            ++this.niveauEau;
            this.notifyObservers();
        }

    }

    public void setCase(Tuile tuile) {
        this.tuile = tuile;
    }

    public Tuile getCurrentCase() {
        return this.tuile;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Joueur joueur = (Joueur)o;
            return this.niveauEau == joueur.niveauEau && this.actions == joueur.actions && Objects.equals(this.nom, joueur.nom);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.niveauEau, this.nom, this.actions});
    }

    public String toString () {
        return "Joueur{niveauEau=" + this.niveauEau + ", nom='" + this.nom + "', actions=" + this.actions + ", pieces=" + "}";
    }

}
