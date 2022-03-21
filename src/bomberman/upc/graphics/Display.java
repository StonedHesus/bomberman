package bomberman.upc.graphics;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import bomberman.upc.Testing;
import bomberman.upc.components.architecture.HumanPlayer;
import bomberman.upc.components.gameElements.background.Cell;
import bomberman.upc.components.gameElements.background.Trail;
import bomberman.upc.components.gameElements.board.Board;
import bomberman.upc.components.gameElements.background.Indestructible;
import bomberman.upc.controller.CollisionChecker;

public class Display extends JPanel implements KeyListener, ActionListener{

    /**
     * The display class is responsible for the visuals of the game, thus it serves the graphical information directly
     * to the GameView, which is a JFrame.
     *
     *
     * @author: Andrei-Paul Ionescu
     * @version: 0.03
     */

    // Attributes of the class.
    public final int size;
    public static final int tileSize = 64;
    public static int staticSize;

    public Point theme;

    private HumanPlayer player;
    private Timer timer;
    private final int delay = 25;

    private final Board board;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public static int gameState;
    public static int playing = 1;
    public static int paused  = 2;

    public static int bombs = 0;

    // Constructor of the class.
    public Display() {

        String parent = this.getClass().getClassLoader().getResource(".").toString();
        parent = parent.split("/out")[0];
        parent = parent + "/src/bomberman/upc/components/gameElements/board/boards/example.board";
        parent = parent.split("file:")[1];

        this.board = new Board(new File(parent));

        this.size = board.getSize();
        staticSize = size;
        setPreferredSize(new Dimension(this.board.getColumn()*tileSize, this.board.getLines()*tileSize));


        this.theme = pickTheme();
        this.player = new HumanPlayer("", new Point(0, 0));

        gameState = playing;

        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(delay, this);
        timer.start();

    }

    // TODO: FIX THE MANNER IN WHICH THE FILE IS READ SO AS TO ALLOW MAPS WHICH ARE NOT PERFECT SQUARES TO BE RENDERED.

    @Deprecated
    public Display(int size) {

        this.size = size;
        staticSize = size;
        setPreferredSize(new Dimension(size*tileSize, size*tileSize));

        String parent = Testing.class.getClassLoader().getResource(".").toString();
        parent = parent.split("/out")[0];
        parent = parent + "/src/bomberman/upc/components/gameElements/board/boards/example.board";
        parent = parent.split("file:")[1];

        this.board = new Board(new File(parent));
        this.theme = pickTheme();
        this.player = new HumanPlayer("", new Point(0, 0));

        gameState = playing;

        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(delay, this);
        timer.start();

    }

    // Getters of the class.
    public int getTileSize() {return this.tileSize;}
    public static int getDisplaySize() {return Display.tileSize * (Display.staticSize -  1);}
    public int getDimension() {return this.size;}

    // Methods of the class.
    @Override
    public void keyPressed(KeyEvent event){


        player.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {

        player.keyReleased(event);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private Point pickTheme(){

        int temporary = (int)(Math.random()*3) + 1;

        return new Point(temporary, temporary*10 + temporary);
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        drawBackground(g);
        g.drawImage(player.getPlayerImage(), player.getxPos(), player.getyPos(), this);


        if(gameState == paused){

            drawPauseScreen(g);
        }

        if(bombs != 0){

            // TODO: FIX THE DISPLAY OF THE BOMB.

            if(player.getPreviousDirection().equals("left")){

                g.drawImage(player.getBombImage(0), player.getxPos(), player.getyPos() + 20, this);

            } else
                g.drawImage(player.getBombImage(0), player.getxPos(), player.getyPos(), this);

            bombs -= 1;
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawPauseScreen(Graphics graphics){

        graphics.setFont(Font.getFont(String.valueOf(Font.BOLD)));
        String text = "GAME PAUSED";

        graphics.setColor(Color.white);



        int x = getXForPauseScreenText(text);
        int y = this.board.getColumn()*tileSize/2;

        graphics.drawString(text, x, y);
    }

    private int getXForPauseScreenText(String text){

        int length = (int)this.getGraphics().getFontMetrics().getStringBounds(text, this.getGraphics()).getWidth();
        return this.size * tileSize/2 - length/2;
    }

    private void drawBackground(Graphics graphics){
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource(
                "bomberman/upc/graphics/sprites/ground_" +
                theme.x + ".png"));
        ImageIcon backgroundWhichContainsObstacles = new ImageIcon(ClassLoader.getSystemResource(
                "bomberman/upc/graphics/sprites/ground_" +
                theme.y + ".png"));
        ImageIcon firstIndestructible = new ImageIcon(ClassLoader.getSystemResource(
                "bomberman/upc/graphics/sprites/blocks/block_" + theme.x + ".png"));

        ImageIcon secondIndestructible = new ImageIcon(ClassLoader.getSystemResource(
                "bomberman/upc/graphics/sprites/blocks/block_" + theme.y + ".png"));

        ImageIcon bomb = new ImageIcon(ClassLoader.getSystemResource(
                "bomberman/upc/graphics/sprites/environment_12.png"
        ));

        for (int i = 0; i < this.board.getLines()  ; ++i){
            for(int j = 0; j < this.board.getColumn()  ; ++j){

                Cell temporary = board.getCell(i, j);

                if(temporary instanceof Trail){

                    graphics.drawImage(background.getImage(),
                            i * this.tileSize, j * this.tileSize,this);

//                    if(((Trail) temporary).containsBomb()){
//
//                        System.out.println("I am going to explode!");
//
//                        graphics.drawImage(bomb.getImage(), i* tileSize, j *tileSize, this);
//
//                    }
                } else if(temporary instanceof Indestructible){


                    graphics.drawImage(backgroundWhichContainsObstacles.getImage(),
                          i * this.tileSize, j * this.tileSize,this);
                    graphics.drawImage(firstIndestructible.getImage(),
                            i * this.tileSize, j * this.tileSize,this);


//
//                    if(j == 0 || i == 0 || j == this.board.getColumn() - 1 || i == this.board.getLines() - 1){
//
//                        graphics.drawImage(firstIndestructible.getImage(),
//                                i * this.tileSize, j* this.tileSize, this
//                        );
//                    } else {
//
//                        graphics.drawImage(secondIndestructible.getImage(),
//                                i * this.tileSize, j* this.tileSize, this
//                        );
//                    }
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(gameState == playing){

            player.move();
            try {
                player.playerMovement();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        repaint();
    }
}
