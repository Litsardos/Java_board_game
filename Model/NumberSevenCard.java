public class NumberSevenCard extends NumberCard{
    /**
     * A constructor that initialize the card number seven.
     */
    public NumberSevenCard(){
        super("Move a pawn seven spaces forward or split the seven spaces forward between two pawns (such as four spaces for one pawn and three for another, five places for one pawn and two for it another or six positions for one pawn and one for the other). You can not move backwards with this card, but neither start a pawn from the Start position.","SEVEN",7,"forward");
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    //@Override
    //public void movePawn(Pawn a, Board board, Pawn b) {}

    //@Override
    //public boolean isitplayable(Pawn a,Pawn b,Board board){}

}
