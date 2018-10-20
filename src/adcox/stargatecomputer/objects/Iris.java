/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;

import java.awt.Component;
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
 * @version Oct 30, 2010
 */
public class Iris extends Component implements Runnable{
    /**
     * The iris will consist of individual blades, which will be the most
     * important object.
     */
    
    protected Blade[] blades;
    protected boolean animating = false;
    protected boolean open = false;
    protected double signOfAnimation = 1.00;     //negative will close the iris
    protected double totalAnimationAngle = 90;

    private Thread thread;
    private int sleepTime;
    private Graphics graphics;
    
    public Iris(int numBlades, Point gateCenter, int radius, int FPS, Graphics graphics){
        blades = new Blade[numBlades];
        
        this.graphics = graphics;
        
        double dTheta = 2*Math.PI/numBlades;
        double theta = 0;
        int x, y;
        double circ = Math.PI*radius*2;
        int width = (int)(circ/numBlades);
        for(int i = 0; i < numBlades; i++){
            y = gateCenter.y - (int)(radius*Math.sin(theta));
            x = gateCenter.x + (int)(radius*Math.cos(theta));
            
            blades[i] = new Blade(new Point(x, y), radius, width, theta - 3*Math.PI/4);
            theta -= dTheta;
        }
        
        sleepTime = 1000/FPS;
        
        thread = new Thread();
    }//===========================

    @Override
    public void paint(Graphics g){
        
        for(int i = 0; i < blades.length; i++){
            blades[i].draw(g);
            if(animating){
                blades[i].changeOrientation(signOfAnimation * 1);
                totalAnimationAngle += signOfAnimation * 1;
                if(totalAnimationAngle > 1440 || totalAnimationAngle < 91){
                    animating = false;
                    open = !open;
                }   
            }
//            g.setColor(Color.white);
//            g.drawString("Blade Orientation" + totalAnimationAngle, 500, 300);
        }
    }//========================

    public void openCloseIris(boolean playSounds){
        MP3 clip;
        if(!open){
            clip= new MP3("aud/openFast.mp3");
            signOfAnimation = 1.00;
        }else{
            clip = new MP3("aud/closeFast.mp3");
            signOfAnimation = -1.00;
        }
        if(playSounds)
            clip.play();

        animating = true;
        if(!thread.isAlive()){
            thread = new Thread(this);
            thread.start();
        }
    }//==========================

    public boolean isOpen(){return open;}

    public boolean isAnimating(){return animating;}

    public void run() {
        while(animating){
            paint(graphics);
        
            try{
                Thread.sleep(sleepTime);
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        
    }//========================================

    
}//===========END OF IRIS================
