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

        Player newPlayer = new Player( "namef",   "fff",  "A", 5);
        Player newPlayer1 = new Player ("nameff", "fff", "M", 6);
        Player newPlayer2 = new Player("nam3e", "fff", "D", 7);
        Player newPlayer3 = new Player("na5me", "fff", "G", 8);
        Player newPlayer4 = new Player("na6me", "1name", "A", 9);
        Player newPlayer6 = new Player("defender", "1name", "D", 69);
        Player newPlayer7 = new Player("goalie", "1name", "G", 69);
        Player newPlayer8 = new Player("midfield", "1name", "M", 69);
        Player newPlayer5 = new Player("not a name", "fff", "A", 11);
        newPlayer5.setBenched(true);
        testPlayers.add(newPlayer);
        testPlayers.add(newPlayer1);
        testPlayers.add(newPlayer2);
        testPlayers.add(newPlayer3);
        testPlayers.add(newPlayer4);
        testPlayers.add(newPlayer5);
        testPlayers.add(newPlayer6);
        testPlayers.add(newPlayer7);
        testPlayers.add(newPlayer8);

        
        
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
    
    public void changePlayerBench(String name)
    {
        for (int i = 0; i < getThePlayerList().size(); i ++)
        {
            if(getThePlayerList().get(i).getName().equals(name))
            {
                if(getThePlayerList().get(i).isBenched())
                {
                    getThePlayerList().get(i).setBenched(false);
                }
                else
                    getThePlayerList().get(i).setBenched(true);
            }
        }
        
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
