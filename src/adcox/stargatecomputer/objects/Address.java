package adcox.stargatecomputer.objects;

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
 * @version Jan 18, 2010
 */
public class Address {
    private ArrayList<Integer> theAddress = new ArrayList<Integer>();
    private String theName = "No name";
    private String info = "No Info";

    public Address(String name, ArrayList<Integer> address){
        theName = name;
        theAddress = address;
    }//================================

    public Address(String name){
        theName = name;
    }//================================

    public void setAddress(ArrayList<Integer> address){
        theAddress = address;
    }//=================================

    /**
     * This function returns the entire list of symbols that correspond with this address
     * @return an ArrayList of integers.  Each integer is the index of a symbol
     * in the address
     */
    public ArrayList<Integer> getAddress(){ return theAddress;}

    /**
     * This function sets the info about an address.
     * @param theInfo a String containing any relevant information (background,
     * historical) about the address.
     */
    public void setInfo (String theInfo){ info = theInfo;}

    /**
     * This function returns the information about this address.
     * @return a String containing the relevant background or historical information
     * about this address.
     */
    public String getInfo(){ return info;}

    /**
     * This function returns the name of the address.
     * @return a String containing the name of this address.
     */
    public String getName(){ return theName;}
}
