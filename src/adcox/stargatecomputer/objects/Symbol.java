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
public class Symbol{
    private int myX, myY;       //location top left corner
    private int index;          //keep track of what symobl this is
    private int[] defaultSizes = new int[39];
    private int myBaseLength;
    private Color myColor = Color.white;

    public Symbol(int theIndex, int x, int y){
        index = theIndex;
        myX = x;
        myY = y;

        setLargeBaseLengths();        
    }//===================================
    
    public Symbol(int theIndex){
        index = theIndex;
        myX = 0;
        myY = 0;
    }//==========================

    public void setLargeBaseLengths(){
        //set all defaults to three
        for(int i = 0; i < defaultSizes.length; i++)
            defaultSizes[i] = 3;

        //exceptions
        defaultSizes[0] = 5;
        defaultSizes[9] = 4;
        defaultSizes[17] = 2;
        defaultSizes[23] = 4;
        defaultSizes[25] = 4;
        defaultSizes[28] = 2;
        defaultSizes[33] = 2;
        
        if(index >= 0)
            myBaseLength = defaultSizes[index];
    }//======================================
    
    public void setSmallBaseLengths(){
        //set all defaults to three
        for(int i = 0; i < defaultSizes.length; i++)
            defaultSizes[i] = 1;

        //exceptions
        defaultSizes[0] = 2;
        defaultSizes[9] = 2;
        defaultSizes[17] = 1;
        defaultSizes[23] = 1;
        defaultSizes[25] = 1;
        defaultSizes[28] = 1;
        defaultSizes[33] = 1;
        
        if(index >= 0)
            myBaseLength = defaultSizes[index];
    }//=====================================

    public void setPosition(int x, int y){
        myX = x;
        myY = y;
    }//=======================================

    public void setBaseLength(int baseLength){    //base size off of the baseLength
        myBaseLength = baseLength;
    }//======================================

    public int getDefaultSize(){
        return defaultSizes[index];
    }//==================================

    public int getCurrentSize(){
        return myBaseLength;
    }//===========================

    public Point getLocation(){
        return new Point(myX, myY);
    }//==================================
    
    public void setIndex(int i){
        index = i;
        if(index >= 0)
            myBaseLength = defaultSizes[index];
    }//==========================
    
    public void setColor(Color c){myColor = c;}
    
    public void draw(Graphics g){
        g.setColor(myColor);
        if(index == 0)
            drawGlyph0(g, myBaseLength);
        if(index == 1)
            drawGlyph1(g, myBaseLength);
        if(index == 2)
            drawGlyph2(g, myBaseLength);
        if(index == 3)
            drawGlyph3(g, myBaseLength);
        if(index == 4)
            drawGlyph4(g, myBaseLength);
        if(index == 5)
            drawGlyph5(g, myBaseLength);
        if(index == 6)
            drawGlyph6(g, myBaseLength);
        if(index == 7)
            drawGlyph7(g, myBaseLength);
        if(index == 8)
            drawGlyph8(g, myBaseLength);
        if(index == 9)
            drawGlyph9(g, myBaseLength);
        if(index == 10)
            drawGlyph10(g, myBaseLength);
        if(index == 11)
            drawGlyph11(g, myBaseLength);
        if(index == 12)
            drawGlyph12(g, myBaseLength);
        if(index == 13)
            drawGlyph13(g, myBaseLength);
        if(index == 14)
            drawGlyph14(g, myBaseLength);
        if(index == 15)
            drawGlyph15(g, myBaseLength);
        if(index == 16)
            drawGlyph16(g, myBaseLength);
        if(index == 17)
            drawGlyph17(g, myBaseLength);
        if(index == 18)
            drawGlyph18(g, myBaseLength);
        if(index == 19)
            drawGlyph19(g, myBaseLength);
        if(index == 20)
            drawGlyph20(g, myBaseLength);
        if(index == 21)
            drawGlyph21(g, myBaseLength);
        if(index == 22)
            drawGlyph22(g, myBaseLength);
        if(index == 23)
            drawGlyph23(g, myBaseLength);
        if(index == 24)
            drawGlyph24(g, myBaseLength);
        if(index == 25)
            drawGlyph25(g, myBaseLength);
        if(index == 26)
            drawGlyph26(g, myBaseLength);
        if(index == 27)
            drawGlyph27(g, myBaseLength);
        if(index == 28)
            drawGLyph28(g, myBaseLength);
        if(index == 29)
            drawGlyph29(g, myBaseLength);
        if(index == 30)
            drawGlyph30(g, myBaseLength);
        if(index == 31)
            drawGlyph31(g, myBaseLength);
        if(index == 32)
            drawGlyph32(g, myBaseLength);
        if(index == 33)
            drawGlyph33(g, myBaseLength);
        if(index == 34)
            drawGlyph34(g, myBaseLength);
        if(index == 35)
            drawGlyph35(g, myBaseLength);
        if(index == 36)
            drawGlyph36(g, myBaseLength);
        if(index == 37)
            drawGlyph37(g, myBaseLength);
        if(index == 38)
            drawGlyph38(g, myBaseLength);

        if(index == -1)
            drawSoundOn(g, 3);
        if(index == -2)
            drawSoundOff(g, 3);
    }//=====================================

    private void drawGlyph0(Graphics g, int x){
        g.drawLine(myX,myY,myX+3*x,myY); //bottom left horizontal line
        g.drawLine(myX+3*x,myY,myX+2*x,myY-x);  //left inside little diagonal
        g.drawLine(myX+2*x,myY-x,myX+4*x,myY-4*x);   //left inside big diagonal
        g.drawLine(myX+4*x,myY-4*x,myX+6*x,myY-x);   //rigth inside big diagonal
        g.drawLine(myX+6*x,myY-x,myX+5*x,myY);  //right inside little diagonal
        g.drawLine(myX+5*x,myY,myX+8*x,myY); //bottom right horizontal line
        g.drawLine(myX+8*x,myY,myX+5*x,myY-5*x);    //right outside diagonal
        g.drawArc(myX+2*x, myY-9*x, 4*x, 4*x, 298, 304);    //big arc
        g.drawLine(myX+3*x,myY-5*x,myX,myY);    //left outside diagonal
        g.drawOval(myX+3*x, myY-8*x, 2*x, 2*x); //inside circle
    }//==========================================================

    private void drawGlyph1(Graphics g, int x){
        g.drawLine(myX, myY, myX + 3*x, myY);   //bottom left horizontal line
        g.drawLine(myX + 3*x, myY, myX + x, myY - 4*x);   //bottom inside left diagonal
        g.drawLine(myX + x, myY - 4*x, myX + 3*x, myY - 6*x); //top inside left diagonal
        g.drawLine(myX + 3*x, myY - 6*x, myX + 6*x, myY - 6*x); //middle inside horizontal
        g.drawLine(myX + 6*x, myY - 6*x, myX + 9*x, myY - 2*x);  //top inside right diagonal
        g.drawLine(myX + 9*x, myY - 2*x, myX + 8*x, myY); //bottom inside right diagonal
        g.drawLine(myX + 8*x, myY, myX + 11*x, myY);    //bottom right horizontal
        g.drawLine(myX + 11*x, myY, myX + 10*x, myY - x);   //bottom outside right diagonal
        g.drawLine(myX + 10*x, myY - x, myX + 10*x, myY - 3*x); //outside right vertical
        g.drawLine(myX + 10*x, myY - 3*x, myX + 7*x, myY - 7*x);    //middle outside right diagonal
        g.drawLine(myX + 7*x, myY - 7*x, myX + 10*x, myY - 12*x);   //top outside right diagonal
        g.drawLine(myX + 10*x, myY - 12*x, myX + 3*x, myY - 12*x);  //top horizontal
        g.drawLine(myX + 3*x, myY - 12*x, myX + 2*x, myY - 6*x);    //top outside left diagonal
        g.drawLine(myX + 2*x, myY - 6*x, myX, myY - 4*x);   //top middle outside left diagonal
        g.drawLine(myX, myY-4*x, myX + x, myY - x); //bottom middle outside left diagonal
        g.drawLine(myX + x, myY - x, myX, myY); //bottom outside left diagonal
    }//==========================================================

