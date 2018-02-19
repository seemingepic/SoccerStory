/**
 * This class is the engine behind how matches are simulated
 * I based off part of the mechanics from "not really jake" 
 * from https://stackoverflow.com/questions/1427043/soccer-simulation-for-a-game
 * There was no code taken, but he did have a very cool thought process on how
 * a simulator should work
 */
package soccerstory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    private List<String> ballLocations = 
            Arrays.asList("hGoal", "hDef", "Mid", "aDef", "aGoal");
    private int ballLocation;
    
    int homeTeamOverall;
    int awayTeamOverall;
    
    int homeTeamAttack;
    int homeTeamDefense;
    int homeTeamMidfield;
    
    int awayTeamAttack;
    int awayTeamDefense;
    int awayTeamMidfield;
    
    @FXML
    private Label homeAttackWeight;
    @FXML
    private Label homeMidfieldWeight;
    @FXML
    private Label homeDefenseWeight;
    @FXML
    private Label awayAttackWeight;
    @FXML
    private Label awayMidfieldWeight;
    @FXML
    private Label awayDefenseWeight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialSetup();

    }
    
    /**
     * This will call the methods for original setup to make code cleaner
     */
    public void initialSetup()
    {
        displayTeamNames();
        getPlayers();
        setLineUp(homeTeamPlayers, "Home");
        setLineUp(awayTeamPlayers, "Away");
        overallTest(homeTeamPlayers.get(1));
        determineWeight(homeTeamPlayers, awayTeamPlayers);
        displayStats();  
        kickOff();
    }

    /**
     * Gets the team namess from the ListController and displays them to
     * the labels on the page
     */
    public void displayTeamNames() {
        homeTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam();
        homeTeamLabel.setText(homeTeam);
        awayTeam = ListController.getInstance().getTheTeamList().getTheListOfTeams().get(1).getTeamName();
        awayTeamLabel.setText(awayTeam);
    }
    
    /**
     * This grabs the stats for each team, and then displays them on the page
     * 
     */
    public void displayStats()
    {
        String homeAttackWeightString = Integer.toString(homeTeamAttack);
        homeAttackWeight.setText(homeAttackWeightString);
        
        String homeMidfieldWeightString = Integer.toString(homeTeamMidfield);
        homeMidfieldWeight.setText(homeMidfieldWeightString);
        
        String homeDefenseWeightString = Integer.toString(homeTeamDefense);
        homeDefenseWeight.setText(homeDefenseWeightString);
        
        String awayAttackWeightString = Integer.toString(awayTeamAttack);
        awayAttackWeight.setText(awayAttackWeightString);
        
        String awayMidfieldWeightString = Integer.toString(awayTeamMidfield);
        awayMidfieldWeight.setText(awayMidfieldWeightString);
        
        String awayDefenseWeightString = Integer.toString(awayTeamDefense);
        awayDefenseWeight.setText(awayDefenseWeightString);
    }

    /**
     * When the home team scores, get the current home score and increment it
     * 
     */
    public void updateHomeScore() {
        int homeScore = Integer.parseInt(homeTeamScore.getText());
        homeScore++;
        String updatedScore = Integer.toString(homeScore);
        homeTeamScore.setText(updatedScore);
        System.out.println("Home Team Scored!");
    }

    public void updateAwayScore() {

    }

    /**
     * What happens when user clicks button
     */
    public void playGame() {
        System.out.println(homePoss);
        System.out.println(currentPoss.getName());
        fieldPosition();
        System.out.println(ballLocation);

    }
    
    /**
     * Determine what course of action to do based on field position
     */
    public void fieldPosition()
    {
        switch (ballLocation) {
            case 0: //shot position
                System.out.println("do something");
                break;
            case 1: //defense position
                System.out.println("Do something else");
                break;
            case 2:
                midfieldPossession();
                System.out.println("it works");
                break;
            case 3: //shot position
                System.out.println("Do something else");
                break;
            case 4: //defense position
                System.out.println("Do something else");
                break;
            default: //shot position
                System.out.println("do something else");
                break;
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
        if (kickOffChance > 3) //98 Percent chance home team gets it
        {
            homePoss = true;
            currentPoss = homeAttack.get(1);
            
        } else //4 percent chance away team gets it
        {
            awayPoss = true;
            currentPoss = awayAttack.get(0);
        }
        
        ballLocation = 2; 
        System.out.println("Game started!! Current ball position: " + ballLocations.get(ballLocation));
    }
    /**
     * This is the algorithm to determine how the playing of the midfield will go
     * 1. Determine random number 1-1000
     * 2. Determine who has poss
     * 3. Based on who has poss, determine success based on position weights
     * 4. Then determine how successful that possession is
     */
    private void midfieldPossession()
    {
        Random r = new Random(); //Random number gen
        int result = r.nextInt(1000 - 1) + 1; 
        if (homePoss) {
            if (result < homeTeamMidfield) { //If maintain possession
                determinePasserScore("H"); //determine how successfull
            } else {
                System.out.println("away team wins"); //if not 
                homePoss = false; //switch poss
                awayPoss = true;
                currentPoss = awayMidfield.get(0); // change current player possession
            }
        } else if (awayPoss) { //repeat same as top with opposite team
            if (result > awayTeamMidfield) {
                determinePasserScore("A");
            } else {
                System.out.println("home team wins!");
                homePoss = true;
                awayPoss = false;
                currentPoss = homeMidfield.get(0);
            }
        }

    }
    /**
     * This algorithm determines how successful the event will be,
     * Normal outcome: Move position 1
     * Somewhat rare: move forward 2 (long pass)
     * Rare outcomes: Score, lose possession and go back position
     * @param possessor 
     */
    private void determinePasserScore(String possessor)
    {
        int passerScore = currentPoss.getPassing();
        Random r = new Random();
        int passerResult = r.nextInt(passerScore - 1) + 1;

        if (passerResult == 1) {
            System.out.println("TERRIBLE FAILURE");
            if (possessor.equals("H")) {
                ballLocation = 4;
            } else {
                ballLocation = 1;
            }
        } else if (passerResult >= 2 && passerResult < 50) {
            System.out.println("Successful pass forward!");
            if (possessor.equals("H")) {
                ballLocation++;
            } else {
                ballLocation--;
            }
        } else if (passerResult >= 50 && passerResult <= 98) {
            System.out.println("Move to shot position!");
            if (possessor.equals("H")) {
                ballLocation = 4;
            } else {
                ballLocation = 1;
            }
        } else {
            System.out.println("LONG SHOT SCORED");
            if (possessor.equals("H")) {
                updateHomeScore();
            }
        }
    }

    /**
     * Gets the players from the listcontroller and sets them as a reference
     */
    private void getPlayers() {
        homeTeamPlayers = ListController.getInstance().getThePlayerList().getPlayersFromTeam(homeTeam);
        awayTeamPlayers = ListController.getInstance().getThePlayerList().getPlayersFromTeam(awayTeam);
    }

    /**
     * Seperates the team into attack,mid,defense and goalie
     * @param team
     * @param homeOrAway 
     */
    private void setLineUp(ArrayList<Player> team, String homeOrAway) {
        for (int i = 0; i < team.size(); i++) {
            if (homeOrAway.equals("Home")) {
                switch (team.get(i).getPosition()) {
                    case "A": //Atackers
                        homeAttack.add(team.get(i));
                        break;
                    case "M": //Midfields
                        homeMidfield.add(team.get(i));
                        break;
                    case "D": //Defense
                        homeDefense.add(team.get(i));
                        break;
                    default: //Goalie
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

    /**
     * Determines how good a team is at doing something
     * This is designed to get an accurate representation of how good a team is
     * and will be used a lot in making the simulation based off who has the
     * better team
     * @param team
     * @param homeOrAway 
     */
    private void determineWeight(ArrayList<Player> homeTeam, ArrayList<Player> awayTeam) {
        
        double homeAttackerPoints = 0, homeMidfieldPoints = 0, homeDefenderPoints = 0,
                homeGoaliePoints = 0, awayAttackerPoints = 0, awayMidfieldPoints = 0, 
                awayDefenderPoints = 0, awayGoaliePoints = 0;

        for (int i = 0; i < homeTeam.size(); i++) { //Go through home team and calculate total overall for each position
            switch (homeTeam.get(i).getPosition()) {
                case "A":
                    homeAttackerPoints += homeTeam.get(i).overall();
                    break;
                case "M":
                    homeMidfieldPoints += homeTeam.get(i).overall();
                    break;
                case "D":
                    homeDefenderPoints += homeTeam.get(i).overall();
                    break;
                default:
                    homeGoaliePoints += homeTeam.get(i).overall();
                    break;
            }
        }
        for (int i = 0; i < awayTeam.size(); i++) { //Go through away team and calculate total overall for each position
            switch (awayTeam.get(i).getPosition()) {
                case "A":
                    awayAttackerPoints += awayTeam.get(i).overall();
                    break;
                case "M":
                    awayMidfieldPoints += awayTeam.get(i).overall();
                    break;
                case "D":
                    awayDefenderPoints += awayTeam.get(i).overall();
                    break;
                default:
                    awayGoaliePoints += awayTeam.get(i).overall();
                    break;
            }
        }
        
        calculateWeight(homeAttackerPoints, homeDefenderPoints, homeMidfieldPoints,
                awayAttackerPoints, awayDefenderPoints, awayMidfieldPoints);

    }
    
    /**
     * Calculates what team is better at what positions and scores them
     * out of a 1000 compared to the other team
     * @param homeAttackPoints
     * @param homeDefensePoints
     * @param homeMidfieldPoints
     * @param awayAttackPoints
     * @param awayDefensePoints
     * @param awayMidfieldPoints 
     */
    private void calculateWeight(double homeAttackPoints, double homeDefensePoints, 
            double homeMidfieldPoints, double awayAttackPoints, double awayDefensePoints,
            double awayMidfieldPoints){
        homeTeamAttack = (int)(((homeAttackPoints) / (homeAttackPoints + awayAttackPoints)) * 1000);
        homeTeamMidfield = (int)(((homeMidfieldPoints) / (homeMidfieldPoints + awayMidfieldPoints))* 1000);
        homeTeamDefense = (int)(((homeDefensePoints) / (homeDefensePoints + awayDefensePoints)) * 1000);
        //The code above calculates the realtive skill of the home team, and the code below gets the relative score for the away team
        awayTeamAttack = 1000 - homeTeamAttack;
        awayTeamMidfield = 1000 - homeTeamMidfield;
        awayTeamDefense = 1000 - homeTeamDefense;
    }

    public void overallTest(Player player)
    {
        System.out.println(player.overall());
    }

    @FXML
    private void shoot(ActionEvent event) {
        playGame();
    }
}
