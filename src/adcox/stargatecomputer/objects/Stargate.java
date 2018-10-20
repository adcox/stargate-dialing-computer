/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;
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
/**
 *
 * @author Andrew
 * @version December 24, 2009
 */
public class Stargate{
    //******GENERAL*******************************************************
    private int myX, myY;   //location top left corner
    private int myDiameter;
    protected ArrayList<Chevron> theChevrons = new ArrayList<Chevron>();
    protected EventHorizon theHorizon;
    protected Shape innerGateArea;

    //*****ANIMATION******************************************************
    protected boolean isTurning;          //know whether or not to spin the gate
    protected boolean turningClockwise;   //clockwise or not
    protected int currentAngle;         //the current position of the ring in degrees
    protected int stopSpot = 0;           //remembers where the ring should stop spinning - degrees
    protected int horizonCounter = 0;     //counts frames to expand the horizon
    protected boolean reachedStopSpot = false;     //whether or not the ring has reached its stopSpot
    protected boolean animateTopChev = false;
    protected int framesToAnimateChev = 40;         //the number of frames the top chevron should take to animate
    protected boolean[] chevIsLitUp = new boolean[9];  //this can be set
    protected boolean horizonIsVisible = false;

    final Color GRAY_GREEN = new Color(122, 157, 160);

    public Stargate(int x, int y, int diameter){
        myX = x;
        myY = y;
        myDiameter = diameter;
        placeChevrons(myDiameter/80);
        //set all booleans in array to false
        for(int i = 0; i<chevIsLitUp.length; i++)
            chevIsLitUp[i] = false;
        //initialize the event horizon - centered, takes up entire center of gate
        theHorizon = new EventHorizon((int)(.74 * myDiameter),
                (int)(myX + .13 * myDiameter), (int)(myY + .13 * myDiameter));
        innerGateArea = insideStargateShape();
    }//=================================

    /**
     * This function sets the animation status of a chevron.
     * @param chevNum an integer representing the index of the chevron.
     * @param hasDoneAnimation a boolean representing what the animation status
     * will be set to.
     */
    public void setAnimationStatus(int chevNum, boolean hasDoneAnimation){
        theChevrons.get(chevNum).hasDoneAnimation = hasDoneAnimation;
    }//================================

    /**
     * This function sets the visibility of the event horizon.
     * @param visible a boolean representing the visibility of the event horizon.
     */
    public void setHorizonVisibility(boolean visible){horizonIsVisible = visible;}

    /**
     * This function tells the top chevron if it needs to animate, and if so, 
     * for how many frames.
     * @param b a boolean for whether or not the top chevron will animate.
     * @param framesToAnimate  an integer representing the number of frames the
     * top chevron is to take while animating.
     */
    public void setAnimateTopChev(boolean b, int framesToAnimate){
        animateTopChev = b;
        framesToAnimateChev = framesToAnimate;
    }//==============================

    /**
     * This function returns the number of frames the stargate uses to animate
     * the top chevron.
     * @return an integer representing the number of frames the top chevron will
     * animate for.
     */
    public int getFramesToAnimateTopChev(){return framesToAnimateChev;}

    /**
     * @return a boolean representing whether or not the stargate has reached a
     * stop spot.
     */
    public boolean hasReachedStopSpot(){return reachedStopSpot;}

    /**
     * This function tells the stargate if it has reached a stop spot.  Normally
     * the stargate determines this on its own, however, this function allows
     * it to be set.
     * @param b a boolean representing whether or not the Stargate has reached
     * its stopping spot.
     */
    public void setReachedStopSpot(boolean b){reachedStopSpot = b;}

    /**
     * @return a boolean array showing which chevrons are lit up. 
     */
    public boolean[] getLitUpChevs(){return chevIsLitUp;}

    /**
     * This function turns the light on a chevron on or off.
     * @param index an integer representing the index of the chevron to light.
     * @param b a boolean representing the 'lit' status of the given chevron.
     */
    public void setLitUpChevs(int index, boolean b){chevIsLitUp[index] = b;}

