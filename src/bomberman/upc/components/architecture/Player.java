package bomberman.upc.components.architecture;

import bomberman.upc.components.gameElements.background.Cell;
import bomberman.upc.components.gameElements.board.Board;
import bomberman.upc.components.gameElements.bombs.Bomb;
import bomberman.upc.components.gameElements.bombs.BombStandard;
import bomberman.upc.controller.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Player extends Controller {

    /**
     * This here class models an abstract player, an object which will be able to become later on either a human
     * controlled player or an AI controlled one.
     *
     * @author
     * @contributor Andrei-Paul Ionescu
     * @version 0.01
     */

    // Attributes of the class.
    private String pseudo;
    private final int lives;
    private final int currentHealth;
    private int velocity;
    private int numberOfBombs;
    protected Point posPx;         //posPx en px
    protected Point posCell;
    private Map<Bomb, Integer> bombs;
    private int coin;
    private char direction;
    protected BombStandard myBomb;
    private BufferedImage playerImage;

    private ArrayList<BufferedImage> bombsSprites;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    // Constructor of the class.
    public Player(String pseudo, Point posCell){

        super();
        this.posCell = posCell;
        this.posPx = new Point();
        this.posPx.setLocation(posCell.x* Cell.size- (Cell.size/2), posCell.y*Cell.size- (Cell.size/2));
        this.pseudo = pseudo;
        lives = 3;       //nombre of lives remaining
        this.currentHealth = this.lives; // The current health of a player can augment or decrement, but the initial
                                        // number of lives of a player remains unchanged, thus if the player receives
                                        // HP we can easily differentiate whether they had properly replenished
                                        // its standard life gauge or it has added some extra points.
        velocity = 2;  //for instance we can choose 10px for one movement
        bombs = new HashMap<Bomb, Integer>();
        bombs.put(new BombStandard(), 1);
        myBomb = new BombStandard();
        loadImage();
        loadBombSprites();

        this.solidArea = new Rectangle(12, 48 , 64, 64);

        setImage(playerImage);
    }

    // Getters of the class.
    public BufferedImage getPlayerImage() {return this.playerImage;}

    // Methods of the class.
    public BufferedImage getBombImage(int type){

        assert type <= this.bombsSprites.size();
        return this.bombsSprites.get(type);
    }

    public void loadBombSprites(){

        this.bombsSprites = new ArrayList<>();

        if(this.bombs.size() == 1){

            try {

                String parent = getClass().getClassLoader().getResource(".").toString();
                parent = parent.split("/out")[0];
                parent = parent + "/src/bomberman/upc/graphics/sprites/environment_12.png";
                parent = parent.split("file:")[1];

                this.bombsSprites.add(ImageIO.read(new File(parent)));
            } catch (IOException exc) {
                System.out.println("Error opening image file: " + exc.getMessage());
            }

        }
    }

    public void playerMovement() throws IOException {
        /**
         *
         * @author Andrei-Paul Ionescu
         * @version 0.01
         */

        String parent = getClass().getClassLoader().getResource(".").toString();
        parent = parent.split("/out")[0];
        parent = parent + "/src/bomberman/upc/graphics/sprites/player/";
        parent = parent.split("file:")[1];


        switch (this.getDirection()) {

            case "left" -> {

                if(this.getChange()){

                    this.setImage(ImageIO.read(new File(parent + "/left/left_2.png")));
                    playerImage = ImageIO.read(new File(parent + "/left/left_2.png"));
                } else{

                    this.setImage(ImageIO.read(new File(parent + "/left/left_1.png")));
                    playerImage = ImageIO.read(new File(parent + "/left/left_1.png"));
                }
            }

            case "right" -> {

                if(this.getChange()){

                    this.setImage(ImageIO.read(new File(parent + "/right/right_2.png")));
                    playerImage = ImageIO.read(new File(parent + "/right/right_2.png"));
                } else {

                    this.setImage(ImageIO.read(new File(parent + "/right/right_1.png")));
                    playerImage = ImageIO.read(new File(parent + "/right/right_1.png"));
                }
            }

            case "up" -> {

                if(this.getChange()){

                    this.setImage(ImageIO.read(new File(parent + "/up/up_2.png")));
                    playerImage = ImageIO.read(new File(parent + "/up/up_2.png"));
                } else {

                    this.setImage(ImageIO.read(new File(parent + "/up/up_1.png")));
                    playerImage = ImageIO.read(new File(parent + "/up/up_1.png"));
                }
            }

            case "down" -> {

                if(this.getChange()){

                    this.setImage(ImageIO.read(new File(parent + "/down/down_2.png")));
                    playerImage = ImageIO.read(new File(parent + "/down/down_2.png"));
                } else {

                    this.setImage(ImageIO.read(new File(parent + "/down/down_1.png")));
                    playerImage = ImageIO.read(new File(parent + "/down/down_1.png"));
                }
            }

            default -> {

                switch(this.getPreviousDirection()){

                    case "up" ->{

                        this.setImage(ImageIO.read(new File(parent + "/idle/idle_4.png")));
                        playerImage = ImageIO.read(new File(parent + "/idle/idle_4.png"));
                    }

                    case "left" ->{

                        this.setImage(ImageIO.read(new File(parent + "/idle/idle_3.png")));
                        playerImage = ImageIO.read(new File(parent + "/idle/idle_3.png"));
                    }

                    case "right" ->{

                        this.setImage(ImageIO.read(new File(parent + "/idle/idle_2.png")));
                        playerImage = ImageIO.read(new File(parent + "/idle/idle_2.png"));
                    }

                    case "down" ->{

                        this.setImage(ImageIO.read(new File(parent + "/idle/idle_1.png")));
                        playerImage = ImageIO.read(new File(parent + "/idle/idle_1.png"));
                    }
                }

            }

        }
    }


    private void loadImage() {
        /**
         *
         *
         * @author Andrei-Paul Ionescu
         * @version 0.01
         */

        try {

            String parent = getClass().getClassLoader().getResource(".").toString();
            parent = parent.split("/out")[0];
            parent = parent + "/src/bomberman/upc/graphics/sprites/player/idle/idle_1.png";
            parent = parent.split("file:")[1];

            playerImage = ImageIO.read(new File(parent));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }


    public void dropBomb(Board board){


    }

    @Override
    public String toString() {
        return "P";
    }
    //
    public Point getIndex(Point posPx)
    {
        Point p=new Point();
        p.setLocation((int)posPx.getX()/Cell.size,(int)posPx.getY()/Cell.size);
        return p;
    }
    //
    public boolean hasObstacle(Point p,Board board)
    {

//        int x= (int) getIndex(p).getX(),y=(int) getIndex(p).getY();
//        if(board.getBoard()[x][y] instanceof Obstacle){
//            return true;
//        }else {
//            if(board.getBoard()[x][y] instanceof Trail) {
//                if(((Trail) board.getBoard()[x][y]).getBombe()==null) {
//                    return true;
//                }
//            }
//        }
        return  false;
    }
}
