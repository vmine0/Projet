package Modele;

public enum TypeTuile {
    Oeil("Oeil"), Oasis("Oasis") , Mirage("Mirage") , Helice("Helice"), BoiteVitesse("Boite de Vitesse"),
    CristalEnergie("Cristal d'energie"), SystemeNaviagtion("Systement de Navigation"), tunnel("Tunnel"), crash("Crash") , decollage("Decollage"),
    Cite("Cité");

    private final String nomTuile;

    private TypeTuile(String s) {
        this.nomTuile = s;
    }

    public boolean equalsName(String otherName) {
        return this.nomTuile.equals(otherName);
    }

    public String toString() {
        return this.nomTuile;
    }

}
