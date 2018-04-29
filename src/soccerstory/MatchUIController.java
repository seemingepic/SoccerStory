/**
 * This class is the engine behind how matches are simulated
 * I based off part of the mechanics from "not really jake" 
 * from https://stackoverflow.com/questions/1427043/soccer-simulation-for-a-game
 * There was no code taken, but he did have a very cool thought process on how
 * a simulator should work
 * 
 * How this class works:
 * 1. Import each teams lineup by asking the matchList who is supposed to be playing
 * 2. Set each team to home/away
 * 3. Calculate the strength of each team by using a probability algorithm
 *    This works by comparing the overall stats (calculated from the player class) from each team, then setting
 *    them on a scale from 1-1000. For example, a team with 80 attack compared to 50 attack will claim 62.5% of the attack points
 * 4. Set up screen
 * 5. When the player starts the match, the simulation will start
 * 6. The home team will start with the ball at midfield
 * 7. The fieldPosition method will then check where the ball is, then call the correct simulation method
 * 8. For example, if the ball is midfield, the points of the two teams are compared. A die is rolled 1-1000
 *    and then compared to the strength of the team. This is compared to the weights controlled earlier.
 *    This way the team with the higher score will have a higher probability to win the play.
 * 9. After the simulation decides who won the possession, it will then go to a player checker
 * 10. The player checker then uses the players overall to see how successfull the play will be
 * 11. The ball will either move up or switch posessions and move back
 * 12. The simulation will continue until all 90 minutes of the game will be played
 * 
 * 13. Stats from each posession will be stored, and the score will be sent to the matchList to be 
 * shown on the calendar
 * 14. The player is awarded 25 gold for playing
 *      
 */
package soccerstory;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafx.util.Duration.seconds;
import javax.swing.SwingUtilities;

public class MatchUIController implements Initializable {

    @FXML
    private Label homeTeamLabel; //used to display team names 
    @FXML
    private Label awayTeamLabel;

    private String homeTeam; //Name of each team
    private String awayTeam;
    @FXML
    private Label homeTeamScore; //Score of each team
    @FXML
    private Label awayTeamScore;

    private ArrayList<Player> homeTeamPlayers; //List of players from each team
    private ArrayList<Player> awayTeamPlayers;

    private boolean homePoss = true; //Sets possession so home team starts with ball
    private boolean awayPoss = false;
    private Player currentPoss; //Whoever has current possession of the ball

    private ArrayList<Player> awayMidfield = new ArrayList<>(); //The lists are used to seperate the lineup into each position 
    private ArrayList<Player> awayDefense = new ArrayList<>();
    private Player awayGoalie;
    private ArrayList<Player> awayAttack = new ArrayList<>();

    private ArrayList<Player> homeMidfield = new ArrayList<>();
    private ArrayList<Player> homeDefense = new ArrayList<>();
    private Player homeGoalie;
    private ArrayList<Player> homeAttack = new ArrayList<>();
    
    private List<String> ballLocations =  //This is wherever the ball can be located hGoal stands for homeGoal and aGoal stands for away goal
            Arrays.asList("hGoal", "hDef", "Mid", "aDef", "aGoal");
    private int ballLocation; //Where the ball is located on the List ex 3 = midfield
    
    int homeTeamOverall; //Used from generateing how strong each team is
    int awayTeamOverall;
    
    int homeTeamAttack;
    int homeTeamDefense;
    int homeTeamMidfield;
    int homeTeamGoalie;
    
    int awayTeamAttack;
    int awayTeamDefense;
    int awayTeamMidfield;
    int awayTeamGoalie;
    
    int homeScore, awayScore;
    
    int gameTime = 1; //What minute the game is in
    
    private Match match; //The match details pulled from the matchList
    
    @FXML
    private Label homeAttackWeight; //Used to display how strong each team is
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
    private CalendarUIController calUiCntl;
    @FXML
    private Button startGameButton;
    
    PersistentDataCntl thePersistentDataCntl;
    @FXML
    private Text actionTarget1;
    @FXML
    private Label updateLabel1;
    @FXML
    private Label updateLabel2;
    @FXML
    private Label updateLabel3;
    @FXML
    private Label updateLabel4;
    @FXML
    private Label updateLabel5;
    
