package bomberman.upc.components.gameElements.bombs;

import bomberman.upc.components.gameElements.board.Board;

public class BombStandard extends Bomb {



    // Constructor
    public BombStandard(){
        super(2);
    }

    @Override
    /*
    public void explose(Board board, int x, int y){
        //la bombe va exploser dans la postion (x, y) dans board
        if (board.getCells()[x][y] instanceof Brick){
            if (board.getCells()[x][y] instanceof Destroyable)
                board.setCells(x, y, new Trail(board.getCells()[x][y].hasBonus));       //on remplace la brick par trail et on arrete la recursivite dans ce sens
        }
        else{
            explose(board, x+1, y);
            explose(board, x-1, y);
            explose(board, x, y+1);
            explose(board, x, y-1);
        }


    }

     */
    public void explose (Board board, int x, int y){

    }



}
