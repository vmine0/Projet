public class Personnage extends EntiteMobile{
    public Personnage(){
        this.direction = Direction.random();
    }
    public Personnage(Direction direction) {
        super(direction);
    }

    @Override
    public String toString(String background) {
        if (this.direction == Direction.nord) {
            return background.charAt(0) + "^" + background.charAt(2);
        } else if (this.direction == Direction.sud) {
            return background.charAt(0) + "v" + background.charAt(2);
        } else if (this.direction == Direction.est) {
            return background.charAt(0) + ">" + background.charAt(2);
        } else {
            return background.charAt(0) + "<" + background.charAt(2);
        }
    }

    public void action(Case courante, Case cible){
        if(courante instanceof Sortie){//si le perso est sur une sortie
            courante.contenu = null;
            //Jeu.sortis++;
        }
        if(cible.contenu instanceof Obstacle){
            cible.decremente();
        }
    }
}