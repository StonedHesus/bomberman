package bomberman.upc.components.gameElements.background;

public abstract class Cell {

    // Attributes of the class.
    public boolean hasBonus;       //if cell contain bonus or not
    public static  int size=64;                  //nbr de pixels de chaque case
    public boolean collision = false;

    // Constructors of the class.
    public Cell(boolean hasBonus){
        this.hasBonus = hasBonus;
    }

    // Methods of the class.
    public abstract String toString();


}
