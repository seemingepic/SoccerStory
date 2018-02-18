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
import javafx.event.ActionEvent;
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

    private boolean homePoss;
    private boolean awayPoss;
    private Player currentPoss;

    private ArrayList<Player> awayMidfield = new ArrayList<>();
    private ArrayList<Player> awayDefense = new ArrayList<>();
    private Player awayGoalie;
    private ArrayList<Player> awayAttack = new ArrayList<>();

    private ArrayList<Player> homeMidfield = new ArrayList<>();
    private ArrayList<Player> homeDefense = new ArrayList<>();
    private Player homeGoalie;
    private ArrayList<Player> homeAttack = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayTeamNames();
        getPlayers();
        setLineUp(homeTeamPlayers, "Home");
        setLineUp(awayTeamPlayers, "Away");
        overallTest(homeTeamPlayers.get(1));
    }

    public void displayTeamNames() {
        homeTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam();
        homeTeamLabel.setText(homeTeam);
        awayTeam = ListController.getInstance().getTheTeamList().getTheListOfTeams().get(1).getTeamName();
        awayTeamLabel.setText(awayTeam);
    }

    public void updateHomeScore() {
        int homeScore = Integer.parseInt(homeTeamScore.getText());
        homeScore++;
        String updatedScore = Integer.toString(homeScore);
        homeTeamScore.setText(updatedScore);
        System.out.println("Home Team Scored!");
    }

    public void updateAwayScore() {

    }

    public void playGame() {
        System.out.println("Game Started!");
        kickOff();
        System.out.println(homePoss);
        System.out.println(currentPoss.getName());

    }
    /**
     * This method determines if a pass will be successful or not!
     * @param passer
     * @param gettingPassedTo 
     */
    public void attemptPass(Player passer, Player gettingPassedTo)
    {
        int passerRating = passer.getPassing(); //Gets passer skill from passing player
        double randomNumber = Math.random() * passerRating;
        int passChance = (int) randomNumber; //Gets random number from 0-passing skill

        if (passChance > 25)
        {
            currentPoss = gettingPassedTo;
            System.out.println("Pass Successful!," + currentPoss.getName() + " has the ball");
        }
        
    }

    /**
     * This method determines initial possession of ball, the home team often
     * ends up with it by the way kick off works but there is a chance the enemy
     * team gets it
     */
    private void kickOff() {
        double randomNumber = Math.random() * 100;
        int kickOffChance = (int) randomNumber + 1;
        if (kickOffChance > 5) //96 Percent chance home team gets it
        {
            homePoss = true;
            currentPoss = homeAttack.get(1);
        } else //4 percent chance away team gets it
        {
            awayPoss = true;
            currentPoss = awayAttack.get(0);
        }
        
        
    }

    private void getPlayers() {
        homeTeamPlayers = ListController.getInstance().getThePlayerList().getPlayersFromTeam(homeTeam);
        awayTeamPlayers = ListController.getInstance().getThePlayerList().getPlayersFromTeam(awayTeam);
    }

    private void setLineUp(ArrayList<Player> team, String homeOrAway) {
        for (int i = 0; i < team.size(); i++) {
            if (homeOrAway.equals("Home")) {
                switch (team.get(i).getPosition()) {
                    case "A":
                        homeAttack.add(team.get(i));
                        break;
                    case "M":
                        homeMidfield.add(team.get(i));
                        break;
                    case "D":
                        homeDefense.add(team.get(i));
                        break;
                    default:
                        homeGoalie = team.get(i);
                        break;
                }
            } else {
                switch (team.get(i).getPosition()) {
                    case "A":
                        awayAttack.add(team.get(i));
                        break;
                    case "M":
                        awayMidfield.add(team.get(i));
                        break;
                    case "D":
                        awayDefense.add(team.get(i));
                        break;
                    default:
                        awayGoalie = team.get(i);
                        break;
                }
            }
        }
    }

    private String shotTest(Player shooter, Player goalie) {
        int shotSkill = shooter.getShooting();
        int goalieSkill = goalie.getGoalie();
        int lowestRandom = 1;
        int highestRandom = 100;
        int result = 0;
        int highestShotResult = 0;
        int highestGoalieResult = 0;

        for (int i = 0; i < 3; i++) {
            Random r = new Random();
            result = r.nextInt(highestRandom - lowestRandom) + lowestRandom;
            result = result * shotSkill;
            if (result > highestShotResult) {
                highestShotResult = result;
            }
        }
        for (int i = 0; i < 3; i++) {
            Random r = new Random();
            result = r.nextInt(highestRandom - lowestRandom) + lowestRandom;
            result = result * goalieSkill;
            if (result > highestGoalieResult) {
                highestGoalieResult = result;
            }
        }
        if (highestShotResult > highestGoalieResult) {
            return "Score";
        } else {
            return "No score";
        }
    }
    
    public void overallTest(Player player)
    {
        System.out.println(player.overall(player));
    }

    @FXML
    private void shoot(ActionEvent event) {
        playGame();
    }
}
