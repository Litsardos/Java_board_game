public class Board {

    private Square[][] board;
    /**
     * A constructor that initialize the board of the game.
     * @pre The board has't initialized yet.
     * @post The board has been initialized.
     */
    public Board(Pawn r1,Pawn r2,Pawn y1,Pawn y2){
        board=new Square[16][16];

        if(true){
            board[0][0]=new normalSquare(26);
            board[0][5]=new normalSquare(31);
            board[0][6]=new normalSquare(32);
            board[0][7]=new normalSquare(33);
            board[0][8]=new normalSquare(34);
            board[0][14]=new normalSquare(40);
            board[0][15]=new normalSquare(41);
            board[1][0]=new normalSquare(25);
            board[7][0]=new normalSquare(19);
            board[8][0]=new normalSquare(18);
            board[9][0]=new normalSquare(17);
            board[10][0]=new normalSquare(16);  //initialize the white blockes
            board[15][0]=new normalSquare(11);
            board[15][1]=new normalSquare(10);
            board[15][7]=new normalSquare(4);
            board[15][8]=new normalSquare(3);
            board[15][9]=new normalSquare(2);
            board[15][10]=new normalSquare(1);
            board[15][15]=new normalSquare(56);
            board[5][15]=new normalSquare(46);
            board[6][15]=new normalSquare(47);
            board[7][15]=new normalSquare(48);
            board[8][15]=new normalSquare(49);
            board[14][15]=new normalSquare(55);
        }
        if(true){
            board[0][1]=new StartSlideSquare("red",27 );
            board[0][2]=new InternalSlideSquare("red", 28);
            board[0][3]=new InternalSlideSquare("red", 29);
            board[0][4]=new EndSlideSquare("red", 30);
            board[0][9]=new StartSlideSquare("red",35 );        //initialize the red slides
            board[0][10]=new InternalSlideSquare("red", 36);
            board[0][11]=new InternalSlideSquare("red", 37);
            board[0][12]=new InternalSlideSquare("red", 38);
            board[0][13]=new EndSlideSquare("red", 39);
        }
        if(true){
            board[2][0]=new EndSlideSquare("green", 24);
            board[3][0]=new InternalSlideSquare("green", 23);
            board[4][0]=new InternalSlideSquare("green", 22);
            board[5][0]=new InternalSlideSquare("green", 21);
            board[6][0]=new StartSlideSquare("green",20 );      //initialize the green slides
            board[11][0]=new EndSlideSquare("green", 15);
            board[12][0]=new InternalSlideSquare("green", 14);
            board[13][0]=new InternalSlideSquare("green", 13);
            board[14][0]=new StartSlideSquare("green",12 );
        }
        if(true){
            board[15][2]=new EndSlideSquare("yellow", 9);
            board[15][3]=new InternalSlideSquare("yellow",8);
            board[15][4]=new InternalSlideSquare("yellow",7);
            board[15][5]=new InternalSlideSquare("yellow",6);
            board[15][6]=new StartSlideSquare("yellow", 5);         //initialize the yellow slides
            board[15][11]=new EndSlideSquare("yellow", 0);
            board[15][12]=new InternalSlideSquare("yellow",59);
            board[15][13]=new InternalSlideSquare("yellow",58);
            board[15][14]=new StartSlideSquare("yellow", 57);
        }
        if(true){
            board[1][15]=new StartSlideSquare("blue", 42);  
            board[2][15]=new InternalSlideSquare("blue",43);
            board[3][15]=new InternalSlideSquare("blue",44);
            board[4][15]=new EndSlideSquare("blue", 45);
            board[9][15]=new StartSlideSquare("blue", 50);          //initialize the blue slides
            board[10][15]=new InternalSlideSquare("blue",51);
            board[11][15]=new InternalSlideSquare("blue",52);
            board[12][15]=new InternalSlideSquare("blue",53);
            board[13][15]=new EndSlideSquare("blue", 54);
        }

        if(true){
            board[1][2]=new SafetyZoneSquare("red",129);
            board[2][2]=new SafetyZoneSquare("red",130);
            board[3][2]=new SafetyZoneSquare("red",131);    //initialize the red safety zone
            board[4][2]=new SafetyZoneSquare("red",132);
            board[5][2]=new SafetyZoneSquare("red",133);
            board[6][2]=new SafetyZoneSquare("red",134);
        }
        if(true){
            board[14][13]=new SafetyZoneSquare("yellow",159);
            board[13][13]=new SafetyZoneSquare("yellow",160);
            board[12][13]=new SafetyZoneSquare("yellow",161);    //initialize the yellow safety zone
            board[11][13]=new SafetyZoneSquare("yellow",162);
            board[10][13]=new SafetyZoneSquare("yellow",163);
            board[9][13]=new SafetyZoneSquare("yellow",164);
        }
        
        if(true){
            board[1][4]=new StartSquare("red",-1);
            board[2][4]=new StartSquare("red",-1);
            board[1][4].placePawnOnTop(r1);
            board[2][4].placePawnOnTop(r2);
            board[14][11]=new StartSquare("yellow",-1);
            board[13][11]=new StartSquare("yellow",-1);
            board[14][11].placePawnOnTop(y2);
            board[13][11].placePawnOnTop(y1);
        }
    }
    /**
     * An accessor that returns if the specific square in the [][] board is empty.
     * @param square which represents the specific square.
     * @param prevpawnsposition which is the pawn's previous position.
     * @return True if the square is empty and false if it is not.
     */
    public boolean returnifempty(int square,int prevpawnsposition){
        if(square>=60&&square<=80){
            square=square%60;
        }
        int x,y;
        if(square<=11||(square>=56&&square<=60)){
            y=15;
            x=(square+4)%60;
            if(board[y][15-x].isPawnOnTop()){
                return false;
            }
            else{
                return true;
            }
        }
        else if(square<=26){
            x=0;
            y=square-11;
            if(board[15-y][x].isPawnOnTop()){
                return false;
            }
            else{
                return true;
            }
        }
        else if(square<=41){
            y=0;
            x=square-26;
            if(board[y][x].isPawnOnTop()){
                return false;
            }
            else{
                return true;
            }
        }
        else if(square<=56){
            x=15;
            y=square-41;
            if(board[y][x].isPawnOnTop()){
                return false;
            }
            else{
                return true;
            }
        } 
        else if(square==134||square==164){
            return true;
        }
        else if(square>=129&&square<155){
            int steps;
                int steps1;
                if(prevpawnsposition<=28){
                    steps=square-(prevpawnsposition+100);
                    steps1=134-(prevpawnsposition+100);
                }
                else{
                    steps=square-prevpawnsposition;
                    steps1=134-prevpawnsposition;
                }
                int steps2=steps-steps1;
                if(steps2>=0){
                    square=134-steps2;
                }
                else{
                    square=134+steps2;
                }
                if(square<=128){
                    square=28+(128-square);
                }
                if(square>=129){
                    x=2;
                    y=(square+2)%10;
                    if(board[y][x].isPawnOnTop()){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                else{
                    y=0;
                    x=square-26;
                    if(board[y][x].isPawnOnTop()){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
        }
        else if(square>=159){
            int steps;
                int steps1;
                if(prevpawnsposition<=58){
                    steps=square-(prevpawnsposition+100);
                    steps1=164-(prevpawnsposition+100);
                }
                else{
                    steps=square-prevpawnsposition;
                    steps1=164-prevpawnsposition;
                }
                int steps2=steps-steps1;
                if(steps2>=0){
                    square=164-steps2;
                }
                else{
                    square=164+steps2;
                }                    
                if(square<=158){
                    square=58+(158-square);
                }
                if(square>=159){
                    x=13;
                    y=15-(square-158);
                    if(board[y][x].isPawnOnTop()){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                else{
                    y=15;
                    x=(square+4)%60;
                    if(board[y][15-x].isPawnOnTop()){
                        return false;
                    }
                    else{
                        return true;
                    }
                }

        }
        else{
            return false;
        }
    }

    /**
     * A transformer that will get any pawn out of the specific square of the board.
     * @param square is the square that we want to get empty.
     */
    public void displacepawnfromsquare(int square){
        if(square>60&&square<80){
            square=square%60;
        }
        int x,y;
        if(square<=11||(square>=56&&square<=60)){
            y=15;
            x=(square+4)%60;
            board[y][15-x].removePawnFromTop1();
        }
        else if(square<=26){
            x=0;
            y=square-11;
            board[15-y][x].removePawnFromTop1();
        }
        else if(square<=41){
            y=0;
            x=square-26;
            board[y][x].removePawnFromTop1();
        }
        else if(square<=56){
            x=15;
            y=square-41;
            board[y][x].removePawnFromTop1();
        }
        else if(square<=163&&square>=159){
            x=13;
            y=15-(square-158);
            board[y][x].removePawnFromTop1();
        }  
        else if(square<=133&&square>=129){
            x=2;
            y=(square+2)%10;
            board[y][x].removePawnFromTop1();
        }
    }
    /**
     * A transformer that will place a pawn on a specific square of the board.
     * @param square is the square that we want to get the pawn on top of it.
     * @param pawn is the pawn that we want to get on top of the square.
     * @pre the pawn has to be initialized.
     */
    public void placePawnOnTop1(int square,Pawn pawn){
        if(pawn.getboardsquare()==-1){
            
        }
        int x,y;
        if(square<=11||(square>=56&&square<=60)){

            y=15;
            x=(square+4)%60;
            board[y][15-x].placePawnOnTop(pawn);
        }
        else if(square<=26){

            x=0;
            y=square-11;
            board[15-y][x].placePawnOnTop(pawn);
        }
        else if(square<=41){

            y=0;
            x=square-26;
            board[y][x].placePawnOnTop(pawn);
        }
        else if(square<=56){

            x=15;
            y=square-41;
            board[y][x].placePawnOnTop(pawn);
        }
        else if(square<=163&&square>=159){

            x=13;
            y=15-(square-158);
            board[y][x].placePawnOnTop(pawn);
        }  
        else if(square<=133&&square>=129){

            x=2;
            y=(square+2)%10;
            board[y][x].placePawnOnTop(pawn);
        }


    }

    /**
     * An accessor that will return if a specific square is the middle of a slide.
     * @param square the square that we want to check.
     * @return true if the square it internalslidesquare and false if it is not.
     * @pre the board has to be initialized.
     */
    public boolean returnifinternalofslide(int square){
        if(square>60&&square<80){
            square=square%60;
        }
        int x,y;
        if(square<=11||(square>=56&&square<=60)){
            y=15;
            x=(square+4)%60;
            if(board[y][15-x] instanceof InternalSlideSquare){
                return true;
            }
            else{
                return false;
            }
        }
        else if(square<=26){
            x=0;
            y=square-11;
            if(board[15-y][x] instanceof InternalSlideSquare){
                return true;
            }
            else{
                return false;
            }
        }
        else if(square<=41){
            y=0;
            x=square-26;
            if(board[y][x] instanceof InternalSlideSquare){
                return true;
            }
            else{
                return false;
            }
        }
        else if(square<=56){
            x=15;
            y=square-41;
            if(board[y][x] instanceof InternalSlideSquare){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * An accessor that will return the pawn that is on the top of a specific square.
     * @param square the square that we want to take the pawn above it.
     * @return the pawn.
     * @pre the board has to be initialized.
     */
    public Pawn returnpawnontop(int square){
        int x,y;
        Pawn a=null;
        if(square<=11||(square>=56&&square<=60)){
            y=15;
            x=(square+4)%60;
            a=board[y][15-x].returnPawn();
        }
        else if(square<=26){
            x=0;
            y=square-11;
            a=board[15-y][x].returnPawn();
        }
        else if(square<=41){
            y=0;
            x=square-26;
            a=board[y][x].returnPawn();
        }
        else if(square<=56){
            x=15;
            y=square-41;
            a=board[y][x].returnPawn();
        }
        return a;
    }

    /**
     * An accessor that will return if a specific square is the start of a slide and it is playable for the specific color.
     * @param square the square that we want to check.
     * @param color the color of the pawn that we move.
     * @return true if the square is the start of a slide and we can play the move,and  false if we can't.
     * @pre the board has to be initialized.
     */
    public boolean returnifstartofslide(int square,String color){
        if(square>60&square<80){
            square=square%60;
        }
        int x,y;
        if(square<=11||(square>=56&&square<=60)){
            y=15;
            x=(square+4)%60;
            if(board[y][15-x] instanceof StartSlideSquare&&board[y][15-x].returncolor()!=color){
                return true;
            }
            else{
                return false;
            }
        }
        else if(square<=26){
            x=0;
            y=square-11;
            if(board[15-y][x] instanceof StartSlideSquare&&board[15-y][x].returncolor()!=color){
                return true;
            }
            else{
                return false;
            }
        }
        else if(square<=41){
            y=0;
            x=square-26;
            if(board[y][x] instanceof StartSlideSquare&&board[y][x].returncolor()!=color){
                return true;
            }
            else{
                return false;
            }
        }
        else if(square<=56){
            x=15;
            y=square-41;
            if(board[y][x] instanceof StartSlideSquare&&board[y][x].returncolor()!=color){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        
    }
   
}