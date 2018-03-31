/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



/**
 *
 * @author mockl
 */
public class Simulation {
    
    private ArrayList<Match> matchList;
    private NavigationUICntl navController;
    private int userMatchNumber;
    
    public Simulation()
    {
    }
    
    public void getMatchList() //gets match list from list controller
    {
        matchList = ListController.getInstance().getTheMatchList().getTheMatchList();
    }
    
    public void getUserMatch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NavigationUI.fxml"));
            Parent root = (Parent) loader.load();
            navController = (loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        userMatchNumber = navController.getNextMatch().getMatchNumber();
        
    }
    
    /**
     * This class is designed to simulate all the matches that are not ran by the user
     * IT gets all the matches and the user matches, and simulates all the matches that 
     * are going on this week, and are not the same as the user match 
     */
    public void simulateOtherMatches()
    {
        getUserMatch(); //gets user match number so this is ignored in the ai simulation
        getMatchList();
        int numberOfMatches = (ListController.getInstance().getTheTeamList().getTheListOfTeams().size() / 2) - 1; //gets number of teams divides by 2 = number of matches minus 1 for user team match
        int currentWeek = ListController.getInstance().getTheMatchList().getCurrentWeek();
        AITeamSimulationController simulator = new AITeamSimulationController();
        
        for (int i = 0; i < matchList.size(); i++)
        {
            if((matchList.get(i).getWeek() == currentWeek) //if the match takes place in the current week and is not the user's match, simulate it
                    && !(matchList.get(i).getMatchNumber() == userMatchNumber)) 
            {
                simulator.startGame(matchList.get(i).getTeam1(), matchList.get(i).getTeam2()); //cal the simulator to simulate the two teams
            }
            
        } 
        

    }
    
}
