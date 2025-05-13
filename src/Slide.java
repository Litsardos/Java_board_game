public class Slide{

    private StartSlideSquare startSquare;
    private EndSlideSquare endSquare;
    private InternalSlideSquare[] middleSquares=new InternalSlideSquare[3];
    /**
     * A constructor that initialize the slide.
     * @param color Represents a string with the slide's color.
     * @param startSquare Represents the square at the start of the slide.
     * @param middleSquares Represents the squares that are in the middle of the slide.
     * @param endSquare Represents the square at the end of the slide.
     * @pre The parameters have to be not null.
     * @post The slide has been initialized.
     */
    public Slide(String color,StartSlideSquare startSquare,InternalSlideSquare[] middleSquares,EndSlideSquare endSquare){
        this.startSquare=startSquare;
        this.endSquare=endSquare;
        for(int i=0;i<middleSquares.length;i++){
            this.middleSquares[i]=middleSquares[i];
        }
    }
    /**
     * A accessor that returns if any pawn is in the slide.
     * @pre The pawn has to be initialized.
     * @return A boolean that tells us if any pawn is in the slide.
     */
    public boolean returnifpawninslide(){
        for(int i=0;i<middleSquares.length;i++){
            if(middleSquares[i].isPawnOnTop()==true)
                return true;
        }
        return false;
    }
    /**
     * A transformer that is activating the slide.
     * @param pawn The pawn that uses the slide.
     * @pre The pawn isn't null.
     * @post The pawn is going at the end of slide.
     * @post Any pawn that is in the internal of the slide is going back to start.
     */
    public void useSlide(Pawn pawn){
        for(int i=0;i<middleSquares.length;i++){
            if(middleSquares[i].isPawnOnTop()==true){
                middleSquares[i].returnPawn().returntostart();
            }
        }
        pawn.movePawn(endSquare.returnboardposition()-startSquare.returnboardposition());
        endSquare.placePawnOnTop(pawn);
    }

}