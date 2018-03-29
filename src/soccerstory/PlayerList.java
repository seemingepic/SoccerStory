/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 
 * @author mockl
 */
public class PlayerList {
    
    private ObservableList<Player> thePlayerList;
    
    public PlayerList()
    {
        this.thePlayerList = setUpPlayers();
       // this.thePlayerList = testPlayers();
    }

    /**
     * Generates random players for each team
     * 
     * @return 
     */
    private ObservableList<Player> setUpPlayers()
    {
        
        ObservableList<Team> theTeamList = ListController.getInstance().getTheTeamList().getTeamData();
        ObservableList<Player> testPlayers = FXCollections.observableArrayList();
        Faker faker = new Faker(); //generate fake data for me pls
        
        for (int i = 0; i < theTeamList.size(); i++) {
            for (int m = 0; m < 4; m++) //generate midfielders
            {
                Player testPlayer = new Player();    
                testPlayer = new PlayerBuilder().setBallskill(randomStat()).
                        setDefense(randomStat()).setSpeed(randomStat())
                        .setGoalie(randomStat())
                        .setPassing(randomStat())
                        .setShooting(randomStat())
                        .setPosition("M")
                        .setTeamPlayFor(theTeamList.get(i).getTeamName()).
                        setName(faker.name().firstName() + " " + faker.name().
                                lastName()).getPlayer();
                testPlayers.add(testPlayer);
            }

            for (int d = 0; d < 4; d++) //generate defenders
            {
                Player testPlayer = new Player();    
                testPlayer = new PlayerBuilder().setBallskill(randomStat()).
                        setDefense(randomStat()).setSpeed(randomStat())
                        .setGoalie(randomStat())
                        .setPassing(randomStat())
                        .setShooting(randomStat())
                        .setPosition("D")
                        .setTeamPlayFor(theTeamList.get(i).getTeamName()).
                        setName(faker.name().firstName() + " " + faker.name().
                                lastName()).getPlayer();
                testPlayers.add(testPlayer);
            }

            for (int a = 0; a < 2; a++) //generate attackers
            {
                Player testPlayer = new Player();    
                testPlayer = new PlayerBuilder().setBallskill(randomStat()).
                        setDefense(randomStat()).setSpeed(randomStat())
                        .setGoalie(randomStat())
                        .setPassing(randomStat())
                        .setShooting(randomStat())
                        .setPosition("A")
                        .setTeamPlayFor(theTeamList.get(i).getTeamName()).
                        setName(faker.name().firstName() + " " + faker.name().
                                lastName()).getPlayer();
                testPlayers.add(testPlayer);
            }

                Player testPlayer = new Player();    
                testPlayer = new PlayerBuilder().setBallskill(randomStat()).
                        setDefense(randomStat()).setSpeed(randomStat())
                        .setGoalie(randomStat())
                        .setPassing(randomStat())
                        .setShooting(randomStat())
                        .setPosition("G")
                        .setTeamPlayFor(theTeamList.get(i).getTeamName()).
                        setName(faker.name().firstName() + " " + faker.name().
                                lastName()).getPlayer();
                testPlayers.add(testPlayer);
        }
        return testPlayers;
    }

/**
 * Turn data into observablelist
 * @return 
 */
    public ObservableList<Player> getUserData() 
    {
        ObservableList<Player> theNewListOfPlayers;
        List<Player> playerList = (List<Player>) getThePlayerList();
        theNewListOfPlayers= FXCollections.observableList(playerList);
        return theNewListOfPlayers;
    }
    
    /**
     * Gets the specific players from each team 
     * @param teamName
     * @return 
     */
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
    
    private int randomStat()
    {
        int max = 90;
        int min = 30;
        
        Random r = new Random();
        int randomNum = r.nextInt((max-min) + 1) + min;
        return randomNum;
    }  
}
