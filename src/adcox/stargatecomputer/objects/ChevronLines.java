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
 * @version December 24, 2009
 */
public class ChevronLines {

    private Stargate myStargate;
    private double slope;
    private boolean litUp = false;
    private final Color GRAY_GREEN = new Color(122, 157, 160);
    private final Color REDISH_ORANGE = new Color(235, 68, 14);
    private int lineIndex;
    //stargate stuff
    private final int TOP_GATE;
    private final int BOTTOM_GATE;
    private final int RIGHT_SIDE_GATE;
    private final int LEFT_SIDE_GATE;
    //box stuff
    private SymbolBox aBox;
    final int TOP_BOXES;
    final int LEFT_BOXES;
    final int RIGHT_BOXES;
    final int BOX_TO_BOX;
    final int BETWEEN_BOXES;
    final int BOX_HEIGHT;
    final int BOX_WIDTH;

    public ChevronLines(Stargate aGate, int index){
        myStargate = aGate;
        TOP_GATE = myStargate.getLocation().y;
        BOTTOM_GATE = myStargate.getLocation().y + myStargate.getDiameter();
        RIGHT_SIDE_GATE = myStargate.getLocation().x + myStargate.getDiameter();
        LEFT_SIDE_GATE = myStargate.getLocation().x;
        //set where the boxes will be
        aBox = new SymbolBox(aGate, 0, 0);
        TOP_BOXES = aBox.TOP_BOXES;
        LEFT_BOXES = aBox.LEFT_BOXES;
        BOX_WIDTH = aBox.BOX_WIDTH;
        BOX_HEIGHT = aBox.BOX_HEIGHT;
        BETWEEN_BOXES = aBox.BETWEEN_BOXES;
        RIGHT_BOXES = LEFT_BOXES + BOX_WIDTH;
        BOX_TO_BOX = BOX_HEIGHT + BETWEEN_BOXES;

        lineIndex = index;
    }//==================================

    public void changeLitUp(boolean isLit){
        litUp = isLit;
    }//=========================================

    public void draw(Graphics g, int destinationIfNeeded, boolean goesOverExtra){
        if(litUp)
            g.setColor(REDISH_ORANGE);
        else
            g.setColor(GRAY_GREEN);

        if(lineIndex == 0)
            drawChevron1Lines(g, destinationIfNeeded);
        if(lineIndex == 1)
            drawChevron2Lines(g);
        if(lineIndex == 2)
            drawChevron3Lines(g);
        if(lineIndex == 3)
            drawChevron4Lines(g);
        if(lineIndex == 4)
            drawChevron5Lines(g);
        if(lineIndex == 5)
            drawChevron6Lines(g);
        if(lineIndex == 6)
            drawChevron7Lines(g, destinationIfNeeded);
        if(lineIndex == 7)
            drawChevron8Lines(g, destinationIfNeeded);
        if(lineIndex == 8)
            drawChevron9Lines(g, destinationIfNeeded, goesOverExtra);
    }//=========================================
    
    private void drawChevron1Lines(Graphics g, int yValChev){
        Point center = myStargate.theChevrons.get(0).findTopCenter();
        //going straight up
        g.drawLine(center.x, center.y, center.x, TOP_GATE - 20);
        //horizontal
        g.drawLine(center.x, TOP_GATE - 20, RIGHT_SIDE_GATE, TOP_GATE - 20);
        //shift down
        g.drawLine(RIGHT_SIDE_GATE, TOP_GATE - 20, RIGHT_SIDE_GATE + 50, TOP_BOXES - 15);
        //horizontal to right side of boxes
        g.drawLine(RIGHT_SIDE_GATE + 50, TOP_BOXES - 15, RIGHT_BOXES + 10, TOP_BOXES - 15);
        //go down to chevronBox
        g.drawLine(RIGHT_BOXES + 10, TOP_BOXES - 15, RIGHT_BOXES + 10, yValChev);
        //yValLastChev is the y coordinate of the box that will hold the point of origin - this could change with 8 0r 9 chevrons
        g.drawLine(RIGHT_BOXES + 10, yValChev, RIGHT_BOXES, yValChev);     //connect to Chevron Box
//        g.drawString("1", center.x, center.y);
    }//=========================================

