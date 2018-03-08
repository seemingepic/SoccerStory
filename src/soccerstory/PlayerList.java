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

        Player newPlayer = new Player( "George Bush",   "fff",  "A", 5);
        Player newPlayer5 = new Player("noName", "fff", "A", 11);
        Player newPlayer1 = new Player ("Vinny Lee", "fff", "M", 6);
        Player newPlayer9 = new Player ("Kevork", "fff", "M", 32);
        Player newPlayer10 = new Player ("Chris", "fff", "M", 67);
        Player newPlayer11 = new Player ("Josh", "fff", "M", 6);
        Player newPlayer2 = new Player("Luke VanKeuren", "fff", "D", 7);
        Player newPlayer12 = new Player("Sick bro", "fff", "D", 135);
        Player newPlayer13 = new Player("bro sick", "fff", "D", 141);
        Player newPlayer14 = new Player("Bro not sick", "fff", "D", 131);
        Player newPlayer3 = new Player("Bill Clinton", "fff", "G", 8);
        
        newPlayer1.setPassing(99);
        newPlayer3.setGoalie(99);
        newPlayer5.setShooting(99);
        newPlayer.setShooting(99);
        newPlayer2.setDefense(450);
        
        Player newPlayer4 = new Player("Microsoft", "1name", "A", 9);
        Player newPlayer19 = new Player("PNC", "1name", "A", 9);
        Player newPlayer20 = new Player("SwagTASTIC", "1name", "D", 9);
        Player newPlayer8 = new Player("LITFAM", "1name", "D", 9);
        Player newPlayer6 = new Player("Apple", "1name", "D", 69);
        Player newPlayer21 = new Player("Apple dad", "1name", "D", 69);
        Player newPlayer7 = new Player("Mouse man", "1name", "G", 69);
        Player newPlayer15 = new Player("Sick player9", "1name", "M", 69);
        Player newPlayer16 = new Player("ded boi", "1name", "M", 69);
        Player newPlayer17 = new Player("alive boi", "1name", "M", 69);
        Player newPlayer18 = new Player("PPG", "1name", "M", 69);

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
        testPlayers.add(newPlayer9);
        testPlayers.add(newPlayer10);
        testPlayers.add(newPlayer11);
        testPlayers.add(newPlayer12);
        testPlayers.add(newPlayer13);
        testPlayers.add(newPlayer14);
        testPlayers.add(newPlayer15);
        testPlayers.add(newPlayer16);
        testPlayers.add(newPlayer17);
        testPlayers.add(newPlayer18);
        testPlayers.add(newPlayer19);
        testPlayers.add(newPlayer20);
        testPlayers.add(newPlayer21);
        
        
        

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
