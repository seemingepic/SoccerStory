/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mockl
 */
public class MatchUIController implements Initializable {

    @FXML
    private Label homeTeamLabel;
    @FXML
    private Label awayTeamLabel;
    
    private String homeTeam;
    private String awayTeam;
    @FXML
    private Label homeTeamScore;
    @FXML
    private Label awayTeamScore;
    
    private ArrayList<Player> homeTeamPlayers;
    private ArrayList<Player> awayTeamPlayers;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayTeamNames();
        getPlayers();
        playGame();
    }    
    
    public void displayTeamNames()
    {
        homeTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam();
        homeTeamLabel.setText(homeTeam);
        awayTeam = ListController.getInstance().getTheTeamList().getTheListOfTeams().get(1).getTeamName();
        awayTeamLabel.setText(awayTeam);
    }
    
    public void updateHomeScore()
    {
        int homeScore = Integer.parseInt(homeTeamScore.getText());
        homeScore++;
        String updatedScore = Integer.toString(homeScore);
        homeTeamScore.setText(updatedScore);
    }
    
    public void updateAwayScore()
    {
        
    }
    
    public void playGame()
    {
         if (shotTest(homeTeamPlayers.get(1), awayTeamPlayers.get(0)).equals("Score"))
            {
                updateHomeScore();     
            }
    }
    
    private void getPlayers()
    {
        homeTeamPlayers = ListController.getInstance().getThePlayerList().getPlayersFromTeam(homeTeam);
        awayTeamPlayers = ListController.getInstance().getThePlayerList().getPlayersFromTeam(awayTeam);
    }

    private String shotTest(Player shooter, Player goalie)
    {
        int shotSkill = shooter.getShooting();
        int goalieSkill = goalie.getGoalie();
        int lowestRandom = 1;
        int highestRandom = 100;
        int result = 0;
        int highestShotResult = 0;
        int highestGoalieResult = 0;
        
        for (int i = 0; i < 3; i++){
            Random r = new Random();
            result = r.nextInt(highestRandom - lowestRandom) + lowestRandom;
            result = result * shotSkill;
            if(result > highestShotResult){
                highestShotResult = result;
            }
            System.out.println(result);
        }
        
        for (int i = 0; i < 3; i++){
            Random r = new Random();
            result = r.nextInt(highestRandom - lowestRandom) + lowestRandom;
            result = result * goalieSkill;
            if(result > highestGoalieResult){
                highestGoalieResult = result;
            }
            System.out.println(result);
        }
        if (highestShotResult > highestGoalieResult)
            return "Score";
        else
            return "No score";
    }
}

