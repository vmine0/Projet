abstract class EntiteMobile extends Entite {
    protected Direction direction;

    public EntiteMobile(Direction direction){
        this.direction = direction;
    }

    public EntiteMobile (int resistance){
        super(resistance);
        this.direction = Direction.random();
    }

    public EntiteMobile(){
        this.resistance = 3;
        this.direction = Direction.random();
    }


}
