public class Obstacle extends Entite{

    public Obstacle(int resistance){
        this.resistance = resistance;
    }

    public Obstacle(){
        this.resistance = 3;
    }
    @Override
    public String toString(String background) {
        if(background.length() >= 3){
            return "@@@";
        }else if(background.length()==1){
            return background.charAt(0)+"@"+background.charAt(2);
        }else{
            return "@@"+background.charAt(0);
        }
    }
}
