package bomberman.upc;

import bomberman.upc.components.architecture.Player;
import bomberman.upc.components.gameElements.board.Board;

import java.awt.*;
import java.util.ArrayList;

public class Configuration {

    /**
     * @author rayane
     * @version 0.2
     * cette classe permet de configurer le jeu c-a-d toutes les configurations necessaries sont dans cette classe
     * y compris le mode , le nombre de joueur humain et IA
     * il peut etre vu comme model
     */

    /* cette class sera utilisée pour configurer le jeu dont le mode, nombre de joueur , activation de l'IA
        avec des methodes qui renvoient le Board rempli selon ces derniers
     */

    private int mode;           // mode de jeu,  à ameliorer plus tard...
    private Point size;   // la taille de la Board sous forme de point(hauteur, largeur)
    private ArrayList<Player> players;
    Board board;



    //constructor


    public Player getPlayer (int i){
        return players.get(i);
    }



}
