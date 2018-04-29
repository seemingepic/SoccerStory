package soccerstory;

import com.github.javafaker.Faker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This is where all of the players are stored into
 * This class is used to send the list of players to other classes
 * @author mockl
 */
public class PlayerList implements Serializable{
    
    private ObservableList<Player> thePlayerList;
    
    public PlayerList()
    {
        this.thePlayerList = setUpPlayers();
       // this.thePlayerList = testPlayers();
    }

    /**
     * Generates random players for each team
     * 
     * @return - List of players
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
                                lastName()).getPlayer(); //Uses faker library to create random name which is the identifier for the player 
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
 * Turn data into observablelist so it can be used in 
 * an observableTable
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
     * @param teamName -- What team the players are from
     * @return  - List of players from that team 
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
     * @param teamName --Team where the players are from 
     * @return -- Get only the starters from that team 
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
    
    /**
     * Changes player to either starter or benched
     * @param name - name of player 
     */
    public void changePlayerBench(String name)
    {
        for (int i = 0; i < getThePlayerList().size(); i ++)
        {
            if(getThePlayerList().get(i).getName().equals(name))
            {
                if(getThePlayerList().get(i).isBenched()) //if benched unbench
                {
                    getThePlayerList().get(i).setBenched(false);
                }
                else //bench player 
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
     * Purpose is to turn the observable list of players to an arraylist so it can be used in other classes 
     * @return -- ArrayList of players 
     */
    public ArrayList<Player> getTheArrayPlayerList()
    {
        List<Player> theNewPlayerList = getThePlayerList().stream().collect(Collectors.toList());
        ArrayList<Player> theArrayPlayerList = new ArrayList<Player>(theNewPlayerList);
        return theArrayPlayerList;  
    }
    
    /**
     * Saves the list based on an arraylist that is given 
     * @param thePlayerList -- ArrayList<Player> 
     */
    public void setThePlayerListFromArray(ArrayList<Player> thePlayerList)
    {
        ObservableList<Player> theNewPlayerList = FXCollections.observableArrayList(thePlayerList);
        this.thePlayerList = theNewPlayerList;
 
    }

    /**
     * @param thePlayerList the thePlayerList to set
     */
    public void setThePlayerList(ObservableList<Player> thePlayerList) {
        this.thePlayerList = thePlayerList;
    }
    
    /**
     * This generates a random stat to be used in creating the players 
     * @return  -- random integer from 30-90
     */
    private int randomStat()
    {
        int max = 90;
        int min = 30;
        
        Random r = new Random();
        int randomNum = r.nextInt((max-min) + 1) + min;
        return randomNum;
    }  
}
