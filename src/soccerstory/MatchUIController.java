/**
 * This class is the engine behind how matches are simulated
 * I based off part of the mechanics from "not really jake" 
 * from https://stackoverflow.com/questions/1427043/soccer-simulation-for-a-game
 * There was no code taken, but he did have a very cool thought process on how
 * a simulator should work
 * 
 * FIXES NEEDED: CHECK IF PLAYER IS BENCHED OR NOT!!!
 */
package soccerstory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    private boolean homePoss = true;
    private boolean awayPoss = false;
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
    int homeTeamGoalie;
    
    int awayTeamAttack;
    int awayTeamDefense;
    int awayTeamMidfield;
    int awayTeamGoalie;
    
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
    @FXML
    private Text actionTarget;
    
    private NavigationUICntl navUiCntl;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialSetup();

    }
    
    /**
     * This will call the methods for original setup to make code cleaner
     * It will only be ran once on startup
     */
    public void initialSetup()
    {
        setUpTeams(); //gets which team is home/away
        getTeams(); //Displays teamname on labels
        getPlayers(); //gets players for each team
        setLineUp(homeTeamPlayers, "Home"); //puts home team players into proper positions
        setLineUp(awayTeamPlayers, "Away"); //puts away team players into proper positions
        determineWeight(homeTeamPlayers, awayTeamPlayers); //Determines how successful each team will be
        displayStats();  //Displays initial scores
        kickOff(); //Determines ininital possession of the game
    }
    
    public void getTeams()
    {
        homeTeamLabel.setText(homeTeam); //Sets home team
        awayTeamLabel.setText(awayTeam); //sets away team name
    }
    
    private void setUpTeams()
    {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("NavigationUI.fxml"));
        Parent root = (Parent) loader.load();
        navUiCntl =loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
        Match match = navUiCntl.getNextMatch();
        
        if(navUiCntl.getHome())
        {
            homeTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam();
            awayTeam = navUiCntl.getOtherTeam();
        }
        else
        {
            awayTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam();
            homeTeam = navUiCntl.getOtherTeam();
        }
    }
    
    /**
     * This grabs the stats for each team, and then displays them on the page
     * By stats I mean the weighted score determined by the method 
     * determineWeight()
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
     * After score is updated, tell who scored
     */
    public void updateHomeScore() {
        int homeScore = Integer.parseInt(homeTeamScore.getText());
        homeScore++;
        String updatedScore = Integer.toString(homeScore);
        homeTeamScore.setText(updatedScore);
        System.out.println("Home Team Scored!");
    }

    /**
     * This method is scored when the away team is scored,
     * gets the original scored, updates it and reposts it
     */
    public void updateAwayScore() {
        int awayScore = Integer.parseInt(awayTeamScore.getText());
        awayScore++;
        String updatedScore = Integer.toString(awayScore);
        awayTeamScore.setText(updatedScore);
        System.out.println("away Team Scored!");
    }

    /**
     * What happens when user clicks button
     * This will begin the "game loop"
     */
    public void playGame() {
        fieldPosition();
    }
    
    /**
     * Determine what course of action to do based on field position
     * 
     */
    public void fieldPosition() {
        for (int i = 0; i < 90; i++) {

            switch (ballLocation) { //Where is the ball? 
                case 0: //home goal
                    shotAttempt("A", this.homeGoalie); //Have away team shoot on home goalie
                    break;
                case 1: //defense position
                    possessionChecker(this.homeTeamDefense, this.awayTeamAttack); //Determine who will win possession, home d vs away attack
                    break;
                case 2: //midfield checker
                    possessionChecker(this.homeTeamMidfield, this.awayTeamMidfield); 
                    break;
                case 3: //shot position
                    possessionChecker(this.homeTeamAttack, this.awayTeamDefense);
                    break;
                default: //defense position
                    shotAttempt("H", this.awayGoalie);
                    break;
            }
        }
    }

    /**
     * This method determines initial possession of ball, the home team often
     * ends up with it by the way kick off works but there is a chance the enemy
     * team gets it
     */
    private void kickOff() {
        double randomNumber = Math.random() * 100;
        int kickOffChance = (int) randomNumber + 1; //gets random number for kickoff success
        
        ballLocation = 2; //Sets position to middle of field 
        
        if (kickOffChance > 3) //98 Percent chance home team gets it
        { //Get who will possess the ball based on rolls
            if (homePoss)
            {
                homePoss = true;
                currentPoss = homeAttack.get(1);
            }
            else if (awayPoss)
            {
                awayPoss = true;
                currentPoss = awayAttack.get(1);
            }
                        
            
        } else //2 percent chance away team gets it
        {
            if (homePoss) {
                awayPoss = true;
                currentPoss = awayAttack.get(0);
            } else if (awayPoss) {
                homePoss = true;
                currentPoss = homeAttack.get(0);
            }
        }
        
        System.out.println("Game started!! Current ball position: " + ballLocations.get(ballLocation));
    }
    
    /**
     * Checks for who currently has possession, and will change it based
     * on field position
     */
    public void changePossessionPlayer() {
        
        Random r = new Random();
        int randomNonAttacker = r.nextInt(3);
        
        r = new Random();
        int randomAttacker = r.nextInt(2);
        
        switch (ballLocation) { //Where is the ball? 
            case 0: //home goal
                if (homePoss) {
                    currentPoss = homeDefense.get(randomNonAttacker);
                } else { //change player based on where the ball is
                    currentPoss = awayAttack.get(randomAttacker);
                }
                break;
            case 1: //defense position
                if (homePoss) {
                    currentPoss = homeDefense.get(randomNonAttacker);
                } else {
                    currentPoss = awayAttack.get(randomAttacker);
                }
                break;
            case 2: //midfield checker
                if (homePoss) {
                    currentPoss = homeMidfield.get(randomNonAttacker);
                } else {
                    currentPoss = awayMidfield.get(randomNonAttacker);
                }
                break;
            case 3: //shot position
                if (homePoss) {
                    currentPoss = homeAttack.get(randomAttacker);
                } else {
                    currentPoss = awayDefense.get(randomNonAttacker);
                }
                break;
            default: //away goal
                if (homePoss) {
                    currentPoss = homeAttack.get(randomAttacker);
                } else {
                    currentPoss = awayDefense.get(randomNonAttacker);
                }
                break;
        }

    }

        /**
     * This is the algorithm to determine how the playing of the midfield will go
     * 1. Determine random number 1-1000
     * 2. Determine who has poss
     * 3. Based on who has poss, determine success based on position weights
     * 4. Then determine how successful that possession is
     */
    private void possessionChecker(int homeTeamScore, int awayTeamScore)
    {
        Random r = new Random(); //Random number gen
        int result = r.nextInt(1000 - 1) + 1; 
        
        if (homePoss) {
            if (result < homeTeamScore) { //If maintain possession
                determinePasserScore("H"); //determine how successfull

            } else {
                homePoss = false; //switch poss
                awayPoss = true;
            }
        } else if (awayPoss) { //repeat same as top with opposite team
            if (result > awayTeamScore) {
                determinePasserScore("A");
            } else { //If away team fails, switch possition and current poss player
                homePoss = true; //swithc poss
                awayPoss = false;
            }
        }
        changePossessionPlayer();
    }
    
    
    /**
     * This algorithm determines how successful the passing event will be,
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

        if (passerResult == 1) { //If passer score is low, go all the way back
            System.out.println(currentPoss.getName() + " HAS A TERRIBLE FAILURE");
            if (possessor.equals("H")) {
                ballLocation = 1;
            } else {
                ballLocation = 4;
            }
        } else if (passerResult >= 2 && passerResult < 75) { //If passer score is normal, move field position
            System.out.println(currentPoss.getName() + " Successful pass forward!");
            if (possessor.equals("H")) {
                ballLocation++;
            } else {
                ballLocation--;
            }
        } else if (passerResult >= 75 && passerResult <= 97) { //If passer score is really high, have a chance to move to shot position
            System.out.println(currentPoss.getName() + " Has a long pass! In Shot Position!");
            if (possessor.equals("H")) {
                ballLocation = 4;
            } else {
                ballLocation = 1;
            }
        } else { //If perfect role, get a goal 
            System.out.println(currentPoss.getName() + " LONG SHOT SCORED");
            if (possessor.equals("H")) {
                updateHomeScore();
            }
        }
    }
    /**
     * This method is designed to simulate how likely a goal will happen in the game
     * 1. Get shooter score from current poss player
     * 2. Get goalie score from current goalie being shot on
     * 3. Determine random roles
     * 4. Subtract roles from each other
     * 5. Determine the outcome of the event
     * @param possessor
     * @param goalie 
     */
    private void shotAttempt(String possessor, Player goalie)
    {
        int shooterScore = currentPoss.getPassing();
        Random r = new Random();
        int shooterResult = r.nextInt(shooterScore - 1) + 1;
        
        int goalieScore = goalie.getGoalie();
        r = new Random();
        int goalieResult = r.nextInt(goalieScore - 1) + 1;
        
        int differentScores = shooterResult - goalieResult; //subtract "skill" found by random

        if (differentScores <= 20) { //If the difference in scores is around this, its a save
            System.out.println(currentPoss.getName() + "Shoots & Goalie saves + passes it!");
            if (possessor.equals("H")) {
                ballLocation = 3;
                awayPoss = true;
                homePoss = false;
                changePossessionPlayer();
            } else { //switch possessions
                ballLocation = 1;
                homePoss = true;
                awayPoss = false;
                changePossessionPlayer();
            }
        } else { //If its a good shot, get a goal
            System.out.println(currentPoss.getName() + " SCORRRRREEEEEEEs");
            if (possessor.equals("H")) {
                updateHomeScore();
                homePoss = false;
                awayPoss = true;
                kickOff(); //re-kick off ball
            } else {
                updateAwayScore();
                homePoss = true;
                awayPoss = false;
                kickOff(); //re-kick off ball
            }
        }   
    }

    /**
     * Gets the players from the listcontroller and sets them as a reference
     */
    private void getPlayers() {
        homeTeamPlayers = ListController.getInstance().getThePlayerList().getStartersFromTeam(homeTeam);
        awayTeamPlayers = ListController.getInstance().getThePlayerList().getStartersFromTeam(awayTeam);
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
        homeTeamGoalie = (int) ((this.homeGoalie.overall() / (this.homeGoalie.overall() + this.awayGoalie.overall())) * 1000);
        //The code above calculates the realtive skill of the home team, and the code below gets the relative score for the away team
        awayTeamAttack = 1000 - homeTeamAttack;
        awayTeamMidfield = 1000 - homeTeamMidfield;
        awayTeamDefense = 1000 - homeTeamDefense;
        awayTeamGoalie = 1000 - homeTeamGoalie;
    }

    public void overallTest(Player player)
    {
        System.out.println(player.overall());
    }
    

    @FXML
    private void shoot(ActionEvent event) {
        playGame();
    }

    @FXML
    private void goHome(ActionEvent event) {
                actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
    
}
