package soccerstory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class This class is designed to keep tracked of the weeks for
 * the game. This class creates a schedule which has each team playing each other once.
 * Each team has 5 home games and 5 away games. 
 * 
 * This also keeps track of the current week and results of each game.
 *
 * @author mockl
 */
public class CalendarUIController implements Initializable {


    private int currentWeek = 1;
    @FXML
    private Label actionTarget;
    @FXML
    private Label litfam;
    
    private ArrayList<Match> matchList = new ArrayList<>();

    
    private String stringOtherTeamName;
    
    private Match nextMatch;
    @FXML
    private Label weekOneResult;
    @FXML
    private Label weekTwoResult;
    @FXML
    private Label weekThreeResult;
    @FXML
    private Label weekFourResult;
    @FXML
    private Label weekFiveResult;
    @FXML
    private Label weekSixResult;
    @FXML
    private Label weekSevenResult;
    @FXML
    private Label weekEightResult;
    @FXML
    private Label weekNineResult;
    @FXML
    private Label currentWeekLabel;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCalendar();
        listMatches(ListController.getInstance().getTheTeamList().getTeamNames());
        //printList();
        findMatch("fff");
        layOutCalendar();
        setUpScoreLabels(1, weekOneResult);

    }

    /**
     * This class is designed to update the current week label with the current
     * week
     */
    private void updateCalendar() {
        String currentWeekLabelText = currentWeekLabel.getText();
        currentWeekLabelText = currentWeekLabelText + Integer.toString(currentWeek);
        currentWeekLabel.setText(currentWeekLabelText);
    }
    
    /**
     * This class calls the method which writes the labels
     * Used to make code look pretty
     */
    private void layOutCalendar()
    {
        setUpScheduleLabels(1, weekOneResult);
        setUpScheduleLabels(2, weekTwoResult);
        setUpScheduleLabels(3, weekThreeResult);
        setUpScheduleLabels(4, weekFourResult);
        setUpScheduleLabels(5, weekFiveResult);
        setUpScheduleLabels(6, weekSixResult);
        setUpScheduleLabels(7, weekSevenResult);
        setUpScheduleLabels(8, weekEightResult);
        setUpScheduleLabels(9, weekNineResult);
    }

    /**
     * @return the currentWeek
     */
    public int getCurrentWeek() {
        return currentWeek;
    }

    /**
     * @param currentWeek the currentWeek to set
     */
    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    /**
     * sends user back home by calling nav cntl
     *
     * @param event
     */
    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();

        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    private void updateMatches() {
        ArrayList<String> teams = new ArrayList<String>();
    }

    /**
     * Creates a calander to be stored into another class 
     * Code partially from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
     * @param listTeam
     * @param ListTeam 
     */
    public void listMatches(List<String> listTeam) {

        int numWeeks = listTeam.size() - 1;
        int halfSize = listTeam.size() / 2;

        List<String> teams = new ArrayList<String>();

        teams.addAll(listTeam); // Add teams to List and remove the first team
        teams.remove(0); //remove first index to base calander off of

        int teamsSize = teams.size(); //how many teams?

        for (int week = 0; week < numWeeks; week++) { //same as int i but change it to weeks as this is based off weeks
            
            System.out.println("Week {" + (week + 1) + ")"); //Prints out what week the matches are being created for

            int teamIdx = week % teamsSize; //get first value of idx, add to it later

            Match newMatch = new Match(teams.get(teamIdx), listTeam.get(0), week + 1, 1); //center around first time in the original list, 
            matchList.add(newMatch);
            
            //Match newMatch = new Match(teams.get(teamIdx).getTeamName());

            for (int idx = 1; idx < halfSize; idx++) {
                int firstTeam = (week + idx) % teamsSize; //get first team from first half of list
                int secondTeam = (week + teamsSize - idx) % teamsSize; //get second team from second half of list
                //this will switch home/away when the week is > 5 to make it more balanced
                Match newerMatch = new Match(teams.get(firstTeam), teams.get(secondTeam), week + 1, idx + 1);
                matchList.add(newerMatch);
            }
        }
    }
    
    
    /**
     * Prints out list of matches for testing purposes...
     * no real use but debugging
     */
    public void printList()
    {
        for (int i = 0; i < matchList.size(); i++)
        {
            System.out.print(matchList.get(i).getTeam1() + "VS");
            System.out.print(matchList.get(i).getTeam2() + "  ");
            System.out.print(matchList.get(i).getWeek() + "  ");
            System.out.println(matchList.get(i).getMatchNumber());
        }
    }
  
    /**
     * This method finds a match based on current week and team name
     * 
     * @param teamName 
     */
    public void findMatch(String teamName)
    {
        
        String teamNamer = "Null";
        for(int i = 0; i < matchList.size(); i++)
        {
            if ((matchList.get(i).getWeek() == currentWeek) && //if the match is this week and has the team name 
                    (matchList.get(i).getTeam1().equals(teamName)))
            {
               teamNamer = (matchList.get(i).getTeam2());
                       setNextMatch(matchList.get(i));
            }
            else if ((matchList.get(i).getWeek() == currentWeek) &&
                    matchList.get(i).getTeam2().equals(teamName))
            {
               teamNamer = (matchList.get(i).getTeam1());
                       setNextMatch(matchList.get(i));
            }
        }
        setStringOtherTeamName(teamNamer); //set other team they are playing
    }
    
    /**
     * This method is passed the week and label and then sets the text
     * equal to the match that will be played
     * @param week
     * @param weekLabel 
     */
    private void setUpScheduleLabels(int week, Label weekLabel)
    {
        String match = "null";
        for (int i = 0; i < matchList.size(); i++)
        {
            if ((matchList.get(i).getTeam1().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && matchList.get(i).getWeek() == week)
            {
                match = "@ home " + " vs " + matchList.get(i).getTeam2(); 
      
            }        
            else if ((matchList.get(i).getTeam2().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && matchList.get(i).getWeek() == week)
            {
                match = "@ " + matchList.get(i).getTeam1();  
            }
                            
            weekLabel.setText(match); 
        }
    }
    /**
     * This method is designed to create the score labels for the matches that have been played
     * Adds on to the match labels
     * @param week
     * @param scoreLabel 
     */
    private void setUpScoreLabels(int week, Label scoreLabel)
    {
        String match = "null";
        
        for (int i = 0; i < matchList.size(); i++)
        {
            if ((matchList.get(i).getTeam1().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && matchList.get(i).getWeek() == week)
            {
                //need to add W/L/D tag
                match = scoreLabel.getText() + "  " +  matchList.get(i).getTeam1Score() + " - " + matchList.get(i).getTeam2Score();
                            scoreLabel.setText(match); 
      
            }        
            else if ((matchList.get(i).getTeam2().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && matchList.get(i).getWeek() == week)
            {
                match = scoreLabel.getText() + "  " + matchList.get(i).getTeam1Score() + " - " + matchList.get(i).getTeam2Score();
                            scoreLabel.setText(match); 
            }
                            

        } 
    }

    /**
     * @return the stringOtherTeamName
     */
    public String getStringOtherTeamName() {
        return stringOtherTeamName;
    }

    /**
     * @param stringOtherTeamName the stringOtherTeamName to set
     */
    public void setStringOtherTeamName(String stringOtherTeamName) {
        this.stringOtherTeamName = stringOtherTeamName;
    }

    /**
     * @return the nextMatch
     */
    public Match getNextMatch() {
        return nextMatch;
    }

    /**
     * @param nextMatch the nextMatch to set
     */
    public void setNextMatch(Match nextMatch) {
        this.nextMatch = nextMatch;
    }

}
