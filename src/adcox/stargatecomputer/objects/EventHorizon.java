/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;
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
 * @version Dec 30, 2009
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;

public class EventHorizon {
    private int diameter, x, y;
    private ArrayList <Point> pointsInRadius = new ArrayList<Point>();
    private Point center;

    private int oldR = 10;
    private int oldG = 50;

    private int blobWidth = 10;

    public EventHorizon(int myDiameter, int myX, int myY){
        diameter = myDiameter;
        x = myX;
        y = myY;

        center = new Point();
        center.x = x + diameter/2;
        center.y = y + diameter/2;
        getAllPointsInRadius(diameter/2, center);
    }//================================================

    public int getDiameter(){
        return diameter;
    }//===============================

    public void setDiameter(int newDiameter){
        diameter = newDiameter;
        getAllPointsInRadius(diameter/2, center);
    }//================================

    public void setLocation(int newX, int newY){
        x = newX;
        y = newY;
    }//================================

    public Point getLocation(){
        return new Point(x, y);
    }//==================================

    private void getAllPointsInRadius(int radius, Point center){
        for(int pointX = -1*(radius + blobWidth); pointX < (radius + blobWidth); pointX += blobWidth){
            for(int pointY = -1*(radius + blobWidth); pointY < (radius + blobWidth); pointY += blobWidth){
                //length is distance from center point
                double length = Math.sqrt(Math.pow(pointX, 2) + Math.pow(pointY, 2));
                if(length <= (radius + blobWidth)){
                    //point is inside the given radius
                    int actualX = pointX + center.x;
                    int actualY = pointY + center.y;
                    pointsInRadius.add(new Point(actualX, actualY));
                }
            }
        }
    }//============================================

    private Color getRandomShadeOfBlue(){
        Random randy = new Random();
        int r = 10;     //initialize our variables
        int g = 50;
        int rPos = randy.nextInt(2);
        int gPos = randy.nextInt(2);

        if(rPos == 1 && r > 2)
            r -= 2;
        else
            if(r < 20)
                r += 2;

        if(gPos == 1 && g > 5)
            g -= 5;
        else
            if(g < 100)
                g += 5;

        oldR = r;
        oldG = g;
        return new Color(r, g, 250);
    }//===================================

    public void drawAnimated(Graphics g, Shape clip){
        Shape oldClip = g.getClip();
        g.setClip(clip);
        for(int i = 0; i < pointsInRadius.size(); i++){
            //pick a color for each pixel
            g.setColor(getRandomShadeOfBlue());
            int theX = pointsInRadius.get(i).x;
            int theY = pointsInRadius.get(i).y;
            g.fillRect(theX - blobWidth/2, theY - blobWidth/2, blobWidth, blobWidth);
        }
        g.setClip(oldClip);     //revert back

//        g.setColor(Color.red);
//        g.drawString("Points in Radius:" + pointsInRadius.size(), 20, 200);
    }//=======================================

    public void drawSimple(Graphics g, Shape clip){
        Shape oldClip = g.getClip();
        g.setClip(clip);
        g.setColor(Color.blue);
        g.fillOval(center.x - diameter/2, center.y - diameter/2, diameter, diameter);
        g.setClip(oldClip);
    }//=======================================
}