    private void drawGlyph2(Graphics g, int x){
        g.drawLine(myX, myY, myX + 3*x, myY);   //bottom horizontal
        g.drawLine(myX + 3*x, myY, myX + 2*x, myY - x);             //bottom right diagonal on left leg
        g.drawLine(myX + 2*x, myY - x, myX + 4*x, myY - 4*x);       //top right diagonal on left leg
        g.drawLine(myX + 4*x, myY - 4*x, myX + 11*x, myY - 3*x);    //bottom diagonal on right leg
        g.drawLine(myX + 11*x, myY - 3*x, myX + 11*x, myY - 5*x);   //vertical on right leg
        g.drawLine(myX + 11*x, myY - 5*x, myX + 10*x, myY - 4*x);   //top right diagonal on right leg
        g.drawLine(myX + 10*x, myY - 4*x, myX + 7*x, myY - 5*x);    //top left diagonal on right leg
        g.drawLine(myX + 7*x, myY - 5*x, myX + 8*x, myY - 9*x);     //right side of body
        g.drawLine(myX + 8*x, myY - 9*x, myX + 11*x, myY - 13*x);   //right diagonal on right arm
        g.drawLine(myX + 11*x, myY - 13*x, myX + 12*x, myY - 13*x); //horizontal on right arm
        g.drawLine(myX + 12*x, myY - 13*x, myX + 10*x, myY - 14*x); //top diagonal on right arm
        g.drawLine(myX + 10*x, myY - 14*x, myX + 10*x, myY - 13*x); //vertical on right arm
        g.drawLine(myX + 10*x, myY - 13*x, myX + 7*x, myY - 9*x);   //left diagonal on right arm
        g.drawLine(myX + 7*x, myY - 9*x, myX + 4*x, myY - 10*x);    //top of body
        g.drawLine(myX + 4*x, myY - 10*x, myX + 5*x, myY - 13*x);   //bottom right diagonal on left arm
        g.drawLine(myX + 5*x, myY - 13*x, myX + 7*x, myY - 12*x);   //middle right diagonal on left arm
        g.drawLine(myX + 7*x, myY - 12*x, myX + 8*x, myY - 14*x);   //top right diagonal on left arm
        g.drawLine(myX + 8*x, myY - 14*x, myX + 4*x, myY - 13*x);   //top left diagonal on left arm
        g.drawLine(myX + 4*x, myY - 13*x, myX + 2*x, myY - 10*x);   //middle left diagonal on left arm
        g.drawLine(myX + 2*x, myY - 10*x, myX + 3*x, myY - 8*x);    //bottom left diagonal on left arm
        g.drawLine(myX + 3*x, myY - 8*x, myX + 2*x, myY - 5*x);     //left side of body
        g.drawLine(myX + 2*x, myY - 5*x, myX, myY);                 //left diagonal on left leg
        g.drawLine(myX + 3*x, myY - 5*x, myX + 4*x, myY - 9*x);     //left side of inner box
        g.drawLine(myX + 4*x, myY - 9*x, myX + 7*x, myY - 8*x);     //top of inner box
        g.drawLine(myX + 7*x, myY - 8*x, myX + 6*x, myY - 5*x);     //right side of inner box
        g.drawLine(myX + 6*x, myY - 5*x, myX + 3*x, myY - 5*x);     //bottom of inner box
    }//===================================================

    private void drawGlyph3(Graphics g, int x){
        g.drawLine(myX, myY - 3*x, myX + 5*x, myY); //very bottom diagonal on bottom part
        g.drawLine(myX + 5*x, myY, myX + 4*x, myY - 4*x);   //bottom right diagonal on bottom part
        g.drawLine(myX + 4*x, myY - 4*x, myX + 6*x, myY - 11*x);    //middle right diagonal on bottom part
        g.drawLine(myX + 6*x, myY - 11*x, myX + 7*x, myY - 12*x);   //top right diagonal on bottom part
        g.drawLine(myX + 7*x, myY - 12*x, myX + 5*x, myY- 13*x);    //very top diagonal on bottom part
        g.drawLine(myX + 5*x, myY- 13*x, myX + 5*x, myY - 11*x);    //vertical on top of bottom part
        g.drawLine(myX + 5*x, myY - 11*x, myX + 3*x, myY - 5*x);  //middle left diagonal on bottom part
        g.drawLine(myX + 3*x, myY - 5*x, myX, myY - 3*x);   //bottom left diagonal on bottom part
        g.drawLine(myX + 9*x, myY - 12*x, myX + 11*x, myY - 12*x);  //bottom horizontal on top part
        g.drawLine(myX + 11*x, myY - 12*x, myX + 4*x, myY - 15*x);  //top long diagonal on top part
        g.drawLine(myX + 4*x, myY - 15*x, myX + 3*x, myY - 14*x);   //top short diagonal on top part
        g.drawLine(myX + 3*x, myY - 14*x, myX + 9*x, myY - 12*x);   //bottom long diagonal on top part
    }//=====================================================

    private void drawGlyph4(Graphics g, int x){
        g.drawLine(myX, myY - 2*x, myX + 2*x, myY); //longest side of bottom triangle
        g.drawLine(myX + 2*x, myY, myX + 2*x, myY - 2*x);   //vertical line on bottom triangle
        g.drawLine(myX + 2*x, myY - 2*x, myX, myY- 2*x);    //horizontal on bottom triangle
        g.drawLine(myX + 2*x, myY - 3*x, myX + 3*x, myY - 2*x); //bottom left diagonal on rectangle
        g.drawLine(myX + 3*x, myY - 2*x, myX + 5*x, myY - 4*x); //bottom right diagonal on rectangle
        g.drawLine(myX + 5*x, myY - 4*x, myX + 4*x, myY - 5*x); //top right diagonal on rectangle
        g.drawLine(myX + 4*x, myY - 5*x, myX + 2*x, myY - 3*x); //top left diagonal on rectangle
        g.drawLine(myX + x, myY - 6*x, myX + 3*x, myY - 6*x);   //horizontal on left arm
        g.drawLine(myX + 3*x, myY - 6*x, myX + 6*x, myY - 5*x); //bottom right diagonal on left arm
        g.drawLine(myX + 6*x, myY - 5*x, myX + 7*x, myY - 2*x); //left diagonal on bottom right arm
        g.drawLine(myX + 7*x, myY - 2*x, myX + 7*x, myY - x);   //horizontal on bottom right arm
        g.drawLine(myX + 7*x, myY - x, myX + 9*x, myY - 2*x);   //bottom middle diagonal on bottom right arm
        g.drawLine(myX + 9*x, myY - 2*x, myX + 7*x, myY - 5*x); //right diagonal on bottom right arm
        g.drawLine(myX + 7*x, myY - 5*x, myX + 7*x, myY - 6*x); //vertical between right arms
        g.drawLine(myX + 7*x, myY - 6*x, myX + 8*x, myY - 10*x);    //bottom right diagonal on top right arm
        g.drawLine(myX + 8*x, myY - 10*x, myX + 9*x, myY - 11*x);   //top right diagonal on top right arm
        g.drawLine(myX + 9*x, myY - 11*x, myX + 7*x, myY - 12*x);   //top middle diagonal on top right arm
        g.drawLine(myX + 7*x, myY - 12*x, myX + 7*x, myY - 11*x);   //vetical on top right arm
        g.drawLine(myX + 7*x, myY - 11*x, myX + 6*x, myY - 6*x);    //left diagonal on top right arm
        g.drawLine(myX + 6*x, myY - 6*x, myX + 3*x, myY - 7*x);   //top right diagonal on left arm
        g.drawLine(myX + 3*x, myY - 7*x, myX + 2*x, myY - 9*x);   //top left diagonal on left arm
        g.drawLine(myX + 2*x, myY - 9*x, myX + x, myY - 6*x);   //far left diagonal on left arm
    }//=====================================================

    private void drawGlyph5(Graphics g, int x){
        g.drawLine(myX, myY - 5*x, myX + 3*x, myY); //bottom diagonal on triangle at left
        g.drawLine(myX + 3*x, myY, myX + 6*x, myY - 3*x);   //bottom left diagonal on bottom arm
        g.drawLine(myX + 6*x, myY - 3*x, myX + 10*x, myY - 2*x);    //bottom middle diagonal on bottom arm
        g.drawLine(myX + 10*x, myY - 2*x, myX + 9*x, myY - 4*x);    //bottom right diagonal on bottom arm
        g.drawLine(myX + 9*x, myY - 4*x, myX + 10*x, myY - 4*x);    //horizontal on bottom arm
        g.drawLine(myX + 10*x, myY - 4*x, myX + 10*x, myY - 5*x);   //vertical on bottom arm
        g.drawLine(myX + 10*x, myY - 5*x, myX + 6*x, myY - 4*x);    //top right diagonal on bottom arm
        g.drawLine(myX + 6*x, myY - 4*x, myX + 3*x, myY - 2*x); //top left diagonal on bottom arm
        g.drawLine(myX + 3*x, myY - 2*x, myX + 3*x, myY - 9*x); //vertical on triangle at left
        g.drawLine(myX + 3*x, myY - 9*x, myX + 8*x, myY - 10*x);    //bottom left diagonal on top arm
        g.drawLine(myX + 8*x, myY - 10*x, myX + 9*x, myY - 11*x);   //bottom right diagonal on top arm
        g.drawLine(myX + 9*x, myY - 11*x, myX + 7*x, myY - 12*x);   //top right diagonal on top arm
        g.drawLine(myX + 7*x, myY - 12*x, myX + 7*x, myY - 11*x);   //vertical on top arm
        g.drawLine(myX + 7*x, myY - 11*x, myX + 3*x, myY - 10*x);   //top left diagonal on top arm
        g.drawLine(myX + 3*x, myY - 10*x, myX, myY - 5*x);  //top diagonal on triangle at left
    }//=====================================================

    private void drawGlyph6(Graphics g, int x){
        g.drawLine(myX, myY - 2*x, myX + x, myY);   //diagonal on left hand
        g.drawLine(myX + x, myY, myX + x, myY - x); //bottom vertical on left hand
        g.drawLine(myX + x, myY - x, myX + 4*x, myY - 4*x); //bottom diagonal on left arm
        g.drawLine(myX + 4*x, myY - 4*x, myX + 6*x, myY - 3*x); //bottom left diagonal on body
        g.drawLine(myX + 6*x, myY - 3*x, myX + 7*x, myY - 4*x); //bottom right diagonal on body
        g.drawLine(myX + 7*x, myY - 4*x, myX + 10*x, myY - 4*x);    //bottom left horizontal on right arm
        g.drawLine(myX + 10*x, myY - 4*x, myX + 11*x, myY - 6*x);   //bottom right diagonal on right arm
        g.drawLine(myX + 11*x, myY - 6*x, myX + 12*x, myY - 7*x);   //right diagonal on right hand
        g.drawLine(myX + 12*x, myY - 7*x, myX + 10*x, myY - 8*x);   //middle diagonal on right hand
        g.drawLine(myX + 10*x, myY - 8*x, myX + 10*x, myY - 6*x);   //left vertical on right hand
        g.drawLine(myX + 10*x, myY - 6*x, myX + 9*x, myY - 5*x);    //top right diagonal on right arm
        g.drawLine(myX + 9*x, myY - 5*x, myX + 7*x, myY - 5*x); //top left horizontal on right arm
        g.drawLine(myX + 7*x, myY - 5*x, myX + 7*x, myY - 6*x); //top right vertical on body
        g.drawLine(myX + 7*x, myY - 6*x, myX + 4*x, myY - 5*x); //top left diagonal on body
        g.drawLine(myX + 4*x, myY - 5*x, myX + x, myY - 2*x);   //top right diagonal on left arm
        g.drawLine(myX + x, myY - 2*x, myX, myY - 2*x); //top horizontal on left hand
    }//=====================================================

