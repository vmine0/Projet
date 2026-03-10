abstract class Case {
    private int lig;
    private int col;

    public Case(int lig, int col){
        this.lig = lig;
        this.col = col;
    }

    public abstract boolean estLibre();
}
