/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcox.stargatecomputer.objects;

import java.awt.Color;
import java.awt.Graphics2D;
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
public class DialingCompButton{
        protected int myX, myY;       //location top left corner
        protected int myHeight, myWidth;
        protected String myText;        //text to display
        protected boolean clickable;  //whether or not user can click on this
        protected boolean stayLit = false;
        private Symbol mySymbol;    //symbol to display

        protected Color background = Color.black;
        protected Color foreground = Color.white;

        public DialingCompButton(int x, int y, int height, int width,
                String text, Color background, Color foreground, boolean canClick){
                myX = x;
                myY = y;
                myHeight = height;
                myWidth = width;
                myText = text;
                mySymbol = null;
                this.background = background;
                this.foreground = foreground;
                clickable = canClick;
        }//=====================================

        public DialingCompButton(int x, int y, int height, int width, int symbolIndex, Color background,
                Color foreground, boolean canClick){
                myX = x;
                myY = y;
                myHeight = height;
                myWidth = width;
                mySymbol = new Symbol(symbolIndex, x, y);
                this.background = background;
                this.foreground = foreground;
                myText = null;
        }//====================================

        public void changeLocation(int x, int y){
            myX = x;
            myY = y;
        }//========================================

        public int getX(){ return myX;}

        public int getY(){return myY;}

        public int getRightSide(){return myX + myWidth;}

        public int getBottom(){return myY + myHeight;}

        public void changeSize(int height, int width){
            myHeight = height;
            myWidth = width;
        }//=========================================

        public void setText(String s){myText = s;}

        public void setSymbolIndex(int i){mySymbol = new Symbol(i, myX, myY);}

        public void changeClickable(boolean canClick){clickable = canClick;}

        public void changeBackground(Color newColor){background = newColor;}

        public boolean staysLit(){return stayLit;}

        public void setStayLit(boolean b){stayLit = b;}

        public boolean mouseInButton(int mouseX, int mouseY){
            if(mouseX > myX && mouseX < getRightSide()
                && mouseY > myY && mouseY < getBottom()){
                return true;
            }
            return false;
        }//=========================================

        public boolean mouseInButton(Point mouseLocation){
            if(mouseLocation.x > myX && mouseLocation.x < getRightSide()
                && mouseLocation.y > myY && mouseLocation.y < getBottom()){
                return true;
            }
            return false;
        }//=========================================

        public void draw(Graphics2D g){
            g.setColor(background);
            g.fillRect(myX, myY, myWidth, myHeight);
            g.setColor(foreground);
            g.draw3DRect(myX, myY, myWidth, myHeight, true);
            if(myText != null){
                g.drawString(myText, myX + 5, myY + myHeight - 5);
            }
            if(mySymbol != null){
                mySymbol.setPosition(myX + myWidth/10, myY + myHeight - myHeight/8);
                mySymbol.draw(g);
            }
        }//========================================
    }