    private void drawChevron2Lines(Graphics g){
        Point center = myStargate.theChevrons.get(1).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from chevron
        g.drawLine(center.x, center.y, center.x + 10, (int)(center.y - 10*slope));
        //horizontal over to shift down
        g.drawLine(center.x + 10, (int)(center.y - 10*slope), RIGHT_SIDE_GATE, (int)(center.y - 10*slope));
        //shift down to 165
        g.drawLine(RIGHT_SIDE_GATE, (int)(center.y - 10*slope), RIGHT_SIDE_GATE + 50, getMiddleYOfBox(1));
        //horizontal to first chevron box: this chevron will ALWAYS go to this box
        g.drawLine(RIGHT_SIDE_GATE + 50, getMiddleYOfBox(1), LEFT_BOXES, getMiddleYOfBox(1));
        
//        g.drawString("2", center.x, center.y);
    }//=========================================

    private void drawChevron3Lines(Graphics g){
        Point center = myStargate.theChevrons.get(2).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //line from chevron
        g.drawLine(center.x, center.y, center.x + 10, center.y);
        //shift up
        g.drawLine(center.x + 10, center.y, RIGHT_SIDE_GATE + 50, getMiddleYOfBox(2));
        //horizontal to chevron box: this chevron will ALWAYS go to this box
        g.drawLine(RIGHT_SIDE_GATE + 50, getMiddleYOfBox(2), LEFT_BOXES, getMiddleYOfBox(2));
//        g.drawString("3", center.x, center.y);
    }//=========================================

    private void drawChevron4Lines(Graphics g){
        Point center = myStargate.theChevrons.get(3).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from chevron
        g.drawLine(center.x, center.y, center.x + 10, (int)(center.y + 10*slope));
        //horizontal to shift up
        g.drawLine(center.x + 10, (int)(center.y + 10*slope), RIGHT_SIDE_GATE, (int)(center.y + 10*slope));
        //shift up to box height
        g.drawLine(RIGHT_SIDE_GATE, (int)(center.y + 10*slope), RIGHT_SIDE_GATE + 50, getMiddleYOfBox(3));
        //horizontal to box: this chevron will ALWAYS go to this box
        g.drawLine(RIGHT_SIDE_GATE + 50, getMiddleYOfBox(3), LEFT_BOXES, getMiddleYOfBox(3));
//        g.drawString("4", center.x, center.y);
    }//=========================================

    private void drawChevron5Lines(Graphics g){
        Point center = myStargate.theChevrons.get(4).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from chevron
        g.drawLine(center.x, center.y + 1, center.x + 8, (int)(center.y + 20*slope));
        //horizontal over to shift up
        g.drawLine(center.x + 8, (int)(center.y + 20*slope), RIGHT_SIDE_GATE, (int)(center.y + 20*slope));
        //shift up
        g.drawLine(RIGHT_SIDE_GATE, (int)(center.y + 20*slope), RIGHT_SIDE_GATE + 50, getMiddleYOfBox(4));
        //horizontal over to box: this chevron will ALWAYS go to this box
        g.drawLine(RIGHT_SIDE_GATE + 50, getMiddleYOfBox(4), LEFT_BOXES, getMiddleYOfBox(4));
//        g.drawString("5", center.x, center.y);
    }//=========================================

    private void drawChevron6Lines(Graphics g){
        Point center = myStargate.theChevrons.get(5).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from chevron
        g.drawLine(center.x, center.y, center.x - 8, (int)(center.y + 20*slope));
        //vertical down for spacing
        g.drawLine(center.x - 8, (int)(center.y + 20*slope), center.x - 8, BOTTOM_GATE + 15);
        //horizontal over to shift up
        g.drawLine(center.x - 8, BOTTOM_GATE + 15, RIGHT_SIDE_GATE + 15, BOTTOM_GATE + 15);
        //shift up
        g.drawLine(RIGHT_SIDE_GATE+ 15, BOTTOM_GATE + 15, RIGHT_SIDE_GATE + 50, getMiddleYOfBox(5));
        //horiztontal over to box: this chevron will ALWAYS go to this box
        g.drawLine(RIGHT_SIDE_GATE + 50, getMiddleYOfBox(5), LEFT_BOXES, getMiddleYOfBox(5));
//        g.drawString("6", center.x, center.y);
    }//==========================================

