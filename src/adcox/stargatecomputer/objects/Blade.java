/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
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
 * @version Oct 30, 2010
 */
public class Blade {
    protected Polygon theBlade;
    protected Point stationary = new Point();
    protected Point movable = new Point();
    protected double myOrientation;
    /**
     * Equation of an ellipse => x^2/a^2 + y^2/b^2 = 1
     * where a^2 and b^2 are the magnitudes of the x-intercepts and y-intercepts respectively.
     */
    //First elipse
    protected int h1, w1;
    //second elipse
    protected int h2, w2;
    protected int lastX, lastY;       //remember points when doing crazy math orientations
    protected Color color = Color.darkGray;

    /**
     * Constructor for the Blade class
     * @param stationary Point representing the stationary point.
     * @param width int representing the vertical distance between the
     * stationary point and the movable point.
     * @param orientation double representing the angle in radians the blade makes relative
     * to the positive y-axis.  Positive values indicate counter-clockwise rotation.
     */
    public Blade(Point stationary, int height, int width, double orientation){
        this.stationary = (Point)stationary.clone();
        //myOrientation = (int)(Math.PI*(orientation/180));     //convert to radians
        myOrientation = orientation;
        //calculate the height and width radii of each arc
        h1 = height;
        h2 = h1 - width;
        w1 = h2;
        w2 = w1;

        movable.x = stationary.x;
        movable.y = stationary.y + h1 - h2;

        theBlade = makeRotatable(calcPolygon(10));
        //theBlade = calcPolygon(10);
    }//=========================================

    /**
     * Adds dTheta degrees to the existing orientation value.
     * @param dTheta double representing the number of degrees to add
     */
    public void changeOrientation(double dTheta){
        dTheta = Math.PI*(dTheta/180.00);   //convert to radians
        myOrientation += dTheta;
        theBlade = makeRotatable(calcPolygon(10));
    }//=========================================

    /**
     * This method uses trig to approximate pieces of two ellipses.
     * @param numSlices int representing the number of slices between the y-
     * axis and the end of the arc.
     * @return Polygon representing the two arc segments.
     */
    private Polygon calcPolygon(int numSlices){
        int[] xVals = new int[2*numSlices + 3];
        int[] yVals = new int[2*numSlices + 3];
        int dX = w1/numSlices;

        //calculate the points on the top curve
        xVals[0] = stationary.x;
        yVals[0] = stationary.y;
        int x = 0;
        for(int i = 0; i < numSlices; i++){
            xVals[i + 1] = stationary.x + x;
            /**
             * Now we have to find y values as a function of x
             * y = b*Math.sqrt(1 - (x^2/a^2))
             * Note: The stuff inside the root is going to have to be positive...
             */
            double insideRoot = 1 - (x*x)/Math.pow((w1 + 4), 2);
            yVals[i + 1] = stationary.y + h1 - smartRounding(h1*Math.sqrt(insideRoot));
            x += dX;
        }

        xVals[numSlices + 1] = stationary.x + w1;
        yVals[numSlices + 1] = stationary.y + h1;
        //going back up the inside arc
        x = w1;
        for(int i = 0; i < numSlices; i++){
            xVals[i + numSlices + 2] = movable.x + x;
            double insideRoot = 1 - (x*x)/Math.pow((w2 - 8), 2);
            yVals[i + numSlices + 2] = movable.y + h2 - smartRounding((h2 + 0)*Math.sqrt(insideRoot));
            x -= dX;
        }
        xVals[2*numSlices + 2] = movable.x;
        yVals[2*numSlices + 2] = movable.y;

        //System.out.println("XVals: " + listToString(xVals));
        //System.out.println("YVals: " + listToString(yVals));
        return new Polygon(xVals, yVals, xVals.length);
    }//==============================================

    /**
     * Takes a list and prints it out so the user can see all the elements
     * @param aList int[] representing the list that is to be "textified"
     * @return String representing the list
     */
    private String listToString(int[] aList){
        String x = "[";
        for(int i = 0; i < aList.length; i++){
            x += aList[i] + ", ";
        }
        x += "]";
        return x;
    }//=========================

