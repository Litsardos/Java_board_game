import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Controller {
    

    private Board board;
    private Deck deck;
    private View view;
    private Player player1;
    private Player player2;
    private JButton startGameButton;
    private JButton imageButton;
    private JButton FoldButton;
    private String player1Name;
    private String player2Name;
    private int i=0;

    private int flag12_8=0;

    /**
     * A constructor that initialize the start button and call the initialize() fuction to do the rest initialization.
     */
    public Controller(){    //HERE I AM STARTING THE GAME BY INITIALING THE START BUTTON AND CALLING THE initialize() method
        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 30));
        startGameButton.setForeground(Color.BLACK);
        startGameButton.setBackground(Color.white);
        startGameButton.setFocusPainted(false);
        startGameButton.setBounds(400, 600, 400, 70);
        initialize();
    }
    /**
     * A transformer that initialize the deck,the buttons and the view.
     */
    public void initialize() {      //HERE I AM INITIALIZING THE VIEW,THE DECK AND THE  BUTTONS.
        view = new View(startGameButton,imageButton);
        imageButton=new JButton();
        ImageIcon originalIcon = new ImageIcon("src/backCard.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(140,180, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        FoldButton = new JButton("Fold Button");
        FoldButton.setFont(new Font("Arial", Font.BOLD, 20));
        FoldButton.setForeground(Color.BLACK);
        FoldButton.setBackground(Color.RED);
        FoldButton.setFocusPainted(false);
        FoldButton.setBounds(820, 425, 350, 50);

        imageButton = new JButton(resizedIcon);

        imageButton.setBounds(820, 200, 140, 180);
        createStartButtonListener();
        deck=new Deck();
        createreceivecardListener();
        createFoldbuttonListener();
    }

    /**
     * A transformer that creates the starting button at the start.
     * @pre The button has to be initialized before.
     * @post When the button is pressed it does specific moves.
     */
    private void createStartButtonListener() {      //MY START THE GAME BUTTON
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1Name = view.getPlayer1Name();
                player2Name = view.getPlayer2Name();

                Pawn a,b;
                a=new Pawn("red",1);
                b=new Pawn("red",2);
                player1=new Player("red",1,player1Name,a,b);                        
                a=new Pawn("yellow",1);
                b=new Pawn("yellow",2);
                player2=new Player("yellow",2,player2Name,a,b);   
                board=new Board(player1.getPawn(0),player1.getPawn(1),player2.getPawn(0),player2.getPawn(1));
                setAllTogether(imageButton);
                view.updateturn(player1);
                view.updatecards();
            }
        });
    }

    /**
     * A transformer that creates the button that receive cards.
     * @pre the button has to be initialized before.
     * @pre the deck has to be initialized before.
     * @pre the deck has to be not empty.
     * @post when the button is pressed, a card will be received from the deck.
     */
    private void createreceivecardListener() {      //MY CARD BUTTON
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card pulledCard = deck.pullAcard();
                if (pulledCard != null) {
                    view.updatelifted(pulledCard);
                    view.updateinfobox(pulledCard);
                    view.updatecards();
                    if(i%2==0){
                        playthemove(pulledCard,player1);
                    }
                    else{
                        playthemove(pulledCard,player2);
                    }
                    if(player2.iswinner()){
                        JOptionPane.showMessageDialog(null,"Congratulations to the winner: Player"+player2.getnumber()+" ("+player2.getname()+")");
                        System.exit(0);
                    }
                    else if(player1.iswinner()){
                        JOptionPane.showMessageDialog(null,"Congratulations to the winner: Player"+player1.getnumber()+" ("+player1.getname()+")");
                        System.exit(0);
                    }
                    if(pulledCard.getname()=="TWO"||flag12_8==1){
                        JOptionPane.showMessageDialog(null,"You play AGAIN!");
                        flag12_8=0;
                    }
                    else{
                        changeturn();
                        i++;
                    }
                }    
            }
        });
    }
        /**
     * A transformer that will fold player's turn.
     * @pre The player need to has the right to fold.
     * @pre The player need to has drawn a card.
     * @post The player give the turn to the next player.
     */
    private void createFoldbuttonListener(){        //MY FOLD BUTTON
        FoldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card pulledCard = deck.pullAcard();
                view.updatelifted(pulledCard);
                view.updateinfobox(pulledCard);
                view.updatecards();
                changeturn();
            }
        });
    }
    public NumberCard getNumberCard(Card card){     //HERE RETURN THE SPECIFIC CARD THAT THE PLAYED HAS PULLED
        NumberCard a;
        if(card.getname()=="ONE")
            a=new NumberCard(card.getdescription(), card.getname(), 1, "forward");
        else if(card.getname()=="TWO"){
            a=new NumberCard(card.getdescription(), card.getname(), 2, "forward");
        }
        else if(card.getname()=="THREE"){
            a=new NumberCard(card.getdescription(), card.getname(), 3, "forward");
        }
        else if(card.getname()=="FOUR"){
            a=new NumberCard(card.getdescription(), card.getname(), 4, "backward");
        }
        else if(card.getname()=="FIVE"){
            a=new NumberCard(card.getdescription(), card.getname(), 5, "forward");
        }
        else if(card.getname()=="SEVEN"){
            a=new NumberCard(card.getdescription(), card.getname(), 7, "forward");
        }
        else if(card.getname()=="EIGHT"){
            a=new NumberCard(card.getdescription(), card.getname(), 8, "forward");
        }
        else if(card.getname()=="TEN"){
            a=new NumberCard(card.getdescription(), card.getname(), 10, "forward");
        }
        else if(card.getname()=="ELEVEN"){
            a=new NumberCard(card.getdescription(), card.getname(), 11, "forward");
        }
        else{
            a=new NumberCard(card.getdescription(), card.getname(), 12, "forward");
        }
        return a;
    }

    /**
     * A transformer that will move the pawn and update the pawn's and square's stats.
     * @param card that is the pulled card.
     * @param   player that is the player that need to play the card.
     * @pre The move has to be playable.
     * @post The pawn's and square's stats have been updated.
     */
    public void playthemove(Card card,Player player){   // HERE I AM CHECKING IF THE MOVE IS PLAYABLE AND I AM USING THE view.updatepawn() TO MOVE THE PAWNS. I AM USING POSITION -1 IF I WANT THE PAWN TO GET BACK TO THE START AND FLAG 1 IF HE COME ACROSS THE SAFETY ZONE
        JFrame frame = new JFrame("Something wrong happened to you.");
        JFrame frame1 = new JFrame("Difficult decision...");
        if(card.getname()!="SORRY"){    //CHECK IF THE CARD IS SORRY OR NOT
            NumberCard pulledcard=getNumberCard(card);
            if(player.getPawn(1).getifstart()&&player.getPawn(2).getifstart()&&pulledcard.getname()!="ONE"&&pulledcard.getname()!="TWO"){   //CHECK IF THE PLAYER'S PAWNS ARE BOTH AT THE START AND IF HE CAN PLAY A MOVE WITH THE STARTING CARDS.
                JOptionPane.showMessageDialog(frame, "You can't play your card.");
            }
            else if(player.getPawn(1).getifstart()&&player.getPawn(2).getifstart()){        //CHECK IF THE PLAYER'S PAWNS ARE IN THE START
                if(pulledcard.getname()=="ONE"||pulledcard.getname()=="TWO"){   //IF THE PULLED CARD IS ONE OR TWO WHILE PLAYER'S PAWNS ARE ON THE START SQUARE
                    if(player.getcolor()=="red"){                       
                        if(player.getPawn(1).returnifisplayable(pulledcard.returnnum()+30,board)&&player.getPawn(2).returnifisplayable(pulledcard.returnnum()+30,board)){
                            Object[] options = {"Move First Pawn", "Move Second Pawn"};
                            int choice = JOptionPane.showOptionDialog(frame1,
                            "Choose which pawn to move:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                            if (choice == JOptionPane.YES_OPTION){
                                view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum()+30, player.getPawn(1), player,0);
                            }
                            else{
                                view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum()+30, player.getPawn(2), player,0);
                            }
                        }else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum()+30,board)){
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum()+30, player.getPawn(1), player,0);
                        }else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum()+30,board)){
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum()+30, player.getPawn(2), player,0);
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "You can't play your card.");
                        }
                    }
                    else{
                        if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                            Object[] options = {"Move First Pawn", "Move Second Pawn"};
                            int choice = JOptionPane.showOptionDialog(frame1,
                            "Choose which pawn to move:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                            if (choice == JOptionPane.YES_OPTION){
                                view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), player.getPawn(1), player,0);
                            }
                            else{
                                view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), player.getPawn(2), player,0);
                            }
                        }else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)){
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), player.getPawn(1), player,0);
                        }else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), player.getPawn(2), player,0);
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "You can't play your card.");
                        }
                    }
                }
                else{       //ELSE THE PLAYER CAN'T MOVE HIS PAWN
                    JOptionPane.showMessageDialog(frame, "You can't play your card.");
                }
            }
            else if(pulledcard.getname()=="THREE"||pulledcard.getname()=="FIVE"){       //ELEGXOS GIA NA DO AN EINAI 3 H 5 H TRABIGMENH KARTA
                if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(1).getifstart()&&!player.getPawn(2).getifstart()){
                    int i;
                    if(player.getnumber()==1){
                        i=0;
                    }
                    else{
                        i=30;
                    }
                    if(player.getPawn(1).getboardsquare()<=28+i&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28+i&&player.getPawn(2).getboardsquare()<=28+i&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(1), player,1);
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(2), player,1);
                    }
                    else if(player.getPawn(1).getboardsquare()<=28+i&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(1), player,1);
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                    }
                    else if(player.getPawn(2).getboardsquare()<=28+i&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(2), player,1);
                    }
                    else{
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                    }
                }
                else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(1).getifstart()){
                    int i;
                    if(player.getnumber()==1){
                        i=0;
                    }
                    else{
                        i=30;
                    }
                    if(player.getPawn(1).getboardsquare()<=28+i&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(1), player,1);
                    }
                    else{
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(1), player,0);
                    }
                }
                else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(2).getifstart()){
                    int i;
                    if(player.getnumber()==1){
                        i=0;
                    }
                    else{
                        i=30;
                    }
                    if(player.getPawn(2).getboardsquare()<=28+i&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(2), player,1);
                    }
                    else{
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(2), player,0);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "You can't play your card.");
                }
            }
            else if(pulledcard.getname()=="TWELVE"||pulledcard.getname()=="EIGHT"){       //ELEGXOS GIA NA DO AN EINAI 8 H 12 H TRABIGMENH KARTA
                if((!player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)||player.getPawn(1).getifstart())&&(!player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)||player.getPawn(2).getifstart())) {
                    JOptionPane.showMessageDialog(frame, "You can't play your card.\nDraw another card!");
                }
                else if((player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(1).getifstart())||(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(2).getifstart())) {
                    Object[] options = {"Play the card " + pulledcard.getname(), "Draw another card"};
                    int choice = JOptionPane.showOptionDialog(frame1,
                            "Would you like to play the card or draw another card?:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (choice == JOptionPane.YES_OPTION) {
                        int i;
                        if (player.getnumber() == 1) {
                            i = 0;
                        } else {
                            i = 30;
                        }
                        if ((player.getPawn(1).returnifisplayable(pulledcard.returnnum(), board) && !player.getPawn(1).getifstart()) && (player.getPawn(2).returnifisplayable(pulledcard.returnnum(), board) && !player.getPawn(2).getifstart())) {
                            Object[] optionss = {"Move First Pawn", "Move Second Pawn"};
                            int choicee = JOptionPane.showOptionDialog(frame1,
                                    "Choose which pawn to move:",
                                    "Pawn Movement",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    optionss,
                                    optionss[0]);
                            if (choicee == JOptionPane.YES_OPTION) {
                                if (player.getPawn(1).getboardsquare() <= 28 + i && player.getPawn(1).getboardsquare() + pulledcard.returnnum() > 28 + i) {
                                    view.updatepawn(player.getPawn(1).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(1), player, 1);
                                } else {
                                    view.updatepawn(player.getPawn(1).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(1), player, 0);
                                }
                            } else {
                                if (player.getPawn(2).getboardsquare() <= 28 + i && player.getPawn(2).getboardsquare() + pulledcard.returnnum() > 28 + i) {
                                    view.updatepawn(player.getPawn(2).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(2), player, 1);
                                } else {
                                    view.updatepawn(player.getPawn(2).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(2), player, 0);
                                }
                            }
                        }
                        else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(), board) && !player.getPawn(1).getifstart()){
                            if (player.getPawn(1).getboardsquare() <= 28 + i && player.getPawn(1).getboardsquare() + pulledcard.returnnum() > 28 + i) {
                                view.updatepawn(player.getPawn(1).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(1), player, 1);
                            } else {
                                view.updatepawn(player.getPawn(1).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(1), player, 0);
                            }
                        }
                        else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(), board) && !player.getPawn(2).getifstart()){
                            if (player.getPawn(2).getboardsquare() <= 28 + i && player.getPawn(2).getboardsquare() + pulledcard.returnnum() > 28 + i) {
                                view.updatepawn(player.getPawn(2).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(2), player, 1);
                            } else {
                                view.updatepawn(player.getPawn(2).getboardsquare() + pulledcard.returnnum(), view.getprev().getPawn(2), player, 0);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "You can't play your card.\nDraw another card!");
                        }
                    }
                    else{
                        flag12_8=1;
                    }
                }
                else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(1).getifstart()){
                    int i;
                    if(player.getnumber()==1){
                        i=0;
                    }
                    else{
                        i=30;
                    }
                    if(player.getPawn(1).getboardsquare()<=28+i&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(1), player,1);
                    }
                    else{
                        view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(1), player,0);
                    }
                }
                else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)&&!player.getPawn(2).getifstart()){
                    int i;
                    if(player.getnumber()==1){
                        i=0;
                    }
                    else{
                        i=30;
                    }
                    if(player.getPawn(2).getboardsquare()<=28+i&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28+i){
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(2), player,1);
                    }
                    else{
                        view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(2), player,0);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "You can't play your card.");
                }
            }
            else if(!player.getPawn(1).getifstart()&&!player.getPawn(2).getifstart()){      //CHECK IF THE PLAYER'S PAWNS ARE NOT IN THE START
                if(player.getcolor()=="red"){   //IF THE PLAYER IS THE RED PLAYER CHECK IF THE MOVE IS PLAYABLE
                    if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                        Object[] options = {"Move First Pawn", "Move Second Pawn"};
                        int choice = JOptionPane.showOptionDialog(frame1,
                        "Choose which pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                        if (choice == JOptionPane.YES_OPTION){
                            if(player.getPawn(1).getboardsquare()<=28&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28){
                                view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,1);
                            }else{
                                view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                            }
                        }
                        else{
                            if(player.getPawn(2).getboardsquare()<=28&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28){
                                view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,1);
                            }else{
                                view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                            }
                        }
                    }else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(1).getboardsquare()<=28&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28){
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,1);
                        }else{
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                        }
                    }else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(2).getboardsquare()<=28&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28){
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,1);
                        }else{
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }
                else{       //IF THE PLAYER IS YELLOW THE SAME
                    if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                        Object[] options = {"Move First Pawn", "Move Second Pawn"};
                        int choice = JOptionPane.showOptionDialog(frame1,
                        "Choose which pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                        if (choice == JOptionPane.YES_OPTION){
                            if(player.getPawn(1).getboardsquare()<=58&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>58){
                                view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,1);
                            }else{
                                view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                            }
                        }
                        else{
                            if(player.getPawn(2).getboardsquare()<=58&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>58){
                                view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,1);
                            }else{
                                view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                            }
                        }
                    }else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(1).getboardsquare()<=58&&pulledcard.returnnum()>58){
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,1);
                        }else{
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                        }
                    }else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(2).getboardsquare()<=58&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>58){
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,1);
                        }else{
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }
            }
            else if(pulledcard.getname()=="ONE"||pulledcard.getname()=="TWO"){      //CHECK IF THE CARD IS ONE OR TWO
                if(player.getcolor()=="red"&&player.getPawn(1).getifstart()){

                }
                int i;
                if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)&&player.getPawn(2).returnifisplayable(pulledcard.returnnum(), board)){                    
                    Object[] options = {"Move First Pawn", "Move Second Pawn"};
                    int choice = JOptionPane.showOptionDialog(frame1,
                    "Choose which pawn to move:",
                    "Pawn Movement",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                    if (choice == JOptionPane.YES_OPTION){
                        i=0;
                    }
                    else{
                        i=1;
                    }
                }
                else if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)){
                    i=0;
                }
                else if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                    i=1;
                }
                else{
                    JOptionPane.showMessageDialog(frame,"You can't play your card.");
                    return;
                }
                if(player.getcolor()=="red"){
                    if(player.getPawn(i+1).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(i+1).getifstart()){
                            view.updatepawn(player.getPawn(i+1).getboardsquare()+pulledcard.returnnum()+30,view.getprev().getPawn(i+1), player,0);
                        }
                        else if(player.getPawn(i+1).getboardsquare()<=28&&player.getPawn(i+1).getboardsquare()+pulledcard.returnnum()>28){
                            view.updatepawn(player.getPawn(i+1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(i+1), player,1);
                        }else{
                            view.updatepawn(player.getPawn(i+1).getboardsquare()+pulledcard.returnnum(),view.getprev().getPawn(i+1), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }else{
                    if(player.getPawn(i+1).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(i+1).getboardsquare()<=58&&player.getPawn(i+1).getboardsquare()+pulledcard.returnnum()>58){
                            view.updatepawn(player.getPawn(i+1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(i+1), player,1);
                        }
                        else{
                            view.updatepawn(player.getPawn(i+1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(i+1), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }
            }
            else if(!player.getPawn(1).getifstart()){          //CHECK IF THE PLAYER CAN PLAY ONLY THE FIRST PAWN
                if(player.getcolor()=="red"){
                    if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(1).getboardsquare()<=28&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>28){
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,1);
                        }else{
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }else{
                    if(player.getPawn(1).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(1).getboardsquare()<=58&&player.getPawn(1).getboardsquare()+pulledcard.returnnum()>58){
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,1);
                        }else{
                            view.updatepawn(player.getPawn(1).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(1), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }                
            }
            else if(!player.getPawn(2).getifstart()){            //CHECK IF THE PLAYER CAN PLAY ONLY THE SECOND PAWN
                if(player.getnumber()==1){
                    if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                        if(player.getPawn(2).getboardsquare()<=28&&player.getPawn(2).getboardsquare()+pulledcard.returnnum()>28){
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,1);
                        }else{
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }else{
                    if(player.getPawn(2).returnifisplayable(pulledcard.returnnum(),board)){
                        if((player.getPawn(2).getboardsquare()<=58)&&(player.getPawn(2).getboardsquare()+pulledcard.returnnum()>58)){
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,1);
                        }else{
                            view.updatepawn(player.getPawn(2).getboardsquare()+pulledcard.returnnum(), view.getprev().getPawn(2), player,0);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "You can't play your card.");
                    }
                }
            }    
        }else{    //IF THE CARD IS SORRY
            if(player.getnumber()==1){
                if((player2.getPawn(1).getsafety()&&player2.getPawn(2).getsafety())||(!player.getPawn(1).getifstart()&&!player.getPawn(2).getifstart())){
                    JOptionPane.showMessageDialog(frame, "You can't play your SORRY cardd.");
                    return;
                }
            }
            else if(player.getnumber()==2){
                if((player1.getPawn(1).getsafety()&&player1.getPawn(2).getsafety())||!player.getPawn(1).getifstart()&&!player.getPawn(2).getifstart()){
                    JOptionPane.showMessageDialog(frame, "You can't play your SORRY carddd.");
                    return;
                }
            }
            if(player.getPawn(1).getifstart()&&player.getPawn(2).getifstart()){
                Object[] options = {"Move First Pawn", "Move Second Pawn"};
                int choice = JOptionPane.showOptionDialog(frame,
                        "Choose which pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (choice == JOptionPane.YES_OPTION){//an dialejei to proto
                    if(player.getnumber()==1){
                        if(player2.getPawn(1).getsafety()&&player2.getPawn(2).getsafety()){
                            JOptionPane.showMessageDialog(frame, "You can't play your card SORRY.");
                        }
                        else if(!player2.getPawn(1).getsafety()&&!player2.getPawn(2).getsafety()){
                            Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                            int choicee = JOptionPane.showOptionDialog(frame,
                            "Choose which enemy's pawn to move:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            optionss,
                            optionss[0]);
                            if(choicee==JOptionPane.YES_OPTION){
                                int square=player2.getPawn(1).getboardsquare();
                                player2.getPawn(1).returntostart();
                                view.updatepawn(-1, player2.getPawn(1),player2,0);
                                view.updatepawn(square, player.getPawn(1),player,0);
                            }
                            else if(choicee==JOptionPane.NO_OPTION){
                                int square=player2.getPawn(2).getboardsquare();
                                player2.getPawn(2).returntostart();
                                view.updatepawn(-1, player2.getPawn(2),player2,0);
                                view.updatepawn(square, player.getPawn(1),player,0);
                            }
                        }
                        else if(!player2.getPawn(1).getsafety()){
                            int square=player2.getPawn(1).getboardsquare();
                            player2.getPawn(1).returntostart();
                            view.updatepawn(-1, player2.getPawn(1),player2,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                        else{
                            int square=player2.getPawn(2).getboardsquare();
                            player2.getPawn(2).returntostart();
                            view.updatepawn(-1, player2.getPawn(2),player2,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                    }
                    else{
                        if(player1.getPawn(1).getsafety()&&player1.getPawn(2).getsafety()){
                            JOptionPane.showMessageDialog(frame, "You can't play your SORRY card.");
                        }
                        else if(!player1.getPawn(1).getsafety()&&!player1.getPawn(2).getsafety()){
                            Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                            int choicee = JOptionPane.showOptionDialog(frame,
                            "Choose which enemy's pawn to move:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            optionss,
                            optionss[0]);
                            if(choicee==JOptionPane.YES_OPTION){
                                int square=player1.getPawn(1).getboardsquare();
                                player1.getPawn(1).returntostart();
                                view.updatepawn(-1, player1.getPawn(1),player1,0);
                                view.updatepawn(square, player.getPawn(1),player,0);
                            }
                            else if(choicee==JOptionPane.NO_OPTION){
                                int square=player1.getPawn(2).getboardsquare();
                                player1.getPawn(2).returntostart();
                                view.updatepawn(-1, player1.getPawn(2),player1,0);
                                view.updatepawn(square, player.getPawn(1),player,0);
                            }
                        }
                        else if(!player1.getPawn(1).getsafety()){
                            int square=player1.getPawn(1).getboardsquare();
                            player1.getPawn(1).returntostart();
                            view.updatepawn(-1, player1.getPawn(1),player1,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                        else{
                            int square=player1.getPawn(2).getboardsquare();
                            player1.getPawn(2).returntostart();
                            view.updatepawn(-1, player1.getPawn(2),player1,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                    }
                } 
                else if(choice == JOptionPane.NO_OPTION) {// an dialejei to deytero
                    if(player.getnumber()==1){
                        if(player2.getPawn(1).getsafety()&&player2.getPawn(2).getsafety()){
                            JOptionPane.showMessageDialog(frame, "You can't play your SORRY card.");
                        }
                        else if(!player2.getPawn(1).getsafety()&&!player2.getPawn(2).getsafety()){
                            Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                            int choicee = JOptionPane.showOptionDialog(frame,
                            "Choose which enemy's pawn to move:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            optionss,
                            optionss[0]);
                            if(choicee==JOptionPane.YES_OPTION){
                                int square=player2.getPawn(1).getboardsquare();
                                player2.getPawn(1).returntostart();
                                view.updatepawn(-1, player2.getPawn(1),player2,0);
                                view.updatepawn(square, player.getPawn(2),player,0);
                            } 
                            else if(choicee==JOptionPane.NO_OPTION){
                                int square=player2.getPawn(2).getboardsquare();
                                player2.getPawn(2).returntostart();
                                view.updatepawn(-1, player2.getPawn(2),player2,0);
                                view.updatepawn(square, player.getPawn(2),player,0);
                            }
                        }
                        else if(!player2.getPawn(1).getsafety()){
                            int square=player2.getPawn(1).getboardsquare();
                            player2.getPawn(1).returntostart();
                            view.updatepawn(-1, player2.getPawn(1),player2,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                        else{
                            int square=player2.getPawn(2).getboardsquare();
                            player2.getPawn(2).returntostart();
                            view.updatepawn(-1, player2.getPawn(2),player2,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                    }
                    else{
                        if(player1.getPawn(1).getsafety()&&player1.getPawn(2).getsafety()){
                            JOptionPane.showMessageDialog(frame, "You can't play your SORRY card.");
                        }
                        else if(!player1.getPawn(1).getsafety()&&!player1.getPawn(2).getsafety()){
                            Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                            int choicee = JOptionPane.showOptionDialog(frame,
                            "Choose which enemy's pawn to move:",
                            "Pawn Movement",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            optionss,
                            optionss[0]);
                            if(choicee==JOptionPane.YES_OPTION){
                                int square=player1.getPawn(1).getboardsquare();
                                player1.getPawn(1).returntostart();
                                view.updatepawn(-1, player1.getPawn(1),player1,0);
                                view.updatepawn(square, player.getPawn(2),player,0);
                            }
                            else if(choicee==JOptionPane.NO_OPTION){
                                int square=player1.getPawn(2).getboardsquare();
                                player1.getPawn(2).returntostart();
                                view.updatepawn(-1, player1.getPawn(2),player1,0);
                                view.updatepawn(square, player.getPawn(2),player,0);
                            }
                        }
                        else if(!player1.getPawn(1).getsafety()){
                            int square=player1.getPawn(1).getboardsquare();
                            player1.getPawn(1).returntostart();
                            view.updatepawn(-1, player1.getPawn(1),player1,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                        else{
                            int square=player1.getPawn(2).getboardsquare();
                            player1.getPawn(2).returntostart();
                            view.updatepawn(-1, player1.getPawn(2),player1,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                    }
                }
            }
            else if(player.getPawn(1).getifstart()){
                if(player.getnumber()==1){
                    if(player2.getPawn(1).getsafety()&&player2.getPawn(2).getsafety()){
                        JOptionPane.showMessageDialog(frame, "You can't play your SORRY card.");
                    }
                    else if(!player2.getPawn(1).getsafety()&&!player2.getPawn(2).getsafety()){
                        Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                        int choicee = JOptionPane.showOptionDialog(frame,
                        "Choose which enemy's pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        optionss,
                        optionss[0]);
                        if(choicee==JOptionPane.YES_OPTION){
                            int square=player2.getPawn(1).getboardsquare();
                            player2.getPawn(1).returntostart();
                            view.updatepawn(-1, player2.getPawn(1),player2,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                        else if(choicee==JOptionPane.NO_OPTION){
                            int square=player2.getPawn(2).getboardsquare();
                            player2.getPawn(2).returntostart();
                            view.updatepawn(-1, player2.getPawn(2),player2,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                    }
                    else if(!player2.getPawn(1).getsafety()){
                        int square=player2.getPawn(1).getboardsquare();
                        player2.getPawn(1).returntostart();
                        view.updatepawn(-1, player2.getPawn(1), player2, 0);
                        view.updatepawn(square, player.getPawn(1),player,0);
                    }
                    else{
                        int square=player2.getPawn(2).getboardsquare();
                        player2.getPawn(2).returntostart();
                        view.updatepawn(-1, player2.getPawn(2), player2, 0);
                        view.updatepawn(square, player.getPawn(1),player,0);
                    }
                }
                else{
                    if(player1.getPawn(1).getsafety()&&player1.getPawn(2).getsafety()){
                        JOptionPane.showMessageDialog(frame, "You can't play your SORRY card.");
                    }
                    else if(!player1.getPawn(1).getsafety()&&!player1.getPawn(2).getsafety()){
                        Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                        int choicee = JOptionPane.showOptionDialog(frame,
                        "Choose which enemy's pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        optionss,
                        optionss[0]);
                        if(choicee==JOptionPane.YES_OPTION){
                            int square=player1.getPawn(1).getboardsquare();
                            player1.getPawn(1).returntostart();
                            view.updatepawn(-1, player1.getPawn(1),player1,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                        else if(choicee==JOptionPane.NO_OPTION){
                            int square=player1.getPawn(2).getboardsquare();
                            player1.getPawn(2).returntostart();
                            view.updatepawn(-1, player1.getPawn(2),player1,0);
                            view.updatepawn(square, player.getPawn(1),player,0);
                        }
                    }
                    else if(!player1.getPawn(1).getsafety()){
                        int square=player1.getPawn(1).getboardsquare();
                        player1.getPawn(1).returntostart();
                        view.updatepawn(-1, player1.getPawn(1), player1, 0);
                        view.updatepawn(square, player.getPawn(1),player,0);
                    }
                    else{
                        int square=player1.getPawn(2).getboardsquare();
                        player1.getPawn(2).returntostart();
                        view.updatepawn(-1, player1.getPawn(2), player1, 0);
                        view.updatepawn(square, player.getPawn(1),player,0);
                    }
                }
            }
            else{
                if(player.getnumber()==1){
                    if(!player2.getPawn(1).getsafety()&&!player2.getPawn(2).getsafety()){
                        Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                        int choicee = JOptionPane.showOptionDialog(frame,
                        "Choose which enemy's pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        optionss,
                        optionss[0]);
                        if(choicee==JOptionPane.YES_OPTION){
                            int square=player2.getPawn(1).getboardsquare();
                            player2.getPawn(1).returntostart();
                            view.updatepawn(-1, player2.getPawn(1),player2,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                        else if(choicee==JOptionPane.NO_OPTION){
                            int square=player2.getPawn(2).getboardsquare();
                            player2.getPawn(2).returntostart();
                            view.updatepawn(-1, player2.getPawn(2),player2,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                    }
                    else if(!player2.getPawn(1).getsafety()){
                        int square=player2.getPawn(1).getboardsquare();
                        player2.getPawn(1).returntostart();
                        view.updatepawn(-1, player2.getPawn(1),player2,0);
                        view.updatepawn(square, player.getPawn(2),player,0);
                    }
                    else{
                        int square=player2.getPawn(2).getboardsquare();
                        player2.getPawn(2).returntostart();
                        view.updatepawn(-1, player2.getPawn(2),player2,0);
                        view.updatepawn(square, player.getPawn(2),player,0);
                    }
                }
                else{
                    if(player1.getPawn(1).getsafety()&&player1.getPawn(2).getsafety()){
                        JOptionPane.showMessageDialog(frame, "You can't play your SORRY card.");
                    }
                    else if(!player1.getPawn(1).getsafety()&&!player1.getPawn(2).getsafety()){
                        Object[] optionss = {"Move Enemy's First Pawn", "Move Enemy's Second Pawn"};
                        int choicee = JOptionPane.showOptionDialog(frame,
                        "Choose which enemy's pawn to move:",
                        "Pawn Movement",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        optionss,
                        optionss[0]);
                        if(choicee==JOptionPane.YES_OPTION){
                            int square=player1.getPawn(1).getboardsquare();
                            player1.getPawn(1).returntostart();
                            view.updatepawn(-1, player1.getPawn(1),player1,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                        else if(choicee==JOptionPane.NO_OPTION){
                            int square=player1.getPawn(2).getboardsquare();
                            player1.getPawn(2).returntostart();
                            view.updatepawn(-1, player1.getPawn(2),player1,0);
                            view.updatepawn(square, player.getPawn(2),player,0);
                        }
                    }
                    else if(!player1.getPawn(1).getsafety()){
                        int square=player1.getPawn(1).getboardsquare();
                        player1.getPawn(1).returntostart();
                        view.updatepawn(-1, player1.getPawn(1),player1,0);
                        view.updatepawn(square, player.getPawn(2),player,0);
                    }
                    else{
                        int square=player1.getPawn(2).getboardsquare();
                        player1.getPawn(2).returntostart();
                        view.updatepawn(-1, player1.getPawn(2),player1,0);
                        view.updatepawn(square, player.getPawn(2),player,0);
                    }
                }
            }
        }
    }
    /**
     * A transformer that will change the player's turn.
     * @pre The player need to play his turn before give the turn to the other player.
     * @post The turn has been given to player two.
     */
    public void changeturn(){       // HERE THE TURN CHANGES
        if(view.getprev().getnumber()==1){
            view.updateturn(player2);
        }
        else{
            view.updateturn(player1);
        }
    }
    /**
     * A transformer that will call a fuction from view and initialize the game's panel and the buttons.
     * @param imageButton Is the button that the view will connect with the back of a card.
     * @pre the button has to be initialized.
     * @pre the board has to be initialized.
     * @pre the Foldbutton has to be initialized.
     * @post The game's panel has been initialized.
     */
    public void setAllTogether(JButton imageButton){    //A METHOD THAT CALLS THE VIEW INITIALIZATION
        view.setAllTogether(imageButton,FoldButton,board);
    }

    public static void main(String[] args) {
        Controller a = new Controller();
    }
}
 