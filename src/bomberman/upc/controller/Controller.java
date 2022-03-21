package bomberman.upc.controller;

import bomberman.upc.components.gameElements.background.Obstacle;
import bomberman.upc.components.gameElements.board.Board;
import bomberman.upc.graphics.Display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Controller {

    // Attributes of the class.
    private Point pos;
    private double velocity = 3.5;
    private BufferedImage image;

    private double xMovement;
    private double yMovement;

    private int xPos = Display.tileSize;
    private int yPos = Display.tileSize;

    private boolean bombAvailability = true;

    private String direction = "";
    private String previousDirection = "";

    private int spriteLifeSpan = 0;
    private boolean change = false;


    // Constructors of the class.
    public Controller(){

        this.pos = new Point(0, 0);
    }

    public Controller(BufferedImage image){


        this.image = image;
        this.pos = new Point(1, 1);
    }

    // Setters of the class.
    public void setImage(BufferedImage image) {this.image = image;}

    // Getters of the class.
    public int getxPos() {return this.xPos;}
    public int getyPos() {return this.yPos;}
    public String getDirection() {return this.direction;}
    public Boolean getChange() {return this.change;}
    public String getPreviousDirection() {return this.previousDirection;}

    // Methods of the class.
    public boolean xBoundaries(){

        return (this.xPos >= Display.tileSize &&
                this.xPos < Display.getDisplaySize() - Display.tileSize);
    }

    public boolean yBoundaries(){

        return (this.yPos >= Display.tileSize &&
                this.yPos <= Display.getDisplaySize() - Display.tileSize );
    }

    public boolean xObstacle(boolean direction){

        if(direction){

            if(pos.y < Display.getDisplaySize()/Display.tileSize)
                 return Board.getCellAt(pos.x, pos.y + 1) instanceof Obstacle;
            else
                return true;
        } else {

            if(pos.y >= 2){

                return Board.getCellAt(pos.x, pos.y - 1) instanceof Obstacle;
            } else
                return true;
        }
    }

    public boolean yObstacle(boolean direction){

        if(direction)
            return Board.getCellAt(pos.x , pos.y + 1) instanceof Obstacle;
        else
            if(pos.y >= 2)
                return Board.getCellAt(pos.x , pos.y - 1) instanceof Obstacle;
            else
                return false;
    }

    public void move(){

        if(yBoundaries() && yObstacle(this.direction.equals("left")) == false) {yPos += yMovement;}
            else          {yPos += 1;}


            if(xBoundaries()) {xPos += xMovement;}
            else if(xPos%64 > Display.getDisplaySize()/Display.tileSize
                && xPos%64 < Display.getDisplaySize()/Display.tileSize ) {xPos -= 1;}
                    else {xPos += 1;}

        // TODO: CHECK THE BOTTOM AND RIGHT MARGINS SO AS TO ASCERTAIN CORRECTLY THAT I AM INSIDE THE PLANE.

        if((double)(this.pos.x/64)+0.5 < this.pos.x)
            this.pos.x = xPos/64 + 1;
        else
            this.pos.x = xPos/64;

        if((double)(this.pos.y/64)+0.5 < this.pos.y)
            this.pos.y = yPos/64 + 1;
        else
            this.pos.y = yPos/64;
       // System.out.println("" + yObstacle(this.direction == "left") + " " + pos.x + " " + pos.y);

    }

    public void draw(Graphics graphics, ImageObserver observer) {

        assert graphics != null;

        graphics.drawImage(
                image,
                pos.x * Display.tileSize,
                pos.y * Display.tileSize,
                observer
        );
    }

    public void keyPressed(KeyEvent event){

        assert event != null;
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_W) {

            this.yMovement = -velocity;
            direction="up";
            if(spriteLifeSpan == 2){

                if(change) change = false;
                else change = true;
                spriteLifeSpan = 0;
            }
        }
        else if (key == KeyEvent.VK_D) {

            this.xMovement = velocity;
            direction="right";

            if(spriteLifeSpan == 2){

                if(change) change = false;
                else change = true;
                spriteLifeSpan = 0;
            }
        }
        else if (key == KeyEvent.VK_S) {

            this.yMovement = velocity;
            direction="down";

            if(spriteLifeSpan == 2){

                if(change) change = false;
                else change = true;
                spriteLifeSpan = 0;
            }
        }
        else if (key == KeyEvent.VK_A) {

            this.xMovement = -velocity;
            direction="left";

            if(spriteLifeSpan == 2){

                if(change) change = false;
                else change = true;
                spriteLifeSpan = 0;
            }
        }
        else if (key == KeyEvent.VK_SPACE && bombAvailability) {
            Display.bombs += 1; bombAvailability = false;
            } // Place a bomb.

        else if(key == KeyEvent.VK_ESCAPE){

            if(Display.gameState == Display.playing){

                spriteLifeSpan = 0;
                Display.gameState = Display.paused;
            } else if(Display.gameState == Display.paused){

                spriteLifeSpan = 0;
                Display.gameState = Display.playing;
            }

        }

        spriteLifeSpan += 1;
    }

    public void keyReleased(KeyEvent event){

        assert event != null;

        int key = event.getKeyCode();

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_S) {this.yMovement = 0; direction = "";}
        else if(key == KeyEvent.VK_A || key == KeyEvent.VK_D) {this.xMovement = 0; direction = "";}

        if(key == KeyEvent.VK_SPACE) {this.bombAvailability = true;}

        this.previousDirection = direction;
    }
}