    private void drawGlyph7(Graphics g, int x){
        g.drawLine(myX, myY - x, myX + 4*x, myY);   //bottom of bottom triangle
        g.drawLine(myX + 4*x, myY, myX + 3*x, myY - 4*x);   //right side of bottom triangle
        g.drawLine(myX + 3*x, myY - 4*x, myX, myY - x); //left side of bottom tiangle
        g.drawLine(myX + 8*x, myY - 8*x, myX + 12*x, myY - 7*x);    //bottom of top triangle
        g.drawLine(myX + 12*x, myY - 7*x, myX + 11*x, myY - 11*x);  //right side of top triangle
        g.drawLine(myX + 11*x, myY - 11*x, myX + 8*x, myY - 8*x);   //left side of top triangle
    }//=====================================================

    private void drawGlyph8(Graphics g, int b){
	g.drawLine(myX + 1*b, myY - 6*b, myX +0*b, myY - 3*b);
	g.drawLine(myX + 0*b, myY - 3*b, myX +0*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 0*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 2*b, myY - 0*b, myX +1*b, myY - 2*b);
	g.drawLine(myX + 1*b, myY - 2*b, myX +5*b, myY - 3*b);
	g.drawLine(myX + 5*b, myY - 3*b, myX +7*b, myY - 7*b);
	g.drawLine(myX + 7*b, myY - 7*b, myX +8*b, myY - 10*b);
	g.drawLine(myX + 8*b, myY - 10*b, myX +11*b, myY - 10*b);
	g.drawLine(myX + 11*b, myY - 10*b, myX +12*b, myY - 8*b);
	g.drawLine(myX + 12*b, myY - 8*b, myX +10*b, myY - 7*b);
	g.drawLine(myX + 10*b, myY - 7*b, myX +10*b, myY - 6*b);
	g.drawLine(myX + 10*b, myY - 6*b, myX +13*b, myY - 7*b);
	g.drawLine(myX + 13*b, myY - 7*b, myX +12*b, myY - 11*b);
	g.drawLine(myX + 12*b, myY - 11*b, myX +7*b, myY - 11*b);
	g.drawLine(myX + 7*b, myY - 11*b, myX +6*b, myY - 7*b);
	g.drawLine(myX + 6*b, myY - 7*b, myX +4*b, myY - 4*b);
	g.drawLine(myX + 4*b, myY - 4*b, myX +1*b, myY - 3*b);
	g.drawLine(myX + 1*b, myY - 3*b, myX +2*b, myY - 6*b);
	g.drawLine(myX + 1*b, myY - 6*b, myX +2*b, myY - 6*b);
    }//======================================

