import javax.swing.*;
import java.awt.*;


public class View extends JFrame{

    private ImageIcon resizedIcon;
    private JLabel receive;
    private JLabel drawnCardLabel;
    private JLabel yellowpawn1;
    private JLabel yellowpawn2;
    private JLabel redpawn1;
    private JLabel redpawn2;
    private JLayeredPane basicPanel;
    private JPanel whiteSquareINFO;
    private JLabel turn;
    private Board board;
    private Player previousPlayer;
    private JTextArea infoboxArea;
    private JLabel infoboxl;

    private JLabel cardsl;
    private int cardsleft=44;

    private JLayeredPane startbasicPanel;
    private JTextField player1NameField;
    private JTextField player2NameField;

    /**
     * A constructor that initialize the panel and the buttons.
     */
    public View(JButton startGameButton,JButton imageButton) {
        createStartPanel(startGameButton);
    }
    /**
     * A transformer that create the menu of the panel.
     * @param startGameButton the button that is in the started panel.
     * @pre the button has to be initialized.
     * @post we have our starting game panel.
     */
    public void createStartPanel(JButton startGameButton) {
        startbasicPanel = new JLayeredPane();
        setLayout(new BorderLayout());
        int initialWidth = 1200;
        int initialHeight = 860;
    
        // Load background image
        ImageIcon backgroundImageIcon = new ImageIcon("src/background.png");
        Image backgroundImage = backgroundImageIcon.getImage();
        Image scaledBackgroundImage = backgroundImage.getScaledInstance(initialWidth, initialHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundImageIcon = new ImageIcon(scaledBackgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundImageIcon);
        backgroundLabel.setBounds(0, 0, initialWidth, initialHeight);
        startbasicPanel.add(backgroundLabel, new Integer(1));
        startbasicPanel.add(startGameButton, new Integer(2));


        JLabel player1Label = new JLabel("Enter Red Player's Name:");
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        player1Label.setBounds(230, 390, 350, 20);
        startbasicPanel.add(player1Label, new Integer(2));
    
        player1NameField = new JTextField();
        player1NameField.setBounds(230, 420, 250, 50);
        startbasicPanel.add(player1NameField, new Integer(2));
    
        // Create label and text field for the second player
        JLabel player2Label = new JLabel("Enter Yellow Player's Name:");
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        player2Label.setBounds(700, 390, 350, 20);
        startbasicPanel.add(player2Label, new Integer(2));
    
        player2NameField = new JTextField();
        player2NameField.setBounds(710, 420, 250, 50);
        startbasicPanel.add(player2NameField, new Integer(2));


        setContentPane(startbasicPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(initialWidth, initialHeight);    
        setLocationRelativeTo(null);
        setTitle("Starting point");
        setVisible(true);
    }
    /**
     * A transformer that create the basic game's panel.
     */
    public void creategamepanel(){
        basicPanel = new JLayeredPane();
        setLayout(new BorderLayout());

        JPanel blackSquare = new JPanel();
        blackSquare.setBackground(Color.black);
        blackSquare.setBounds(0, 0, 800, 800);
        basicPanel.add(blackSquare, new Integer(1));
        JPanel cyan = new JPanel();
        cyan.setBackground(Color.cyan);
        cyan.setBounds(50, 50, 700, 700);
        basicPanel.add(cyan, new Integer(2));
    
        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("src/background.png");
        Image backgroundImage = backgroundImageIcon.getImage();
    
        // Specify initial width and height
        int initialWidth = 1200;  // Adjust this value as needed
        int initialHeight = 860;  // Adjust this value as needed
    
        // Scale the background image to fit the panel
        Image scaledBackgroundImage = backgroundImage.getScaledInstance(initialWidth, initialHeight, Image.SCALE_SMOOTH);
    
        ImageIcon scaledBackgroundImageIcon = new ImageIcon(scaledBackgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundImageIcon);
        backgroundLabel.setBounds(0, 0, initialWidth, initialHeight);    
        basicPanel.add(backgroundLabel, new Integer(0));
    
        // Create and add small white blocks (16x16) with black lines between them
        int blockSize = 50;
        for (int x = 0; x <= 750; x += blockSize) {
            for (int y = 0; y <= 750; y += blockSize) {
                if (x == 0 || x == 750 || y == 0||y==750) {
                    JPanel whiteBlock = new JPanel();
                    whiteBlock.setBackground(Color.WHITE);
                    whiteBlock.setBounds(x, y, blockSize-1, blockSize-1);
                    basicPanel.add(whiteBlock, new Integer(3));
                }
                if(y==0){
                    JLabel imageLabel = new JLabel();
                    ImageIcon imageIcon=null;
                    if(x==150||x==100||x==500||x==600||x==550){
                        imageIcon = new ImageIcon("src/redSlideMedium.png");
                    }
                    else if(x==200||x==650){
                        imageIcon = new ImageIcon("src/redSlideEnd.png");
                    }
                    else if(x==50||x==450){
                        imageIcon = new ImageIcon("src/redSlideStart.png");
                    }
                    imageLabel.setIcon(imageIcon);
                    imageLabel.setBounds(x, y, blockSize - 1, blockSize - 1);
                    basicPanel.add(imageLabel, new Integer(4));
                }
                else if(x==0){
                    JLabel imageLabel = new JLabel();
                    ImageIcon imageIcon=null;
                    if(y==150||y==250||y==200||y==650||y==600){
                        imageIcon = new ImageIcon("src/greenSlideMedium.png");
                    }
                    else if(y==100||y==550){
                        imageIcon = new ImageIcon("src/greenSlideEnd.png");
                    }
                    else if(y==700||y==300){
                        imageIcon = new ImageIcon("src/greenSlideStart.png");
                    }
                    imageLabel.setIcon(imageIcon);
                    imageLabel.setBounds(x, y, blockSize - 1, blockSize - 1);
                    basicPanel.add(imageLabel, new Integer(4));
                }
                else if(y==750){
                    JLabel imageLabel = new JLabel();
                    ImageIcon imageIcon=null;
                    if(x==150||x==250||x==650||x==600||x==200){
                        imageIcon = new ImageIcon("src/yellowSlideMedium.png");;
                    }
                    else if(x==300||x==700){
                        imageIcon = new ImageIcon("src/yellowSlideStart.png");
                    }
                    else if(x==100||x==550){
                        imageIcon = new ImageIcon("src/yellowSlideEnd.png");
                    }
                    imageLabel.setIcon(imageIcon);
                    imageLabel.setBounds(x, y, blockSize - 1, blockSize - 1);
                    basicPanel.add(imageLabel, new Integer(4));
                }
                else if(x==750){
                    JLabel imageLabel = new JLabel();
                    ImageIcon imageIcon=null;
                    if(y==150||y==100||y==500||y==550||y==600){
                        imageIcon = new ImageIcon("src/blueSlideMedium.png");
                    }
                    else if(y==200||y==650){
                        imageIcon = new ImageIcon("src/blueSlideEnd.png");
                    }
                    else if(y==50||y==450){
                        imageIcon = new ImageIcon("src/blueSlideStart.png");
                    }
                    imageLabel.setIcon(imageIcon);
                    imageLabel.setBounds(x, y, blockSize - 1, blockSize - 1);
                    basicPanel.add(imageLabel, new Integer(4));
                }
                if(x==100){
                    if(y==50||y==100||y==150||y==200||y==250){
                        JPanel whiteBlock = new JPanel();
                        whiteBlock.setBackground(Color.RED);
                        whiteBlock.setBounds(x, y, blockSize-1, blockSize-1);
                        basicPanel.add(whiteBlock, new Integer(3));
                    }
                    if(y==300){
                        JPanel whiteBlock = new JPanel();
                        whiteBlock.setBackground(Color.RED);
                        whiteBlock.setBounds(x - 25, y, 100, 100);
                        JLabel homeLabel = new JLabel("HOME");
                        JPanel whiteBlock1 = new JPanel();
                        whiteBlock1.setBackground(Color.WHITE);
                        whiteBlock1.setBounds(x - 20, y+5, 90, 90);
                        whiteBlock1.setLayout(null);
                        homeLabel.setFont(new Font("Arial", Font.BOLD, 20));
                        homeLabel.setForeground(Color.BLACK);
                        homeLabel.setHorizontalAlignment(JLabel.CENTER);
                        homeLabel.setVerticalAlignment(JLabel.BOTTOM);  
                        whiteBlock1.setLayout(new BorderLayout());
                        whiteBlock1.add(homeLabel);   
                        basicPanel.add(whiteBlock, new Integer(3));
                        basicPanel.add(whiteBlock1, new Integer(4));
                    }
                }
                else if(x==650){
                    if(y==700||y==600||y==500||y==550||y==650){
                        JPanel whiteBlock = new JPanel();
                        whiteBlock.setBackground(Color.YELLOW);
                        whiteBlock.setBounds(x, y, blockSize-1, blockSize-1);
                        basicPanel.add(whiteBlock, new Integer(3));
                    }
                    if (y == 400) {
                        JPanel whiteBlock = new JPanel();
                        whiteBlock.setBackground(Color.YELLOW);
                        whiteBlock.setBounds(x - 25, y, 100, 100);
                        JPanel whiteBlock1 = new JPanel();
                        whiteBlock1.setBackground(Color.WHITE);
                        whiteBlock1.setBounds(x - 20, y+5, 90, 90);
                        whiteBlock1.setLayout(null);
                        JLabel homeLabel = new JLabel("HOME");
                        homeLabel.setFont(new Font("Arial", Font.BOLD, 20));
                        homeLabel.setForeground(Color.BLACK);
                        homeLabel.setHorizontalAlignment(JLabel.CENTER);
                        homeLabel.setVerticalAlignment(JLabel.TOP); 
                        whiteBlock1.setLayout(new BorderLayout());
                        whiteBlock1.add(homeLabel);   
                        basicPanel.add(whiteBlock, new Integer(3));
                        basicPanel.add(whiteBlock1, new Integer(4));
                    }
                }
                if (x == 200 && y == 50) {
                    // Create the START square with the label
                    JPanel startSquare = new JPanel();
                    startSquare.setBackground(Color.RED);
                    startSquare.setBounds(x - 25, y, 100, 100);
                    startSquare.setLayout(null);
                    
                    JPanel startSquare1 = new JPanel();
                    startSquare1.setBackground(Color.WHITE);
                    startSquare1.setBounds(x - 20, 55, 90, 90);
                    startSquare1.setLayout(null);

                    // Add the "START" label
                    JLabel startLabel = new JLabel("START");
                    startLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    startLabel.setForeground(Color.BLACK);
                    startLabel.setHorizontalAlignment(JLabel.CENTER);
                    startLabel.setVerticalAlignment(JLabel.BOTTOM);
                
                    // Add the label to the center of the square
                    startLabel.setBounds(-5, -10, 100, 100);
                    startSquare1.add(startLabel);
                    
                    // Add pawn images on the left and right sides (smaller size)
                    redpawn1 = new JLabel();
                    ImageIcon leftPawnIcon = new ImageIcon("src/redPawn1.png");
                    leftPawnIcon = new ImageIcon(leftPawnIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH)); // Adjust size
                    redpawn1.setIcon(leftPawnIcon);
                    redpawn1.setBounds(0, 5, 40, 60);  // Adjust size and position
                    startSquare1.add(redpawn1);
                
                    redpawn2 = new JLabel();
                    ImageIcon rightPawnIcon = new ImageIcon("src/redPawn2.png");
                    rightPawnIcon = new ImageIcon(rightPawnIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH)); // Adjust size
                    redpawn2.setIcon(rightPawnIcon);
                    redpawn2.setBounds(50, 5, 40, 60);  // Adjust size and position
                    startSquare1.add(redpawn2);
                
                    basicPanel.add(startSquare, new Integer(3));
                    basicPanel.add(startSquare1, new Integer(4));
                }
                else if(x==550&&y==650){
                    // Create the START square with the label
                    JPanel startSquare = new JPanel();
                    startSquare.setBackground(Color.YELLOW);
                    startSquare.setBounds(x - 25, y, 100, 100);
                    startSquare.setLayout(null);  // Use null layout to manually set component positions

                    JPanel startSquare1 = new JPanel();
                    startSquare1.setBackground(Color.WHITE);
                    startSquare1.setBounds(x - 20, 655, 90, 90);
                    startSquare1.setLayout(null);
                
                    // Add the "START" label
                    JLabel startLabel = new JLabel("START");
                    startLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    startLabel.setForeground(Color.BLACK);
                    startLabel.setHorizontalAlignment(JLabel.CENTER);
                    startLabel.setVerticalAlignment(JLabel.TOP);
                
                    // Add the label to the center of the square
                    startLabel.setBounds(-5, 0, 100, 100);  // Adjust size and position
                    startSquare1.add(startLabel);
                    
                    // Add pawn images on the left and right sides (smaller size)
                    yellowpawn1 = new JLabel();
                    ImageIcon leftPawnIcon = new ImageIcon("src/yellowPawn1.png");
                    leftPawnIcon = new ImageIcon(leftPawnIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH)); // Adjust size
                    yellowpawn1.setIcon(leftPawnIcon);
                    yellowpawn1.setBounds(0, 25, 40, 60);  // Adjust size and position
                    startSquare1.add(yellowpawn1);
                
                    yellowpawn2 = new JLabel();
                    ImageIcon rightPawnIcon = new ImageIcon("src/yellowPawn2.png");
                    rightPawnIcon = new ImageIcon(rightPawnIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH)); // Adjust size
                    yellowpawn2.setIcon(rightPawnIcon);
                    yellowpawn2.setBounds(50, 25, 40, 60);  // Adjust size and position
                    startSquare1.add(yellowpawn2);
                
                    basicPanel.add(startSquare, new Integer(3));
                    basicPanel.add(startSquare1, new Integer(4));
                }
                if(x==0){
                    JLabel middleImageLabel = new JLabel();
                    ImageIcon middleImageIcon = new ImageIcon("src/sorryImage.png");
                    middleImageLabel.setIcon(middleImageIcon);
                    middleImageLabel.setBounds(200, 350,400 , 100);
                    basicPanel.add(middleImageLabel, new Integer(5));                    
                }
            }
        }
    }
    /**
     * A transformer that will manage the lifted card.
     * @pre the card has to be not null.
     */
    public void updatelifted(Card card) {
        if (drawnCardLabel == null) {
            drawnCardLabel = new JLabel(); 
            ImageIcon originalIcon = new ImageIcon(card.getImagePath());
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(140, 180, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            drawnCardLabel.setIcon(resizedIcon);
            drawnCardLabel.setBounds(1025, 200, 140, 180);
            basicPanel.add(drawnCardLabel, new Integer(4));
        } else {
            ImageIcon newImageIcon = new ImageIcon(card.getImagePath());
            Image newOriginalImage = newImageIcon.getImage();
            Image newResizedImage = newOriginalImage.getScaledInstance(140, 180, Image.SCALE_SMOOTH);
            ImageIcon newResizedIcon = new ImageIcon(newResizedImage);
            drawnCardLabel.setIcon(newResizedIcon);
        }
        
    }


    /**
     * A transformer that will set the basic panel and the buttons together.
     * @param imageButton the button that will be under the image of the backcard.
     * @param boardd is our board
     * @pre the button has to be initialized.
     * @pre the board has to be initialized and not null.
     * @post the buttons now interupt with the game
     */
    public void setAllTogether(JButton imageButton,JButton foldButton,Board boardd) {
        dispose();
        board=boardd;
        creategamepanel();
        int initialWidth = 1200;
        int initialHeight = 840;
        
        basicPanel.add(foldButton, new Integer(2));

        whiteSquareINFO = new JPanel();
        whiteSquareINFO.setBackground(Color.WHITE);
        whiteSquareINFO.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        whiteSquareINFO.setBounds(820, 490, 350, 150);
        whiteSquareINFO.setLayout(null);
        basicPanel.add(whiteSquareINFO, new Integer(2));

        infoboxl = new JLabel("Info Box");
        infoboxl.setFont(new Font("Arial", Font.BOLD, 15));
        infoboxl.setForeground(Color.BLACK);
        infoboxl.setBounds(2, 1, 350, 20);
        whiteSquareINFO.add(infoboxl);

        receive = new JLabel("Receive Card");
        receive.setFont(new Font("Arial", Font.BOLD, 20));
        receive.setForeground(Color.BLACK);
        receive.setBounds(830, 400, 150, 20);
        basicPanel.add(receive,new Integer(4));

        JLabel current = new JLabel("Current Card");
        current.setFont(new Font("Arial", Font.BOLD, 20));
        current.setForeground(Color.BLACK);
        current.setBounds(1030, 400, 150, 20);
        basicPanel.add(current,new Integer(4));
        
        basicPanel.add(imageButton, new Integer(4));


        setContentPane(basicPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(initialWidth, initialHeight);    
        setLocationRelativeTo(null);
        setTitle("Game");
        setVisible(true);
    }
    /**
     * A transformer that will change game's turn.
     * @param player The player that already played.
     * @pre the player has to be not null.
     * post the turn has be changed.
     */
    public void updateturn(Player player){
        
        if(turn==null){
            turn = new JLabel("Turn: Player "+player.getnumber()+" ("+player.getname()+")");
            turn.setFont(new Font("Arial", Font.BOLD, 15));
            turn.setForeground(Color.BLACK);
            turn.setBounds(2, 110, 400, 20);
            whiteSquareINFO.add(turn);
        }
        else{
            turn.setText("Turn: Player "+player.getnumber()+" ("+player.getname()+")");
        }
        if(previousPlayer!=null){
            JOptionPane.showMessageDialog(null, "Player "+player.getnumber()+" ("+player.getname()+") is your turn!");
        }
        previousPlayer=player;
        
    }

    /**
     * A transformer that will update the cards in the info box.
     */
    public void updatecards(){
        if(cardsl==null){
            cardsl = new JLabel("Cards Left: "+cardsleft);
            cardsl.setFont(new Font("Arial", Font.BOLD, 15));
            cardsl.setForeground(Color.BLACK);
            cardsl.setBounds(2, 130, 150, 20);
            whiteSquareINFO.add(cardsl);
        }
        else{
            cardsl.setText("Cards Left: " + cardsleft);
        }
        cardsleft--;
        if(cardsleft==0){
            cardsleft=44;
        }
    }
    /**
     * A transformer that will update the info box with the card's details.
     * @param card is the card that is pulled from the deck.
     * @pre the card has to be not null.
     * @post the infobox has been updated.
     */
    public void updateinfobox(Card card) {
        
        if (infoboxl != null) {
            whiteSquareINFO.remove(infoboxl);
        }
        if (infoboxArea != null) {
            whiteSquareINFO.remove(infoboxArea);
        }

        infoboxl = new JLabel("Info Box");
        infoboxl.setFont(new Font("Arial", Font.BOLD, 15));
        infoboxl.setForeground(Color.BLACK);
        infoboxl.setBounds(2, 1, 350, 20);
        whiteSquareINFO.add(infoboxl);

        String originalDescription = card.getdescription();

        infoboxArea = new JTextArea(originalDescription);
        infoboxArea.setFont(new Font("Arial", Font.BOLD, 12));
        infoboxArea.setForeground(Color.BLACK);
        infoboxArea.setEditable(false);
        infoboxArea.setLineWrap(true);
        infoboxArea.setWrapStyleWord(true);
        infoboxArea.setBounds(2, 20, 350, 90);
        whiteSquareINFO.add(infoboxArea);

        whiteSquareINFO.revalidate();
        whiteSquareINFO.repaint();
    }

/**
 * A transformer that will update a pawn on the game.
 * @param position It is the square on the board that the pawn will move.
 * @param pawn It is the pawn that will move.
 * @param player It is the player to which the pawn belongs.
 * @param flag is true if the player has to decide which path he wants to follow.
 * @pre player has to be not null.
 * @pre the position has to be real.
 * @pre the pawn has to be initialized and not null.
 * @post the pawns image move.
 */
    public void updatepawn(int position, Pawn pawn, Player player,int flag) {
        JLabel pawnLabel;
        if(!pawn.returniffinished()){
            if(flag!=1){
                if(board.returnifstartofslide(position,player.getcolor())){
                    int finalposition=position+1;
                    finalposition=finalposition%60;
                    while(board.returnifinternalofslide(finalposition)){
                        finalposition++;
                    }
                    finalposition--;
                    finalposition=finalposition%60;
                    if(board.returnifempty(finalposition,pawn.returnprev())){
                        int i=position+1;
                        i=i%60;
                        while(board.returnifinternalofslide(i)){
                            if(board.returnpawnontop(i)!=null){
                                board.returnpawnontop(i).returntostart();
                                getpawnbacktostart(board.returnpawnontop(i));

                            }
                            i++;
                            i=i%60;
                        }
                        position=i;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "You can't use the slide.");
                    }
                }
            }
            if(player.getcolor().equals("red")){
                if (pawn == player.getPawn(1)) {
                    if (redpawn1 != null) {
                        basicPanel.remove(redpawn1);
                    }
                    pawnLabel = redpawn1;
                } else {
                    if (redpawn2 != null) {
                        basicPanel.remove(redpawn2); 
                    }
                    pawnLabel = redpawn2;
                }
            }else{
                if (pawn == player.getPawn(1)) {
                    if (yellowpawn1 != null) {
                        basicPanel.remove(yellowpawn1);
                    }
                    pawnLabel = yellowpawn1;
                } else {
                    if (yellowpawn2 != null) {
                        basicPanel.remove(yellowpawn2);
                    }
                    pawnLabel = yellowpawn2;
                }
            }
            ImageIcon newPawnIcon = new ImageIcon(pawn.getImagePath());
            newPawnIcon = new ImageIcon(newPawnIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH));
            pawnLabel.setIcon(newPawnIcon);

            if(position==-1){
                if(player.getcolor()=="red"){
                    if(player.getPawn(1)==pawn){
                        pawnLabel.setBounds(180,60, 40, 60);
                    }else{
                        pawnLabel.setBounds(230,60, 40, 60);
                    }
                }
                else{
                    if(player.getPawn(1)==pawn){
                        pawnLabel.setBounds(530,680, 40, 60);
                    }else{
                        pawnLabel.setBounds(580,680, 40, 60);
                    }
                }
                basicPanel.add(pawnLabel,new Integer(5));
                basicPanel.revalidate();
                basicPanel.repaint();
            }
            else{       
                int x,y;     

                if(position<=11||(position>=56&&position<100)){
                    y=15;
                    x=(position+4)%60;
                    x=15-x;
                }
                else if(position<=26){
                    x=0;
                    y=position-11;
                    y=15-y;
                }
                else if(position<=41){
                    y=0;
                    x=position-26;
                }
                else{
                    x=15;
                    y=position-41;
                }
                if(flag==1){
                    if(player.getcolor()=="red"){
                        pawnLabel.setBounds(2*50+5,2, 40, 45);
                    }
                    else{
                        pawnLabel.setBounds(13*50+5,752, 40, 45);
                    }

                    basicPanel.add(pawnLabel, new Integer(5));
                    basicPanel.revalidate();
                    basicPanel.repaint();
                    int option = showMoveChoiceDialog();
                    int a=handleMoveChoice(option);
                    position=pawn.movePawn(position+a);
                    if(position>128){
                        if(player.getcolor()=="yellow"){
                            if(position==164){
                                if(player.getPawn(1)==pawn){
                                    pawnLabel.setBounds(630,440, 40, 60);
                                }
                                else{
                                    pawnLabel.setBounds(680,440, 40, 60);
                                }
                                basicPanel.add(pawnLabel, new Integer(5));
                                basicPanel.revalidate();
                                basicPanel.repaint();
                                board.placePawnOnTop1(position,pawn);
                                board.displacepawnfromsquare(pawn.returnprev());
                                return;
                            }
                            x=13;
                            y=15-(position-158);
                        }
                        else if(player.getcolor()=="red"){
                            if(position==134){
                                if(player.getPawn(1)==pawn){
                                    pawnLabel.setBounds(80,310, 40, 60);
                                }
                                else{
                                    pawnLabel.setBounds(130,310, 40, 60);                     
                                }
                                basicPanel.add(pawnLabel, new Integer(6));
                                basicPanel.revalidate();
                                basicPanel.repaint();
                                board.placePawnOnTop1(position,pawn);
                                board.displacepawnfromsquare(pawn.returnprev());
                                return;
                            }
                            x=2;
                            y=(position+2)%10;
                        }
                    }else{
                        if(position<=11||(position>=56&&position<100)){
                            y=15;
                            x=(position+4)%60;
                            x=15-x;
                        }
                        else if(position<=26){
                            x=0;
                            y=position-11;
                            y=15-y;
                        }
                        else if(position<=41){
                            y=0;
                            x=position-26;
                        }
                        else{
                            x=15;
                            y=position-41;
                        }
                    }
                }
                else{ 
                    position=pawn.movePawn(position);
                    if(player.getcolor()=="red"&&position>=129){
                        if(position==134){
                            if(player.getPawn(1)==pawn){
                                pawnLabel.setBounds(80,315, 40, 60);
                            }
                            else{
                                pawnLabel.setBounds(130,315, 40, 60);                     
                            }
                            basicPanel.add(pawnLabel, new Integer(6));
                            basicPanel.revalidate();
                            basicPanel.repaint();
                            board.placePawnOnTop1(position,pawn);
                            board.displacepawnfromsquare(pawn.returnprev());
                            return;
                        }
                        x=2;
                        y=(position+2)%10;
                    }
                    else if(player.getcolor()=="yellow"&&position>=159){
                        if(position==164){
                            if(player.getPawn(1)==pawn){
                                pawnLabel.setBounds(630,435, 40, 60);
                            }
                            else{
                                pawnLabel.setBounds(680,435, 40, 60);
                            }
                            basicPanel.add(pawnLabel, new Integer(5));
                            basicPanel.revalidate();
                            basicPanel.repaint();
                            board.placePawnOnTop1(position,pawn);
                            board.displacepawnfromsquare(pawn.returnprev());
                            return;
                        }
                        x=13;
                        y=15-(position-158);
                    }
                    else{
                        if(position<=11||(position>=56&&position<100)){
                            y=15;
                            x=(position+4)%60;
                            x=15-x;
                        }
                        else if(position<=26){
                            x=0;
                            y=position-11;
                            y=15-y;
                        }
                        else if(position<=41){
                            y=0;
                            x=position-26;
                        }
                        else{
                            x=15;
                            y=position-41;
                        }
                    }
                }
                pawnLabel.setBounds(x*50+5, y*50+2, 40, 45);
                basicPanel.add(pawnLabel, new Integer(5));

                basicPanel.revalidate();
                basicPanel.repaint();
            }
            board.placePawnOnTop1(position,pawn);
            board.displacepawnfromsquare(pawn.returnprev());
        }
    }

    /**
     * A transformer that gets a pawn and send it back to the starting point of the game.
     * @param pawn is the pawn that is returning to the back.
     * @pre the pawn has to be not null.
     * @pre the pawn has to be out of the starting point already.
     * @post the pawn is going back to START square.
     */
    public void getpawnbacktostart(Pawn pawn){
        board.displacepawnfromsquare(pawn.returnprev());
        JLabel pawnLabel;
        if(pawn.getcolor()=="red"){
            if(pawn.getnum()==1){
                if (redpawn1 != null) {
                    basicPanel.remove(redpawn1);
                }
                pawnLabel = redpawn1;
            }
            else{
                if (redpawn2 != null) {
                    basicPanel.remove(redpawn2);
                }
                pawnLabel = redpawn2;
            }
        }
        else{
            if(pawn.getnum()==1){
                if (yellowpawn1 != null) {
                    basicPanel.remove(yellowpawn1);
                }
                pawnLabel = yellowpawn1;
            }
            else{
                if (yellowpawn2 != null) {
                    basicPanel.remove(yellowpawn2);
                }
                pawnLabel = yellowpawn2;
            }
        }
        ImageIcon newPawnIcon = new ImageIcon(pawn.getImagePath());
        newPawnIcon = new ImageIcon(newPawnIcon.getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH));
        pawnLabel.setIcon(newPawnIcon);
        if(pawn.getcolor()=="red"){
            if(pawn.getnum()==1){
                pawnLabel.setBounds(180,60, 40, 60);
            }else{
                pawnLabel.setBounds(230,60, 40, 60);
            }
        }
        else{
            if(pawn.getnum()==1){
                pawnLabel.setBounds(530,680, 40, 60);
            }else{
                pawnLabel.setBounds(580,680, 40, 60);
            }
        }
        basicPanel.add(pawnLabel,new Integer(5));
        basicPanel.revalidate();
        basicPanel.repaint();
    }

    /**
     * An accessor that returns the first player's name.
     * @return A string that represents a name.
     */
    public String getPlayer1Name() {
        return player1NameField.getText();
    }
    /**
     * An accessor that returns the second player's name.
     * @return A string that represents a name.
     */
    public String getPlayer2Name() {
        return player2NameField.getText();
    }

    /**
     * An accessor that returns the previous player.
     * @pre the game has to be started.
     * @return A Player .
     */
    public Player getprev(){   
        return previousPlayer;
    }

    /**
     * A transformer that will return an int based on the player's choice.
     * @param option is the option that is the player's choice.
     * @pre the player has to  already given a choice.
     * @return 100 if he wants the pawn to move on the sefaty zone and 0 if he wants to move in circle.
     */
    private int handleMoveChoice(int option) {
        switch (option) {
            case JOptionPane.YES_OPTION:
                return 100;
            case JOptionPane.NO_OPTION:
                return 0;
            default:
                return 0;
        }
    }
    
    /*public void returnn(){
        for(int i=0;i<60;i++){
            Pawn a =board.returnpawnontop(i);
            if(a!=null){
                System.out.print(a.getboardsquare()+a.getcolor()+" "+i+"    ");
            }
        }
    }*/

    private int showMoveChoiceDialog() {
        Object[] options = {"YES", "NO, continue in a circle"};
        return JOptionPane.showOptionDialog(
                null,
                "Wanna go toward the HOME?",
                "Move Choice",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
}