    /**
     * 
     * @return a boolean representing whether or not the event horizon is visible. 
     */
    public boolean hasVisibleHorizon(){return horizonIsVisible;}

    /**
     * This function sets the diameter of the stargate.  This diameter is the
     * outer most diameter.
     * @param diameter an integer representing the diameter of the 'gate.
     */
    public void setDiameter(int diameter){myDiameter = diameter;}

    /**
     * 
     * @return an integer representing the diameter of the stargate. 
     */
    public int getDiameter(){ return myDiameter;}

    /**
     * This function returns the inner diameter of the stargate.  This is the 
     * inner most diameter.
     * @return an integer representing the inner diameter of the stargate.
     */
    public int getInnerDiameter(){ return (int)(.76 * myDiameter);}

    /**
     * This function returns the area inside the gate, occupied by empty space
     * or the event horizon as a shape.
     * @return a Shape object representing the inside of the stargate.
     */
    public Shape getInnerGateArea(){ return innerGateArea;}

    /**
     * This function sets the location of the top left corner of the square that
     * bounds the stargate
     * @param x an integer representing the x coordinate in pixels.
     * @param y an integer representing the y coordinate in pixels.
     */
    public void setLocation(int x, int y){
        myX = x;
        myY = y;
    }//==================================

    /**
     * This function returns the location of the top left corner of the square that
     * bounds the stargate.
     * @return a Point representing the described location. Coordinates are in 
     * pixels.
     */
    public Point getLocation(){
        return new Point(myX, myY);
    }//==================================

    /**
     * @return a Point representing the center of the stargate.  Coordinates are
     * in pixels.
     */
    public Point getCenter(){
        return new Point(myX + myDiameter/2, myY + myDiameter/2);
    }//================================

    /**
     * This function  sets a new stopping spot for the stargate.
     * @param newStopSpot an integer representing the stopping spot of the stargate.
     * This value is in degrees, [0, 360).
     */
    public void setStopSpot(int newStopSpot){
        //set stopSpot = to 0 if you want ring to spin indeffinitely
        stopSpot = newStopSpot;
    }//=====================================

    /**
     * This function places the chevrons around the stargate using trig.
     * @param baseLength an integer representing how large the base unit in drawing
     * a chevron is going to be.
     */
    private void placeChevrons(int baseLength){
        for(double theta = 0; theta < (2*Math.PI); theta = theta + Math.PI/4.5){
            int x, y;
            /**
             * explanation: "myX + myDiameter/2" - this is the offset to the middle of the stargate
             * "myDiamter/2 + 7 - this is the radius of a circle slightly larger than the Stargate
             * "Math.cos(theta + .0873)" - this is trig to find the x offset for the different
             * chevrons.  the .0873 is a 5 degree offset in radians.  I don't know why it is nessesary,
             * just that it is.
             */
            x = myX + myDiameter/2 - smartRounding((myDiameter/2 + 7)*Math.cos(theta + Math.PI/2 - .087266));
            y = myY + myDiameter/2 - smartRounding((myDiameter/2 + 7)*Math.sin(theta + Math.PI/2 - .087266));
            theChevrons.add(new Chevron(x, y, baseLength, theta));
        }
    }//=========================================

    /**
     * This function changes the current angle of the inner ring on the Stargate
     * and ensures that that angle stays within [0, 360).
     */
    private void animate(){
        if(turningClockwise)
            currentAngle--;
        else
            currentAngle++;
        
        //restrict animation angle to [0, 360)
        if(currentAngle < 0)
            currentAngle += 360;
        if(currentAngle >= 360)
            currentAngle -= 360;
    }//==========================

    /**
     * This function is responsible for animating the ring and watching to make
     * sure the inner ring doesn't pass its stopping spot.
     */
    public void spinRing(){
        //if ring hasn't reached the stopSpot, keep spinning
        if(stopSpot == 10000){   // just keep spinning
            animate();
            reachedStopSpot = false;
        }else{
            if(currentAngle != stopSpot)
                animate();
            else{   //if it has, switch directions, set boolean to true
                turningClockwise = !turningClockwise;
                reachedStopSpot = true;
            }
        }
    }//==============================
    

