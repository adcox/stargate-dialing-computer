/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
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
public class Chevron{
        private int myX, myY;           //location top left corner
        private double myOrientation;    //radians relative to North
        private int myHeight, myWidth;
        private int myBaseLength;

        private int vX, vY;
        public int frameCounter;
        public boolean hasDoneAnimation;

        int lastX, lastY;       //remember points when doing crazy math orientations
        private final Color litUp_COLOR = new Color(235, 68, 14);     //redish orange

        public Chevron(int x, int y, int baseLength, double orientation){
            myX = x;
            myY = y;
            myWidth = 7*baseLength;
            myBaseLength = baseLength;
            myOrientation = orientation;
            myHeight = myWidth;
            vX = x;
            vY = y;
            frameCounter = 0;
            hasDoneAnimation = false;
        }//===================================

        public void setLocation(int x, int y){
            myX = x;    myY = y;
        }//==================================

        public void setSize(int baseLength){ //base height off of width
            myWidth = 7*baseLength;
            myHeight = myWidth;
        }//=========================
        
        public int getSize(){
            return myWidth;
        }//=========================

        public double getOrientation(){
            return myOrientation;
        }

        public Point findTopCenter(){
            Point topCenter = new Point();
            //simple trig
            int x = smartRounding(3.5*myBaseLength*Math.cos(myOrientation));
            int y = smartRounding(3.5*myBaseLength*Math.sin(myOrientation));
            topCenter.x = x + myX;
            topCenter.y = y + myY;
            return topCenter;
        }//=====================================

        public void draw(Graphics g, boolean animating, int framesToAnimate, boolean isLitUp){
            //if we are animating this chevron's "V", and it hasn't completed the animation yet
            if(animating && !hasDoneAnimation)
                animateV(framesToAnimate);
            drawV(g);
            if(isLitUp)
                drawCenterPiece(g, litUp_COLOR);
            else
                drawCenterPiece(g, Color.black);
        }//============================================================

        private void drawV(Graphics g){
            ArrayList<Point> thePoints = new ArrayList<Point>();
            //make sure starting point is in there
            thePoints.add(new Point(vX, vY));
            //left outside line
            calcWithOrientation(vX, vY, myX + 2*myBaseLength, vY + myHeight, 1.25, thePoints);
            //bottom line
            calcWithOrientation(lastX, lastY, lastX + 3*myBaseLength, lastY, 0, thePoints);
            //right outside line
            calcWithOrientation(lastX, lastY, lastX + 2*myBaseLength, lastY - myHeight, -1.25, thePoints);
            //top right
            calcWithOrientation(lastX, lastY, lastX - myBaseLength, lastY, -Math.PI, thePoints);
            //inside right
            calcWithOrientation(lastX, lastY, lastX - 2*myBaseLength, lastY + 6*myBaseLength, 1.95, thePoints);
            //inside bottom
            calcWithOrientation(lastX, lastY, lastX - myBaseLength, lastY, -Math.PI, thePoints);
            //inside left
            calcWithOrientation(lastX, lastY, lastX - 2*myBaseLength, lastY - 6*myBaseLength, -1.95, thePoints);
            //top left
            calcWithOrientation(lastX, lastY, lastX - myBaseLength, lastY, -Math.PI, thePoints);

            //fill in this area with black
            fillPolygon(g, thePoints, Color.black);

            g.setColor(Color.red);
            //draw all the lines between precalculated points
            for(int i = 0; i < thePoints.size() - 1; i++){
                g.drawLine(thePoints.get(i).x, thePoints.get(i).y,
                        thePoints.get(i + 1).x, thePoints.get(i + 1).y);
            }
        }//==========================================

        public void animateV(int framesToAnimate){
            double theta;
            theta = Math.PI/2 - myOrientation;
            frameCounter++;

            //move downwards
            if(frameCounter < 4){
                vX = smartRounding(vX + 2*Math.cos(theta));
                vY = smartRounding(vY + 2*Math.sin(theta));
            }
            //move upwards
            if(frameCounter > framesToAnimate - 4){
                vX = smartRounding(vX - 2*Math.cos(theta));
                vY = smartRounding(vY - 2*Math.sin(theta));
            }
            if(frameCounter > framesToAnimate - 1){
                frameCounter = 0;
                hasDoneAnimation = true;
                vX = myX;   //make sure the V ends at exactly the same position
                vY = myY;
            }
        }//==================================================

        private void drawCenterPiece(Graphics g, Color colorToUse){
            ArrayList<Point> thePoints = new ArrayList<Point>();
            //the first point will be at the end of this horizontal line
            calcWithOrientation(myX, myY, myX + 2*myBaseLength, myY, 0, thePoints);
            //left vertical line
            calcWithOrientation(lastX, lastY, lastX, lastY + myBaseLength, Math.PI/2, thePoints);
            //left sloped line
            calcWithOrientation(lastX, lastY, lastX + myBaseLength, lastY + 3*myBaseLength, 1.19, thePoints);
            //bottom
            calcWithOrientation(lastX, lastY, lastX + myBaseLength, lastY, 0, thePoints);
            //right sloped line
            calcWithOrientation(lastX, lastY, lastX + myBaseLength, lastY - 3*myBaseLength, -1.19, thePoints);
            //right vertical line
            calcWithOrientation(lastX, lastY, lastX, lastY - myBaseLength, -Math.PI/2, thePoints);
            //top line
            calcWithOrientation(lastX, lastY, lastX - 3*myBaseLength, lastY, -Math.PI, thePoints);

            //fill in this area with black
            fillPolygon(g, thePoints, colorToUse);

            g.setColor(Color.red);
            //draw all the lines between precalculated points
            for(int i = 0; i < thePoints.size() - 1; i++){
                g.drawLine(thePoints.get(i).x, thePoints.get(i).y,
                        thePoints.get(i + 1).x, thePoints.get(i + 1).y);
            }
        }//=============================================

        private void fillPolygon(Graphics g, ArrayList<Point> theArrayList, Color colorToFill){
            int[] x = new int[theArrayList.size()];
            int[] y = new int[theArrayList.size()];
            //convert arrayList of points into two integer arrays
            for(int i = 0; i < theArrayList.size(); i++){
                x[i] = theArrayList.get(i).x;
                y[i] = theArrayList.get(i).y;
            }
            g.setColor(colorToFill);
            g.fillPolygon(x, y, theArrayList.size());
        }//==============================================
    

        private void calcWithOrientation(int x1, int y1, int x2, int y2,
                double degreesRelEast, ArrayList<Point> thePoints){

            double destX, destY;
            double a, b;
            a = Math.pow(x1 - x2, 2);
            b = Math.pow(y1 - y2, 2);
            double length = Math.sqrt(a + b);

            destX = x1 + length*Math.cos(myOrientation + degreesRelEast);
            destY = y1 + length*Math.sin(myOrientation + degreesRelEast);

            lastX = smartRounding(destX);
            lastY = smartRounding(destY);

            thePoints.add(new Point(lastX, lastY));
        }//========================================================

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

    }
