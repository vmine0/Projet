abstract class Entite {
    public Integer resistance;

    public Entite(int resistance){
        this.resistance = resistance;
    }

    public Entite(){
        this.resistance = 3;
    }

    public abstract String toString(String background);

    public void decremente(){
        this.resistance--;
        if(this.resistance == 0){
            this.resistance = null;
        }
    }
}