    private int updateLabelCounter = 1;
    @FXML
    private TextArea textArea; //Used to display what happens in game
    


    /**
     * Initializes the controller class.
     * Calls the initial setup to update things like labels
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
    /**
     * Sets up labels for team names
     */
    public void getTeams()
    {
        homeTeamLabel.setText(homeTeam); //Sets home team
        awayTeamLabel.setText(awayTeam); //sets away team name
    }
    
    /**
     * Grabs the current match from navigationUI and pulls the
     * data from the match that is returned
     */
    private void setUpTeams()
    {
        try{ //Pull the nav ui to get the current match
        FXMLLoader loader=new FXMLLoader(getClass().getResource("NavigationUI.fxml"));
        Parent root = (Parent) loader.load();
        navUiCntl =loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
        match = navUiCntl.getNextMatch(); //Gehat teams will be playing  the match object to pull 
        
        if(navUiCntl.getHome()) //Puts the teams into home and away respectively
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
        homeScore = Integer.parseInt(homeTeamScore.getText());
        homeScore++;
        String updatedScore = Integer.toString(homeScore);
        homeTeamScore.setText(updatedScore);
        displayUpdates("Home Team Scored!");
    }

    /**
     * This method is scored when the away team is scored,
     * gets the original scored, updates it and reposts it
     */
    public void updateAwayScore() {
        awayScore = Integer.parseInt(awayTeamScore.getText());
        awayScore++;
        String updatedScore = Integer.toString(awayScore);
        awayTeamScore.setText(updatedScore);
        displayUpdates("Away Team Scored!");
    }

    /**
     * What happens when user clicks button
     * This will begin the "game loop"
     */
    public void playGame() {
        Simulation simulator = new Simulation();
        simulator.simulateOtherMatches();
        fieldPosition(); //The "game loop" used to run the simulation
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
                    gameTime++;
                    break;
                case 1: //home defense position
                    possessionChecker(this.homeTeamDefense, this.awayTeamAttack); //Determine who will win possession, home d vs away attack
                    gameTime++;
                    break;
                case 2: //midfield 
                    possessionChecker(this.homeTeamMidfield, this.awayTeamMidfield); //Checks which team will win the poessession in the midfield
                    gameTime++;
                    break;
                case 3: //away defense position
                    possessionChecker(this.homeTeamAttack, this.awayTeamDefense); //Checks which team wins posession in the away defense side of field 
                    gameTime++;
                    break;
                default: //away goal position
                    shotAttempt("H", this.awayGoalie); //The home team attempts a shot on the away goalie
                    gameTime++;
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
    }
    
    /**
     * Checks for who currently has possession, and will change it based
     * on field position
     */
    public void changePossessionPlayer() {
        
        
        switch (ballLocation) { //Where is the ball? 
            case 0: //home goal
                if (homePoss) {
                    changePossessionSucPass(homeDefense);//currentPoss = homeDefense.get(randomNonAttacker);
                } else { //change player based on where the ball is
                    changePossessionSucPass(awayAttack);//currentPoss = awayAttack.get(randomAttacker);
                }
                break;
            case 1: //defense position
                if (homePoss) {
                    changePossessionSucPass(homeDefense);//currentPoss = homeDefense.get(randomNonAttacker);
                } else {
                    changePossessionSucPass(awayAttack);// currentPoss = awayAttack.get(randomAttacker);
                }
                break;
            case 2: //midfield checker
                if (homePoss) {
                    changePossessionSucPass(homeMidfield);//currentPoss = homeMidfield.get(randomNonAttacker);
                } else {
                    changePossessionSucPass(awayMidfield);//currentPoss = awayMidfield.get(randomNonAttacker);
                }
                break;
            case 3: //shot position
                if (homePoss) {
                    changePossessionSucPass(homeAttack);//currentPoss = homeAttack.get(randomAttacker);
                } else {
                    changePossessionSucPass(awayDefense);//currentPoss = awayDefense.get(randomNonAttacker);
                }
                break;
            default: //away goal
                if (homePoss) {
                    changePossessionSucPass(homeAttack);//currentPoss = homeAttack.get(randomAttacker);
                } else {
                    changePossessionSucPass(awayDefense);//currentPoss = awayDefense.get(randomNonAttacker);
                }
                break;
        }
    }
 
    /**
     * This algorithm determines the success rate of who will get the ball after
     * a successful ball is passed.
     * 
     * Based on the player's overall, there is a weight assigned, then a random number 0-1,
     * this will then determine the % chance a player has of getting the ball
     * 
     * @param newPlayersInvolved - The correct ArrayList<Player> like midfield or attack based
     * on where the ball is located
     */
    private void changePossessionSucPass(ArrayList<Player> newPlayersInvolved) {
        ArrayList<Player> playersInvolved = newPlayersInvolved; //Which teams were involved on the play
        int numPlayersInvolved = playersInvolved.size();
        ArrayList<Double> playerSkills; //list of %chance of player getting ball
        playerSkills = new ArrayList<Double>(); 
        int totalOverall = 0; //used for the bottom number in the fraction to determine % chance
        double randomNum = Math.random(); //random number 0-1

        for (int i = 0; i < newPlayersInvolved.size(); i++) {
            totalOverall += newPlayersInvolved.get(i).getOverall(); //get sum of overall
        }

        for (int i = 0; i < numPlayersInvolved; i++) {
            playerSkills.add((double) (newPlayersInvolved.get(i).getOverall()) / totalOverall); //set up array with weight of each player
        }
        //This will then determine who will get the ball based on probability 
        
        if (numPlayersInvolved == 2) { //If the amount of players is based on attack
            if (randomNum > 0 && randomNum < playerSkills.get(0)) {
                currentPoss = newPlayersInvolved.get(0);
            } else if (randomNum > playerSkills.get(0) && randomNum < (playerSkills.get(0) + playerSkills.get(1))) {
                currentPoss = newPlayersInvolved.get(1);
            }
        } else { //If the players were either midfielders or defenders
            if (randomNum > 0 && randomNum < playerSkills.get(0)) 
            {
                currentPoss = newPlayersInvolved.get(0);//player 1 gets ball
            } 
            else if (randomNum > playerSkills.get(0) && randomNum < (playerSkills.get(0) + playerSkills.get(1))) 
            {
                currentPoss = newPlayersInvolved.get(1);//player 2 gets ball
            } 
            else if (randomNum > (playerSkills.get(0) + playerSkills.get(1))
                    && randomNum < (playerSkills.get(0) + playerSkills.get(1) + playerSkills.get(2))) 
            {
                currentPoss = newPlayersInvolved.get(2);//player 3 gets ball
            }
            else
            {
               currentPoss = newPlayersInvolved.get(3); //player 4 gets ball
            }
    }
    }
    

        /**
     * This is the algorithm to determine how the playing of the game will go
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
                currentPoss.getStats().setPasses(currentPoss.getStats().getPasses() + 1);
            } else {
                displayUpdates(currentPoss.getName() + " Tries to move the ball and loses it");
                homePoss = false; //switch poss
                awayPoss = true;
            }
        } else if (awayPoss) { //repeat same as top with opposite team
            if (result > awayTeamScore) {
                determinePasserScore("A");
                currentPoss.getStats().setPasses(currentPoss.getStats().getPasses() + 1);
            } else { //If away team fails, switch possition and current poss player
                displayUpdates(currentPoss.getName() + " Tries to move the ball and loses it");
                homePoss = true; //swithc poss
                awayPoss = false;
            }
        }
        
        changePossessionPlayer(); //Change possession based on where field position is 
        displayUpdates(currentPoss.getName() + " Now has the ball");
    }
    
    
    /**
     * This algorithm determines how successful the passing event will be,
     * Normal outcome: Move position 1
     * Somewhat rare: move forward 2 (long pass)
     * Rare outcomes: Score, lose possession and go back position
     * @param possessor  -- Whoever current has the ball - from variable currentPoss
     */
    private void determinePasserScore(String possessor)
    {
        int passerScore = currentPoss.getPassing();
        Random r = new Random();
        int passerResult = r.nextInt(passerScore - 1) + 1;

        if (passerResult == 1) { //If passer score is low, go all the way back
            displayUpdates(currentPoss.getName() + " HAS A TERRIBLE FAILURE and lost the ball!");
            if (possessor.equals("H")) {
                ballLocation = 1;
            } else {
                ballLocation = 4;
            }
        } else if (passerResult >= 2 && passerResult < 75) { //If passer score is normal, move field position
            displayUpdates(currentPoss.getName() + " Goes forward with the ball!");
         
    
            if (possessor.equals("H")) {
                ballLocation++;
            } else {
                ballLocation--;
            }
        } else if (passerResult >= 75 && passerResult <= 97) { //If passer score is really high, have a chance to move to shot position
            displayUpdates(currentPoss.getName() + " Has a long pass! In Shot Position!");
            if (possessor.equals("H")) {
                ballLocation = 4;
            } else {
                ballLocation = 1;
            }
        } else { //If perfect role, get a goal 
            displayUpdates(currentPoss.getName() + " LONG SHOT SCORED");
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
        int shooterScore = currentPoss.getPassing(); //Generate random numbers to be used in simulation 
        Random r = new Random();
        int shooterResult = r.nextInt(shooterScore - 1) + 1;
        
        int goalieScore = goalie.getGoalie();
        r = new Random();
        int goalieResult = r.nextInt(goalieScore - 1) + 1;
        
        int differentScores = shooterResult - goalieResult; //subtract "skill" found by random

        if (differentScores <= 20) { //If the difference in scores is around this, its a save
            displayUpdates(currentPoss.getName() + " Shoots!!! " + goalie.getName() + " Saves it!");
            if (possessor.equals("H")) {
                ballLocation = 3;
                homeGoalie.getStats().setShotsAgainst(homeGoalie.getStats().getShotsAgainst() + 1);
                awayPoss = true;
                homePoss = false;
                changePossessionPlayer();
            } else { //switch possessions if the ball is saved
                ballLocation = 1;
                awayGoalie.getStats().setShotsAgainst(awayGoalie.getStats().getShotsAgainst() + 1);
                homePoss = true;
                awayPoss = false;
                changePossessionPlayer();
            }
            displayUpdates(goalie.getName() + " Passes to " + currentPoss.getName());
        } else { //If its a good shot, get a goal
            displayUpdates(currentPoss.getName() + " SHOOTS AND SCORRRRREEEEEEEs");
            if (possessor.equals("H")) {
                updateHomeScore();
                homePoss = false;
                awayPoss = true;
                homeGoalie.getStats().setGoalsAllowed(homeGoalie.getStats().getGoalsAllowed() + 1); 
                currentPoss.getStats().setGoals(currentPoss.getStats().getGoals() + 1);
                kickOff(); //re-kick off ball
            } else {
                updateAwayScore();
                homePoss = true;
                awayPoss = false;
                awayGoalie.getStats().setGoalsAllowed(awayGoalie.getStats().getGoalsAllowed() + 1);
                currentPoss.getStats().setGoals(currentPoss.getStats().getGoals() + 1);
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
            if (homeOrAway.equals("Home")) { //Put each player into the respective position based on the players attribute 
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
            } else { //Put each player into the respective player based on the players position attribute
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
     * @param homeTeam -- All the players from the homeTeam
     * @param awayTeam -- All the players from the awayTeam
     */
    private void determineWeight(ArrayList<Player> homeTeam, ArrayList<Player> awayTeam) 
    {
        
        double homeAttackerPoints = 0, homeMidfieldPoints = 0, homeDefenderPoints = 0,
                homeGoaliePoints = 0, awayAttackerPoints = 0, awayMidfieldPoints = 0, 
                awayDefenderPoints = 0, awayGoaliePoints = 0; //The scores that will be based from 1-1000

        for (int i = 0; i < homeTeam.size(); i++) { //Go through home team and calculate total overall for each position
            switch (homeTeam.get(i).getPosition()) {
                case "A":
                    homeAttackerPoints += homeTeam.get(i).getOverall();
                    break;
                case "M":
                    homeMidfieldPoints += homeTeam.get(i).getOverall();
                    break;
                case "D":
                    homeDefenderPoints += homeTeam.get(i).getOverall();
                    break;
            }
        }
        for (int i = 0; i < awayTeam.size(); i++) { //Go through away team and calculate total overall for each position
            switch (awayTeam.get(i).getPosition()) {
                case "A":
                    awayAttackerPoints += awayTeam.get(i).getOverall();
                    break;
                case "M":
                    awayMidfieldPoints += awayTeam.get(i).getOverall();
                    break;
                case "D":
                    awayDefenderPoints += awayTeam.get(i).getOverall();
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
        homeTeamAttack = (int)(((homeAttackPoints) / (homeAttackPoints + awayAttackPoints)) * 1000); //Compare skills to the overall skill of both teams to set up a probability 1-1000
        homeTeamMidfield = (int)(((homeMidfieldPoints) / (homeMidfieldPoints + awayMidfieldPoints))* 1000);
        homeTeamDefense = (int)(((homeDefensePoints) / (homeDefensePoints + awayDefensePoints)) * 1000);
        homeTeamGoalie = (int) ((this.homeGoalie.getOverall() / (this.homeGoalie.getOverall() + this.awayGoalie.getOverall())) * 1000);
        //The code above calculates the realtive skill of the home team, and the code below gets the relative score for the away team
        awayTeamAttack = 1000 - homeTeamAttack;
        awayTeamMidfield = 1000 - homeTeamMidfield;
        awayTeamDefense = 1000 - homeTeamDefense;
        awayTeamGoalie = 1000 - homeTeamGoalie;
    }
    
    /**
     * Compares the two scores of the teams, updates points + games played
     * in the team list based on the outcome
     */
    private void updatePoints()
    {
        if (homeScore > awayScore) //if home wins, give them 3 points, away 0
        {
            ListController.getInstance().getTheTeamList().updateTeamPoints(awayTeam, 0);
            ListController.getInstance().getTheTeamList().updateTeamPoints(homeTeam, 3);
        }
        else if (awayScore > homeScore) //if away wins, give 3 points, home 0
        {
            ListController.getInstance().getTheTeamList().updateTeamPoints(homeTeam, 0);
            ListController.getInstance().getTheTeamList().updateTeamPoints(awayTeam, 3); 
        }
        else if (awayScore == homeScore) //if points equal, give each team 1 point
        {
            ListController.getInstance().getTheTeamList().updateTeamPoints(awayTeam, 1);
            ListController.getInstance().getTheTeamList().updateTeamPoints(homeTeam, 1);
        }

        setPlayerTeamResult(); //Stores results of the game
       
        int currentCap = ListController.getInstance().getTheTeamList().getUserTeam().getCapSpace(); //gets users current cap to be added in next line
        ListController.getInstance().getTheTeamList().getUserTeam().setCapSpace(currentCap + 25); //Adds 25 gold to the players team after playing a game
    }
    
    /**
     * This stores the result of the game, then increases the week to the next one
     */
    private void setPlayerTeamResult()
    {
        ListController.getInstance().getTheMatchList().updateScores(homeTeam, homeScore, awayScore);
        ListController.getInstance().getTheMatchList().setCurrentWeek(ListController.getInstance().getTheMatchList().getCurrentWeek() + 1);
    }
    
    
    /**
     * Sends the user back home to the nav screen
     * @param event 
     */
    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        saveData();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    /**
     * What happens when the user clicks the start game button
     * Game simulation starts 
     * @param event 
     */
    @FXML
    private void startGame(ActionEvent event) {
        playGame(); //run simulation
        updatePoints(); //determine winner give points based on winner
        startGameButton.setVisible(false);
    }
    
    
    /**
     * This saves data using persistent data
     */
    private void saveData()
    {

        PersistentDataCntl.getInstance().serialize(ListController.getInstance().
                getTheMatchList().getTheMatchList(), "matchList.ser"); //save the matchList
       
        PersistentDataCntl.getInstance().serialize(ListController.getInstance().
                getThePlayerList().getTheArrayPlayerList(), "playerList.ser"); //save the playerList
        
        PersistentDataCntl.getInstance().serialize(ListController.getInstance(). //save the TeamList
                getTheTeamList().getTheListOfTeams(), "teamList.ser");
    }
    
   
    /**
     * This displays the gameText to the textArea 
     * @param updateGameText 
     */
    private void displayUpdates(String updateGameText) {
        
        textArea.setText(textArea.getText() + "Minute: " + gameTime + " " +updateGameText + "\n");
        
    }

}
