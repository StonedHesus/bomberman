package bomberman.upc.components.gameElements.background;

public class Indestructible extends Brick {


    //constructor
    public Indestructible(){
        super(false);       //because indestructible brick can't contain bonus
        super.collision = true;
    }
    //functions
    @Override
    public String toString() {
        return " I ";
    }
}