    /**
     * This method takes a Polygon and uses the calcWithOrientation method
     * I wrote to make a new Polygon that has been rotated
     * @param poly Polygon representing the shape that is to be rotated
     * @return Polygon that has been rotated
     */
    private Polygon makeRotatable(Polygon poly){
        int[] xVals = poly.xpoints;
        int[] yVals = poly.ypoints;
        ArrayList<Point> thePoints = new ArrayList<Point>();
        for(int i = 1; i < poly.npoints; i++){
            if(i > 1){
                int x1 = xVals[i - 1];
                int y1 = yVals[i - 1];
                int x2 = xVals[i];
                int y2 = yVals[i];
                double dX = x2 - x1;
                double dY = y1 - y2;
                double theta = 0;
                if((x2 - x1) != 0)
                    theta = Math.atan(dY/dX);
                if((x2 - x1) < 0)
                    theta += Math.PI;
                calcWithOrientation(lastX, lastY, smartRounding(lastX + dX), smartRounding(lastY - dY), theta, thePoints);
            }else{
                int x1 = xVals[i - 1];
                int y1 = yVals[i - 1];
                int x2 = xVals[i];
                int y2 = yVals[i];
                double dX = x2 - x1;
                double dY = y1 - y2;
                double theta = 0;
                if((x2 - x1) != 0)
                    theta = Math.atan(dX/dY);
                if((x2 - x1) < 0)
                    theta += Math.PI;
                calcWithOrientation(x1, y1, x2, y2, theta, thePoints);
            }
        }
        return convertToPoly(thePoints);
    }//==================================

    /**
     * This method takes two points and calculates where the second set of
     * Points would lie if the picture was rotated a certain amount.
     * @param x1 int representing the first x value.  This should be lastX
     * except for the very first point.  Take a look at implemented code to
     * understand this.
     * @param y1 int representing the first y value.  This should be lastY
     * except for the very first point.  Take a look at implemented coded to
     * understand this.
     * @param x2 int representing the second x value.  This should be lastX
     * plus some dX between the original two points.  Check out implemented
     * code for a good example.
     * @param y2 int representing the second y value.  This should be lastY
     * plus some dY between the original two points.
     * @param radiansRelEast double representing the number of radians between
     * the line (x1, y2) to (x2, y2)
     * @param thePoints
     */
    private void calcWithOrientation(int x1, int y1, int x2, int y2,
            double radiansRelEast, ArrayList<Point> thePoints){

        double destX, destY;
        double a, b;
        a = Math.pow(x1 - x2, 2);
        b = Math.pow(y1 - y2, 2);
        double length = Math.sqrt(a + b);

        destX = x1 + length*Math.cos(myOrientation + radiansRelEast);
        destY = y1 - length*Math.sin(myOrientation + radiansRelEast);

        lastX = smartRounding(destX);
        lastY = smartRounding(destY);

        thePoints.add(new Point(lastX, lastY));
    }//========================================================

    /**
     * This method ACTUALLY rounds a number the right way.
     * @param number double representing the number to be rounded.
     * @return int representing the rounded number
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
     * This method takes an ArrayList of Points and turns it into a Polygon
     * @param theArrayList ArrayList<Point> representing
     * @return
     */
    private Polygon convertToPoly(ArrayList<Point> theArrayList){
        int[] x = new int[theArrayList.size()];
        int[] y = new int[theArrayList.size()];
        //convert arrayList of points into two integer arrays
        for(int i = 0; i < theArrayList.size(); i++){
            x[i] = theArrayList.get(i).x;
            y[i] = theArrayList.get(i).y;
        }
        return new Polygon(x, y, x.length);
    }//==============================================

    public void draw(Graphics g){
        g.setColor(color);
        g.drawPolygon(theBlade);
        g.setColor(Color.gray);
        g.fillPolygon(theBlade);
    }//================================

}//###########END OF BLADE###################
