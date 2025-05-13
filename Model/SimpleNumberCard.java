public class SimpleNumberCard extends NumberCard {

    private boolean candrawanothercard;
    /**
     *  A contractor that initialize the SimpleNumber card.
     * @param description Represents the description of the card.
     * @param cardname Represents the number of the card in a string.
     * @param Number Represents the number of the card in a int.
     * @param direction Represents the pawn's possible direction using this card.
     * @pre The parameters have to be not null.
     * @post The simpleNumber card has been initialized.
     */
    public SimpleNumberCard(String description, String cardname,int Number,String direction){
        super(description, cardname, Number, "forward");
        if(Number==8||Number==12){
            candrawanothercard=true;
        }
        else{
            candrawanothercard=true;
        }
    }
    /**
     * here i am doing Override to the methods from the abstract class so i can use here only the methods that i need.
     */
    @Override
    public void movePawn(Pawn a, Board board) {}

    /*@Override
    public boolean isitplayable(Pawn a, Board board){}

    /**
     * A accessor that tells us if the player can draw another card on not.
     * @return True if the player can draw another card or false if he can't.
     */
    //public boolean returncandraw(){}

}
