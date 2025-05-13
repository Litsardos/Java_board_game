public class StartSquare extends Square {
    /**
     * A constructor that initialize the starting square.
     * @param color A string that represent the player's color.
     * @pre The color has to match the player's color.
     */
    public StartSquare(String color,int positiononboard){
        super(color,positiononboard);
    }
}