    /**
     * Sets the stargate's animation so that it will spin to where the given 
     * symbol would be if it were visible on the spinning ring.
     * @param symbolNum - the index of the symbol, 0 being the point of origin
     */
    public void moveToSymbol(int symbolNum){
        stopSpot = (int)((360.0/39.0)*(39 - symbolNum));
    }//=================================
    
    /**
     * This function calculates a random spin time (in degrees turned) for the 
     * gate to spin.
     */
    public void calcSpinTime(){
        /**
         * I want to be able to randomly choose groups of currentAngles for the
         * gate to spin before it stops, the chevron  appears, and the gate
         * switches direction.
         */
         Random randy = new Random();
         double timeToSpin = 10*(randy.nextInt(10) + 5);  //this will be number of frames
         if(turningClockwise)
             stopSpot = currentAngle - (int)timeToSpin;
         else
             stopSpot = currentAngle + (int)timeToSpin;
    }//===================================

    /**
     * This function is responsible for drawing the stargate.
     * @param g the Graphics object responsible for drawing everything.
     * @param showDebugInfo a boolean representing whether or not debugging 
     * information will be drawn to the screen.
     * @param irisAnimating  a boolean representing whether or not the iris is 
     * in the process of an animation cycle.
     */
    public void draw(Graphics g, boolean showDebugInfo, boolean irisAnimating){
        //draw outer ring
        g.setColor(GRAY_GREEN);
        g.fillArc(myX, myY, myDiameter, myDiameter, 0, 360);
        //black space
        g.setColor(Color.black);
        g.fillOval((int)(myX + .02 * myDiameter),(int)(myY + .02 * myDiameter),
                (int)(myDiameter * .96), (int)(myDiameter * .96));
        //next rings
        g.setColor(GRAY_GREEN);
        g.drawOval((int)(myX + .03 * myDiameter),(int)(myY + .03 * myDiameter),
                (int)(myDiameter * .94), (int)(myDiameter * .94));
        g.drawOval((int)(myX + .045 * myDiameter), (int)(myY + .045 * myDiameter),
                (int)(myDiameter * .91), (int)(myDiameter * .91));
        //drawLittleLines(g);

        //actual spinning ring
        g.drawOval((int)(myX + .06 * myDiameter), (int)(myY + .06 * myDiameter),
                (int)(myDiameter * .88), (int)(myDiameter * .88));
        g.drawOval((int)(myX + .1 * myDiameter), (int)(myY + .1 * myDiameter),
                (int)(myDiameter * .80), (int)(myDiameter * .80));
        //draw dividing lines
        /*drawLines(g);*/
        Graphics2D gg = (Graphics2D)g;
        g = drawInnerRing(gg);

        //final ring
        g.fillOval((int)(myX + .12 * myDiameter), (int)(myY + .12 * myDiameter),
                (int)(myDiameter * .76), (int)(myDiameter * .76));

       //center of Stargate
        if(horizonIsVisible){
            if(!irisAnimating){
                theHorizon.drawAnimated(g, innerGateArea);
            }
            else
                theHorizon.drawSimple(g, innerGateArea);

        }
        else{
            g.setColor(Color.black);        //fill it in black
            g.fillOval((int)(myX + .13 * myDiameter), (int)(myY + .13 * myDiameter),
                    (int)(myDiameter * .74), (int)(myDiameter * .74));
        }

        drawChevrons(g);

        //debugging
        if(showDebugInfo){
            g.drawString("Chevron frame Counter : " + theChevrons.get(0).frameCounter, 50, 160);
            g.drawString("stopSpot: " + stopSpot, 50, 100);
            g.drawString("currentAngle: " + currentAngle, 50, 120);
            g.drawString("turningClockwise: " + turningClockwise, 50, 140);
        }
    }//==================================

