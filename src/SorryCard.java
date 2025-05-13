public class SorryCard extends Card {
    /**
     *  A contractor that initialize the Sorry card.
     */
    public SorryCard() {
        super("Take one of your pawns that is in the Start position (only) and move it directly to a position occupied by the pawn of an opponent, sending the opponent's pawn back to his ownStart position.The card cannot be used for an opponent's pawn in a safe area.If there are no pawns in the Start position of the playing player or the opponent's pawns cannot be moved (eg if they are in their secure area), then the card is simply declined and the order changes.", "SORRY");
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    @Override
    public void movePawn(Pawn a,Pawn b){}

    /*@Override
    public boolean isitplayable(Pawn a,Pawn b){
        if(a.getifstart()&&!b.getsafety()){
            return true;
        }
        else{
            return false;
        }
    }*/

}