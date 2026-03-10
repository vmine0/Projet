package Modele;

import java.util.Objects;

public class Emplacement {
    private int ligne, colonne;
    public static Emplacement[] diagonnale = new Emplacement[]{new Emplacement(0, 2), new Emplacement(1, 1),
            new Emplacement(1, 3), new Emplacement(2, 0), new Emplacement(2, 4),
            new Emplacement(3, 1), new Emplacement(3, 3), new Emplacement(4, 2)};

    public Emplacement(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int get_ligne() {
        return this.ligne;
    }

    public int get_colonne() {
        return this.colonne;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Emplacement that = (Emplacement)o;
            return this.ligne == that.ligne && this.colonne == that.colonne;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.ligne, this.colonne});
    }

    public String toString() {
        return "Coordonnee{ligne=" + this.ligne + ", colonne =" + this. colonne + "}";
    }

}
