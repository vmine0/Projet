public class CaseTraversable extends Case{
    private Entite contenu;

    public CaseTraversable(int lig, int col, Entite contenu) {
        super(lig, col);
        this.contenu = contenu;
    }

    public CaseTraversable(int lig, int col){
        super(lig , col);
    }

    public Entite getContenu(){
        return this.contenu;
    }

    public void vide(){
        this.contenu = null;
    }

    public void entre(Entite e){
        this.contenu = e;
    }

    @Override
    public boolean estLibre() {
        if(this.contenu == null){
            return true;
        }else{
            return false;
        }
    }
}
