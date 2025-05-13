public class NumberCard extends Card{

    int Number;
    String direction;

    /**
     *  A contractor that initialize the Number card.
     * @param description Represents the description of the card.
     * @param cardname Represents the number of the card in a string.
     * @param Number Represents the number of the card in a int.
     * @param direction Represents the pawn's possible direction using this card.
     * @pre The parameters have to be not null.
     * @para The number card has been initialized.
     */

    public NumberCard(String description, String cardname,int Number,String direction){
        super(description, cardname);
        this.Number=Number;
        this.direction=direction;
    }
    /**
     * An accessor the returns the number of the card.
     * @return The number of the card.
     */
    public int returnnum(){
        return Number;
    }
    /**
     * An accessor the returns the card's path.
     * @return A string that is a path to an image.
     */

    @Override
    public String getImagePath() {
        return "src/card" + Number + ".png";
    }

}