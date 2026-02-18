public class Sortie extends CaseTraversable{

    public Sortie(int lig, int col, Entite contenu){
        super(lig, col, contenu);
    }

    public Sortie(int lig, int col){
        super(lig, col);
    }

    public String toString(){
        return "( )";
    }


}
