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

/**
 *
 * @author Andrew
 * @version Dec 26, 2009
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class DialingJPanel extends JPanel implements MouseListener, 
        MouseMotionListener, KeyListener, ListSelectionListener{

    // Global variables
    // -- do not need to be set in subclasses
    public int frameNumber;
    public Point mouseLocation;
    String myName;
    //

    public DialingJPanel(String n, int width, int height)
    {
        frameNumber = 0;
        mouseLocation = new Point(0,0);
        myName = n;

        this.setPreferredSize(new Dimension(width, height));
        this.setLocation(80,80);    //move to the right
        this.setVisible (true);         // make it visible to the user
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }//===============================================

    ///////////////////////////////////////////////////
    /* Method renderFrame()
     * This is what is repeatedly animated,
     * it paints your graphics to the frame.
     * Don't forget to extend this!
     */
    protected Graphics renderFrame(Graphics g)
    {
        return g;
    }
    ///////////////////////////////////////////////////

    public String getMyName()
    {
        return myName;
    }

    ////// Event Listener Methods //////
    // -- Inherited in all subclasses of MotionPanel
    // -- Extend these however you like!


    // MouseListener
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    /**/

    // MouseMotionListener
    // -- mouse position tracked
    // -- you shouldn't have to extend these methods, but if you do,
    // -- remember to call super() to keep mouse tracking functionality
    public void mouseMoved(MouseEvent e) { mouseLocation = e.getPoint();}
    public void mouseDragged(MouseEvent e) { mouseLocation = e.getPoint();}

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    /**/

    public void valueChanged(ListSelectionEvent e) {}
////// -- end Event Listeners () -- //////

    // method paintComponent
    // inherited from Class JPanel
    // does the repaint() of your panel
    // --- DO NOT MODifY ---
    @Override
    public void paintComponent(Graphics g)
    {
        frameNumber++;
        this.requestFocusInWindow();
        g = renderFrame(g);
    }

}