    private void drawGlyph9(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 1*b, myX +2*b, myY - 1*b);
	g.drawLine(myX + 2*b, myY - 1*b, myX +1*b, myY - 3*b);
	g.drawLine(myX + 1*b, myY - 3*b, myX +0*b, myY - 1*b);
	g.drawLine(myX + 3*b, myY - 1*b, myX +3*b, myY - 0*b);
	g.drawArc(myX + -1*b, myY - 8*b, 8*b, 8*b, -90 , 180);
	g.drawArc(myX + 0*b, myY - 7*b, 6*b, 6*b, -90 , 180);
	g.drawLine(myX + 3*b, myY - 7*b, myX +1*b, myY - 7*b);
	g.drawLine(myX + 1*b, myY - 7*b, myX +2*b, myY - 9*b);
	g.drawLine(myX + 2*b, myY - 9*b, myX +3*b, myY - 8*b);
}//======================================
    
    private void drawGlyph10(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 2*b, myX +6*b, myY - 0*b);
	g.drawLine(myX + 6*b, myY - 0*b, myX +7*b, myY - 3*b);
	g.drawLine(myX + 7*b, myY - 3*b, myX +12*b, myY - 7*b);
	g.drawLine(myX + 12*b, myY - 7*b, myX +12*b, myY - 7*b);
	g.drawLine(myX + 7*b, myY - 4*b, myX +0*b, myY - 2*b);
	g.drawLine(myX + 7*b, myY - 5*b, myX +7*b, myY - 6*b);
	g.drawLine(myX + 7*b, myY - 6*b, myX +5*b, myY - 5*b);
	g.drawLine(myX + 5*b, myY - 5*b, myX +5*b, myY - 4*b);
	g.drawLine(myX + 5*b, myY - 4*b, myX +7*b, myY - 5*b);
	g.drawLine(myX + 7*b, myY - 4*b, myX +11*b, myY - 7*b);
	g.drawLine(myX + 11*b, myY - 7*b, myX +11*b, myY - 9*b);
	g.drawLine(myX + 11*b, myY - 9*b, myX +13*b, myY - 7*b);
	g.drawLine(myX + 13*b, myY - 7*b, myX +12*b, myY - 7*b);
    }//======================================

    private void drawGlyph11(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 6*b, myX +1*b, myY - 9*b);
	g.drawLine(myX + 1*b, myY - 9*b, myX +2*b, myY - 8*b);
	g.drawLine(myX + 2*b, myY - 8*b, myX +4*b, myY - 8*b);
	g.drawLine(myX + 4*b, myY - 8*b, myX +4*b, myY - 10*b);
	g.drawLine(myX + 4*b, myY - 10*b, myX +2*b, myY - 12*b);
	g.drawLine(myX + 2*b, myY - 12*b, myX +9*b, myY - 12*b);
	g.drawLine(myX + 9*b, myY - 12*b, myX +12*b, myY - 10*b);
	g.drawLine(myX + 12*b, myY - 10*b, myX +5*b, myY - 10*b);
	g.drawLine(myX + 5*b, myY - 10*b, myX +6*b, myY - 6*b);
	g.drawLine(myX + 6*b, myY - 6*b, myX +5*b, myY - 5*b);
	g.drawLine(myX + 5*b, myY - 5*b, myX +4*b, myY - 3*b);
	g.drawLine(myX + 4*b, myY - 3*b, myX +5*b, myY - 1*b);
	g.drawLine(myX + 5*b, myY - 1*b, myX +11*b, myY - 1*b);
	g.drawLine(myX + 11*b, myY - 1*b, myX +11*b, myY - 3*b);
	g.drawLine(myX + 11*b, myY - 3*b, myX +13*b, myY - 4*b);
	g.drawLine(myX + 13*b, myY - 4*b, myX +12*b, myY - 0*b);
	g.drawLine(myX + 12*b, myY - 0*b, myX +5*b, myY - 0*b);
	g.drawLine(myX + 5*b, myY - 0*b, myX +3*b, myY - 3*b);
	g.drawLine(myX + 3*b, myY - 3*b, myX +4*b, myY - 5*b);
	g.drawLine(myX + 4*b, myY - 5*b, myX +4*b, myY - 7*b);
	g.drawLine(myX + 4*b, myY - 7*b, myX +2*b, myY - 7*b);
	g.drawLine(myX + 2*b, myY - 7*b, myX +0*b, myY - 6*b);
    }//======================================

    private void drawGlyph12(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 5*b, myX +2*b, myY - 6*b);
	g.drawLine(myX + 2*b, myY - 6*b, myX +2*b, myY - 6*b);
	g.drawLine(myX + 3*b, myY - 1*b, myX +6*b, myY - 7*b);
	g.drawLine(myX + 6*b, myY - 7*b, myX +11*b, myY - 6*b);
	g.drawLine(myX + 11*b, myY - 6*b, myX +12*b, myY - 7*b);
	g.drawLine(myX + 12*b, myY - 7*b, myX +12*b, myY - 5*b);
	g.drawLine(myX + 12*b, myY - 5*b, myX +7*b, myY - 6*b);
	g.drawLine(myX + 7*b, myY - 6*b, myX +4*b, myY - 1*b);
	g.drawLine(myX + 4*b, myY - 1*b, myX +4*b, myY - 0*b);
	g.drawLine(myX + 4*b, myY - 0*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 2*b, myY - 0*b, myX +2*b, myY - 1*b);
	g.drawLine(myX + 2*b, myY - 1*b, myX +1*b, myY - 4*b);
	g.drawLine(myX + 1*b, myY - 4*b, myX +0*b, myY - 5*b);
	g.drawLine(myX + 2*b, myY - 6*b, myX +2*b, myY - 4*b);
	g.drawLine(myX + 2*b, myY - 4*b, myX +3*b, myY - 1*b);
    }//======================================

    private void drawGlyph13(Graphics g, int b){
	g.drawLine(myX + 1*b, myY - 10*b, myX +2*b, myY - 8*b);
	g.drawLine(myX + 2*b, myY - 8*b, myX +0*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 0*b, myX +10*b, myY - 1*b);
	g.drawLine(myX + 10*b, myY - 1*b, myX +11*b, myY - 0*b);
	g.drawLine(myX + 11*b, myY - 0*b, myX +11*b, myY - 3*b);
	g.drawLine(myX + 11*b, myY - 3*b, myX +10*b, myY - 2*b);
	g.drawLine(myX + 10*b, myY - 2*b, myX +1*b, myY - 1*b);
	g.drawLine(myX + 1*b, myY - 1*b, myX +3*b, myY - 8*b);
	g.drawLine(myX + 3*b, myY - 8*b, myX +4*b, myY - 9*b);
	g.drawLine(myX + 4*b, myY - 9*b, myX +1*b, myY - 10*b);
	g.drawLine(myX + 7*b, myY - 7*b, myX +9*b, myY - 5*b);
	g.drawLine(myX + 9*b, myY - 5*b, myX +6*b, myY - 5*b);
	g.drawLine(myX + 6*b, myY - 5*b, myX +7*b, myY - 7*b);
    }//======================================

    private void drawGlyph14(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 8*b, myX +0*b, myY - 9*b);
	g.drawLine(myX + 0*b, myY - 9*b, myX +8*b, myY - 11*b);
	g.drawLine(myX + 8*b, myY - 11*b, myX +9*b, myY - 7*b);
	g.drawLine(myX + 9*b, myY - 7*b, myX +8*b, myY - 2*b);
	g.drawLine(myX + 8*b, myY - 2*b, myX +9*b, myY - 1*b);
	g.drawLine(myX + 9*b, myY - 1*b, myX +7*b, myY - 0*b);
	g.drawLine(myX + 7*b, myY - 0*b, myX +7*b, myY - 2*b);
	g.drawLine(myX + 7*b, myY - 2*b, myX +1*b, myY - 8*b);
	g.drawLine(myX + 1*b, myY - 8*b, myX +0*b, myY - 8*b);
	g.drawLine(myX + 2*b, myY - 8*b, myX +7*b, myY - 10*b);
	g.drawLine(myX + 7*b, myY - 10*b, myX +8*b, myY - 7*b);
	g.drawLine(myX + 8*b, myY - 7*b, myX +7*b, myY - 3*b);
	g.drawLine(myX + 7*b, myY - 3*b, myX +2*b, myY - 8*b);
    }//======================================

    private void drawGlyph15(Graphics g, int b){
	g.drawLine(myX + 3*b, myY - 2*b, myX +2*b, myY - 5*b);
	g.drawLine(myX + 2*b, myY - 5*b, myX +2*b, myY - 7*b);
	g.drawLine(myX + 2*b, myY - 7*b, myX +3*b, myY - 10*b);
	g.drawLine(myX + 3*b, myY - 10*b, myX +5*b, myY - 12*b);
	g.drawLine(myX + 5*b, myY - 12*b, myX +6*b, myY - 10*b);
	g.drawLine(myX + 6*b, myY - 10*b, myX +7*b, myY - 5*b);
	g.drawLine(myX + 7*b, myY - 5*b, myX +8*b, myY - 2*b);
	g.drawLine(myX + 8*b, myY - 2*b, myX +8*b, myY - 1*b);
	g.drawLine(myX + 8*b, myY - 1*b, myX +6*b, myY - 0*b);
	g.drawLine(myX + 6*b, myY - 0*b, myX +3*b, myY - 2*b);
	g.drawLine(myX + 7*b, myY - 3*b, myX +4*b, myY - 3*b);
	g.drawLine(myX + 4*b, myY - 3*b, myX +3*b, myY - 4*b);
	g.drawLine(myX + 3*b, myY - 4*b, myX +3*b, myY - 7*b);
	g.drawLine(myX + 3*b, myY - 7*b, myX +4*b, myY - 10*b);
	g.drawLine(myX + 4*b, myY - 10*b, myX +5*b, myY - 11*b);
	g.drawLine(myX + 5*b, myY - 11*b, myX +5*b, myY - 10*b);
	g.drawLine(myX + 5*b, myY - 10*b, myX +6*b, myY - 5*b);
	g.drawLine(myX + 6*b, myY - 5*b, myX +7*b, myY - 3*b);
    }//======================================

    private void drawGlyph16(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 2*b, myX +11*b, myY - 0*b);
	g.drawLine(myX + 11*b, myY - 0*b, myX +11*b, myY - 2*b);
	g.drawLine(myX + 11*b, myY - 2*b, myX +12*b, myY - 4*b);
	g.drawLine(myX + 12*b, myY - 4*b, myX +9*b, myY - 5*b);
	g.drawLine(myX + 9*b, myY - 5*b, myX +10*b, myY - 1*b);
	g.drawLine(myX + 10*b, myY - 1*b, myX +4*b, myY - 2*b);
	g.drawLine(myX + 4*b, myY - 2*b, myX +2*b, myY - 3*b);
	g.drawLine(myX + 2*b, myY - 3*b, myX +4*b, myY - 5*b);
	g.drawLine(myX + 4*b, myY - 5*b, myX +6*b, myY - 6*b);
	g.drawLine(myX + 6*b, myY - 6*b, myX +3*b, myY - 7*b);
	g.drawLine(myX + 3*b, myY - 7*b, myX +3*b, myY - 5*b);
	g.drawLine(myX + 3*b, myY - 5*b, myX +0*b, myY - 2*b);
    }//======================================

    private void drawGlyph17(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 2*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 2*b, myX +1*b, myY - 2*b);
	g.drawLine(myX + 1*b, myY - 2*b, myX +3*b, myY - 4*b);
	g.drawLine(myX + 3*b, myY - 4*b, myX +3*b, myY - 6*b);
	g.drawLine(myX + 3*b, myY - 6*b, myX +1*b, myY - 7*b);
	g.drawLine(myX + 1*b, myY - 7*b, myX +5*b, myY - 10*b);
	g.drawLine(myX + 5*b, myY - 10*b, myX +8*b, myY - 9*b);
	g.drawLine(myX + 8*b, myY - 9*b, myX +9*b, myY - 10*b);
	g.drawLine(myX + 9*b, myY - 10*b, myX +17*b, myY - 7*b);
	g.drawLine(myX + 17*b, myY - 7*b, myX +18*b, myY - 7*b);
	g.drawLine(myX + 18*b, myY - 7*b, myX +17*b, myY - 5*b);
	g.drawLine(myX + 17*b, myY - 5*b, myX +17*b, myY - 6*b);
	g.drawLine(myX + 17*b, myY - 6*b, myX +9*b, myY - 9*b);
	g.drawLine(myX + 9*b, myY - 9*b, myX +8*b, myY - 6*b);
	g.drawLine(myX + 8*b, myY - 6*b, myX +8*b, myY - 2*b);
	g.drawLine(myX + 8*b, myY - 2*b, myX +9*b, myY - 1*b);
	g.drawLine(myX + 9*b, myY - 1*b, myX +6*b, myY - 1*b);
	g.drawLine(myX + 6*b, myY - 1*b, myX +7*b, myY - 2*b);
	g.drawLine(myX + 7*b, myY - 2*b, myX +7*b, myY - 6*b);
	g.drawLine(myX + 7*b, myY - 6*b, myX +8*b, myY - 8*b);
	g.drawLine(myX + 8*b, myY - 8*b, myX +5*b, myY - 9*b);
	g.drawLine(myX + 5*b, myY - 9*b, myX +3*b, myY - 7*b);
	g.drawLine(myX + 3*b, myY - 7*b, myX +4*b, myY - 6*b);
	g.drawLine(myX + 4*b, myY - 6*b, myX +4*b, myY - 4*b);
	g.drawLine(myX + 4*b, myY - 4*b, myX +2*b, myY - 2*b);
	g.drawLine(myX + 2*b, myY - 2*b, myX +2*b, myY - 0*b);
    }//======================================
    
    private void drawGlyph18(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 2*b, myX +0*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 0*b, myX +1*b, myY - 1*b);
	g.drawLine(myX + 1*b, myY - 1*b, myX +0*b, myY - 2*b);
	g.drawLine(myX + 1*b, myY - 5*b, myX +2*b, myY - 7*b);
	g.drawLine(myX + 2*b, myY - 7*b, myX +3*b, myY - 7*b);
	g.drawLine(myX + 3*b, myY - 7*b, myX +8*b, myY - 11*b);
	g.drawLine(myX + 8*b, myY - 11*b, myX +8*b, myY - 12*b);
	g.drawLine(myX + 8*b, myY - 12*b, myX +7*b, myY - 13*b);
	g.drawLine(myX + 7*b, myY - 13*b, myX +9*b, myY - 14*b);
	g.drawLine(myX + 9*b, myY - 14*b, myX +9*b, myY - 11*b);
	g.drawLine(myX + 9*b, myY - 11*b, myX +4*b, myY - 7*b);
	g.drawLine(myX + 4*b, myY - 7*b, myX +6*b, myY - 5*b);
	g.drawLine(myX + 6*b, myY - 5*b, myX +11*b, myY - 9*b);
	g.drawLine(myX + 11*b, myY - 9*b, myX +11*b, myY - 10*b);
	g.drawLine(myX + 11*b, myY - 10*b, myX +10*b, myY - 11*b);
	g.drawLine(myX + 10*b, myY - 11*b, myX +12*b, myY - 12*b);
	g.drawLine(myX + 12*b, myY - 12*b, myX +12*b, myY - 9*b);
	g.drawLine(myX + 12*b, myY - 9*b, myX +7*b, myY - 5*b);
	g.drawLine(myX + 7*b, myY - 5*b, myX +6*b, myY - 1*b);
	g.drawLine(myX + 6*b, myY - 1*b, myX +6*b, myY - 0*b);
	g.drawLine(myX + 6*b, myY - 0*b, myX +4*b, myY - 1*b);
	g.drawLine(myX + 4*b, myY - 1*b, myX +5*b, myY - 1*b);
	g.drawLine(myX + 5*b, myY - 1*b, myX +6*b, myY - 4*b);
	g.drawLine(myX + 4*b, myY - 6*b, myX +1*b, myY - 5*b);
	g.drawLine(myX + 6*b, myY - 4*b, myX +4*b, myY - 6*b);
    }//======================================

    private void drawGlyph19(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 4*b, myX +0*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 0*b, myX +5*b, myY - 1*b);
	g.drawLine(myX + 5*b, myY - 1*b, myX +12*b, myY - 9*b);
	g.drawLine(myX + 12*b, myY - 9*b, myX +14*b, myY - 10*b);
	g.drawLine(myX + 14*b, myY - 10*b, myX +11*b, myY - 11*b);
	g.drawLine(myX + 5*b, myY - 2*b, myX +0*b, myY - 4*b);
	g.drawLine(myX + 11*b, myY - 11*b, myX +11*b, myY - 9*b);
	g.drawLine(myX + 11*b, myY - 9*b, myX +5*b, myY - 2*b);
    }//======================================

    private void drawGlyph20(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 0*b, myX +3*b, myY - 2*b);
	g.drawLine(myX + 3*b, myY - 2*b, myX +9*b, myY - 1*b);
	g.drawLine(myX + 9*b, myY - 1*b, myX +7*b, myY - 8*b);
	g.drawLine(myX + 7*b, myY - 8*b, myX +7*b, myY - 11*b);
	g.drawLine(myX + 7*b, myY - 11*b, myX +6*b, myY - 12*b);
	g.drawLine(myX + 6*b, myY - 12*b, myX +7*b, myY - 13*b);
	g.drawLine(myX + 7*b, myY - 13*b, myX +8*b, myY - 13*b);
	g.drawLine(myX + 8*b, myY - 13*b, myX +9*b, myY - 12*b);
	g.drawLine(myX + 9*b, myY - 12*b, myX +8*b, myY - 11*b);
	g.drawLine(myX + 8*b, myY - 11*b, myX +8*b, myY - 8*b);
	g.drawLine(myX + 8*b, myY - 8*b, myX +10*b, myY - 1*b);
	g.drawLine(myX + 10*b, myY - 1*b, myX +11*b, myY - 0*b);
	g.drawLine(myX + 11*b, myY - 0*b, myX +8*b, myY - 0*b);
	g.drawLine(myX + 8*b, myY - 0*b, myX +5*b, myY - 1*b);
	g.drawLine(myX + 2*b, myY - 0*b, myX +0*b, myY - 0*b);
	g.drawLine(myX + 5*b, myY - 1*b, myX +4*b, myY - 1*b);
	g.drawLine(myX + 4*b, myY - 1*b, myX +2*b, myY - 0*b);
    }//======================================
    
    private void drawGlyph21(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 12*b, myX +1*b, myY - 10*b);
	g.drawLine(myX + 1*b, myY - 10*b, myX +1*b, myY - 9*b);
	g.drawLine(myX + 1*b, myY - 9*b, myX +2*b, myY - 8*b);
	g.drawLine(myX + 2*b, myY - 8*b, myX +2*b, myY - 7*b);
	g.drawLine(myX + 2*b, myY - 7*b, myX +3*b, myY - 6*b);
	g.drawLine(myX + 3*b, myY - 6*b, myX +3*b, myY - 4*b);
	g.drawLine(myX + 3*b, myY - 4*b, myX +7*b, myY - 0*b);
	g.drawLine(myX + 7*b, myY - 0*b, myX +6*b, myY - 6*b);
	g.drawLine(myX + 6*b, myY - 6*b, myX +7*b, myY - 7*b);
	g.drawLine(myX + 7*b, myY - 7*b, myX +5*b, myY - 7*b);
	g.drawLine(myX + 5*b, myY - 7*b, myX +6*b, myY - 4*b);
	g.drawLine(myX + 6*b, myY - 4*b, myX +4*b, myY - 4*b);
	g.drawLine(myX + 4*b, myY - 4*b, myX +4*b, myY - 7*b);
	g.drawLine(myX + 4*b, myY - 7*b, myX +3*b, myY - 8*b);
	g.drawLine(myX + 3*b, myY - 8*b, myX +3*b, myY - 9*b);
	g.drawLine(myX + 3*b, myY - 9*b, myX +2*b, myY - 10*b);
	g.drawLine(myX + 2*b, myY - 10*b, myX +1*b, myY - 12*b);
	g.drawLine(myX + 1*b, myY - 12*b, myX +0*b, myY - 12*b);
    }//==========================================

    private void drawGlyph22(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 8*b, myX +2*b, myY - 2*b);
	g.drawLine(myX + 2*b, myY - 2*b, myX +12*b, myY - 6*b);
	g.drawLine(myX + 12*b, myY - 6*b, myX +0*b, myY - 8*b);
	g.drawLine(myX + 1*b, myY - 7*b, myX +2*b, myY - 4*b);
	g.drawLine(myX + 2*b, myY - 4*b, myX +8*b, myY - 6*b);
	g.drawLine(myX + 1*b, myY - 7*b, myX +8*b, myY - 6*b);
    }//=====================================

    private void drawGlyph23(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 2*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 2*b, myY - 0*b, myX +2*b, myY - 2*b);
	g.drawLine(myX + 2*b, myY - 2*b, myX +5*b, myY - 6*b);
	g.drawLine(myX + 5*b, myY - 6*b, myX +10*b, myY - 8*b);
	g.drawLine(myX + 10*b, myY - 8*b, myX +8*b, myY - 10*b);
	g.drawLine(myX + 8*b, myY - 10*b, myX +8*b, myY - 8*b);
	g.drawLine(myX + 8*b, myY - 8*b, myX +4*b, myY - 6*b);
	g.drawLine(myX + 4*b, myY - 6*b, myX +1*b, myY - 2*b);
	g.drawLine(myX + 1*b, myY - 2*b, myX +0*b, myY - 2*b);
    }//========================================


    private void drawGlyph24(Graphics g, int b){
	g.drawLine(myX + 2*b, myY - 0*b, myX +1*b, myY - 0*b);
	g.drawLine(myX + 1*b, myY - 0*b, myX +0*b, myY - 1*b);
	g.drawLine(myX + 0*b, myY - 1*b, myX +0*b, myY - 3*b);
	g.drawLine(myX + 0*b, myY - 3*b, myX +1*b, myY - 7*b);
	g.drawLine(myX + 1*b, myY - 7*b, myX +3*b, myY - 10*b);
	g.drawLine(myX + 3*b, myY - 10*b, myX +3*b, myY - 12*b);
	g.drawLine(myX + 3*b, myY - 12*b, myX +8*b, myY - 14*b);
	g.drawLine(myX + 8*b, myY - 14*b, myX +8*b, myY - 9*b);
	g.drawLine(myX + 8*b, myY - 9*b, myX +9*b, myY - 4*b);
	g.drawLine(myX + 9*b, myY - 4*b, myX +6*b, myY - 3*b);
	g.drawLine(myX + 6*b, myY - 3*b, myX +7*b, myY - 8*b);
	g.drawLine(myX + 6*b, myY - 3*b, myX +7*b, myY - 8*b);
	g.drawLine(myX + 7*b, myY - 8*b, myX +7*b, myY - 10*b);
	g.drawLine(myX + 7*b, myY - 10*b, myX +4*b, myY - 10*b);
	g.drawLine(myX + 4*b, myY - 10*b, myX +2*b, myY - 7*b);
	g.drawLine(myX + 2*b, myY - 7*b, myX +1*b, myY - 3*b);
	g.drawLine(myX + 1*b, myY - 3*b, myX +1*b, myY - 1*b);
	g.drawLine(myX + 1*b, myY - 1*b, myX +2*b, myY - 1*b);
	g.drawLine(myX + 2*b, myY - 1*b, myX +2*b, myY - 0*b);
    }//=========================================

    private void drawGlyph25(Graphics g, int b){
	g.drawLine(myX + 0*b, myY - 6*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 2*b, myY - 0*b, myX +5*b, myY - 1*b);
	g.drawLine(myX + 5*b, myY - 1*b, myX +7*b, myY - 1*b);
	g.drawLine(myX + 7*b, myY - 1*b, myX +6*b, myY - 5*b);
	g.drawLine(myX + 6*b, myY - 5*b, myX +7*b, myY - 6*b);
	g.drawLine(myX + 7*b, myY - 6*b, myX +8*b, myY - 8*b);
	g.drawLine(myX + 8*b, myY - 8*b, myX +8*b, myY - 11*b);
	g.drawLine(myX + 8*b, myY - 11*b, myX +9*b, myY - 12*b);
	g.drawLine(myX + 9*b, myY - 12*b, myX +7*b, myY - 12*b);
	g.drawLine(myX + 7*b, myY - 12*b, myX +7*b, myY - 8*b);
	g.drawLine(myX + 7*b, myY - 8*b, myX +5*b, myY - 6*b);
	g.drawLine(myX + 5*b, myY - 6*b, myX +0*b, myY - 6*b);
	g.drawLine(myX + 1*b, myY - 5*b, myX +2*b, myY - 2*b);
	g.drawLine(myX + 2*b, myY - 2*b, myX +2*b, myY - 1*b);
	g.drawLine(myX + 2*b, myY - 1*b, myX +5*b, myY - 2*b);
	g.drawLine(myX + 5*b, myY - 2*b, myX +6*b, myY - 2*b);
	g.drawLine(myX + 6*b, myY - 2*b, myX +5*b, myY - 5*b);
	g.drawLine(myX + 5*b, myY - 5*b, myX +1*b, myY - 5*b);
    }//============================================

    private void drawGlyph26(Graphics g, int b){
        g.drawLine(myX, myY - 5*b, myX, myY - 7*b); //far right vertical end of glyph
        g.drawLine(myX, myY - 7*b, myX + 12*b, myY - 7*b);
        g.drawLine(myX + 12*b, myY - 7*b, myX + 12*b, myY - 5*b);   //top right vertical segment
        g.drawLine(myX + 12*b, myY - 5*b, myX + 11*b, myY - 6*b);
        g.drawLine(myX + 11*b, myY - 6*b, myX + 6*b, myY - 6*b);
        g.drawLine(myX + 6*b, myY - 6*b, myX + 10*b, myY - 3*b);
        g.drawLine(myX + 10*b, myY - 3*b, myX + 12*b, myY - 3*b);
        g.drawLine(myX + 12*b, myY - 3*b, myX + 12*b, myY - b);     //bottom right vertical segment
        g.drawLine(myX + 12*b, myY - b, myX + 11*b, myY - 2*b);
        g.drawLine(myX + 11*b, myY - 2*b, myX + 10*b, myY - 2*b);
        g.drawLine(myX + 10*b, myY - 2*b, myX + 4*b, myY - 6*b);
        g.drawLine(myX + 4*b, myY - 6*b, myX + b, myY - 6*b);
        g.drawLine(myX + b, myY - 6*b, myX, myY - 5*b);     //back to start
    }//========================================

    private void drawGlyph27(Graphics g, int b){
        g.drawLine(myX, myY - 8*b, myX + 3*b, myY - 11*b);  //farthest left point
        g.drawLine(myX + 3*b, myY - 11*b, myX + 4*b, myY - 11*b);
        g.drawLine(myX + 4*b, myY - 11*b, myX + 6*b, myY - 10*b);
        g.drawLine(myX + 6*b, myY - 10*b, myX + 9*b, myY - 10*b);
        g.drawLine(myX + 9*b, myY - 10*b, myX + 11*b, myY - 11*b);
        g.drawLine(myX + 11*b, myY - 11*b, myX + 11*b, myY - 8*b);  //far right end on top
        g.drawLine(myX + 11*b, myY - 8*b, myX + 10*b, myY - 9*b);
        g.drawLine(myX + 10*b, myY - 9*b, myX + 4*b, myY - 9*b);
        g.drawLine(myX + 4*b, myY - 9*b, myX + 2*b, myY - 7*b);
        g.drawLine(myX + 2*b, myY - 7*b, myX + 2*b, myY - 3*b);
        g.drawLine(myX + 2*b, myY - 3*b, myX + 9*b, myY - 2*b); //far right point on bottom
        g.drawLine(myX + 9*b, myY - 2*b, myX + 8*b, myY);
        g.drawLine(myX + 8*b, myY, myX + 6*b, myY - b);
        g.drawLine(myX + 6*b, myY - b, myX + b, myY - 2*b);
        g.drawLine(myX + b, myY - 2*b, myX + b, myY - 7*b);
        g.drawLine(myX + b, myY - 7*b, myX, myY - 8*b);
        //triangle
        g.drawLine(myX + 11*b, myY - 4*b, myX + 11*b, myY - 6*b);  //from botom vertice
        g.drawLine(myX + 11*b, myY - 6*b, myX + 13*b, myY - 5*b);   //to far right vertice
        g.drawLine(myX + 13*b, myY - 5*b, myX + 11*b, myY - 4*b);   //back to first
    }//=====================================================

    private void drawGLyph28(Graphics g, int b){
        g.drawLine(myX, myY - b, myX + b, myY - 3*b);   //far left end piece
        g.drawLine(myX + b, myY - 3*b, myX + 2*b, myY - 2*b);   //moving clockwise around the glyph
        g.drawLine(myX + 2*b, myY - 2*b, myX + 6*b, myY - b);
        g.drawLine(myX + 6*b, myY - b, myX + 8*b, myY - 5*b);
        g.drawLine(myX + 8*b, myY - 5*b, myX + 9*b, myY - 8*b);
        g.drawLine(myX + 9*b, myY - 8*b, myX + 12*b, myY - 8*b);
        g.drawLine(myX + 12*b, myY - 8*b, myX + 12*b, myY - 9*b);
        g.drawLine(myX + 12*b, myY - 9*b, myX + 14*b, myY - 9*b);  //top of little bump
        g.drawLine(myX + 14*b, myY - 9*b, myX + 14*b, myY - 8*b);
        g.drawLine(myX + 14*b, myY - 8*b, myX + 15*b, myY - 9*b);
        g.drawLine(myX + 15*b, myY - 9*b, myX + 16*b, myY -  11*b);
        g.drawLine(myX + 16*b, myY -  11*b, myX + 16*b, myY - 12*b);    //highest point
        g.drawLine(myX + 16*b, myY - 12*b, myX + 18*b, myY - 11*b);
        g.drawLine(myX + 18*b, myY - 11*b, myX + 16*b, myY - 10*b);
        g.drawLine(myX + 16*b, myY - 10*b, myX + 16*b, myY - 8*b);
        g.drawLine(myX + 16*b, myY - 8*b, myX + 14*b, myY - 6*b);
        g.drawLine(myX + 14*b, myY - 6*b, myX + 10*b, myY - 7*b);
        g.drawLine(myX + 10*b, myY - 7*b, myX + 9*b, myY - 6*b);
        g.drawLine(myX + 9*b, myY - 6*b, myX + 10*b, myY - 4*b);
        g.drawLine(myX + 10*b, myY - 4*b, myX + 6*b, myY);  //lowest point
        g.drawLine(myX + 6*b, myY, myX + 2*b, myY - b);
        g.drawLine(myX + 2*b, myY - b, myX, myY - b);
    }//==============================================

    private void drawGlyph29(Graphics g, int b){
        g.drawLine(myX, myY - 4*b, myX + b, myY - 5*b);     //start at bottom left corner
        g.drawLine(myX + b, myY - 5*b, myX + b, myY - 8*b);
        g.drawLine(myX + b, myY - 8*b, myX, myY - 9*b);     //top left corner
        g.drawLine(myX, myY - 9*b, myX + 2*b, myY - 10*b);
        g.drawLine(myX + 2*b, myY - 10*b, myX + 2*b, myY - 9*b);
        g.drawLine(myX + 2*b, myY - 9*b, myX + 5*b, myY - 8*b);
        g.drawLine(myX + 5*b, myY - 8*b, myX + 6*b, myY - 8*b);
        g.drawOval(myX + 6*b, myY - 8*b, b, b);     //top circle
        g.drawOval(myX + 6*b, myY - 7*b, b, b);     //middle circle
        g.drawOval(myX + 6*b, myY - 6*b, b, b);     //bottom circle
        g.drawLine(myX + 7*b, myY - 8*b, myX + 10*b, myY - 10*b);
        g.drawLine(myX + 10*b, myY - 10*b, myX + 11*b, myY - 11*b);   //top right corner
        g.drawLine(myX + 11*b, myY - 11*b, myX + 12*b, myY - 9*b);
        g.drawLine(myX + 12*b, myY - 9*b, myX + 11*b, myY - 9*b);
        g.drawLine(myX + 11*b, myY - 9*b, myX + 11*b, myY - 4*b);
        g.drawLine(myX + 11*b, myY - 4*b, myX + 12*b, myY - 3*b);     //bottom right corner
        g.drawLine(myX + 12*b, myY - 3*b, myX + 10*b, myY - 2*b);
        g.drawLine(myX + 10*b, myY - 2*b, myX + 10*b, myY - 3*b);
        g.drawLine(myX + 10*b, myY - 3*b, myX + 6*b, myY - 5*b);  //back to the circles at this point
        g.drawLine(myX + 6*b, myY - 5*b, myX + 5*b, myY - 5*b);
        g.drawLine(myX + 5*b, myY - 5*b, myX + 2*b, myY - 4*b);
        g.drawLine(myX + 2*b, myY - 4*b, myX + 2*b, myY - 3*b);
        g.drawLine(myX + 2*b, myY - 3*b, myX, myY - 4*b);     //back to starting point
        //inside
        g.drawLine(myX + 2*b, myY - 5*b, myX + 2*b, myY - 8*b);     //left vertical
        g.drawLine(myX + 2*b, myY - 8*b, myX + 5*b, myY - 7*b);     //going down
        g.drawLine(myX + 5*b, myY - 7*b, myX + 6*b, myY - 7*b);
        g.drawLine(myX + 7*b, myY - 7*b, myX + 10*b, myY - 9*b);    //going up to the right
        g.drawLine(myX + 10*b, myY - 9*b, myX + 10*b, myY - 4*b);   //right vertical
        g.drawLine(myX + 10*b, myY - 4*b, myX + 7*b, myY - 6*b);    //going up to the left
        g.drawLine(myX + 6*b, myY - 6*b, myX + 5*b, myY - 6*b);
        g.drawLine(myX + 5*b, myY - 6*b, myX + 2*b, myY - 5*b);     //back down to start
    }//===========================================

    private void drawGlyph30(Graphics g, int b){
        g.drawLine(myX, myY - 9*b, myX + 6*b, myY - 12*b);  //top of hourglass
        g.drawLine(myX + 6*b, myY - 12*b, myX + 6*b, myY - b);  //vertical line
        g.drawLine(myX + 6*b, myY - b, myX + 4*b, myY - b);     //horizontal to the left
        g.drawLine(myX + 4*b, myY - b, myX + 4*b, myY);     //vertical down
        g.drawLine(myX + 4*b, myY, myX + 12*b, myY - 4*b);  //bottom of hourglass
        g.drawLine(myX + 12*b, myY - 4*b, myX, myY - 9*b);  //last diagonal of hourglass
    }//========================================
    private void drawGlyph31(Graphics g, int b){
        g.drawLine(myX, myY - 4*b, myX, myY - 7*b);     //far left side of glyph
        g.drawLine(myX, myY - 7*b, myX + 4*b, myY - 5*b);
        g.drawLine(myX + 4*b, myY - 5*b, myX + 4*b, myY - 6*b);     //heading up arm
        g.drawLine(myX + 4*b, myY - 6*b, myX + 3*b, myY - 9*b);
        g.drawLine(myX + 3*b, myY - 9*b, myX + 2*b, myY - 10*b);
        g.drawLine(myX + 2*b, myY - 10*b, myX + b, myY - 10*b);
        g.drawLine(myX + b, myY - 10*b, myX + b, myY - 11*b);   //left end of arm
        g.drawLine(myX + b, myY - 11*b, myX + 2*b, myY - 11*b); //top end of arm
        g.drawLine(myX + 2*b, myY - 11*b, myX + 3*b, myY - 10*b);
        g.drawLine(myX + 3*b, myY - 10*b, myX + 4*b, myY - 10*b);   //top of little protrusion off of arm
        g.drawLine(myX + 4*b, myY - 10*b, myX + 4*b, myY - 9*b);
        g.drawLine(myX + 4*b, myY - 9*b, myX + 5*b, myY - 6*b);
        g.drawLine(myX + 5*b, myY - 6*b, myX + 5*b, myY - 5*b);     //back down to body
        g.drawLine(myX + 5*b, myY - 5*b, myX + 11*b, myY - 5*b);    //over to right side
        g.drawLine(myX + 11*b, myY - 5*b, myX + 10*b, myY - b);     //right side of leg
        g.drawLine(myX + 10*b, myY - b, myX + 11*b, myY);
        g.drawLine(myX + 11*b, myY, myX + 8*b, myY);    //botoom of foot/leg
        g.drawLine(myX + 8*b, myY, myX + 9*b, myY - b);
        g.drawLine(myX + 9*b, myY - b, myX + 9*b, myY - 4*b);   //left side of leg
        g.drawLine(myX + 9*b, myY - 4*b, myX, myY - 4*b);       //connect to start
    }//=========================================

    private void drawGlyph32(Graphics g, int b){
        g.drawLine(myX, myY - 5*b, myX + b, myY - 5*b);
        g.drawLine(myX + b, myY - 5*b, myX + 3*b, myY - 9*b);   //left outside diagonal
        g.drawLine(myX + 3*b, myY - 9*b, myX + 3*b, myY - 10*b);
        g.drawLine(myX + 3*b, myY - 10*b, myX + 5*b, myY - 10*b);   //top of top left corner
        g.drawLine(myX + 5*b, myY - 10*b, myX + 5*b, myY - 9*b);
        g.drawLine(myX + 5*b, myY - 9*b, myX + 11*b, myY - 6*b);    //top outside diagonal
        g.drawLine(myX + 11*b, myY - 6*b, myX + 12*b, myY - 7*b);   //top of top right corner
        g.drawLine(myX + 12*b, myY - 7*b, myX + 12*b, myY - 5*b);
        g.drawLine(myX + 12*b, myY - 5*b, myX + 11*b, myY - 3*b);
        g.drawLine(myX + 11*b, myY - 3*b, myX + 9*b, myY - 2*b);
        g.drawLine(myX + 9*b, myY - 2*b, myX + 8*b, myY - 2*b);     //bottom right corner
        g.drawLine(myX + 8*b, myY - 2*b, myX + 2*b, myY - 5*b);     //bottome outside diagonal
        g.drawLine(myX + 2*b, myY - 5*b, myX + 2*b, myY - 3*b);
        g.drawLine(myX + 2*b, myY - 3*b, myX, myY - 5*b);   //connect to starting point

        g.drawLine(myX + 3*b, myY - 6*b, myX + 4*b, myY - 8*b);     //left inside diagonal
        g.drawLine(myX + 4*b, myY - 8*b, myX + 10*b, myY - 5*b);    //top inside diagonal
        g.drawLine(myX + 10*b, myY - 5*b, myX + 9*b, myY - 3*b);    //right inside diagonal
        g.drawLine(myX + 9*b, myY - 3*b, myX + 3*b, myY - 6*b);     //bottom inside diagonal
    }//=========================================

    private void drawGlyph33(Graphics g, int b){
        g.drawLine(myX, myY - 16*b, myX + b, myY - 18*b);   //top left side of top part
        g.drawLine(myX + b, myY - 18*b, myX + 4*b, myY - 18*b); //top of top part
        g.drawLine(myX + 4*b, myY - 18*b, myX + 6*b, myY - 15*b);
        g.drawLine(myX + 6*b, myY - 15*b, myX + 9*b, myY - 12*b);
        g.drawLine(myX + 9*b, myY - 12*b, myX + 10*b, myY - 11*b);
        g.drawLine(myX + 10*b, myY - 11*b, myX + 10*b, myY - 10*b);     //right side of bottom knob part
        g.drawLine(myX + 10*b, myY - 10*b, myX + 9*b, myY - 9*b);
        g.drawLine(myX + 9*b, myY - 9*b, myX + 8*b, myY - 10*b);
        g.drawLine(myX + 8*b, myY - 10*b, myX + 8*b, myY - 11*b);
        g.drawLine(myX + 8*b, myY - 11*b, myX + 5*b, myY - 14*b);   //heading back up to the left
        g.drawLine(myX + 5*b, myY - 14*b, myX + 4*b, myY - 16*b);
        g.drawLine(myX + 4*b, myY - 16*b, myX + 2*b, myY - 15*b);
        g.drawLine(myX + 2*b, myY - 15*b, myX + b, myY - 15*b);
        g.drawLine(myX + b, myY - 15*b, myX, myY - 16*b);   //conects back to starting point

        g.drawLine(myX + 4*b, myY - 8*b, myX + 6*b, myY - 9*b);     //top part of triangle
        g.drawLine(myX + 6*b, myY - 9*b, myX + 6*b, myY - 6*b);     //right side
        g.drawLine(myX + 6*b, myY - 6*b, myX + 4*b, myY - 8*b);     //conect back to start

        g.drawLine(myX + 6*b, myY - 5*b, myX + 7*b, myY - 6*b);     //top left side of bottom part
        g.drawLine(myX + 7*b, myY - 6*b, myX + 8*b, myY - 5*b);
        g.drawLine(myX + 8*b, myY - 5*b, myX + 11*b, myY - 5*b);
        g.drawLine(myX + 11*b, myY - 5*b, myX + 12*b, myY - 4*b);
        g.drawLine(myX + 12*b, myY - 4*b, myX + 13*b, myY - 2*b);
        g.drawLine(myX + 13*b, myY - 2*b, myX + 17*b, myY - 2*b);
        g.drawLine(myX + 17*b, myY - 2*b, myX + 18*b, myY - 3*b);
        g.drawLine(myX + 18*b, myY - 3*b, myX + 19*b, myY - 2*b);   //top right side of bottome part
        g.drawLine(myX + 19*b, myY - 2*b, myX + 18*b, myY);
        g.drawLine(myX + 18*b, myY, myX + 17*b, myY);
        g.drawLine(myX + 17*b, myY, myX + 16*b, myY - b);
        g.drawLine(myX + 16*b, myY - b, myX + 12*b, myY - b);
        g.drawLine(myX + 12*b, myY - b, myX + 11*b, myY - 3*b);
        g.drawLine(myX + 11*b, myY - 3*b, myX + 10*b, myY - 4*b);
        g.drawLine(myX + 10*b, myY - 4*b, myX + 9*b, myY - 4*b);
        g.drawLine(myX + 9*b, myY - 4*b, myX + 7*b, myY - 3*b);
        g.drawLine(myX + 7*b, myY - 3*b, myX + 7*b, myY - 4*b);
        g.drawLine(myX + 7*b, myY - 4*b, myX + 6*b, myY - 5*b);     //return back to start of bottom part
    }//=========================================

    private void drawGlyph34(Graphics g, int b){
        g.drawLine(myX, myY - 12*b, myX, myY - 10*b);   //left side
        g.drawLine(myX, myY - 10*b, myX + 2*b, myY - 10*b);     //going right on the bottom side
        g.drawLine(myX + 2*b, myY - 10*b, myX + 6*b, myY - 6*b);
        g.drawLine(myX + 6*b, myY - 6*b, myX + 7*b, myY - 3*b);
        g.drawLine(myX + 7*b, myY - 3*b, myX + 8*b, myY - 3*b);
        g.drawLine(myX + 8*b, myY - 3*b, myX + 11*b, myY - b);
        g.drawLine(myX + 11*b, myY - b, myX + 12*b, myY - b);       //reached far right bottom corner
        g.drawLine(myX + 12*b, myY - b, myX + 12*b, myY - 3*b);     //right side
        g.drawLine(myX + 12*b, myY - 3*b, myX + 10*b, myY - 3*b);   //going left on top side
        g.drawLine(myX + 10*b, myY - 3*b, myX + 8*b, myY - 4*b);
        g.drawLine(myX + 8*b, myY - 4*b, myX + 6*b, myY - 7*b);
        g.drawLine(myX + 6*b, myY - 7*b, myX + 6*b, myY - 9*b);
        g.drawLine(myX + 6*b, myY - 9*b, myX + 5*b, myY - 9*b);
        g.drawLine(myX + 5*b, myY - 9*b, myX + 2*b, myY - 11*b);
        g.drawLine(myX + 2*b, myY - 11*b, myX, myY - 12*b);
    }

    private void drawGlyph35(Graphics g, int b){
        g.drawLine(myX, myY - 11*b, myX + 3*b, myY - 12*b);     //top part of bone
        g.drawLine(myX + 3*b, myY - 12*b, myX + 3*b, myY - 10*b);   //going down right side
        g.drawLine(myX + 3*b, myY - 10*b, myX + 4*b, myY - 8*b);
        g.drawLine(myX + 4*b, myY - 8*b, myX + 5*b, myY - 5*b);
        g.drawLine(myX + 5*b, myY - 5*b, myX + 5*b, myY - 2*b);
        g.drawLine(myX + 5*b, myY - 2*b, myX + 6*b, myY);   //reached bottom of right side
        g.drawLine(myX + 6*b, myY, myX + 3*b, myY - b);     //bottom of bone
        g.drawLine(myX + 3*b, myY - b, myX + 4*b, myY - 2*b);   //going up left side
        g.drawLine(myX + 4*b, myY - 2*b, myX + 4*b, myY - 5*b);
        g.drawLine(myX + 4*b, myY - 5*b, myX + 3*b, myY - 8*b);
        g.drawLine(myX + 3*b, myY - 8*b, myX + 2*b, myY - 10*b);
        g.drawLine(myX + 2*b, myY - 10*b, myX, myY - 11*b);     //reached top of left side
        g.drawLine(myX + 9*b, myY - 2*b, myX + 12*b, myY - b);  //bottom of triangle
        g.drawLine(myX + 12*b, myY - b, myX + 11*b, myY - 4*b);     //right side of triangle
        g.drawLine(myX + 11*b, myY - 4*b, myX + 9*b, myY - 2*b);    //left side of triangle
    }

    private void drawGlyph36(Graphics g, int b){
        g.drawLine(myX, myY - 8*b, myX + 2*b, myY - 9*b);   //bottom of left side of bone thing
        g.drawLine(myX + 2*b, myY - 9*b, myX + 7*b, myY - 8*b);
        g.drawLine(myX + 7*b, myY - 8*b, myX + 8*b, myY - 7*b);     //reached right side of bone
        g.drawLine(myX + 8*b, myY - 7*b, myX + 9*b, myY - 10*b);    //right side of bone
        g.drawLine(myX + 9*b, myY - 10*b, myX + 7*b, myY - 9*b);    //goint to the right on top now
        g.drawLine(myX + 7*b, myY - 9*b, myX + 2*b, myY - 10*b);
        g.drawLine(myX + 2*b, myY - 10*b, myX, myY - 11*b);     //reached top side of bone
        g.drawLine(myX, myY - 11*b, myX, myY - 8*b);    //left side of bone
        g.drawLine(myX + 8*b, myY - 6*b, myX + 9*b, myY - 4*b);     //left side of little rectangle
        g.drawLine(myX + 9*b, myY - 4*b, myX + 10*b, myY - 5*b);    //bottom of little rectangle
        g.drawLine(myX + 10*b, myY - 5*b, myX + 9*b, myY - 7*b);    //right side of little rectangle
        g.drawLine(myX + 9*b, myY - 7*b, myX + 8*b, myY - 6*b);     //top of little rectangle
        g.drawLine(myX + 9*b, myY, myX + 12*b, myY - 2*b);      //bottom side of triangle
        g.drawLine(myX + 12*b, myY - 2*b, myX + 9*b, myY - 3*b);  //top-right side of trianlge
        g.drawLine(myX + 9*b, myY - 3*b, myX + 9*b, myY); //left side of triangle
    }//==========================================

    private void drawGlyph37(Graphics g, int b){
        g.drawLine(myX, myY - b, myX + 2*b, myY -3*b);    //starting on top left side, going to the right
        g.drawLine(myX + 2*b, myY - 3*b, myX + 4*b, myY - 7*b);
        g.drawLine(myX + 4*b, myY - 7*b, myX + 6*b, myY - 9*b);
        g.drawLine(myX + 6*b, myY - 9*b, myX + 9*b, myY - 10*b);
        g.drawLine(myX + 9*b, myY - 10*b, myX + 11*b, myY - 12*b);  //reached top right side
        g.drawLine(myX + 11*b, myY - 12*b, myX + 12*b, myY - 8*b);  //nearly vertical right end
        g.drawLine(myX + 12*b, myY - 8*b, myX + 9*b, myY - 9*b);   //heading back to the left on the bottom side
        g.drawLine(myX + 9*b, myY - 9*b, myX + 7*b, myY - 8*b);
        g.drawLine(myX + 7*b, myY - 8*b, myX + 5*b, myY - 6*b);
        g.drawLine(myX + 5*b, myY - 6*b, myX + 3*b, myY - 2*b);
        g.drawLine(myX + 3*b, myY - 2*b, myX + 3*b, myY);   //down to left bottom corner
        g.drawLine(myX + 3*b, myY, myX, myY - b);     //left end cap
    }//===============================================

    private void drawGlyph38(Graphics g, int b){
        g.drawLine(myX, myY - 10*b, myX + 3*b, myY - 8*b);      //bottom of left "arm"
        g.drawLine(myX + 3*b, myY - 8*b, myX + 4*b, myY - 8*b);     //horizontal bottom of arm
        g.drawLine(myX + 4*b, myY - 8*b, myX + 5*b, myY - 2*b);     //left side of left leg
        g.drawLine(myX + 5*b, myY - 2*b, myX + 5*b, myY - b);     //vertical part of left side of left leg
        g.drawLine(myX + 5*b, myY - b, myX + 7*b, myY - b);     //bottom of left leg
        g.drawLine(myX + 7*b, myY - b, myX + 5*b, myY - 8*b);   //right side of left leg
        g.drawLine(myX + 5*b, myY - 8*b, myX + 5*b, myY - 9*b);     //vertical right side of left leg
        g.drawLine(myX + 5*b, myY - 9*b, myX + 7*b, myY - 10*b);    //over to right leg
        g.drawLine(myX + 7*b, myY - 10*b, myX + 7*b, myY - 7*b);    //first vertical part of left side of right leg
        g.drawLine(myX + 7*b, myY - 7*b, myX + 8*b, myY - b);      //slanted segment of left side of right leg
        g.drawLine(myX + 8*b, myY - b, myX + 8*b, myY);     //second vertical segment of left side of right leg
        g.drawLine(myX + 8*b, myY, myX + 10*b, myY);    //bottom of right leg
        g.drawLine(myX + 10*b, myY, myX + 9*b, myY - 7*b);     //right side of right leg
        g.drawLine(myX + 9*b, myY - 7*b, myX + 9*b, myY - 10*b);      //vertical part of right side of right leg
        g.drawLine(myX + 9*b, myY - 10*b, myX + 8*b, myY - 12*b);      //far right side of glyph
        g.drawLine(myX + 8*b, myY - 12*b, myX + 5*b, myY - 11*b);       //top segment
        g.drawLine(myX + 5*b, myY - 11*b, myX + 4*b, myY - 9*b);        //left side of the top
        g.drawLine(myX + 4*b, myY - 9*b, myX + 3*b, myY - 9*b);     //small horizontal piece on top
        g.drawLine(myX + 3*b, myY - 9*b, myX + b, myY - 12*b);      //top of left arm
        g.drawLine(myX + b, myY - 12*b, myX, myY - 10*b);       //left side of left arm
    }//================================================

    private void drawSoundOn(Graphics g, int b){
	g.drawLine(myX + 2*b, myY - 0*b, myX +2*b, myY - 3*b);
	g.drawLine(myX + 1*b, myY - 1*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 1*b, myX +1*b, myY - 1*b);
	g.drawLine(myX + 0*b, myY - 1*b, myX +0*b, myY - 2*b);
	g.drawLine(myX + 0*b, myY - 2*b, myX +1*b, myY - 2*b);
	g.drawLine(myX + 1*b, myY - 2*b, myX +2*b, myY - 3*b);
	g.drawArc(myX + 2*b, myY - 3*b, 3*b, 3*b, -90 , 180);
	g.drawArc(myX + 3*b, myY - 2*b, 1*b, 1*b, -90 , 180);
    }//======================================

    private void drawSoundOff(Graphics g, int b){
	g.drawLine(myX + 2*b, myY - 0*b, myX +2*b, myY - 3*b);
	g.drawLine(myX + 1*b, myY - 1*b, myX +2*b, myY - 0*b);
	g.drawLine(myX + 0*b, myY - 1*b, myX +1*b, myY - 1*b);
	g.drawLine(myX + 0*b, myY - 1*b, myX +0*b, myY - 2*b);
	g.drawLine(myX + 0*b, myY - 2*b, myX +1*b, myY - 2*b);
	g.drawLine(myX + 1*b, myY - 2*b, myX +2*b, myY - 3*b);
}//======================================

}//END OF SYMBOL CLASS
