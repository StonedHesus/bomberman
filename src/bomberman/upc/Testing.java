package bomberman.upc;

import bomberman.upc.components.gameElements.board.Board;

import java.io.File;

public class Testing {

    public static void main(String[] args ) {

        String parent = Testing.class.getClassLoader().getResource(".").toString();
        parent = parent.split("/out")[0];
        parent = parent + "/src/bomberman/upc/components/gameElements/board/boards/example.board";
        parent = parent.split("file:")[1];


        Board test = new Board(new File(parent));
    }
}
