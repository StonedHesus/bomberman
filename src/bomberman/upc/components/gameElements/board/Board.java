package bomberman.upc.components.gameElements.board;

import bomberman.upc.components.gameElements.background.Cell;
import bomberman.upc.components.gameElements.background.Trail;
import bomberman.upc.components.gameElements.background.Indestructible;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Board {

    // Attributes of the class.
    private int lines, column;
    private int size;
    private Cell[][] board;
    public static Cell[][] staticBoard;


    // Constructors of the class.
    @Deprecated
    public Board(int lines, int column){

        this.lines = lines;
        this.column = column;
        this.board  = new Cell[lines][column];
    }

    public Board(File file){


        this.readBoardFile(file);
        staticBoard = board;
    }

    public Board(int size){

        this.board = new Cell[size][size];
        this.size  = size;



        buildBoard();
        staticBoard = board;
    }

    // Getters of the class.
    public Cell getCell(int i, int j){ return this.board[i][j];}
    public static Cell getCellAt(int i, int j){return staticBoard[i][j];}
    public int getLines() {return this.lines;}
    public int getColumn() {return this.column;}
    public int getSize() {return this.size;}

    // Setter methods of the class.
    public void placeBomb(int i, int j) {this.board[i][j] = new Trail(true);}
    public static void placeBombAt(int i, int j){staticBoard[i][j] = new Trail(true);}

    // Methods of the class.
    public void print(){

        for(int i = 0 ; i < this.board.length ; ++i){
            for(int j = 0 ; j < this.board[0].length ; ++j){

                System.out.print(this.board[i][j].toString());
            }

            System.out.print("\n");
        }
    }

    private void readBoardFile(File board){

        try {
            Scanner read = new Scanner(board);
            List<String> lines = new LinkedList<>();
            while(read.hasNextLine()){

                lines.add(read.nextLine());

                    // Place each line in a list.
                    // Convert the list in a matrix.
                    // Assume that the file always contains a playable configuration which has a shape of (2**n, 2**n)
            }
            int size = lines.size();


            this.lines = Character.getNumericValue(lines.get(0).split(" ")[0].charAt(0));
            this.column = Character.getNumericValue(lines.get(0).split(" ")[1].charAt(0));

            System.out.println(this.lines + " " + this.column);

            this.board = new Cell[this.lines][this.column];

            this.size = Character.getNumericValue(lines.get(0).split(" ")[0].charAt(0));

            for(int i = 1 ; i < this.lines + 1; ++i){

                String content = lines.get(i);
                String[] values = content.split(" ");

                System.out.println(lines.get(i));

                for(int j = 0 ; j < values.length ; ++j){

                    System.out.print(values[j].charAt(0) + " ");

                    this.assignTile(values[j].charAt(0), i - 1, j);
                }

                System.out.print("\n");
            }

            read.close();
            this.print();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void assignTile(char value, int i, int j){


        System.out.println("" + i + " " + j);
        switch (value){

            case 'T' -> this.board[i][j] = new Trail(false);
            case 'I' -> this.board[i][j] = new Indestructible();
        }
    }

    public void buildBoard(){

        fillItWithTrails();
        margin();
        //addObstacles(24);
        classicalObstacles(8);
        //isBoardValid();
        //if(!isBoardValid()) {buildBoard();}
    }

    public boolean isBoardValid(){

        return fill(1, 1);
    }

    public boolean fill(int x, int y){

        if(x == this.size - 2 && y == this.size - 2) return true;

        if((x >= 1 && x < this.size - 1) && (y >=  1 && y < this.size - 1)){

            if(this.board[x][y] instanceof Trail){


                // Suggestion, add a visited boolean matrix.
                fill(x, y + 1); // North.
                fill(x + 1, y); // West.
                fill(x, y - 1); // South.
                fill(x - 1, y); // East.
            }

        }

        return false;
    }

    public void margin(){

        for(int j = 0 ; j < this.size ; ++j){

            this.board[0][j] = new Indestructible();
            this.board[0][j].collision = true;

            this.board[this.size-1][j] = new Indestructible();
            this.board[this.size-1][j].collision = true;
        }

        for(int i = 1 ; i < this.size - 1; ++i){

            this.board[i][0] = new Indestructible();
            this.board[i][0].collision = true;

            this.board[i][this.size-1] = new Indestructible();
            this.board[i][this.size-1].collision = true;
        }
    }

    public void classicalObstacles(int numberOfLinesOrColumns){

        assert numberOfLinesOrColumns > 0;

        int position;
        int previous = 1;
        int flip;


        while(numberOfLinesOrColumns != 0){

            position = (int)(Math.random() * (this.size - 1)) + 1;

            flip = (int)(Math.random() * 2);

            if(flip == 1){

                // Add numberOfLinesOrColumns rows to the board.
                if((position > 1 && position < this.size - 1)){

                    for(int index = 0 ; index < this.size - 1 ; ++index){

                        if(this.board[index][position + previous] instanceof Trail)
                        {
                            this.board[index][position + previous] = new Indestructible();
                            this.board[index][position + previous].collision = true;
                        }

                    }
                } else {

                    while(!(position > 1 && position < this.size - 1)){


                        position =  (int)(Math.random() * (this.size - 1)) + 1;
                    }

                    for(int index = 0 ; index < this.size - 1 ; ++index){

                        if(this.board[index][position + previous] instanceof Trail){

                            this.board[index][position + previous] = new Indestructible();
                            this.board[index][position + previous].collision = true;
                        }

                    }
                }


                //previous = position;
                numberOfLinesOrColumns -= 1;
            } else if(flip == 0){

                // Add numberOfLinesOrColumns columns to the board.
                if((position > 1 && position < this.size - 1)){

                    for(int index = 0 ; index < this.size - 1 ; ++index){

                        if(this.board[position + previous][index] instanceof Trail){

                            this.board[position + previous][index] = new Indestructible();
                            this.board[position + previous][index].collision = true;
                        }

                    }
                } else {

                    while(!(position > 1 && position < this.size - 1)){


                        position =  (int)(Math.random() * (this.size - 1)) + 1;
                    }

                    for(int index = 0 ; index < this.size - 1 ; ++index){

                        if(this.board[position + previous][index] instanceof Trail){

                            this.board[position + previous][index] = new Indestructible();
                            this.board[position + previous][index].collision = true;
                        }

                    }
                }

                //previous = position;
                numberOfLinesOrColumns -= 1;
            }
        }
    }



    public void addObstacles(int numberOfObstacles){


        assert numberOfObstacles > 0;

        int xPos;
        int yPos;

        while(numberOfObstacles != 0){

            xPos = (int)(Math.random() * (this.size - 1)) + 1;
            yPos = (int)(Math.random() * (this.size - 1)) + 1;

            if(this.board[xPos][yPos] instanceof Trail && (xPos != 1 && yPos !=1)){

                this.board[xPos][yPos] = new Indestructible();
            } else {

                while(!(this.board[xPos][yPos] instanceof Trail && (xPos != 1 && yPos !=1))){

                    xPos =  (int)(Math.random() * (this.size - 1)) + 1;
                    yPos =  (int)(Math.random() * (this.size - 1)) + 1;
                }

                this.board[xPos][yPos] = new Indestructible();
            }

            numberOfObstacles -= 1;
        }
    }

    public void fillItWithTrails(){

        for(int i = 0 ; i < this.size ; ++i){
            for(int j = 0 ; j < this.size ; ++j){

                this.board[i][j] = new Trail(false);
            }
        }
    }

    //TODO: implement boardBuilder
    public void boardBuilder(){
        putUndestroyableBrick();
        //putDestroyableBrick();
    }

    //TODO: implement putDestroyableBrick
//    public void putDestroyableBrick(){
//        String [] terrain = {"trail"," trail", "brick"};
//        boolean[] bonus = {false, false, true};
//        Random rd = new Random();
//        for (int i = 1 ; i < hauteur; i++){
//            for (int j = 1; j < largeur; j++){
//                if (cells[i][j] == null){
//                    if (terrain[(rd.nextInt(0, 3))].equals("trail")){
//                        // 2/3 de probabilité pour avoir trail et 1/3 pour avoir brick
//                        cells[i][j] = new Trail(false);
//                    }
//                    else {
//                        // pour les bricks , 1/3 pour qu'elle contient un bonus
//                        cells[i][j] = new Destroyable(bonus[(rd.nextInt(0,3))]);
//                    }
//
//                }
//            }
//        }
//    }

    //poser les brick undestroyable sur le plateau
    public void putUndestroyableBrick() {
        //pour les murs qui sont au tour du plateau
        for(int i = 0; i< lines; i+= lines -1) {
            for(int j = 0; j< column; j++) {
                board[i][j]=new Indestructible();
            }
        }
        //pour les brick intérieures
        for(int i = 0; i< lines; i++) {
            for(int j = 0; j< column; j+= column -1) {
                board[i][j]=new Indestructible();
            }
        }
        for(int i = 2; i< lines -2; i+=2) {
            for(int j = 2; j< column -2; j+=2) {
                board[i][j]=new Indestructible();
            }
        }
    }
    //affichage du plateau
    public   void afficheBoard() {
        for(int i = 0; i< lines; i++) {
            for(int j = 0; j< column; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public Point getFreeTrail (){
        Point point = new Point();
        for (int i = 1; i < lines -1; i++){
            for (int j = 1; j < column -1; j++){
                if (board[i][j] instanceof Trail){
                    if (!((Trail) board[i][j]).containsBomb()){
                        point.x = i;
                        point.y = j;
                        return point;
                    }
                }
            }
        }
        return point;
    }

}
