package bomberman.upc.components.gameElements.background;

public class Destroyable extends Brick {


    private String bonus;       // for the moment, we choose bonus as string type but we can use objects....

    //constructoer
    public Destroyable(boolean hasBonus){

        super(hasBonus);
        super.collision = true;

    }
    @Override
    public String toString() {
        return " D ";
    }


    //getters and setters
}
