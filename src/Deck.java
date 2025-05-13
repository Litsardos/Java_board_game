import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>(44);
    /**
     * A constructor that use some methods to initialize the deck of the cards. 
     */
    public Deck() {
        putcardsindeck();
        shuffleDeck();
    }
    /**
     * A accessor that put all the cards in the deck.
     * @pre The deck has to be empty.
     * @para The deck has all the cards we need.
     */
    public void putcardsindeck() {
        for(int i=0;i<4;i++){
            SorryCard sorry=new SorryCard();
            NumberOneCard card1=new NumberOneCard();
            NumberTwoCard card2=new NumberTwoCard();
            NumberCard card3=new NumberCard("Move all your pawns three spaces forward (according to the time of the hands of the clock). If it is not possible to move all your pawns, then you simply move the pawn you can.", "THREE", 3, "forward");
            NumberFourCard card4=new NumberFourCard();
            NumberCard card5=new NumberCard("Move all your pawns five spaces forward (according to\r\n" + //
                    "clockwise). If it is not possible to\r\n" + //
                    "move all your pawns, then you simply move the pawn that\r\n" + //
                    "you can.", "FIVE", 5, "forward");
            NumberSevenCard card7=new NumberSevenCard();
            NumberCard card8=new NumberCard("Move a pawn eight spaces forward or draw 1 new one card", "EIGHT", 8, "forward");
            NumberTenCard card10=new NumberTenCard();
            NumberElevenCard card11=new NumberElevenCard();
            NumberCard card12=new NumberCard("Move a pawn twelve spaces forward or draw 1 new one card.", "TWELVE", 12, "forward");
            deck.add(sorry);
            deck.add(card1);
            deck.add(card2);
            deck.add(card3);
            deck.add(card4);
            deck.add(card5);
            deck.add(card7);
            deck.add(card8);
            deck.add(card10);
            deck.add(card11);
            deck.add(card12);
        }
    }
    /**
     * A accessor that shuffle the deck.
     * @pre The deck has to be not null.
     * @post The deck is now shuffled.
     */

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * A accessor that "pulls" a card from the deck and returns it.
     * @pre The deck has to be not null.
     * @post One card has been pulled from the deck.
     * @return The card.
     */

    public Card pullAcard() {
         if (!deck.isEmpty()) {
            Card pulledCard = deck.remove(0);
            if(deck.isEmpty()){
                putcardsindeck();
                shuffleDeck();
            }
            return pulledCard;
        }
        return null;
    }
}