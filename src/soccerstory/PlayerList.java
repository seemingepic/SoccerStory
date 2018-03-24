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

        Player newPlayer = new Player( "George Bush",   "fff",  "A");
        Player newPlayer5 = new Player("noName", "fff", "A");
        Player newPlayer1 = new Player ("Vinny Lee", "fff", "M");
        Player newPlayer9 = new Player ("Kevork", "fff", "M");
        Player newPlayer10 = new Player ("Chris", "fff", "M");
        Player newPlayer11 = new Player ("Josh", "fff", "M");
        Player newPlayer2 = new Player("Luke VanKeuren", "fff", "D");
        Player newPlayer12 = new Player("Sick bro", "fff", "D");
        Player newPlayer13 = new Player("bro sick", "fff", "D");
        Player newPlayer14 = new Player("Bro not sick", "fff", "D");
        Player newPlayer3 = new Player("Bill Clinton", "fff", "G");
        Player testPlayer = new PlayerBuilder().setName("Tester").
                setTeamPlayFor("fff").setBenched(true).setPosition("M").getPlayer();
        
        //testing builder framework here ^^^^ will be used later for player shop
        
        newPlayer1.setPassing(99);
        newPlayer3.setGoalie(99);
        newPlayer5.setShooting(99);
        newPlayer.setShooting(99);
        newPlayer2.setDefense(450);
        
        testPlayers.add(testPlayer);
        
        Player newPlayer4 = new Player("Microsoft", "2name", "A");
        Player newPlayer19 = new Player("PNC", "2name", "A");
        Player newPlayer20 = new Player("SwagTASTIC", "2name", "D");
        Player newPlayer8 = new Player("LITFAM", "2name", "D");
        Player newPlayer6 = new Player("Apple", "2name", "D");
        Player newPlayer21 = new Player("Apple dad", "2name", "D");
        Player newPlayer7 = new Player("Mouse man", "2name", "G");
        Player newPlayer15 = new Player("Sick player9", "2name", "M");
        Player newPlayer16 = new Player("ded boi", "2name", "M");
        Player newPlayer17 = new Player("alive boi", "2name", "M");
        Player newPlayer18 = new Player("PPG", "2name", "M");

        newPlayer5.setBenched(false);
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
    /**
     * This method is designed to only get the starters from the
     * appropriate team to be put into the simulation
     * @param teamName
     * @return 
     */
    public ArrayList<Player> getStartersFromTeam(String teamName)
    {
        ArrayList<Player> theTeamsPlayers = new ArrayList<>();
        
        for (int i = 0; i < getThePlayerList().size(); i ++)
        {
            if(getThePlayerList().get(i).getTeamPlayFor().equals(teamName) &&
                    !getThePlayerList().get(i).isBenched())
            { // if the player is from the team and not benched
                //return an arraylist with the players
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
