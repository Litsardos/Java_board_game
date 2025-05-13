public class NumberTwoCard extends NumberCard{
    /**
     * A constructor that initialize the card number two.
     */
    public NumberTwoCard(){
        super("With this card a pawn can start from the Start position or to move a pawn two spaces forward (according to the turn of the hands of the clock). With this card the player must play again.","TWO",2,"forward");
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    @Override
    public void movePawn(Pawn a, Board board) {}

    //@Override
    //public boolean isitplayable(Pawn a, Board board){}

}
