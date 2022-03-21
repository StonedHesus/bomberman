package bomberman.upc.components.gameElements.bombs;

import bomberman.upc.components.gameElements.board.Board;
import bomberman.upc.components.gameElements.background.Obstacle;

public abstract class Bomb implements Obstacle {

    protected int dammage;        // ray of explosion

    //constructor
    public Bomb(int dammage){
        this.dammage = dammage;
    }

    //getters and setters



    //fonctions
    public abstract void explose(Board board, int x, int y);


}
