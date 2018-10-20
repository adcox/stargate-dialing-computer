/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @version Dec 31, 2009
 */
public class AddressLibrary {
    private ArrayList<Address> theAddresses = new ArrayList<Address>();

    public AddressLibrary(){
        initAddresses();
        //System.out.println("Initialized addresses");
        //put addreses in alphabetical order
        theAddresses = (ArrayList<Address>)alphabetizeList(theAddresses).clone();
        //System.out.println("Sotred Addresses");
    }//=====================

    private ArrayList<Address> alphabetizeList(ArrayList<Address> addresses){
        //This is just Merge Sort applied to characters
        //If the array is only 1 or 0 long, it is already sorted
        if (addresses.size() <= 1)
            return addresses;
        //Initialize sub arrays
        ArrayList<Address> left = new ArrayList<Address>();
        ArrayList<Address> right = new ArrayList<Address>();
        ArrayList<Address> result = new ArrayList<Address>();
        int middle = addresses.size()/2;

        //Split addresses in half around some middle
        for(int i = 0; i < middle; i++){
            left.add(addresses.get(i));
        }

        for(int i = middle; i < addresses.size(); i++){
            right.add(addresses.get(i));
        }

        //recursively sort the arrays
        left = alphabetizeList(left);
        right = alphabetizeList(right);
        result = merge(left, right);
        return result;
    }//=====================================

    private ArrayList<Address> merge(ArrayList<Address> left, ArrayList<Address> right){
        //initialize the array to return
        ArrayList<Address> result = new ArrayList<Address>();

        while(left.size() > 0 || right.size() > 0){
            if(left.size() > 0 && right.size() > 0){
                //iterate through the letters in the words if first letters are identical
                for(int i = 0; i < left.get(0).getName().toCharArray().length; i++){
                    //System.out.println(String.format("Comparing %s and %s", left.get(0).getName(), right.get(0).getName()));
                    //get the first letter of the name of each address, capitalized
                    char leftLetter = (left.get(0).getName().toUpperCase().toCharArray())[i];
                    char rightLetter = 'Z';
                    if(i < right.get(0).getName().toCharArray().length)
                        rightLetter = (right.get(0).getName().toUpperCase().toCharArray())[i];
                    else{
                        //We have reached the end of a word, and they're still the same
                        result.add(left.get(0));
                        left.remove(0);
                        break;
                    }

                    //a letter less than another letter means it comes earlier in the alphabet
                    if(leftLetter < rightLetter){
                        result.add(left.get(0));
                        left.remove(0);
                        break;
                    }
                    else if(leftLetter > rightLetter){
                        result.add(right.get(0));
                        right.remove(0);
                        break;
                    }
                    else if(leftLetter == rightLetter && i == left.get(0).getName().toCharArray().length - 1){
                        result.add(left.get(0));
                        left.remove(0);
                        break;
                    }
                }

            }
            else if(left.size() > 0){
                result.add(left.get(0));
                left.remove(0);
            }
            else if(right.size() > 0){
                result.add(right.get(0));
                right.remove(0);
            }

        }

        return result;
    }//==========================

    public ArrayList<Address> getTheAddresses(){ return theAddresses;}

