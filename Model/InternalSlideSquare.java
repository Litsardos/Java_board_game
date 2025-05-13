public class InternalSlideSquare extends Square{

    /**
     * A constructor that initialize the square that represents the middle of a slide.
     * @param color represents the color of the square and the slide.
     * @param positiononboard represents the number of the square of our board.
     * @pre The parameters have to be not null.
     * @post Our internalSlide square has been initialized.
     */

    InternalSlideSquare(String color,int positiononboard){
        super(color,positiononboard);
    }

}