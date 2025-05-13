import javax.swing.ImageIcon;

public abstract class Card{

    private String description;
    private String cardname;
    /**
     * A constructor that initialize each card.
     * @param description A string which has the info of the card.
     * @param cardname A string which has the name of the card.
     * @pre The parameters have to be not null.
     * @post Our private variables are initialized.
     */
    public Card(String description,String cardname) {
        this.description = description;
        this.cardname=cardname;
    }
    /**
     * A transformer that change the location of the Pawn on the board.
     * @param a is a Pawn.
     * @param board is our board.
     * @pre The move has to be playable.
     * @post The pawn is moving depending on the card.
     */
    public void movePawn(Pawn a,Board board){}

    /**
     * A transformer that change each other's pawn's position.
     * @param a The first pawn.
     * @param b The second pawn.
     * @pre The move has to be playable.
     * @post The two pawns are moving depending on the card.
     */

    /**
     * A transformer that moves pawn's position.
     * @param a the first pawn.
     * @param b the second pawn.
     * @pre the a pawn and the b pawn have to be initialized before.
     * @pre the move has to be playable.
     * @post the pawns have been moved.
     */
    public void movePawn(Pawn a,Pawn b){}

    /**
     * An accessor that will get the card's image's path.
     * @return a String that is a path to an image.
     */
    public String getImagePath() {
        return "src/card"+cardname+".png";
    }

    /**
     * An accessor that will return an image from a specific path.
     * @return an image
     */

    public ImageIcon getImage() {
        String imagePath = getImagePath();  // Replace with the actual path
        return new ImageIcon(imagePath);
    }

    /**
     * An accessor that returns a String that represents a card's name.
     * @return A name
     */
    public String getname(){
        return cardname;
    }
    /**
     * An accessor that returns a String that represents a card's description.
     * @return A card's description.
     */
    public String getdescription() {
        return description;
    }

}