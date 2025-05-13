public class Player{
    private String color;
    private String name;
    private Pawn Pawn1;
    private Pawn Pawn2;
    private int number;
    /**
     * A constructor that initialize the player.
     * @param color Represents a string with the player's color.
     * @param name Represents a string with the player's name.
     * @param Pawn1 Represents the first pawn that the player has.
     * @param Pawn2 Represents the second pawn that the player has.
     */
    public Player(String color,int number,String name,Pawn Pawn1,Pawn Pawn2){
        this.color=color;
        this.name=name;
        this.number=number;
        this.Pawn1=Pawn1;
        this.Pawn2=Pawn2;
    }
    /**
     * An accessor that returns if the player's pawns have been finished.
     * @return a boolean if the player has been finished or not.
     * @pre The two pawns have to be not null.
     */
    public boolean iswinner(){
        return Pawn1.returniffinished() && Pawn2.returniffinished();
    }
    /**
     * An accessor that returns player's name.
     * @return A string that represents a name.
     */
    public String getname(){
        return name;
    }

    /**
     * An accessor that returns player's number.
     * @return An int that represents a number.
     */
    public int getnumber(){
        return number;
    }

    /**
     * An accessor that returns a specific player's pawn.
     * @param i Represent the first pawn if it is 1 or the second if it 2.
     * @return A string that represents a number.
     */
    public Pawn getPawn(int i){
        if(i==1){
            return Pawn1;
        }
        else{
            return Pawn2;
        }
    }
    /**
     * An accessor that returns player's color.
     * @return A string that represents a color.
     */
    public String getcolor(){
        return color;
    }
}