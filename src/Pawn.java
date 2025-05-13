import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Pawn {

    private String color;
    private int boardsquare;
    private int number;
    private boolean isstillplaying;
    private boolean isinsafety;
    private boolean isinstart;
    private int prevboardsquare;
    /**
     * A constructor the initialize the player's pawn.
     * @param color Represent the player's color that will pass to the pawn.
     * @pre The color has to be not null.
     * @post The pawn has been initialized. 
     */

    public Pawn(String color,int number){
        this.color=color;
        this.boardsquare=-1;
        this.number=number;
        this.isstillplaying=true;
        this.isinsafety=true;
        this.isinstart=true;
    }
    /**
     * A transformer that getting the pawn out of the game.
     */
    public void finished(){
        isstillplaying=false;
        isinsafety=true;
    }

    /**
     * A transformer that setting the pawn in the safety of getting it out of it.
     * @param safety Represents true if the pawn is on safety and false if it is not.
     */
    public void setsafety(boolean safety){
        isinsafety=safety;
    }

    /**
     * An accessor the returns the pawn's position on the board.
     * @return an int that represents a square on the board.
     */
    public int getboardsquare(){
        return this.boardsquare;
    }
    /**
     * An accessor that returns the pawn's color;
     * @return an int that represents the pawn's color.
     */
    public String getcolor(){
        return color;
    }
    /**
     * An accessor that returns if the pawn is on safety or not.
     * @return a boolean that represent if the pawn is on safety or not.
     */
    public boolean getsafety(){
        return isinsafety;
    }
    /**
     * An accessor that returns if the pawn is on the start square or not.
     * @return a boolean that represent is the pawn is on the start square or not.
     */
    public boolean getifstart(){
        return isinstart;
    }
    /**
     * A transformer that setting the pawn to the start.
     */
    public void returntostart(){
        isinstart=true;
        isinsafety=true;
        boardsquare=-1;
    }
    /**
     * A transformer that changes the position of the pawn.
     * @param position represent the number of the squares that the pawn has to move.
     * @pre The move has to be playable
     * @post The pawn moved.
     */
    public int movePawn(int position){
        JFrame frame = new JFrame("A got HOME.");
        if(color=="red"&&position>128){
            int steps;
            int steps1;
            if(prevboardsquare<=28){
                steps=position-(prevboardsquare+100);
                steps1=134-(prevboardsquare+100);
            }
            else{
                steps=position-prevboardsquare;
                steps1=134-prevboardsquare;
            }
            int steps2=steps-steps1;
            if(steps2>=0){
                position=134-steps2;
            }
            else{
                position=134+steps2;
            }
            if(position<=128){
                position=28+(128-position);
            }
           
        }
        else if(color=="yellow"&&position>158){
            int steps;
            int steps1;
            if(prevboardsquare<=58){
                steps=position-(prevboardsquare+100);
                steps1=164-(prevboardsquare+100);
            }
            else{
                steps=position-prevboardsquare;
                steps1=164-prevboardsquare;
            }
            int steps2=steps-steps1;
            if(steps2>=0){
                position=164-steps2;
            }
            else{
                position=164+steps2;
            }                    
            if(position<=158){
                position=58+(158-position);
            }
   
        }
        prevboardsquare=boardsquare;
        if(isinstart){
            isinstart=false;
            isinsafety=false;
        }
        boardsquare=position;
        if(boardsquare<100){
            boardsquare=boardsquare%60;
        }
        if(color=="red"){
            if(boardsquare==134){
                finished();
                JOptionPane.showMessageDialog(frame, "Your Pawn"+getnum()+" got HOME!");
            }
            else if(boardsquare>=129&&boardsquare<=134){
                setsafety(true);
            }
        }else{
            if(boardsquare==164){
                finished();
                JOptionPane.showMessageDialog(frame, "Your Pawn"+getnum()+" got HOME!");
            }
            else if(boardsquare>=159&&boardsquare<=164){
                setsafety(true);
            }
        }
        return boardsquare;
    }

    /**
     * An accessor the returns the pawn's previous position on the board.
     * @return an int that represents a square on the board.
     */
    public int returnprev(){
        return prevboardsquare;
    }

    /**
     * An accessor the returns if the pawn is playable.
     * @return a boolean that represents if the pawn is playable or not.
     */
    public boolean returnifisplayable(int squares,Board board){
        if(!isstillplaying){
            return false;
        }
        if(board.returnifempty(squares+boardsquare,prevboardsquare)){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * An accessor the returns if the pawn is finished.
     * @return a boolean that represents if the pawn has been finished.
     */
    public boolean returniffinished(){
        return !isstillplaying;
    }
    /**
     * An accessor the returns the path to the pawn's image.
     * @return a String that represents the pawn's image's path.
     */
    public String getImagePath() {
        return "src/" + color +"Pawn"+number+ ".png";
    }

    /**
     * An accessor the returns if the pawn's number.
     * @return an int that represents the pawn's number.
     */
    public int getnum(){
        return number;
    }
}