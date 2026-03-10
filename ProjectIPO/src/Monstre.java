public class Monstre extends EntiteMobile {

    public Monstre (){
        this.direction = Direction.random();
    }
    public Monstre (Direction direction){
        super(direction);
    }
    @Override
    public String toString(String background) {
        if (this.direction == Direction.nord) {
            return background.charAt(0) + "m" + background.charAt(2);
        } else if (this.direction == Direction.sud) {
         return background.charAt(0) + "w" + background.charAt(2);
        } else if (this.direction == Direction.est) {
            return background.charAt(0) + ">>" + background.charAt(2);
        } else {
            return background.charAt(0) + "<<" + background.charAt(2);
        }
    }
}
