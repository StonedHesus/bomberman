package bomberman.upc.components.gameElements.background;

public abstract class Brick extends Cell implements Obstacle {

    //constructor
    public Brick (boolean hasBonus){

        super(hasBonus);
        super.collision = true;
    }
}
