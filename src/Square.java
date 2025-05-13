public abstract class Square{
    private int positiononboard;
    private String color;
    private boolean IsPawnOnTop;
    private Pawn PawnOnTop=null;
    /**
     * A constructor that initialize a SIMPLE square of the board.
     * @param positiononboard An int that represent a position on the board that the square is going to be on.
     * @pre The parameter has to be not null.
     * @pre The positiononboard has to be a real position on the board.
     * @post The square is going to be initialized.
     */
    public Square(int positiononboard){
        this.color="white";
        this.positiononboard=positiononboard;
        this.IsPawnOnTop=false;
    }
    /**
     * A constructor that initialize a square of the board that has a player's color.
     * @param positiononboard An int that represent a position on the board that the square is going to be on.
     * @param color A string that has the color of the player.
     * @pre The parameters have to be not null.
     * @pre The color has to be one of the player's.
     * @pre The positiononboard has to be a real position on the board.
     * @post The square is going to be initialized.
     */
    public Square(String color,int positiononboard){
        this.color=color;
        if(positiononboard==-1){
            this.IsPawnOnTop=true;
        }
        else{
            this.IsPawnOnTop=false;
        }
        this.positiononboard=positiononboard;
    }
    /**
     * An accessor that returns if the square has a pawn on it.
     * @return A true if a pawn is on top of the square of false if it is not.
     */
    public boolean isPawnOnTop(){
        return IsPawnOnTop;
    }
    /**
     * A transformer that places a pawn on the top of the square.
     * @param pawn The pawn that is going to be placed on top of the square.
     */

    public void placePawnOnTop(Pawn pawn){
        this.IsPawnOnTop=true;
        this.PawnOnTop=pawn;
    }
    /**
     * A transformer that removes a pawn from the top of the square.
     * @pre The square need to has a pawn on top of it.
     * @post The square won't have a pawn on top of it.
     */

    public void removePawnFromTop1(){
        this.IsPawnOnTop=false;
        this.PawnOnTop=null;
    }
    /**
     * An accessor that return the pawn that is on top of the square.
     * @pre A pawn has to be on top of the square.
     * @return The pawn that is on top of the square.
     */
    public Pawn returnPawn(){
        if(PawnOnTop==null){
            return null;
        }
        return PawnOnTop;
    }
    /**
     * An accessor that returns the position of the square on the board.
     * @return An int that represents a position on the board.
     */
    public int returnboardposition(){
        return positiononboard;
    }

    /**
     * An accessor that returns the color of the square on the board.
     * @return A string that represents a color on the board.
     */
    public String returncolor(){
        return color;
    }
}