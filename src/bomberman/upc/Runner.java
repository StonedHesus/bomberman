package bomberman.upc;

import javax.swing.SwingUtilities;
import bomberman.upc.GUI.GameView;

public class Runner {
    /**
     *
     * This here class is the compilation unit which instantiates the game view and sets hitherto in motion
     * the different intricacies, such as the generation of the map, placements of obstacles, mobs and bonuses etcetera.
     *
     * @author Andrei-Paul Ionescu
     * version 0.01
     */

    public static void main( String[] args ){

        SwingUtilities.invokeLater(()->{

            new GameView();
        });
    }
}
