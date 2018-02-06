/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.util.ArrayList;

/**
 *
 * @author mockl
 */
public class PlayerList {
    
    private ArrayList<Player> thePlayerList;
    
    public PlayerList()
    {
        this.thePlayerList = testPlayers();
    }

    /**
     * create test players for testing purposes
     * @return 
     */
    private ArrayList<Player> testPlayers()
    {
        ArrayList<Player> testPlayers = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Player newPlayer = new Player(i + "name",  "team" + i, i + "coach", i);
            testPlayers.add(newPlayer);
        }
        return testPlayers;
        
    }

    /**
     * @return the thePlayerList
     */
    private ArrayList<Player> getThePlayerList() {
        return thePlayerList;
    }

    /**
     * @param thePlayerList the thePlayerList to set
     */
    private void setThePlayerList(ArrayList<Player> thePlayerList) {
        this.thePlayerList = thePlayerList;
    }


    
    
    
    
}
