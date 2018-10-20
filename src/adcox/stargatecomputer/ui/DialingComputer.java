/**
 *  Dialing Computer: A simulation of the dialing computer found on the TV show
 *  Stargate SG-1
 * 
 *  Copyright (C) 2010 Andrew Cox
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package adcox.stargatecomputer.ui;

import adcox.stargatecomputer.objects.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Andrew
 * @version December 24, 2009
 */
public class DialingComputer extends JFrame implements ActionListener, KeyListener, MouseListener,
        MouseMotionListener{
/**
 * Objects**********************************************************************
 */
    
     /**
     * The Graphics object created from the image object.
     */
    private BufferedImage image = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);
    private Graphics2D buffer = (Graphics2D)image.getGraphics();

    Stargate myStargate = new Stargate(250, 70, 480);
    Iris theIris = new Iris(25, myStargate.getCenter(),
            myStargate.getInnerDiameter()/2 + 60, FPS, buffer);
    Shape innerGateArea;        //the area inside the gate - black space or event horizon or iris
    JList addressList;
    JScrollPane addressScroller;

    AddressLibrary myAddressLibrary = new AddressLibrary();
    ArrayList<ChevronLines> theLines = new ArrayList<ChevronLines>();
    ArrayList<SymbolBox> theSymbolBoxes = new ArrayList<SymbolBox>();

    DialingCompButton keyboardBtn;
    DialingCompButton dialBtn;
    DialingCompButton shutdownBtn;
    DialingCompButton irisBtn;
    DialingCompButton loadAddress;
    DialingCompButton displayAddress;
    DialingCompButton soundBtn;

    Keyboard theKeyboard;

