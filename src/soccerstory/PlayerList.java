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

        Player newPlayer = new Player( "name",   "fff",  "A", 5);
        testPlayers.add(newPlayer);
        Player newPlayer1 = new Player ("name", "fff", "M", 6);
        Player newPlayer2 = new Player("name", "fff", "D", 7);
        Player newPlayer3 = new Player("name", "fff", "G", 8);
        Player newPlayer4 = new Player("name", "1name", "A", 9);
        testPlayers.add(newPlayer1);
        testPlayers.add(newPlayer2);
        testPlayers.add(newPlayer3);
        testPlayers.add(newPlayer4);
        
        return testPlayers;
        

    }
    
    public ObservableList<Player> getUserData() 
    {
        ObservableList<Player> theNewListOfPlayers;
        List<Player> playerList = (List<Player>) getThePlayerList();
        theNewListOfPlayers= FXCollections.observableList(playerList);
        return theNewListOfPlayers;
    }
    
    public ArrayList<Player> getPlayersFromTeam(String teamName)
    {
        ArrayList<Player> theTeamsPlayers = new ArrayList<>();
        for (int i = 0; i < getThePlayerList().size(); i ++)
        {
            if(getThePlayerList().get(i).getTeamPlayFor().equals(teamName))
            {
                theTeamsPlayers.add(getThePlayerList().get(i));
                
            }
        }
        return theTeamsPlayers;
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
