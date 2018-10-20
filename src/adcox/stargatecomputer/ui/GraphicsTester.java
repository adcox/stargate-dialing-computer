/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.ui;

import java.awt.Graphics;
import javax.swing.JFrame;

import adcox.stargatecomputer.objects.Blade;
import adcox.stargatecomputer.objects.EventHorizon;
import adcox.stargatecomputer.objects.Iris;
import java.awt.Color;
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
 * @version Dec 30, 2009
 */
public class GraphicsTester extends JFrame{

    private final int P_WIDTH = 1024;
    private final int P_HEIGHT = 768;
    private EventHorizon theHorizon = new EventHorizon(200, 200, 200);
    private Iris theIris = new Iris(1, new Point(300,300), 100, 35, null);
    private Blade aBlade = new Blade(new Point(300,300), 200, 70, 0);

    public GraphicsTester(){
        super("Dialing Computer - Graphics Tester");
        this.setSize(P_WIDTH, P_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//===========================================

    @Override
    public void paint(Graphics g){
        for(int frame = 0; frame < 1000; frame++){
            g.setColor(Color.white);
            g.fillRect(0, 0, P_WIDTH, P_HEIGHT);
            //theHorizon.drawAnimated(g);
            g.setColor(Color.black);
            //drawFibonacciCurve(g);
            //theIris.draw(g);
            aBlade.draw(g);
            frame++;
            g.drawString("" + frame, 100, 100);
        }
    }//================================

    private void drawFibonacciCurve(Graphics g){
        int[] nums = getFibonacciNumbers(20);
        int scalar = 10;
        Point[] points = getFibonacciPoints(8, new Point(500, 300), scalar);
        int startAngle = 0;
        for(int i = 0; i < points.length; i++){
            //g.fillOval(points[i].x, points[i].y, 3, 3);
            g.drawArc(points[i].x, points[i].y, 2*scalar*nums[i + 2], 2*scalar*nums[i + 2], startAngle, 90);
//            System.out.println("Center at: " + points[i].toString());
//            System.out.println("Width of box = 2*scalar*" + nums[i + 2]);
//            System.out.println("Start at " + startAngle + " End at " + (startAngle + 90));
//            System.out.println();
            if(startAngle + 90 < 360)
                startAngle += 90;
            else
                startAngle = 0;
        }
    }//======================================

    private Point[] getFibonacciPoints(int numTerms, Point startingPoint, int scalar){
        int[] nums = getFibonacciNumbers(numTerms + 2);
        boolean moveX = false;
        int signX = 1;
        int signY = -1;
        Point[] points = new Point[numTerms];
        points[0] = (Point)startingPoint.clone();
        //System.out.println(startingPoint.toString());
        for(int i = 1; i < numTerms; i++){
            Point temp = new Point();
            if(moveX){
                temp.x = points[i - 1].x + signX*scalar*nums[i];
                temp.y = points[i - 1].y;
                signX = -signX;
            }else{
                temp.x = points[i - 1].x;
                temp.y = points[i - 1].y - signY*scalar*nums[i];
                signY = -signY;
            }
            points[i] = (Point)temp.clone();
            //System.out.println("Point before Shift: " + temp.toString());
            moveX = !moveX;
        }

        //addjust each point to the top left corner...
        for(int i = 0; i < points.length; i++)
            points[i].translate(-scalar*nums[i + 2], -scalar*nums[i + 2]);
        return points;
    }//=============================================

    private int[] getFibonacciNumbers(int numTerms){
        int[] fib = new int[numTerms];
        if(numTerms == 0)
            return fib;
        else{
            fib[0] = 0;
            fib[1] = 1;
            for(int i = 2; i < numTerms; i++){
                fib[i] = fib[i - 1] + fib[i - 2];
            }
            return fib;
        }
    }//===================================

    public static void main(String[] args){
        GraphicsTester tester = new GraphicsTester();
    }

    //pause for input millisecond
    private static void pause (int milliseconds)
    {   try
        {   Thread.sleep (milliseconds);
        }
        catch (InterruptedException e)
        {   // never happens
        }
    }//======================
}
