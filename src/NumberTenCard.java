public class NumberTenCard extends NumberCard{
    /**
     * A constructor that initialize the card number ten.
     */
    public NumberTenCard(){
        super("Move a pawn ten spaces forward or one space back back. If none of a player's pawns can advance 10 spaces forward, then a pawn must be moved back one position if of course such a move is possible.","TEN",10,"forward");
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    @Override
    public void movePawn(Pawn a, Board board) {}

    
}
