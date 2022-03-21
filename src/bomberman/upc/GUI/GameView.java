package bomberman.upc.GUI;


import javax.swing.*;
import java.awt.*;

import bomberman.upc.graphics.Display;


public class GameView extends JFrame{
    /**
     *
     *
     * author Andrei-Paul Ionescu
     * version 0.01
     */

    //TODO: CHANGE THE DATA TYPE UTILISED BY THE GAME SO THAT IT STORES EVERYTHING IN GRAPHICS AS OPPOSED TO BUTTONS
    // AND LABELS, AND MAKE SURE THAT EVENTS ARE PROPERLY INTERCEPTED AND THE ACTIONS BASED ON THE EVENTS WHICH ARE
    // TRIGGER ARE SMOOTHLY IMPLEMENTED.


    // Attributes of the class.
    private int size;
    private Display display;
    private JButton[][] graphicalRepresentation;
    private Point theme;

    private boolean movingLeft  = false;
    private boolean movingRight = false;
    private boolean movingUp    = false;
    private boolean movingDown  = false;

    JLabel player;

    // Constructors of the class.
    @Deprecated
    public GameView(int size){
        /**
         *
         *
         * @author: Andrei-Paul Ionescu
         * @version: 0.01
         */

        this.initialiseWindow(size);

    }

    public GameView(){
        /**
         *
         *
         * @author: Andrei-Paul Ionescu
         * @version: 0.01
         */

        this.initialiseWindow();

    }

    // Methods of the class.
    private Point pickTheme() {return new Point(((int)Math.random()*4) + 1, this.getX() + 1);}

    private void initialiseWindow(){
        /**
         *
         *
         * @author: Andrei-Paul Ionescu
         * @version: 0.01
         */

        this.display = new Display();
        this.size = this.display.getDimension();
        this.theme = this.pickTheme();

        setTitle("Bombarman");

        add(this.display);
        addKeyListener(display);
        setResizable(false);
        pack();


        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Deprecated
    private void initialiseWindow(int size){
        /**
         *
         *
         * @author: Andrei-Paul Ionescu
         * @version: 0.01
         */

        this.size = size;
        this.graphicalRepresentation = new JButton[this.size][this.size];
        this.display = new Display(this.size);
        this.theme = this.pickTheme();

        setTitle("Bombarman");

        add(this.display);
        addKeyListener(display);
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