    private void drawChevron7Lines(Graphics g, int yValChev){
        Point center = myStargate.theChevrons.get(6).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from chevron
        g.drawLine(center.x, center.y, center.x - 10, (int)(center.y + 10*slope));
        //horizontal to the left for space
        g.drawLine(center.x - 10, (int)(center.y + 10*slope), LEFT_SIDE_GATE - 20, (int)(center.y + 10*slope));
        //straight up
        g.drawLine(LEFT_SIDE_GATE - 20, (int)(center.y + 10*slope), LEFT_SIDE_GATE - 20, TOP_GATE - 35);
        //over to the shift down
        g.drawLine(LEFT_SIDE_GATE - 20, TOP_GATE - 35, RIGHT_SIDE_GATE, TOP_GATE - 35);
        //shift down
        g.drawLine(RIGHT_SIDE_GATE, TOP_GATE - 35, RIGHT_SIDE_GATE + 50, TOP_BOXES - 30);
        //horizontal to right side of boxes
        g.drawLine(RIGHT_SIDE_GATE + 50, TOP_BOXES - 30, RIGHT_BOXES + 25, TOP_BOXES - 30);
        //down to appropriate box
        g.drawLine(RIGHT_BOXES + 25, TOP_BOXES - 30, RIGHT_BOXES + 25, yValChev);
        //over to box
        g.drawLine(RIGHT_BOXES + 25, yValChev, RIGHT_BOXES, yValChev);
//        g.drawString("7", center.x, center.y);
    }//=========================================

    private void drawChevron8Lines(Graphics g, int yValChev){
        Point center = myStargate.theChevrons.get(7).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from chevron
        g.drawLine(center.x, center.y, center.x - 10, center.y);
        //up to top
        g.drawLine(center.x - 10, center.y, center.x - 10, TOP_GATE - 30);
        //over to the shift down
        g.drawLine(center.x - 10, TOP_GATE - 30, RIGHT_SIDE_GATE, TOP_GATE - 30);
        //shift down
        g.drawLine(RIGHT_SIDE_GATE, TOP_GATE - 30, RIGHT_SIDE_GATE + 50, TOP_BOXES - 25);
        //horizontal to right side of boxes
        g.drawLine(RIGHT_SIDE_GATE + 50, TOP_BOXES - 25, RIGHT_BOXES + 20, TOP_BOXES - 25);
        //down to appropriate box
        g.drawLine(RIGHT_BOXES + 20, TOP_BOXES - 25, RIGHT_BOXES + 20, yValChev);
        //over to box
        g.drawLine(RIGHT_BOXES + 20, yValChev, RIGHT_BOXES, yValChev);
//        g.drawString("8", center.x, center.y);
    }//=========================================

    private void drawChevron9Lines(Graphics g, int yValChev, boolean goesOver){
        Point center = myStargate.theChevrons.get(8).findTopCenter();
        slope = getSlopeExitingChevron(1);
        //diagonal from Chevron
        g.drawLine(center.x, center.y, center.x - 10, (int)(center.y - 10*slope));
        //up to top
        g.drawLine(center.x - 10, (int)(center.y - 10*slope), center.x - 10, TOP_GATE - 25);
        //over to the shift down
        g.drawLine(center.x - 10, TOP_GATE - 25, RIGHT_SIDE_GATE, TOP_GATE - 25);
        //shift down
        g.drawLine(RIGHT_SIDE_GATE, TOP_GATE - 25, RIGHT_SIDE_GATE + 50, TOP_BOXES - 20);
        //horizontal to right side of boxes
        g.drawLine(RIGHT_SIDE_GATE + 50, TOP_BOXES - 20, RIGHT_BOXES + 15, TOP_BOXES - 20);
        //down to appropriate box
        g.drawLine(RIGHT_BOXES + 15, TOP_BOXES - 20, RIGHT_BOXES + 15, yValChev);
        //over to box
        //if the yValChev is inBetween the boxes, when we're dialing 9 chevrons:
        if(goesOver)
            g.drawLine(RIGHT_BOXES + 15, yValChev, LEFT_BOXES - BETWEEN_BOXES, yValChev);
        else
           g.drawLine(RIGHT_BOXES + 15, yValChev, RIGHT_BOXES, yValChev);
//        g.drawString("9", center.x, center.y);
    }//=========================================

    private double getSlopeExitingChevron(int chevronIndex){
        double theta = myStargate.theChevrons.get(chevronIndex).getOrientation();
        double rise = Math.sin(theta);
        double run = Math.cos(theta);
        return (rise/run);
    }//=========================================

    private int getMiddleYOfBox(int numBox){
        //top box is numBox = 1, next is 2, etc.
        return(TOP_BOXES +  BOX_HEIGHT/2 + (numBox - 1)*BOX_TO_BOX);
    }//=======================================

}
