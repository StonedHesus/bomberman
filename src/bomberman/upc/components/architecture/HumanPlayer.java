package bomberman.upc.components.architecture;

import bomberman.upc.components.gameElements.board.Board;

import java.awt.*;

public class HumanPlayer extends Player {

    public HumanPlayer(String pseudo, Point position) {

        super(pseudo, position);
        this.solidArea = new Rectangle(12, 48 , 64, 64);
    }

    @Override
    public void dropBomb(Board board){
//        if (board.getBoard()[posCell.x][posCell.y] instanceof Trail)    //car on peut pas poser une bombe que sur trail
//            myBomb.explose(board, posCell.x, posCell.y);
    }


    public void jouer (Board board){
        System.out.println();
    }

}
