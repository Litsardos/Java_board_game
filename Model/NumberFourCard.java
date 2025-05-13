public class NumberFourCard extends NumberCard{

    /**
     * A constructor that initialize the card number four.
     */
    public NumberFourCard(){
        super("Move a pawn four spaces back (opposite of clockwise).", "FOUR", 4,"backward");
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    @Override
    public void movePawn(Pawn a, Board board){}


}
