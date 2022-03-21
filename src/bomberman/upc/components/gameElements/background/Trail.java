package bomberman.upc.components.gameElements.background;

import bomberman.upc.components.gameElements.bombs.Bomb;

public class Trail extends Cell {

    // Attributes of the class.
    private Bomb bomb = null;

    // Constructors of the class.
    public  Trail (boolean hasBonus){

        super(hasBonus);
        super.collision = false;
    }


    // Getters of the class.
    public Bomb getBombe() {return this.bomb;}

    // Setters of the class.
    public void setBomb(Bomb bomb) {this.bomb = bomb;}

    // Methods of the class.
    public boolean containsBomb() {return this.bomb != null;}

    @Override
    public String toString(){
        return "T";
    }
}
