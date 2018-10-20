/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.ui;

import javax.swing.JFrame;

import adcox.stargatecomputer.objects.DialingCompButton;
import adcox.stargatecomputer.objects.Symbol;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
public class Keyboard extends JFrame implements MouseListener, MouseMotionListener{
    ArrayList<DialingCompButton> theButtons = new ArrayList<DialingCompButton>();
    ArrayList<Symbol> theSymbols = new ArrayList<Symbol>();
    //everybody is going to want this ArrayList
    public ArrayList<Integer> chevsToEncode = new ArrayList<Integer>();
    DialingComputer theCompu;

    private final int BUTTON_WIDTH = 50;
    private final int BUTTON_HEIGHT = 56;
    private final int MARGIN = 10;
    private final int P_WIDTH = 10*(BUTTON_WIDTH + MARGIN) + MARGIN;
    private final int P_HEIGHT = 4 * (BUTTON_HEIGHT + MARGIN) + 20 + MARGIN;

    int maxButtonsToPush;

    public Keyboard(DialingComputer theBoss){
        super("Dialing Computer - Keyboard");
        this.setSize(P_WIDTH, P_HEIGHT);
        //center the keyboard on the screen relative to Dialing Computer
        this.setLocation(DialingComputer.P_WIDTH/2 - P_WIDTH/2, DialingComputer.P_HEIGHT/2 - P_HEIGHT/2);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        maxButtonsToPush = theBoss.numToDial;
        theCompu = theBoss;
        placeBoxes();
        chevsToEncode = (ArrayList<Integer>) theCompu.chevsToEncode.clone();
        //check chevsToEncode and light up any buttons that need to be lit up in blue
        for(int c = 0; c < chevsToEncode.size(); c++){
                theButtons.get(chevsToEncode.get(c)).changeBackground(Color.blue);
            }
    }//====================================

    private void placeBoxes(){
        int row = 0;     //keep track of row number
        int collum = 0;     //keep track of collum

        for(int i = 0; i < 39; i++){
            int x, y;
        //X's******************************************************
            //reset collum after 10 collums
            if(collum > 9)
                collum = 0;
            if(row == 3)    //offset the bottom row
                x = (MARGIN + collum * (BUTTON_WIDTH + MARGIN) + BUTTON_WIDTH/2);
            else
                x = (MARGIN + collum * (BUTTON_WIDTH + MARGIN));
            collum++;
        //Y's*******************************************************
            y = (20+ MARGIN + row*(BUTTON_HEIGHT + MARGIN));
            //start a new row every ten buttons
            if((i + 1) % 10 == 0)
                row++;

            theButtons.add(new DialingCompButton(x, y, BUTTON_HEIGHT, BUTTON_WIDTH, i, Color.black, Color.white, true));
        }
    }//======================================================

    // Drawing
    /**
     * The Graphics object created from the image object.
     */
    private BufferedImage image = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);
    private Graphics2D buffer = (Graphics2D)image.getGraphics();

    /**
     * Buffer the graphics by calling drawScreen with our buffer, and then clearing the screen.
     */
    public void bufferDraw(Graphics g) {
        buffer.clearRect(0,0,1024,768);
        drawScreen(buffer);
        g.drawImage(image, 0, 0, null);
    }//==================================

    @Override
    public void paint(Graphics g){
        bufferDraw(g);
    }//=======================================================

    private void drawScreen(Graphics2D g){
        g.setColor(Color.black);
        g.fillRect(0, 0, P_WIDTH, P_HEIGHT);

        for(int i = 0; i < theButtons.size(); i++){
            theButtons.get(i).draw(g);
        }

    }//=======================================

    public void clear(){
        chevsToEncode.clear();
        for(int i = 0; i < theButtons.size(); i++)
            theButtons.get(i).changeBackground(Color.black);
        repaint();
    }//==============================

    public void refresh(DialingComputer theBoss){
        theCompu = theBoss;
        chevsToEncode = (ArrayList<Integer>) theCompu.chevsToEncode.clone();
        maxButtonsToPush = theCompu.numToDial;
        //check chevsToEncode and light up any buttons that need to be lit up in blue
        for(int c = 0; c < chevsToEncode.size(); c++){
            theButtons.get(chevsToEncode.get(c)).changeBackground(Color.blue);
        }
    }//========================
    
    //MouseListener methods
    public void mouseClicked(MouseEvent e){
        Point mouseLocation = e.getPoint();
        
        for(int b = 0; b < theButtons.size(); b++)
            if(mouseLocation.x > theButtons.get(b).getX() && mouseLocation.x < theButtons.get(b).getRightSide()
                    && mouseLocation.y > theButtons.get(b).getY() && mouseLocation.y < theButtons.get(b).getBottom()){
                if(!chevsToEncode.contains(b)){
                    theButtons.get(b).changeBackground(Color.blue);     //keep dialed buttons blue
                    if(chevsToEncode.size() < maxButtonsToPush)
                        chevsToEncode.add(b);
                        theCompu.chevsToEncode = (ArrayList<Integer>) chevsToEncode.clone();
                }
            }
        
        repaint();
    }//===================================

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e)  {}

    //MouseMotionListener methods
    public void mouseDragged(MouseEvent e)  {}
    public void mouseMoved(MouseEvent e){
        Point mouseLocation = e.getPoint();
        
        for(int b = 0; b < theButtons.size(); b++)
            if(mouseLocation.x > theButtons.get(b).getX() && mouseLocation.x < theButtons.get(b).getRightSide()
                    && mouseLocation.y > theButtons.get(b).getY() && mouseLocation.y < theButtons.get(b).getBottom()){
                
                theButtons.get(b).changeBackground(Color.BLUE);
            }
            else{
            boolean beenClicked = false;
                for(int index = 0; index < chevsToEncode.size(); index++){
                    if(chevsToEncode.get(index) == b)
                        beenClicked = true;
                }
                if(!beenClicked)
                    theButtons.get(b).changeBackground(Color.BLACK);
            }
        repaint();
    }//===========================================
}
