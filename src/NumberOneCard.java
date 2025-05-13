public class NumberOneCard extends NumberCard{
    /**
     * A constructor that initialize the card number one.
     */
    public NumberOneCard(){
        super("With this card a pawn can start from the Start position.Alternatively, the player may move a pawn one space to forward (clockwise).", "ONE", 1,"forward");
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    @Override
    public void movePawn(Pawn a, Board board) {}

    //@Override
    //public boolean isitplayable(Pawn a, Board board){}


}
