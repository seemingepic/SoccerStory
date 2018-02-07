/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mockl
 */
public class PlayerList {
    
    private ObservableList<Player> thePlayerList;
    
    public PlayerList()
    {
        this.thePlayerList = testPlayers();
    }

    /**
     * create test players for testing purposes
     * @return 
     */
    public ObservableList<Player> testPlayers()
    {
        ObservableList<Player> testPlayers = FXCollections.observableArrayList();
        for(int i = 0; i < 10; i ++)
        {
            Player newPlayer = new Player( "name",   "team",  "position", 5);
            testPlayers.add(newPlayer);
        }
        return testPlayers;   
    }
    
    public ObservableList<Player> getUserData() 
    {
        ObservableList<Player> theNewListOfPlayers;
        List<Player> playerList = (List<Player>) getThePlayerList();
        theNewListOfPlayers= FXCollections.observableList(playerList);
        return theNewListOfPlayers;
    }

    /**
     * @return the thePlayerList
     */
    public ObservableList<Player> getThePlayerList() {
        return thePlayerList;
    }

    /**
     * @param thePlayerList the thePlayerList to set
     */
    public void setThePlayerList(ObservableList<Player> thePlayerList) {
        this.thePlayerList = thePlayerList;
    }


    
    
    
    
}