/**
 * Values***********************************************************************
 */
    //constants for entire program:
    final int TOP_GATE = myStargate.getLocation().y;
    final int BOTTOM_GATE = myStargate.getLocation().y + myStargate.getDiameter();
    final int RIGHT_SIDE_GATE = myStargate.getLocation().x + myStargate.getDiameter();
    final int LEFT_SIDE_GATE = myStargate.getLocation().x;
    static final int P_WIDTH = 1024;       //width of page
    static final int P_HEIGHT = 768;       //height of page
    int numToDial = 7;              //number of chevrons that can be encoded
    final int[] SEVEN_CHEVRON_ORDER = new int[7];       //order of chevrons if we are dialing 7
    final int[] EIGHT_CHEVRON_ORDER = new int[8];       //order of chevrons if we are dialing 8
    final int[] NINE_CHEVRON_ORDER = new int[9];        //order of chevrons if we are dialing 9

    boolean showDebugInfo = false;
    boolean playSounds = true;
    String status = "";

    static public enum InputMode{REGULAR, PROGRAMMING};
    InputMode mode = InputMode.REGULAR;

    //keeps track of which symbols have been clicked and need to be encoded
    public ArrayList<Integer> chevsToEncode = new ArrayList<Integer>();
    ArrayList<Symbol> symbolsToDisplay = new ArrayList<Symbol>();

    //values for animation purposes
    int frameCounter = 0;                       //counts total frames for entire window
    int pauseTimer = 0;                         //counts frames to create a pause during stargate dialin sequence
    int chevsEncoded = 0;                       //keeps track of how many chevrons have been encoded
    boolean stargateIsActive = false;           //whether or not the stargate is spinning
    boolean encodingInProcess = false;          //whether or not a chevron is in the process of being encoded
    boolean addressValid = false;               //whether or not the stargate has locked on to an address
    boolean playedEncodingClip = false;         //whether or not the sound clip has been played to "lock" a chevron
    boolean displayAnAddress = false;           //whether or not to display an address below the gate for the user to type in
    
    final Color GRAY_GREEN = new Color(122, 157, 160);

    private static int FPS = 35;
    private final Timer timer = new Timer(1000/FPS, this);

    public DialingComputer(){   //constructor
        setPreferredSize(new Dimension(1024, 768));
        setSize(new Dimension(1024, 768));
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationByPlatform(true);
        
        getContentPane().setBackground(Color.black);
        
        initChevronOrders();
        initLinesAndBoxes();
        initList();

        //initialize all buttons
        keyboardBtn = new DialingCompButton(LEFT_SIDE_GATE + myStargate.getDiameter()/4, 650,
                20, 70, "Keyboard", Color.black, GRAY_GREEN, true);
        dialBtn = new DialingCompButton(LEFT_SIDE_GATE + myStargate.getDiameter()/4 + 100,
                650, 20, 50, "Dial", Color.black, GRAY_GREEN, true);
        shutdownBtn = new DialingCompButton(LEFT_SIDE_GATE + myStargate.getDiameter()/4 + 180,
                650, 20, 70, "Shutdown", Color.black, GRAY_GREEN, true);
        irisBtn = new DialingCompButton(LEFT_SIDE_GATE + myStargate.getDiameter()/4 + 270,
                650, 20, 50, "Iris", Color.black, GRAY_GREEN, true);
        soundBtn = new DialingCompButton(20, 5, 16, 23, -1, Color.black, GRAY_GREEN, true);

        int bottomOfScroller = addressScroller.getLocation().y + addressScroller.getSize().height;
        loadAddress = new DialingCompButton(20, bottomOfScroller + 30, 20, 45, "Load",
                Color.black, GRAY_GREEN, true);
        displayAddress = new DialingCompButton(75, bottomOfScroller + 30, 20, 55, "Display",
                Color.black, GRAY_GREEN, true);

        theIris.openCloseIris(playSounds);
        innerGateArea = myStargate.getInnerGateArea();
        theKeyboard = new Keyboard(this);
        theKeyboard.setVisible(false);
        
        setVisible(true);
        timer.start();
    }//======================================

    /**
     * Initializes the order that chevrons are to be endcoded in.
     */
    private void initChevronOrders(){
        //NINE CHEVRONS
        for(int i = 1; i < 9; i++){
            NINE_CHEVRON_ORDER[i - 1] = i;
        }
        NINE_CHEVRON_ORDER[8] = 0;
        //EIGHT CHEVRONS
        for(int i = 1; i < 5; i++){
            EIGHT_CHEVRON_ORDER[i - 1] = i;
        }
        EIGHT_CHEVRON_ORDER[4] = 6;
        EIGHT_CHEVRON_ORDER[5] = 7;
        EIGHT_CHEVRON_ORDER[6] = 8;
        EIGHT_CHEVRON_ORDER[7] = 0;
        
        //SEVEN CHEVRONS
        SEVEN_CHEVRON_ORDER[0] = 1;
        SEVEN_CHEVRON_ORDER[1] = 2;
        SEVEN_CHEVRON_ORDER[2] = 3;
        SEVEN_CHEVRON_ORDER[3] = 6;
        SEVEN_CHEVRON_ORDER[4] = 7;
        SEVEN_CHEVRON_ORDER[5] = 8;
        SEVEN_CHEVRON_ORDER[6] = 0;
    }//=========================================

    /**
     * Initializes the lines and boxes on screen
     */
    private void initLinesAndBoxes(){
        for(int index = 0; index < 9; index++){
            theLines.add(new ChevronLines(myStargate, index));
            theSymbolBoxes.add(new SymbolBox(myStargate, index, numToDial));
        }
    }//======================================

    /**
     * Initializes the list of addresses.
     */
    private void initList(){
        ArrayList<Address> addresses = myAddressLibrary.getTheAddresses();
        String[] names = new String[addresses.size()];
        for(int i = 0; i < addresses.size(); i++){
            names[i] = addresses.get(i).getName();
        }
        addressList = new JList(names); //data has type Object[]
        addressList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        addressList.setLayoutOrientation(JList.VERTICAL);
        addressList.setVisibleRowCount(10);
        addressList.setBackground(Color.black);
        addressList.setForeground(GRAY_GREEN);

        addressScroller = new JScrollPane(addressList);
        addressScroller.setPreferredSize(new Dimension(150, 300));
        addressScroller.setSize(150, 300);
        addressScroller.setLocation(20, 30);
        addressScroller.setBackground(Color.black);
        addressScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        addressScroller.setDoubleBuffered(true);
        
        this.add(addressScroller);
    }//====================================


    //##########################################################################
    //********************DRAWING***********************************************

    @Override
    public void actionPerformed(ActionEvent e){
        this.repaint();
    }

    @Override
    public void paint(Graphics g){
        buffer.clearRect(0, 0, getWidth(), getHeight());
        super.paint(buffer);
        drawScreen(buffer);
        g.drawImage(image, 0, 0, null);
    }//============================

    /**
     * This function takes care of ALL GRAPHICS for the program - everything
     * that gets drawn gets passed through THIS function
     * @param g - the Graphics2D object responsible for drawing everything
     */
    private void drawScreen(Graphics2D g){
        //draw aspects of the screen
        drawLines(g);
        drawChevBoxes(g);
        drawButtons(g);
        
        drawStatusBox(g, status, GRAY_GREEN);

        if(displayAnAddress)
            displayEntireAddress(g);

        //check to see if anything about the stargate is changing
        if(stargateIsActive)
            animateStargate();
        myStargate.draw(g, showDebugInfo, theIris.isAnimating());

        //Iris has to be drawn over the stargate
//        drawIris(g);
        
        //draw any symbols
        for(int i = 0; i < symbolsToDisplay.size(); i++)
            symbolsToDisplay.get(i).draw(g);

        //draw things specially if in Programming mode
        if(mode == InputMode.PROGRAMMING){
            g.setColor(Color.red);
            g.drawString("PROGRAMMING MODE", 20, 450);
            g.drawString("numToDial: " + numToDial, 20, 480);
        }

        //dubugging:
        if(showDebugInfo){
            g.drawString("Total frames: " + frameCounter, 50, 200);
            for(int i = 0; i < chevsToEncode.size(); i++)
                g.drawString("ChevsToEncode[" + i + "]: " + chevsToEncode.get(i), 50, 220 + 20*i);
        }

        frameCounter++;
    }//=======================================

    /**
     * This function draws all the buttons.
     * @param g - the Graphics object responsible for drawing things.
     */
    private void drawButtons(Graphics2D g){
        keyboardBtn.draw(g);
        dialBtn.draw(g);
        shutdownBtn.draw(g);
        irisBtn.draw(g);
        loadAddress.draw(g);
        displayAddress.draw(g);
        soundBtn.draw(g);
    }//=================================

    /**
     * This function draws all the lines on the screen.
     * @param g - the Graphics object responsible for drawing things.
     */
    private void drawLines(Graphics g){     //draw lines going from stargate to sidebar
        /**
         * All values for the boxes are set in the ChevronLines class
         * All values for the lines are set off of stargate values or the boxes' values
         */
        SymbolBox calculator = new SymbolBox(myStargate, 0, 0);
        if(numToDial <8){
            theLines.get(0).draw(g, calculator.getMiddleYOfBox(7), false);
            theLines.get(1).draw(g, 0, false);
            theLines.get(2).draw(g, 0, false);
            theLines.get(3).draw(g, 0, false);
            theLines.get(6).draw(g, calculator.getMiddleYOfBox(4), false);
            theLines.get(7).draw(g, calculator.getMiddleYOfBox(5), false);
            theLines.get(8).draw(g, calculator.getMiddleYOfBox(6), false);
        }
        if(numToDial == 8){
            theLines.get(0).draw(g,calculator.getMiddleYOfBox(8), false);
            theLines.get(1).draw(g, 0, false);
            theLines.get(2).draw(g, 0, false);
            theLines.get(3).draw(g, 0, false);
            theLines.get(4).draw(g, 0, false);
            theLines.get(6).draw(g, calculator.getMiddleYOfBox(5), false);
            theLines.get(7).draw(g, calculator.getMiddleYOfBox(6), false);
            theLines.get(8).draw(g, calculator.getMiddleYOfBox(7), false);
        }
        if(numToDial > 8){
            theLines.get(0).draw(g,calculator.getMiddleYOfBox(8), false);
            theLines.get(1).draw(g, 0, false);
            theLines.get(2).draw(g, 0, false);
            theLines.get(3).draw(g, 0, false);
            theLines.get(4).draw(g, 0, false);
            theLines.get(5).draw(g, 0, false);
            theLines.get(6).draw(g, calculator.getMiddleYOfBox(6), false);
            theLines.get(7).draw(g, calculator.getMiddleYOfBox(7), false);
            theLines.get(8).draw(g, calculator.getMiddleYOfBox(7)
                    + calculator.BOX_HEIGHT/2 + calculator.BETWEEN_BOXES/2, true);
        }
    }//=================================

    /**
     * This function draws all the symbolBoxes.
     * @param g - The Graphics object the program is using to draw things with.
     */
    private void drawChevBoxes(Graphics g){
        for(int index = 0; index < numToDial; index++){
            theSymbolBoxes.get(index).draw(g, numToDial);
        }
    }//===================================================

    /**
     * This function draws the iris, most of which is contained in the Iris class.
     * @param g - the Graphics object responsible for drawing stuffs
     */
    private void drawIris(Graphics g){
        Shape oldClip = g.getClip();        //save the old clip
        g.setClip(innerGateArea);
//        theIris.draw(g);
        //revert back to the old clip
        g.setClip(oldClip);
    }//=========================================

    /**
     * This function displays a selected address below the gate for the user to
     * see.  It does NOT load the address into the computer for quick dialing
     * @param g - the Graphics object the program is using to draw things with
     */
    private void displayEntireAddress(Graphics g){
        //make sure an address has been selected
        if(addressList.getSelectedIndex() != -1){
            //get the name of the address and load it from the database
            String name = (String)addressList.getSelectedValue();
            Address theAddress = new Address(null);
            ArrayList<Address> allAddresses = myAddressLibrary.getTheAddresses();
            for(int i = 0; i < allAddresses.size(); i++){
                if(name.equals(allAddresses.get(i).getName())){
                    theAddress = allAddresses.get(i);
                }
            }

            //if we did find an address, draw it below the gate
            if(theAddress != null){
                status = "";
                int xVal = 0;
                for(int i = 0; i < theAddress.getAddress().size(); i++){
                    int halfWidth = (theAddress.getAddress().size() * 50)/2;
                    int gateCenterX = myStargate.getLocation().x + myStargate.getDiameter()/2;
                    Symbol symbol = new Symbol(theAddress.getAddress().get(i),
                            gateCenterX - halfWidth + xVal, BOTTOM_GATE + 80);
                    g.setColor(Color.white);
                    symbol.draw(g);
                    xVal += 50;
                }
            }
        }
    }//===============================

    /**
     * This function draws the status box.
     * @param g - the Graphics object the program is using to draw things with
     * @param toWrite - a String representing what is to go in the status box
     * @param colorToWrite - a Color object representing the color text is to be
     * written in
     */
    private void drawStatusBox(Graphics g, String toWrite, Color colorToWrite){
        g.setColor(GRAY_GREEN);
        //top and bottom lines
        g.drawLine(LEFT_SIDE_GATE, BOTTOM_GATE + 20, RIGHT_SIDE_GATE, BOTTOM_GATE + 20);
        g.drawLine(LEFT_SIDE_GATE, BOTTOM_GATE + 90, RIGHT_SIDE_GATE, BOTTOM_GATE + 90);

        //top and bottom end lines - they are a little longer than the others
        g.drawLine(LEFT_SIDE_GATE, BOTTOM_GATE + 20, LEFT_SIDE_GATE, BOTTOM_GATE + 30);
        g.drawLine(LEFT_SIDE_GATE, BOTTOM_GATE + 80, LEFT_SIDE_GATE, BOTTOM_GATE + 90);
        g.drawLine(RIGHT_SIDE_GATE, BOTTOM_GATE + 20, RIGHT_SIDE_GATE, BOTTOM_GATE + 30);
        g.drawLine(RIGHT_SIDE_GATE, BOTTOM_GATE + 80, RIGHT_SIDE_GATE, BOTTOM_GATE + 90);

        //all the inebetween lines
        for(int x = 1; x < 30; x++){
            int interval = (RIGHT_SIDE_GATE - LEFT_SIDE_GATE)/30;
            g.drawLine(LEFT_SIDE_GATE + x*interval, BOTTOM_GATE + 20,
                    LEFT_SIDE_GATE + x*interval, BOTTOM_GATE + 25);
            g.drawLine(LEFT_SIDE_GATE + x*interval, BOTTOM_GATE + 85,
                    LEFT_SIDE_GATE + x*interval, BOTTOM_GATE + 90);
        }

        g.setColor(colorToWrite);
        Font backup = g.getFont();      //remember the default font, cause I like it
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 56));
        int len = toWrite.length();
        int centerXGate = LEFT_SIDE_GATE + myStargate.getDiameter()/2;
        g.drawString(toWrite, centerXGate - 37*(len/2), BOTTOM_GATE + 72);

        g.setFont(backup);  //restore defaults
    }//=====================================

    /**
     * This function is responsible for animating the chevrons as the seem to
     * appear from the gate and then zoom over to their box
     */
    private void blowUpSymbol(){

        if(pauseTimer == 1){    //1st time only
            //add it to array of symbols to be displayed
            symbolsToDisplay.add( new Symbol(chevsToEncode.get(chevsEncoded), 0, 0));
            //get the default size of this symbol
            int defaultBaseLength = symbolsToDisplay.get(chevsEncoded).getDefaultSize();
            //x = the stargate's x + half the diameter - about half the glyph width
            int x = myStargate.getLocation().x + myStargate.getDiameter()/2 - (5*defaultBaseLength)/2;
            //y = the stargate's y + 1/8 of the diameter
            int y = myStargate.getLocation().y + myStargate.getDiameter()/8;
            //change the x, y location to what we just calculated
            symbolsToDisplay.get(chevsEncoded).setPosition(x, y);
        }

        if(pauseTimer < 9){      //for the first 5 frames during pauseTimer
            //enlarge the glyph
            int defaultBaseLength = symbolsToDisplay.get(chevsEncoded).getDefaultSize();
            symbolsToDisplay.get(chevsEncoded).setBaseLength(defaultBaseLength + pauseTimer);
            int oldX = symbolsToDisplay.get(chevsEncoded).getLocation().x;
            int oldY = symbolsToDisplay.get(chevsEncoded).getLocation().y;
            int newX = oldX - defaultBaseLength;     //recalutlate x and y when enlarging
            int newY = oldY + 2*defaultBaseLength;
            symbolsToDisplay.get(symbolsToDisplay.size() - 1).setPosition(newX, newY);
        }

        int totalFrames = myStargate.getFramesToAnimateTopChev();
        if(pauseTimer >= 9 && pauseTimer < totalFrames){
            //calculate where the symbol is going
            int timeLeft = totalFrames - pauseTimer;
            int oldX = symbolsToDisplay.get(chevsEncoded).getLocation().x;
            int oldY = symbolsToDisplay.get(chevsEncoded).getLocation().y;
            int oldBaseLength = symbolsToDisplay.get(chevsEncoded).getCurrentSize();
            int destX = theSymbolBoxes.get(chevsEncoded).getLocation().x + 15;
            int destY = theSymbolBoxes.get(chevsEncoded).getLocation().y +
                    theSymbolBoxes.get(chevsEncoded).BOX_HEIGHT - 3;
            int totalX = destX - oldX;
            int totalY = destY - oldY;
            double deltaX = totalX/timeLeft;
            double deltaY = totalY/timeLeft;
            //send it over to the box
            int newX = (int)(oldX + deltaX);
            int newY = (int)(oldY + deltaY);
            symbolsToDisplay.get(chevsEncoded).setPosition(newX, newY);
            if(timeLeft < 8)
                symbolsToDisplay.get(chevsEncoded).setBaseLength(oldBaseLength - 1);
        }
    }//===========================================================

    /**
     * This function is responsible for knowing where the stargate is in terms
     * of animation, and to calculate the next move the Stargate should make.
     */
    private void animateStargate(){
        //check to see if there is a new chevron to encode
       checkForNewEncodee();

       //See if the inner ring of the 'gate has reached it's stop spot
       if(!myStargate.hasReachedStopSpot()){
            myStargate.spinRing();
            //tell chev0 that it is going to have to animate
            myStargate.setAnimationStatus(0, false);
        }
        else{   //if the inner ring HAS reached its stopSpot
            //check to see if the 'gate has encoded all the needed chevrons
            if(chevsEncoded >= numToDial - 1){
                //check addresses if all chevrons have been encoded
                checkAddresses();
                if(!addressValid){
                    //if the address is NOT valid, clear things up and exit the funtion
                    clearScreen(false);
                    if(mode == InputMode.REGULAR)
                        JOptionPane.showMessageDialog(this, "That address does not exist.");
                    else{
                        writeCode();
                        clearScreen(true);
                    }
                    chevsToEncode.clear();
                    return;     //exit the function
                }
            }
           
            pauseTimer++;                               //add one to the pauseTimer
            myStargate.setLitUpChevs(0, true);          //light up the top chevron
            myStargate.setAnimateTopChev(true, 31);         //animate the top chevron - takes 22 frames
            blowUpSymbol();                             //blow up the symbol and move it to its box
            //check to see if the program has played the clip already, and then check to see if sound is allowed
            if(!playedEncodingClip){
                if(playSounds){
                    //play the right clip
                    if(chevsEncoded <= 6){
                        MP3 clip = new MP3("aud/chevron_incoming_" + (chevsEncoded + 1) + ".mp3");
                        clip.play();
                    }
                    else{
                        MP3 clip = new MP3("aud/chevron_incoming_1.mp3");
                        clip.play();
                    }
                    //remember I've played the clip
                    playedEncodingClip = true;
                }
            }
           
        }
        //this is actual ENCODING BELOW
        if(pauseTimer > 31){                                        //52 frames to animate the chevron
            pauseTimer = 0;                                         //reset timer
            myStargate.setReachedStopSpot(false);                   //reset booleans
            myStargate.setAnimateTopChev(false, 41);                    //tell the top chevron to stop animating
            myStargate.setLitUpChevs(0, false);                     //turn off the top chevron
            playedEncodingClip = false;                             //reset this variable so I will play the clip next time a chevron is encoded
            encodingInProcess = false;                              //there is no long an encoding going on
            chevsEncoded++;                                         //remember I encoded a chevron
            myStargate.setLitUpChevs(lightUpCorrectChev(), true);   //light up the chevron that was just encoded
            theLines.get(lightUpCorrectChev()).changeLitUp(true);   //light up the correct lines
            theSymbolBoxes.get(chevsEncoded - 1).changeLitUp(true); //light up the right symbolBox
            checkForNewEncodee();                                   //check for a new chevron to encode
        }
    }//==========================================================

    /**
     * This function uses the computer 'robo-voice' on a Mac to say a string.
     * Java accesses the unix base of Mac OS to do this.
     * @param stuffToSay - String representing what the computer should say
     */
    public void say(String stuffToSay){
        if(playSounds){
            try {
                Runtime.getRuntime().exec("say " + stuffToSay);
            } catch (IOException ex) {
                Logger.getLogger(DialingComputer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//==================================

    //##########################################################################
    //********************INFORMATION STUFF*************************************

    /**
     * This function checks to see if there is another chevron to encode and
     * then directs the program accordingly.
     */
    private void checkForNewEncodee(){
        //Check to see if we are ready to encode the next chevron
        if(chevsEncoded < chevsToEncode.size()){
            //there IS a new chevron to encode!!
            //get the index of which symbol it is
            if(!encodingInProcess){
                //myStargate.calcSpinTime();
                myStargate.moveToSymbol(chevsToEncode.get(chevsEncoded));
                encodingInProcess = true;
            }
        }
        //We are NOT ready to encode the next chevron
        else{
            if(chevsEncoded < numToDial)    //if we still have chevrons left to encode, spin!!
                myStargate.setStopSpot(10000);
            else{
                if(addressValid){
                    //GATE IS LOCKED!!
                    //play sound
                    if(playSounds){
                        MP3 clip = new MP3("aud/openGate.mp3");
                        clip.play();
                    }
                    //Flash the backgrounds of the chevron boxes while playing the MP3 clip
                    for(int i = 0; i < chevsEncoded; i++){
                        theSymbolBoxes.get(i).startFlashing();
                    }
                    //display wormhole
                    myStargate.setHorizonVisibility(true);
                    //stop spinning
                    stargateIsActive = false;
                    //*** start timer to disengage wormhole
                }
            }
        }
    }//=======================================

    /**
     * This function checks an address to see if it is in the database. If the
     * address is in the database, then the boolean addressValid is updated to
     * true.
     */
    private void checkAddresses(){
        //clone the list of addresses from the database
        ArrayList<Address> theAddresses = (ArrayList<Address>)
                myAddressLibrary.getTheAddresses().clone();

        //go through all the addresses and look for a match
        addressValid = false;
        for(int i = 0; i < theAddresses.size(); i++){
            if(chevsToEncode.equals(theAddresses.get(i).getAddress())){
                status = theAddresses.get(i).getName();
                displayAnAddress = false;
                addressValid = true;
            }
        }
    }//============================

    /**
     * This function takes a dialed address and converts it into java code for
     * easy transfusion into the database.
     */
    private void writeCode(){
        BufferedWriter out;
        try{
            out = new BufferedWriter(new FileWriter("Adresses.txt", true));
            String name = JOptionPane.showInputDialog("What would you like to name the ArrayList of symbols?");
            String planet = JOptionPane.showInputDialog("What would you like to name the Planet?");

            if(name != null && planet != null){
                out.write("ArrayList<Integer> " + name + " = new ArrayList<Integer>();\n");
                for(int i = 0; i < chevsToEncode.size(); i++){
                    //code.add(name + ".add(" + chevsToEncode.get(i) + ");");
                    out.write(name + ".add(" + chevsToEncode.get(i) + ");\n");
                }
                out.write("theAddresses.add(new Address(\"" + planet + "\", " + name + "));\n\n");
            }
            out.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "There was a problem:" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//================================

     /**
     * Prints out an ArrayList of Strings.
     * @param theStrings ArrayList<String> representing the strings to be printed
     * out.
     */
    private void printStrings(ArrayList<String> theStrings){
        JTextArea text = new JTextArea(20, 80);
        JScrollPane scroller = new JScrollPane(text);
        for(int i = 0; i < theStrings.size(); i++){
            text.append('\n' + theStrings.get(i));
        }
        JOptionPane.showMessageDialog(this, scroller, "Your code, Your Majesty!", JOptionPane.PLAIN_MESSAGE);
    }//===========================

    /**
     * This function uses hard-coded arrays to determine the order in which
     * chevrons are to be lit up and encoded
     * @return an integer representing the chevron number that is to be used.
     * The function will return -1 if an error occurs.
     */
    private int lightUpCorrectChev(){
        if(numToDial < 8)
            return SEVEN_CHEVRON_ORDER[chevsEncoded - 1];
        if(numToDial == 8)
            return EIGHT_CHEVRON_ORDER[chevsEncoded - 1];
        if(numToDial > 8)
            return NINE_CHEVRON_ORDER[chevsEncoded - 1];
        //code should NEVER reach this line below
        return -1;
    }//=================================

    /**
     * This function completely clears the screen, but has the option of not
     * clearing the chevrons
     * @param clearChevs - a boolean representing whether or not to clear the chevrons
     */
    private void clearScreen(boolean clearChevs){
        pauseTimer = 0;
        chevsEncoded = 0;
        stargateIsActive = false;
        encodingInProcess = false;
        addressValid = false;
        symbolsToDisplay.clear();
        status = "";
        if(clearChevs)
            chevsToEncode.clear();
        for(int i = 0; i < theLines.size(); i++)
            theLines.get(i).changeLitUp(false);
        for(int i = 0; i < theSymbolBoxes.size(); i++)
            theSymbolBoxes.get(i).changeLitUp(false);

        myStargate.clear(playSounds);

        addressList.clearSelection();
        
        theKeyboard.clear();
        theKeyboard.refresh(this);
    }//==========================================

    /**
     * This function loads an address from the data base into the dialing
     * computer so that the user can quickly dial the address
     */
    private void loadEntireAddress(){
        //check to make sure an address has been selected
        if(addressList.getSelectedIndex() != -1){
            //get the name of the address and search through the data base for it
            String name = (String)addressList.getSelectedValue();
            Address theAddress = new Address(null);
            ArrayList<Address> allAddresses = myAddressLibrary.getTheAddresses();
            for(int i = 0; i < allAddresses.size(); i++){
                if(name.equals(allAddresses.get(i).getName())){
                    theAddress = allAddresses.get(i);
                }
            }

            //once have the address, (hopefully!)
            if(theAddress != null){
                //check to make sure the current configuration will dial the address
                if((numToDial == theAddress.getAddress().size())){
                    //save the chevrons to encode into the dialer, and say the name
                    chevsToEncode = theAddress.getAddress();
                    say(theAddress.getName());
                    //attempmt to repaint the keyboard.  It will throw an exception if it isn't yet visible.
                    try{
                        theKeyboard.setVisible(true);
                        theKeyboard.clear();
                        theKeyboard.refresh(this);
                        theKeyboard.repaint();
                    }catch(Exception x){}

                }
                //display error message
                else
                    JOptionPane.showMessageDialog(this, "You must turn on the correct number of chevrons.",
                            "There was a problem...", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//=================================

/**
 * *******************************MouseListener Methods*************************
 */

    public void mouseClicked(MouseEvent e){
        Point mouseLocation = e.getPoint();
        if(keyboardBtn.mouseInButton(mouseLocation)){
                theKeyboard.setVisible(true);
                theKeyboard.refresh(this);
        }
        if(dialBtn.mouseInButton(mouseLocation)){
            stargateIsActive = true;
            checkForNewEncodee();
            if(!theIris.isOpen())
                theIris.openCloseIris(playSounds);
        }
        if(shutdownBtn.mouseInButton(mouseLocation)){
            //change whether or not the stargate is spinning
            stargateIsActive = false;
            clearScreen(true);
        }
        if(irisBtn.mouseInButton(mouseLocation)){
            if(!stargateIsActive){
                theIris.openCloseIris(playSounds);
            }
        }
        if(loadAddress.mouseInButton(mouseLocation)){
            loadEntireAddress();
        }
        if(displayAddress.mouseInButton(mouseLocation)){
            displayAnAddress = !displayAnAddress;
        }
        if(soundBtn.mouseInButton(mouseLocation)){
            playSounds = !playSounds;
            if(playSounds)
                soundBtn.setSymbolIndex(-1);
            else
                soundBtn.setSymbolIndex(-2);
        }
    }//=======================================

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
/**
 * ************************MouseMotionListener Methods*************************
 */

    public void mouseMoved(MouseEvent e){
        Point mouseLocation = e.getPoint();
        
        if(keyboardBtn.mouseInButton(mouseLocation))
            keyboardBtn.changeBackground(Color.blue);
        else
            keyboardBtn.changeBackground(Color.black);

        if(dialBtn.mouseInButton(mouseLocation))
            dialBtn.changeBackground(Color.blue);
        else
            dialBtn.changeBackground(Color.black);

        if(shutdownBtn.mouseInButton(mouseLocation))
            shutdownBtn.changeBackground(Color.blue);
        else
            shutdownBtn.changeBackground(Color.black);

        if(irisBtn.mouseInButton(mouseLocation))
            irisBtn.changeBackground(Color.blue);
        else
            irisBtn.changeBackground(Color.black);

        if(loadAddress.mouseInButton(mouseLocation))
            loadAddress.changeBackground(Color.blue);
        else
            loadAddress.changeBackground(Color.black);

        if(displayAddress.mouseInButton(mouseLocation))
            displayAddress.changeBackground(Color.blue);
        else
            displayAddress.changeBackground(Color.black);

        if(soundBtn.mouseInButton(mouseLocation))
            soundBtn.changeBackground(Color.blue);
        else
            soundBtn.changeBackground(Color.black);

//        repaint();
    }//==========================================================
    
    public void mouseDragged(MouseEvent e) {}

/**
 * ***************************KEY LISTENER METHODS*****************************
 */


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(e.isMetaDown() || e.isControlDown()){
            if(code == KeyEvent.VK_Z){
                if(mode == InputMode.REGULAR){
                    mode = InputMode.PROGRAMMING;
                }else
                    mode = InputMode.REGULAR;
            }
            if(mode == InputMode.PROGRAMMING){
                if(code == KeyEvent.VK_D){  //toggle debug
                    showDebugInfo = !showDebugInfo;
                }
                if(code == KeyEvent.VK_E)
                    myStargate.setHorizonVisibility(true);
                if(code == KeyEvent.VK_7)
                    numToDial = 7;
                if(code == KeyEvent.VK_8)
                    numToDial = 8;
                if(code == KeyEvent.VK_9)
                    numToDial = 9;
            }
        }
        
//        repaint();
    }//=======================================

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

}//***END OF DIALING COMPUTER***