    private Graphics2D drawInnerRing(Graphics2D g){
        //move g to the center of the stargate
        g.translate(myX + myDiameter/2, myY + myDiameter/2);
        
        double innerRad = (.8 * myDiameter)/2;
        double outerRad = (.88 * myDiameter)/2;
        double theta = ((2* Math.PI)/39);
        int line;
        Symbol sym = new Symbol(0);
        sym.setSmallBaseLengths();
        sym.setColor(GRAY_GREEN); 
        
        //draw all the very little lines
        int innerRad2 = (int)((.91 * myDiameter)/2);
        int outerRad2 = (int)((.94 * myDiameter)/2);
        for(line = 0; line < 100; line++){
            g.drawLine(0, innerRad2, 0, outerRad2);
            g.rotate((Math.PI/50));
        }
        
        //orient g according to the current rotation
        g.rotate(Math.toRadians(currentAngle));
        //draw all the dividing lines
        
        for(line = 0; line < 39; line++){
            g.drawLine(0, (int)innerRad, 0, (int)outerRad);     //draw a separator line
            sym.setIndex(line);                                 //set the index of the symbol
            sym.setPosition(-6, -(int)innerRad - 2);            //set the position in the center of a subdivision on the ring
            sym.draw(g);                                        //draw the symbol
            g.rotate(theta);                                    //rotate clockwise around the ring
        }    
        
        //reset g
        g.rotate(Math.toRadians(-currentAngle));
        g.translate(-1*(myX + myDiameter/2), -1*(myY + myDiameter/2));
        
        return g;
    }//====================================
    
    private void drawChevrons(Graphics g){
        //draw the first separately because it is the only one that will ever be animated
        theChevrons.get(0).draw(g, animateTopChev, framesToAnimateChev, chevIsLitUp[0]);
        
        for(int i = 1; i < 9; i++){
            theChevrons.get(i).draw(g, false, framesToAnimateChev, chevIsLitUp[i]);
        }
    }//====================================

    /**
     * This function clears the stargate and all variables it uses.
     * @param playSound a boolean representing whether or not the closing sound
     * should be played.
     */
    public void clear(boolean playSound){
        isTurning = false;
        stopSpot = 0;
        horizonCounter = 0;
        reachedStopSpot = false;
        animateTopChev = false;
        if(horizonIsVisible && playSound){
            MP3 clip = new MP3("aud/closeGate.mp3");
            clip.playSameThread();
        }
        horizonIsVisible = false;
        for(int i = 0; i<chevIsLitUp.length; i++)
            chevIsLitUp[i] = false;
    }//====================================

    /**
     * This function rounds a double according to normal rounding rules, not 
     * integer math.  ie 1.5 rounds to 2 and 1.4 rounds to 1.
     * @param number a double that needs to be rounded.
     * @return an integer representing "number" that has been rounded.
     */
    private int smartRounding(double number){
        //round up or down in a smart manner
        double difference1, difference2;
        difference1 = number - (int)number;     //rounding down
        difference2 = (int)(number + 1) - number;   //rounding up

        if(difference1 < difference2)
            return (int)number;     //round down
        else
            return (int)(number + 1);   //round up
    }//========================================

    /**
     * This function calculates the shape that makes the inside of the Starate.
     * This space is normally occupied by the even horizon or empty space.
     * @return a Shape object representing the region described.
     */
    private Shape insideStargateShape(){
    //create a circle polygon
    int[] xVals = new int[360];
    int[] yVals = new int[360];
    for(int a = 0; a < 360; a++){
        //convert a to radians
        double theta = (Math.PI/180)*a;
        xVals[a] = getCenter().x + (int)(Math.cos(theta)*(getInnerDiameter()/2 - 3));
        yVals[a] = getCenter().y - (int)(Math.sin(theta)*(getInnerDiameter()/2 - 3));
    }
    //a circle matching the inner area of the stargate
    return new Polygon(xVals, yVals, xVals.length);
    }//=========================================
}
