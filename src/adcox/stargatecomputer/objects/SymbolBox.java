/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
 * @version Dec 28, 2009
 *
 * This class is a box that the symbols go in when the gate is dialing
 */

public class SymbolBox {
    //stargate stuff
    private Stargate myStargate;
    private final int TOP_GATE;
    private final int BOTTOM_GATE;
    private final int RIGHT_SIDE_GATE;
    private final int LEFT_SIDE_GATE;
    //box stuff
    public final int TOP_BOXES = 80;
    public final int LEFT_BOXES = 900;
    public final int BETWEEN_BOXES = 10;
    public final int BOX_HEIGHT = 65;
    public final int BOX_WIDTH = 75;
    public final int RIGHT_BOXES = LEFT_BOXES + BOX_WIDTH;
    public final int BOX_TO_BOX = BOX_HEIGHT + BETWEEN_BOXES;
    //box stuff
    private final int FLASH_INTERVAL = 7;      //the number of frames between changing color or 'flashing'
    private final int NUM_FLAHES = 4;          //the number of times the backgrounds will flash in a given sequence
    private final Color GRAY_GREEN = new Color(122, 157, 160);
    private final Color REDISH_ORANGE = new Color(235, 68, 14);
    
    //my information
    private int myIndex;
    private boolean litUp = false;              //this boolean controls the outside edge color of the box
    private boolean backgroundLit = false;      //whether or not the background of the box is lit
    private boolean flashingBackground = false; //whether or not the background is flashing
    private Color backgroundColor = Color.black;       //color of the background, initially set to black

    //info for flashing the background color
    private int targetFrame;                    //the 'target' or ending frame for the flashing sequence
    private int currentFrame;                   //the current frame in the flashing sequence
    

    //other info
    private int myX, myY;

    public SymbolBox(Stargate aGate, int index, int numToDialTo){
        myStargate = aGate;
        TOP_GATE = myStargate.getLocation().y;
        BOTTOM_GATE = myStargate.getLocation().y + myStargate.getDiameter();
        RIGHT_SIDE_GATE = myStargate.getLocation().x + myStargate.getDiameter();
        LEFT_SIDE_GATE = myStargate.getLocation().x;

        myIndex = index;
    }//=========================================

    public void changeLitUp(boolean isLit){
        litUp = isLit;
    }//=========================================

    public void toggleBackgroundLit(boolean isLit){
        backgroundLit = isLit;
    }//==================================

    public boolean isBackgroundLit(){return backgroundLit;}
    
    public Point getLocation(){
       return new Point(myX, myY);
    }//=======================================

    public void startFlashing(){
        backgroundLit = true;
        flashingBackground = true;
        targetFrame = 2*NUM_FLAHES*FLASH_INTERVAL;
        currentFrame = 0;
    }//================================

    public void draw(Graphics g, int numToDial){
        
        if(numToDial < 9){
            myX = LEFT_BOXES;
            myY = TOP_BOXES + BOX_TO_BOX*myIndex;
        }
        else{
            if(myIndex < 7){
                myX = LEFT_BOXES;
                myY = TOP_BOXES + BOX_TO_BOX*myIndex;
            }
            if(myIndex == 7){
                myX = LEFT_BOXES - BOX_WIDTH - BETWEEN_BOXES;
                myY = (getMiddleYOfBox(7) + 10);
            }
            if(myIndex == 8){
                myX = LEFT_BOXES;
                myY = TOP_BOXES + BOX_TO_BOX*(myIndex - 1);
            }
        }

        if(flashingBackground){
            currentFrame++;
            //swap the color of the background every FLASH_INTERVAL number of frames
            if(currentFrame % FLASH_INTERVAL == 0 && currentFrame != 0){
                backgroundLit = !backgroundLit;
            }
            //once the target is reached, turn flashing off and return backrounds to original state
            if(currentFrame == targetFrame){
                flashingBackground = false;
                backgroundLit = false;
            }
        }

        if(backgroundLit)
            backgroundColor = Color.blue;
        else
            backgroundColor = Color.black;
        
        g.setColor(backgroundColor);
        g.fillRect(myX, myY, BOX_WIDTH, BOX_HEIGHT);

        if(litUp)
            g.setColor(REDISH_ORANGE);
        else
            g.setColor(GRAY_GREEN);
        g.drawRect(myX, myY, BOX_WIDTH, BOX_HEIGHT);
    }//===========================================

    public int getMiddleYOfBox(int numBox){
        //top box is numBox = 1, next is 2, etc.
        return(TOP_BOXES +  BOX_HEIGHT/2 + (numBox - 1)*BOX_TO_BOX);
    }//=======================================
}