    private void initAddresses(){
        ArrayList<Integer> alphaSiteAddress = new ArrayList<Integer>();
        alphaSiteAddress.add(28);
        alphaSiteAddress.add(4);
        alphaSiteAddress.add(23);
        alphaSiteAddress.add(5);
        alphaSiteAddress.add(22);
        alphaSiteAddress.add(19);
        alphaSiteAddress.add(0);
        theAddresses.add(new Address("Alpha Site"));
        theAddresses.get(theAddresses.size() - 1).setAddress(alphaSiteAddress);

        ArrayList<Integer> ancientColonyAddress = new ArrayList<Integer>();
        ancientColonyAddress.add(20);
        ancientColonyAddress.add(21);
        ancientColonyAddress.add(2);
        ancientColonyAddress.add(23);
        ancientColonyAddress.add(30);
        ancientColonyAddress.add(26);
        ancientColonyAddress.add(0);
        theAddresses.add(new Address("Ancient Colony"));
        theAddresses.get(theAddresses.size() - 1).setAddress(ancientColonyAddress);

        ArrayList<Integer> antikerColonyAddress = new ArrayList<Integer>();
        antikerColonyAddress.add(11);
        antikerColonyAddress.add(35);
        antikerColonyAddress.add(22);
        antikerColonyAddress.add(17);
        antikerColonyAddress.add(6);
        antikerColonyAddress.add(26);
        antikerColonyAddress.add(0);
        theAddresses.add(new Address("Antiker Colony"));
        theAddresses.get(theAddresses.size() - 1).setAddress(antikerColonyAddress);

        ArrayList<Integer> apophisHomeworldAddress = new ArrayList<Integer>();
        apophisHomeworldAddress.add(19);
        apophisHomeworldAddress.add(17);
        apophisHomeworldAddress.add(10);
        apophisHomeworldAddress.add(37);
        apophisHomeworldAddress.add(9);
        apophisHomeworldAddress.add(31);
        apophisHomeworldAddress.add(0);
        theAddresses.add(new Address("Apophis's Homeworld"));
        theAddresses.get(theAddresses.size() - 1).setAddress(apophisHomeworldAddress);

        ArrayList<Integer> apophisShipyardAddress = new ArrayList<Integer>();
        apophisShipyardAddress.add(28);
        apophisShipyardAddress.add(12);
        apophisShipyardAddress.add(25);
        apophisShipyardAddress.add(38);
        apophisShipyardAddress.add(35);
        apophisShipyardAddress.add(9);
        apophisShipyardAddress.add(0);
        theAddresses.add(new Address("Apophis's Shipyard"));
        theAddresses.get(theAddresses.size() - 1).setAddress(apophisShipyardAddress);

        ArrayList<Integer> argosAddress = new ArrayList<Integer>();
        argosAddress.add(23);
        argosAddress.add(3);
        argosAddress.add(19);
        argosAddress.add(8);
        argosAddress.add(14);
        argosAddress.add(28);
        argosAddress.add(0);
        theAddresses.add(new Address("Argos"));
        theAddresses.get(theAddresses.size() - 1).setAddress(argosAddress);

        ArrayList<Integer> atlantisAddress = new ArrayList<Integer>();
        atlantisAddress.add(18);
        atlantisAddress.add(20);
        atlantisAddress.add(1);
        atlantisAddress.add(15);
        atlantisAddress.add(14);
        atlantisAddress.add(7);
        atlantisAddress.add(19);
        atlantisAddress.add(0);
        theAddresses.add(new Address("Atlantis"));
        theAddresses.get(theAddresses.size() - 1).setAddress(atlantisAddress);

        ArrayList<Integer> baalsBaseAddress = new ArrayList<Integer>();
        baalsBaseAddress.add(30);
        baalsBaseAddress.add(27);
        baalsBaseAddress.add(4);
        baalsBaseAddress.add(7);
        baalsBaseAddress.add(17);
        baalsBaseAddress.add(36);
        baalsBaseAddress.add(0);
        theAddresses.add(new Address("Baal's Base"));
        theAddresses.get(theAddresses.size() - 1).setAddress(baalsBaseAddress);

        ArrayList<Integer> baalsMineAddress = new ArrayList<Integer>();
        baalsMineAddress.add(16);
        baalsMineAddress.add(19);
        baalsMineAddress.add(37);
        baalsMineAddress.add(33);
        baalsMineAddress.add(34);
        baalsMineAddress.add(2);
        baalsMineAddress.add(0);
        theAddresses.add(new Address("Baal's Mine"));
        theAddresses.get(theAddresses.size() - 1).setAddress(baalsMineAddress);

        ArrayList<Integer> baalsOutpostAddress = new ArrayList<Integer>();
        baalsOutpostAddress.add(3);
        baalsOutpostAddress.add(15);
        baalsOutpostAddress.add(6);
        baalsOutpostAddress.add(38);
        baalsOutpostAddress.add(8);
        baalsOutpostAddress.add(35);
        baalsOutpostAddress.add(0);
        theAddresses.add(new Address("Baal's Outpost"));
        theAddresses.get(theAddresses.size() - 1).setAddress(baalsOutpostAddress);

        ArrayList<Integer> bedrosiaAddress = new ArrayList<Integer>();
        bedrosiaAddress.add(19);
        bedrosiaAddress.add(3);
        bedrosiaAddress.add(22);
        bedrosiaAddress.add(28);
        bedrosiaAddress.add(24);
        bedrosiaAddress.add(36);
        bedrosiaAddress.add(0);
        theAddresses.add(new Address("Bedrosia"));
        theAddresses.get(theAddresses.size() - 1).setAddress(bedrosiaAddress);

        ArrayList<Integer> abydosAddress = new ArrayList<Integer>();
        abydosAddress.add(26);
        abydosAddress.add(6);
        abydosAddress.add(14);
        abydosAddress.add(31);
        abydosAddress.add(11);
        abydosAddress.add(29);
        abydosAddress.add(0);
        theAddresses.add(new Address("Abydos"));
        theAddresses.get(theAddresses.size() - 1).setAddress(abydosAddress);

        ArrayList<Integer> bp63q1Address = new ArrayList<Integer>();
        bp63q1Address.add(28);
        bp63q1Address.add(29);
        bp63q1Address.add(23);
        bp63q1Address.add(9);
        bp63q1Address.add(33);
        bp63q1Address.add(31);
        bp63q1Address.add(0);
        theAddresses.add(new Address("BP6-3Q1"));
        theAddresses.get(theAddresses.size() - 1).setAddress(bp63q1Address);

        ArrayList<Integer> camelotAddress = new ArrayList<Integer>();
        camelotAddress.add(19);
        camelotAddress.add(1);
        camelotAddress.add(34);
        camelotAddress.add(7);
        camelotAddress.add(25);
        camelotAddress.add(14);
        camelotAddress.add(0);
        theAddresses.add(new Address("Camelot"));
        theAddresses.get(theAddresses.size() - 1).setAddress(camelotAddress);

        ArrayList<Integer> castianaAddress = new ArrayList<Integer>();
        castianaAddress.add(28);
        castianaAddress.add(2);
        castianaAddress.add(27);
        castianaAddress.add(8);
        castianaAddress.add(11);
        castianaAddress.add(15);
        castianaAddress.add(0);
        theAddresses.add(new Address("Castiana"));
        theAddresses.get(theAddresses.size() - 1).setAddress(castianaAddress);

        ArrayList<Integer> chulakAddress = new ArrayList<Integer>();
        chulakAddress.add(8);
        chulakAddress.add(1);
        chulakAddress.add(22);
        chulakAddress.add(14);
        chulakAddress.add(36);
        chulakAddress.add(19);
        chulakAddress.add(0);
        theAddresses.add(new Address("Chulak"));
        theAddresses.get(theAddresses.size() - 1).setAddress(chulakAddress);

        ArrayList<Integer> cimmeriaAddress = new ArrayList<Integer>();
        cimmeriaAddress.add(10);
        cimmeriaAddress.add(34);
        cimmeriaAddress.add(21);
        cimmeriaAddress.add(16);
        cimmeriaAddress.add(5);
        cimmeriaAddress.add(25);
        cimmeriaAddress.add(0);
        theAddresses.add(new Address("Cimmeria"));
        theAddresses.get(theAddresses.size() - 1).setAddress(cimmeriaAddress);

        ArrayList<Integer> earthAddress = new ArrayList<Integer>();
        earthAddress.add(27);
        earthAddress.add(25);
        earthAddress.add(4);
        earthAddress.add(35);
        earthAddress.add(10);
        earthAddress.add(28);
        earthAddress.add(0);
        theAddresses.add(new Address("Earth"));
        theAddresses.get(theAddresses.size() - 1).setAddress(earthAddress);
        
        ArrayList<Integer> edoraAddress = new ArrayList<Integer>();
        edoraAddress.add(27);
        edoraAddress.add(23);
        edoraAddress.add(34);
        edoraAddress.add(8);
        edoraAddress.add(14);
        edoraAddress.add(2);
        edoraAddress.add(0);
        theAddresses.add(new Address("Edora"));
        theAddresses.get(theAddresses.size() - 1).setAddress(edoraAddress);

        ArrayList<Integer> entityPlanetAddress = new ArrayList<Integer>();
        entityPlanetAddress.add(24);
        entityPlanetAddress.add(7);
        entityPlanetAddress.add(17);
        entityPlanetAddress.add(28);
        entityPlanetAddress.add(3);
        entityPlanetAddress.add(21);
        entityPlanetAddress.add(0);
        theAddresses.add(new Address("Entity Planet"));
        theAddresses.get(theAddresses.size() - 1).setAddress(entityPlanetAddress);

        ArrayList<Integer> erebusAddress = new ArrayList<Integer>();
        erebusAddress.add(21);
        erebusAddress.add(31);
        erebusAddress.add(3);
        erebusAddress.add(26);
        erebusAddress.add(22);
        erebusAddress.add(37);
        erebusAddress.add(0);
        theAddresses.add(new Address("Erebus"));
        theAddresses.get(theAddresses.size() - 1).setAddress(erebusAddress);

        ArrayList<Integer> eurondaAddress = new ArrayList<Integer>();
        eurondaAddress.add(29);
        eurondaAddress.add(26);
        eurondaAddress.add(8);
        eurondaAddress.add(6);
        eurondaAddress.add(17);
        eurondaAddress.add(15);
        eurondaAddress.add(0);
        theAddresses.add(new Address("Euronda"));
        theAddresses.get(theAddresses.size() - 1).setAddress(eurondaAddress);

        ArrayList<Integer> p3X888Address = new ArrayList<Integer>();
        p3X888Address.add(2);
        p3X888Address.add(14);
        p3X888Address.add(3);
        p3X888Address.add(4);
        p3X888Address.add(18);
        p3X888Address.add(36);
        p3X888Address.add(0);
        theAddresses.add(new Address("P3X-888"));
        theAddresses.get(theAddresses.size() - 1).setAddress(p3X888Address);

        ArrayList<Integer> GoauldShip = new ArrayList<Integer>();
        GoauldShip.add(2);
        GoauldShip.add(38);
        GoauldShip.add(15);
        GoauldShip.add(7);
        GoauldShip.add(9);
        GoauldShip.add(11);
        GoauldShip.add(0);
        theAddresses.add(new Address("Goa'uld Ship", GoauldShip));

        ArrayList<Integer> Hanka = new ArrayList<Integer>();
        Hanka.add(29);
        Hanka.add(5);
        Hanka.add(23);
        Hanka.add(11);
        Hanka.add(13);
        Hanka.add(18);
        Hanka.add(0);
        theAddresses.add(new Address("Hanka", Hanka));

        ArrayList<Integer> Hadante = new ArrayList<Integer>();
        Hadante.add(1);
        Hadante.add(16);
        Hadante.add(33);
        Hadante.add(17);
        Hadante.add(34);
        Hadante.add(5);
        Hadante.add(0);
        theAddresses.add(new Address("Hadante", Hadante));

        ArrayList<Integer> Hala = new ArrayList<Integer>();
        Hala.add(2);
        Hala.add(3);
        Hala.add(29);
        Hala.add(38);
        Hala.add(34);
        Hala.add(14);
        Hala.add(21);
        Hala.add(0);
        theAddresses.add(new Address("Hala", Hala));

        ArrayList<Integer> HarlansPlanet = new ArrayList<Integer>();
        HarlansPlanet.add(21);
        HarlansPlanet.add(16);
        HarlansPlanet.add(28);
        HarlansPlanet.add(3);
        HarlansPlanet.add(25);
        HarlansPlanet.add(2);
        HarlansPlanet.add(0);
        theAddresses.add(new Address("Harlan's Planet", HarlansPlanet));

        ArrayList<Integer> HathorsPlanet = new ArrayList<Integer>();
        HathorsPlanet.add(2);
        HathorsPlanet.add(22);
        HathorsPlanet.add(37);
        HathorsPlanet.add(29);
        HathorsPlanet.add(9);
        HathorsPlanet.add(4);
        HathorsPlanet.add(0);
        theAddresses.add(new Address("Hathor's Planet", HathorsPlanet));
        
        ArrayList<Integer> Heliopolis = new ArrayList<Integer>();
        Heliopolis.add(26);
        Heliopolis.add(6);
        Heliopolis.add(14);
        Heliopolis.add(31);
        Heliopolis.add(12);
        Heliopolis.add(29);
        Heliopolis.add(0);
        theAddresses.add(new Address("Heliopolis", Heliopolis));

        ArrayList<Integer> lordKhonsu = new ArrayList<Integer>();
        lordKhonsu.add(29);
        lordKhonsu.add(38);
        lordKhonsu.add(15);
        lordKhonsu.add(6);
        lordKhonsu.add(7);
        lordKhonsu.add(18);
        lordKhonsu.add(0);
        theAddresses.add(new Address("Home of Lord Khonsu", lordKhonsu));

        ArrayList<Integer> juna = new ArrayList<Integer>();
        juna.add(28);
        juna.add(7);
        juna.add(17);
        juna.add(21);
        juna.add(3);
        juna.add(24);
        juna.add(0);
        theAddresses.add(new Address("Juna", juna));

        ArrayList<Integer> kTau = new ArrayList<Integer>();
        kTau.add(17);
        kTau.add(1);
        kTau.add(29);
        kTau.add(11);
        kTau.add(25);
        kTau.add(32);
        kTau.add(0);
        theAddresses.add(new Address("K'Tau", kTau));

        ArrayList<Integer> kalanaAddress = new ArrayList<Integer>();
        kalanaAddress.add(5);
        kalanaAddress.add(15);
        kalanaAddress.add(7);
        kalanaAddress.add(2);
        kalanaAddress.add(25);
        kalanaAddress.add(24);
        kalanaAddress.add(0);
        theAddresses.add(new Address("Kalana", kalanaAddress));

        ArrayList<Integer> kelownaAddress = new ArrayList<Integer>();
        kelownaAddress.add(12);
        kelownaAddress.add(7);
        kelownaAddress.add(15);
        kelownaAddress.add(14);
        kelownaAddress.add(24);
        kelownaAddress.add(21);
        kelownaAddress.add(0);
        theAddresses.add(new Address("Kelowna", kelownaAddress));

        ArrayList<Integer> khebAddress = new ArrayList<Integer>();
        khebAddress.add(25);
        khebAddress.add(34);
        khebAddress.add(5);
        khebAddress.add(7);
        khebAddress.add(22);
        khebAddress.add(13);
        khebAddress.add(0);
        theAddresses.add(new Address("Kheb", khebAddress));

        ArrayList<Integer> kloreShipAddress = new ArrayList<Integer>();
        kloreShipAddress.add(12);
        kloreShipAddress.add(37);
        kloreShipAddress.add(14);
        kloreShipAddress.add(7);
        kloreShipAddress.add(27);
        kloreShipAddress.add(11);
        kloreShipAddress.add(0);
        theAddresses.add(new Address("Klorel's Ship", kloreShipAddress));

        ArrayList<Integer> p3x797Address = new ArrayList<Integer>();
        p3x797Address.add(25);
        p3x797Address.add(1);
        p3x797Address.add(30);
        p3x797Address.add(23);
        p3x797Address.add(9);
        p3x797Address.add(31);
        p3x797Address.add(0);
        theAddresses.add(new Address("P3X-797", p3x797Address));

        ArrayList<Integer> loknakoPlanet1Address = new ArrayList<Integer>();
        loknakoPlanet1Address.add(35);
        loknakoPlanet1Address.add(30);
        loknakoPlanet1Address.add(34);
        loknakoPlanet1Address.add(11);
        loknakoPlanet1Address.add(28);
        loknakoPlanet1Address.add(31);
        loknakoPlanet1Address.add(0);
        theAddresses.add(new Address("Lok'nako Planet: P2D-277", loknakoPlanet1Address));

        ArrayList<Integer> loknakoPlanet2Address = new ArrayList<Integer>();
        loknakoPlanet2Address.add(23);
        loknakoPlanet2Address.add(8);
        loknakoPlanet2Address.add(20);
        loknakoPlanet2Address.add(33);
        loknakoPlanet2Address.add(29);
        loknakoPlanet2Address.add(6);
        loknakoPlanet2Address.add(0);
        theAddresses.add(new Address("Lok'nako Planet 2: P2T-2PT", loknakoPlanet2Address));

        ArrayList<Integer> loknakoPlanet3Address = new ArrayList<Integer>();
        loknakoPlanet3Address.add(21);
        loknakoPlanet3Address.add(26);
        loknakoPlanet3Address.add(33);
        loknakoPlanet3Address.add(13);
        loknakoPlanet3Address.add(3);
        loknakoPlanet3Address.add(18);
        loknakoPlanet3Address.add(0);
        theAddresses.add(new Address("Lok'nako Planet 3: P9T-491", loknakoPlanet3Address));

        ArrayList<Integer> loknakoPlanet4Address = new ArrayList<Integer>();
        loknakoPlanet4Address.add(21);
        loknakoPlanet4Address.add(28);
        loknakoPlanet4Address.add(10);
        loknakoPlanet4Address.add(25);
        loknakoPlanet4Address.add(9);
        loknakoPlanet4Address.add(16);
        loknakoPlanet4Address.add(0);
        theAddresses.add(new Address("Lok'nako Planet 4", loknakoPlanet4Address));

        ArrayList<Integer> loknakoPlanet5Address = new ArrayList<Integer>();
        loknakoPlanet5Address.add(9);
        loknakoPlanet5Address.add(4);
        loknakoPlanet5Address.add(18);
        loknakoPlanet5Address.add(19);
        loknakoPlanet5Address.add(24);
        loknakoPlanet5Address.add(36);
        loknakoPlanet5Address.add(0);
        theAddresses.add(new Address("Lok'nako Planet 5", loknakoPlanet5Address));

        ArrayList<Integer> loknakoPlanet6Address = new ArrayList<Integer>();
        loknakoPlanet6Address.add(21);
        loknakoPlanet6Address.add(2);
        loknakoPlanet6Address.add(23);
        loknakoPlanet6Address.add(14);
        loknakoPlanet6Address.add(33);
        loknakoPlanet6Address.add(31);
        loknakoPlanet6Address.add(0);
        theAddresses.add(new Address("Lok'nako Planet 6: P2X-311", loknakoPlanet6Address));

        ArrayList<Integer> madronaAddress = new ArrayList<Integer>();
        madronaAddress.add(26);
        madronaAddress.add(31);
        madronaAddress.add(33);
        madronaAddress.add(35);
        madronaAddress.add(19);
        madronaAddress.add(28);
        madronaAddress.add(0);
        theAddresses.add(new Address("Madrona", madronaAddress));

        ArrayList<Integer> MartinHomeworld = new ArrayList<Integer>();
        MartinHomeworld.add(23);
        MartinHomeworld.add(11);
        MartinHomeworld.add(31);
        MartinHomeworld.add(21);
        MartinHomeworld.add(10);
        MartinHomeworld.add(33);
        MartinHomeworld.add(0);
        theAddresses.add(new Address("Martin's Homeworld", MartinHomeworld));

        ArrayList<Integer> MerlinLab = new ArrayList<Integer>();
        MerlinLab.add(25);
        MerlinLab.add(16);
        MerlinLab.add(30);
        MerlinLab.add(7);
        MerlinLab.add(37);
        MerlinLab.add(14);
        MerlinLab.add(0);
        theAddresses.add(new Address("Merlin's Lab", MerlinLab));

        ArrayList<Integer> MerlinWeapon = new ArrayList<Integer>();
        MerlinWeapon.add(29);
        MerlinWeapon.add(18);
        MerlinWeapon.add(33);
        MerlinWeapon.add(8);
        MerlinWeapon.add(32);
        MerlinWeapon.add(17);
        MerlinWeapon.add(0);
        theAddresses.add(new Address("Merlin's Weapon", MerlinWeapon));

        ArrayList<Integer> Nasya = new ArrayList<Integer>();
        Nasya.add(17);
        Nasya.add(21);
        Nasya.add(33);
        Nasya.add(15);
        Nasya.add(19);
        Nasya.add(20);
        Nasya.add(0);
        theAddresses.add(new Address("Nasya", Nasya));

        ArrayList<Integer> NoahPlanet = new ArrayList<Integer>();
        NoahPlanet.add(2);
        NoahPlanet.add(29);
        NoahPlanet.add(11);
        NoahPlanet.add(22);
        NoahPlanet.add(32);
        NoahPlanet.add(15);
        NoahPlanet.add(0);
        theAddresses.add(new Address("Noah's Planet", NoahPlanet));

        ArrayList<Integer> NoxPlanet = new ArrayList<Integer>();
        NoxPlanet.add(31);
        NoxPlanet.add(17);
        NoxPlanet.add(11);
        NoxPlanet.add(22);
        NoxPlanet.add(6);
        NoxPlanet.add(26);
        NoxPlanet.add(0);
        theAddresses.add(new Address("Nox Planet", NoxPlanet));

        ArrayList<Integer> Oannes = new ArrayList<Integer>();
        Oannes.add(3);
        Oannes.add(28);
        Oannes.add(9);
        Oannes.add(16);
        Oannes.add(35);
        Oannes.add(24);
        Oannes.add(0);
        theAddresses.add(new Address("Oannes", Oannes));

        ArrayList<Integer> Othalla = new ArrayList<Integer>();
        Othalla.add(10);
        Othalla.add(26);
        Othalla.add(22);
        Othalla.add(15);
        Othalla.add(32);
        Othalla.add(2);
        Othalla.add(8);
        Othalla.add(0);
        theAddresses.add(new Address("Othalla", Othalla));

        ArrayList<Integer> P1B3R2 = new ArrayList<Integer>();
        P1B3R2.add(13);
        P1B3R2.add(6);
        P1B3R2.add(10);
        P1B3R2.add(8);
        P1B3R2.add(27);
        P1B3R2.add(24);
        P1B3R2.add(0);
        theAddresses.add(new Address("P1B-3R2", P1B3R2));

        ArrayList<Integer> P2A347 = new ArrayList<Integer>();
        P2A347.add(4);
        P2A347.add(34);
        P2A347.add(29);
        P2A347.add(6);
        P2A347.add(5);
        P2A347.add(19);
        P2A347.add(0);
        theAddresses.add(new Address("P2A-347", P2A347));

        ArrayList<Integer> P2T2W3 = new ArrayList<Integer>();
        P2T2W3.add(37);
        P2T2W3.add(28);
        P2T2W3.add(10);
        P2T2W3.add(25);
        P2T2W3.add(9);
        P2T2W3.add(16);
        P2T2W3.add(0);
        theAddresses.add(new Address("P2T-2W3", P2T2W3));

        ArrayList<Integer> P2X555 = new ArrayList<Integer>();
        P2X555.add(27);
        P2X555.add(7);
        P2X555.add(15);
        P2X555.add(32);
        P2X555.add(12);
        P2X555.add(30);
        P2X555.add(0);
        theAddresses.add(new Address("P2X-555", P2X555));

        ArrayList<Integer> P3R118 = new ArrayList<Integer>();
        P3R118.add(7);
        P3R118.add(5);
        P3R118.add(12);
        P3R118.add(37);
        P3R118.add(22);
        P3R118.add(36);
        P3R118.add(0);
        theAddresses.add(new Address("P3R-118", P3R118));

        ArrayList<Integer> P3T761 = new ArrayList<Integer>();
        P3T761.add(32);
        P3T761.add(28);
        P3T761.add(12);
        P3T761.add(24);
        P3T761.add(11);
        P3T761.add(31);
        P3T761.add(0);
        theAddresses.add(new Address("P3T-761", P3T761));

        ArrayList<Integer> P3T762 = new ArrayList<Integer>();
        P3T762.add(21);
        P3T762.add(2);
        P3T762.add(37);
        P3T762.add(22);
        P3T762.add(33);
        P3T762.add(31);
        P3T762.add(0);
        theAddresses.add(new Address("P3T-762", P3T762));

        ArrayList<Integer> P3W451 = new ArrayList<Integer>();
        P3W451.add(8);
        P3W451.add(1);
        P3W451.add(26);
        P3W451.add(14);
        P3W451.add(36);
        P3W451.add(19);
        P3W451.add(0);
        theAddresses.add(new Address("P3W-451", P3W451));

        ArrayList<Integer> P3X110 = new ArrayList<Integer>();
        P3X110.add(30);
        P3X110.add(26);
        P3X110.add(1);
        P3X110.add(6);
        P3X110.add(22);
        P3X110.add(10);
        P3X110.add(0);
        theAddresses.add(new Address("P3X-110", P3X110));

        ArrayList<Integer> P3X116 = new ArrayList<Integer>();
        P3X116.add(20);
        P3X116.add(12);
        P3X116.add(25);
        P3X116.add(16);
        P3X116.add(30);
        P3X116.add(8);
        P3X116.add(0);
        theAddresses.add(new Address("P3X-116", P3X116));

        ArrayList<Integer> P3X118 = new ArrayList<Integer>();
        P3X118.add(8);
        P3X118.add(25);
        P3X118.add(33);
        P3X118.add(36);
        P3X118.add(16);
        P3X118.add(20);
        P3X118.add(0);
        theAddresses.add(new Address("P3X-118", P3X118));

        ArrayList<Integer> P3X289 = new ArrayList<Integer>();
        P3X289.add(14);
        P3X289.add(31);
        P3X289.add(17);
        P3X289.add(2);
        P3X289.add(33);
        P3X289.add(9);
        P3X289.add(0);
        theAddresses.add(new Address("P3X-289", P3X289));

        ArrayList<Integer> P3X562 = new ArrayList<Integer>();
        P3X562.add(2);
        P3X562.add(27);
        P3X562.add(8);
        P3X562.add(34);
        P3X562.add(37);
        P3X562.add(14);
        P3X562.add(0);
        theAddresses.add(new Address("P3X-562", P3X562));

        ArrayList<Integer> P4A771 = new ArrayList<Integer>();
        P4A771.add(26);
        P4A771.add(34);
        P4A771.add(14);
        P4A771.add(31);
        P4A771.add(11);
        P4A771.add(29);
        P4A771.add(0);
        theAddresses.add(new Address("P4A-771", P4A771));

        ArrayList<Integer> P4F08T = new ArrayList<Integer>();
        P4F08T.add(36);
        P4F08T.add(6);
        P4F08T.add(10);
        P4F08T.add(19);
        P4F08T.add(8);
        P4F08T.add(29);
        P4F08T.add(0);
        theAddresses.add(new Address("P4F-08T", P4F08T));

        ArrayList<Integer> P4S42Q = new ArrayList<Integer>();
        P4S42Q.add(35);
        P4S42Q.add(14);
        P4S42Q.add(25);
        P4S42Q.add(31);
        P4S42Q.add(7);
        P4S42Q.add(4);
        P4S42Q.add(0);
        theAddresses.add(new Address("P4S-42Q", P4S42Q));

        ArrayList<Integer> P4X103 = new ArrayList<Integer>();
        P4X103.add(26);
        P4X103.add(3);
        P4X103.add(33);
        P4X103.add(7);
        P4X103.add(17);
        P4X103.add(35);
        P4X103.add(0);
        theAddresses.add(new Address("P4X-103", P4X103));

        ArrayList<Integer> P4X501 = new ArrayList<Integer>();
        P4X501.add(3);
        P4X501.add(25);
        P4X501.add(22);
        P4X501.add(14);
        P4X501.add(11);
        P4X501.add(24);
        P4X501.add(0);
        theAddresses.add(new Address("P4X-501", P4X501));

        ArrayList<Integer> P2D4Z2 = new ArrayList<Integer>();
        P2D4Z2.add(31);
        P2D4Z2.add(13);
        P2D4Z2.add(14);
        P2D4Z2.add(34);
        P2D4Z2.add(30);
        P2D4Z2.add(7);
        P2D4Z2.add(0);
        theAddresses.add(new Address("P2D-4Z2", P2D4Z2));

        ArrayList<Integer> P5T4H2 = new ArrayList<Integer>();
        P5T4H2.add(7);
        P5T4H2.add(31);
        P5T4H2.add(22);
        P5T4H2.add(13);
        P5T4H2.add(36);
        P5T4H2.add(3);
        P5T4H2.add(0);
        theAddresses.add(new Address("P5T-4H2", P5T4H2));

        ArrayList<Integer> P5X284 = new ArrayList<Integer>();
        P5X284.add(21);
        P5X284.add(7);
        P5X284.add(15);
        P5X284.add(1);
        P5X284.add(28);
        P5X284.add(36);
        P5X284.add(0);
        theAddresses.add(new Address("P5X-284", P5X284));

        ArrayList<Integer> P6T9DA = new ArrayList<Integer>();
        P6T9DA.add(16);
        P6T9DA.add(36);
        P6T9DA.add(1);
        P6T9DA.add(9);
        P6T9DA.add(25);
        P6T9DA.add(4);
        P6T9DA.add(0);
        theAddresses.add(new Address("P6T-9DA", P6T9DA));

        ArrayList<Integer> P7J1P3 = new ArrayList<Integer>();
        P7J1P3.add(23);
        P7J1P3.add(33);
        P7J1P3.add(17);
        P7J1P3.add(3);
        P7J1P3.add(16);
        P7J1P3.add(1);
        P7J1P3.add(0);
        theAddresses.add(new Address("P7J-1P3", P7J1P3));

        ArrayList<Integer> p8c909List = new ArrayList<Integer>();
        p8c909List.add(36);
        p8c909List.add(27);
        p8c909List.add(24);
        p8c909List.add(14);
        p8c909List.add(17);
        p8c909List.add(15);
        p8c909List.add(0);
        theAddresses.add(new Address("P8C-909", p8c909List));

        ArrayList<Integer> p9q281List = new ArrayList<Integer>();
        p9q281List.add(31);
        p9q281List.add(25);
        p9q281List.add(22);
        p9q281List.add(26);
        p9q281List.add(6);
        p9q281List.add(28);
        p9q281List.add(0);
        theAddresses.add(new Address("P9Q-281", p9q281List));

        ArrayList<Integer> p9x602 = new ArrayList<Integer>();
        p9x602.add(36);
        p9x602.add(26);
        p9x602.add(19);
        p9x602.add(29);
        p9x602.add(30);
        p9x602.add(14);
        p9x602.add(0);
        theAddresses.add(new Address("P9X-602", p9x602));

        ArrayList<Integer> p26007 = new ArrayList<Integer>();
        p26007.add(3);
        p26007.add(17);
        p26007.add(21);
        p26007.add(37);
        p26007.add(12);
        p26007.add(27);
        p26007.add(0);
        theAddresses.add(new Address("P26-007", p26007));

        ArrayList<Integer> p3435jList = new ArrayList<Integer>();
        p3435jList.add(37);
        p3435jList.add(8);
        p3435jList.add(27);
        p3435jList.add(14);
        p3435jList.add(34);
        p3435jList.add(2);
        p3435jList.add(0);
        theAddresses.add(new Address("P34-35J", p3435jList));

        ArrayList<Integer> pa5ad1Address = new ArrayList<Integer>();
        pa5ad1Address.add(1);
        pa5ad1Address.add(16);
        pa5ad1Address.add(33);
        pa5ad1Address.add(17);
        pa5ad1Address.add(28);
        pa5ad1Address.add(24);
        pa5ad1Address.add(0);
        theAddresses.add(new Address("PA5-AD1", pa5ad1Address));

        ArrayList<Integer> pb5926Address = new ArrayList<Integer>();
        pb5926Address.add(11);
        pb5926Address.add(35);
        pb5926Address.add(22);
        pb5926Address.add(27);
        pb5926Address.add(6);
        pb5926Address.add(26);
        pb5926Address.add(0);
        theAddresses.add(new Address("PB5-926", pb5926Address));

        ArrayList<Integer> pf48t2Address = new ArrayList<Integer>();
        pf48t2Address.add(5);
        pf48t2Address.add(38);
        pf48t2Address.add(23);
        pf48t2Address.add(33);
        pf48t2Address.add(32);
        pf48t2Address.add(28);
        pf48t2Address.add(0);
        theAddresses.add(new Address("PF4-8T2", pf48t2Address));

        ArrayList<Integer> pj2455Address = new ArrayList<Integer>();
        pj2455Address.add(38);
        pj2455Address.add(11);
        pj2455Address.add(33);
        pj2455Address.add(5);
        pj2455Address.add(9);
        pj2455Address.add(3);
        pj2455Address.add(0);
        theAddresses.add(new Address("PJ2-455", pj2455Address));

        ArrayList<Integer> replicatorHomworldAddress = new ArrayList<Integer>();
        replicatorHomworldAddress.add(7);
        replicatorHomworldAddress.add(30);
        replicatorHomworldAddress.add(25);
        replicatorHomworldAddress.add(19);
        replicatorHomworldAddress.add(22);
        replicatorHomworldAddress.add(1);
        replicatorHomworldAddress.add(0);
        theAddresses.add(new Address("Replicator Homeworld", replicatorHomworldAddress));

        ArrayList<Integer> tombPlanetAddress = new ArrayList<Integer>();
        tombPlanetAddress.add(33);
        tombPlanetAddress.add(28);
        tombPlanetAddress.add(17);
        tombPlanetAddress.add(21);
        tombPlanetAddress.add(24);
        tombPlanetAddress.add(32);
        tombPlanetAddress.add(0);
        theAddresses.add(new Address("Tomb Planet", tombPlanetAddress));

        ArrayList<Integer> pn7f4eAddress = new ArrayList<Integer>();
        pn7f4eAddress.add(13);
        pn7f4eAddress.add(26);
        pn7f4eAddress.add(22);
        pn7f4eAddress.add(2);
        pn7f4eAddress.add(30);
        pn7f4eAddress.add(14);
        pn7f4eAddress.add(0);
        theAddresses.add(new Address("PN7-F4E", pn7f4eAddress));

        ArrayList<Integer> taonasPraklarushAddress = new ArrayList<Integer>();
        taonasPraklarushAddress.add(28);
        taonasPraklarushAddress.add(2);
        taonasPraklarushAddress.add(30);
        taonasPraklarushAddress.add(33);
        taonasPraklarushAddress.add(4);
        taonasPraklarushAddress.add(16);
        taonasPraklarushAddress.add(0);
        theAddresses.add(new Address("Taonas Praklarush", taonasPraklarushAddress));

        ArrayList<Integer> pt1aa1Address = new ArrayList<Integer>();
        pt1aa1Address.add(19);
        pt1aa1Address.add(7);
        pt1aa1Address.add(36);
        pt1aa1Address.add(30);
        pt1aa1Address.add(3);
        pt1aa1Address.add(26);
        pt1aa1Address.add(0);
        theAddresses.add(new Address("PT1-AA1", pt1aa1Address));

        ArrayList<Integer> pwwdacAddress = new ArrayList<Integer>();
        pwwdacAddress.add(13);
        pwwdacAddress.add(1);
        pwwdacAddress.add(29);
        pwwdacAddress.add(6);
        pwwdacAddress.add(12);
        pwwdacAddress.add(25);
        pwwdacAddress.add(0);
        theAddresses.add(new Address("PWW-DAC", pwwdacAddress));

        ArrayList<Integer> px9757Address = new ArrayList<Integer>();
        px9757Address.add(28);
        px9757Address.add(12);
        px9757Address.add(13);
        px9757Address.add(23);
        px9757Address.add(35);
        px9757Address.add(16);
        px9757Address.add(0);
        theAddresses.add(new Address("PX9-757", px9757Address));

        ArrayList<Integer> px38812Address = new ArrayList<Integer>();
        px38812Address.add(3);
        px38812Address.add(22);
        px38812Address.add(9);
        px38812Address.add(32);
        px38812Address.add(17);
        px38812Address.add(19);
        px38812Address.add(0);
        theAddresses.add(new Address("PX3-812", px38812Address));

        ArrayList<Integer> rogueNIDBaseAddress = new ArrayList<Integer>();
        rogueNIDBaseAddress.add(37);
        rogueNIDBaseAddress.add(27);
        rogueNIDBaseAddress.add(14);
        rogueNIDBaseAddress.add(34);
        rogueNIDBaseAddress.add(2);
        rogueNIDBaseAddress.add(18);
        rogueNIDBaseAddress.add(0);
        theAddresses.add(new Address("Rogue NID Base", rogueNIDBaseAddress));

        ArrayList<Integer> sahalAddress = new ArrayList<Integer>();
        sahalAddress.add(28);
        sahalAddress.add(17);
        sahalAddress.add(38);
        sahalAddress.add(37);
        sahalAddress.add(11);
        sahalAddress.add(21);
        sahalAddress.add(0);
        theAddresses.add(new Address("Sahal", sahalAddress));

        ArrayList<Integer> tartarusAddress = new ArrayList<Integer>();
        tartarusAddress.add(32);
        tartarusAddress.add(27);
        tartarusAddress.add(22);
        tartarusAddress.add(25);
        tartarusAddress.add(15);
        tartarusAddress.add(30);
        tartarusAddress.add(0);
        theAddresses.add(new Address("Tartarus", tartarusAddress));

        ArrayList<Integer> thorsHomeworldAddress = new ArrayList<Integer>();
        thorsHomeworldAddress.add(27);
        thorsHomeworldAddress.add(1);
        thorsHomeworldAddress.add(21);
        thorsHomeworldAddress.add(2);
        thorsHomeworldAddress.add(26);
        thorsHomeworldAddress.add(8);
        thorsHomeworldAddress.add(0);
        theAddresses.add(new Address("Thor's Homeworld", thorsHomeworldAddress));

        ArrayList<Integer> tiernodAddress = new ArrayList<Integer>();
        tiernodAddress.add(20);
        tiernodAddress.add(1);
        tiernodAddress.add(29);
        tiernodAddress.add(37);
        tiernodAddress.add(30);
        tiernodAddress.add(18);
        tiernodAddress.add(0);
        theAddresses.add(new Address("Tiernod", tiernodAddress));

        ArrayList<Integer> tokraPlanetAddress = new ArrayList<Integer>();
        tokraPlanetAddress.add(38);
        tokraPlanetAddress.add(8);
        tokraPlanetAddress.add(27);
        tokraPlanetAddress.add(14);
        tokraPlanetAddress.add(34);
        tokraPlanetAddress.add(2);
        tokraPlanetAddress.add(0);
        theAddresses.add(new Address("Tok'ra Planet", tokraPlanetAddress));

        ArrayList<Integer> tollanAddress = new ArrayList<Integer>();
        tollanAddress.add(5);
        tollanAddress.add(32);
        tollanAddress.add(26);
        tollanAddress.add(36);
        tollanAddress.add(10);
        tollanAddress.add(17);
        tollanAddress.add(0);
        theAddresses.add(new Address("Tollan", tollanAddress));

        ArrayList<Integer> tollanaAddress = new ArrayList<Integer>();
        tollanaAddress.add(3);
        tollanaAddress.add(28);
        tollanaAddress.add(7);
        tollanaAddress.add(21);
        tollanaAddress.add(17);
        tollanaAddress.add(24);
        tollanaAddress.add(0);
        theAddresses.add(new Address("Tollana", tollanaAddress));

        ArrayList<Integer> unnofficialSGCBaseAddress = new ArrayList<Integer>();
        unnofficialSGCBaseAddress.add(23);
        unnofficialSGCBaseAddress.add(3);
        unnofficialSGCBaseAddress.add(8);
        unnofficialSGCBaseAddress.add(16);
        unnofficialSGCBaseAddress.add(14);
        unnofficialSGCBaseAddress.add(28);
        unnofficialSGCBaseAddress.add(0);
        theAddresses.add(new Address("Unnofficial SGC Base", unnofficialSGCBaseAddress));

        ArrayList<Integer> urgosPlanetAddress = new ArrayList<Integer>();
        urgosPlanetAddress.add(32);
        urgosPlanetAddress.add(36);
        urgosPlanetAddress.add(38);
        urgosPlanetAddress.add(26);
        urgosPlanetAddress.add(9);
        urgosPlanetAddress.add(6);
        urgosPlanetAddress.add(0);
        theAddresses.add(new Address("Urgos Planet", urgosPlanetAddress));

        ArrayList<Integer> Violan = new ArrayList<Integer>();
        Violan.add(3);
        Violan.add(28);
        Violan.add(7);
        Violan.add(21);
        Violan.add(8);
        Violan.add(2);
        Violan.add(0);
        theAddresses.add(new Address("Violan", Violan));

        ArrayList<Integer> vorashAddress = new ArrayList<Integer>();
        vorashAddress.add(37);
        vorashAddress.add(8);
        vorashAddress.add(27);
        vorashAddress.add(34);
        vorashAddress.add(2);
        vorashAddress.add(36);
        vorashAddress.add(0);
        theAddresses.add(new Address("Vorash", vorashAddress));

        ArrayList<Integer> xyberAddress = new ArrayList<Integer>();
        xyberAddress.add(29);
        xyberAddress.add(31);
        xyberAddress.add(7);
        xyberAddress.add(25);
        xyberAddress.add(26);
        xyberAddress.add(24);
        xyberAddress.add(0);
        theAddresses.add(new Address("Xyber", xyberAddress));
    }// END OF INIT_ADDRESSES

}
