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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.control.Alert;


/**
 * FXML Controller class This class is designed to keep tracked of the weeks for
 * the game. This class manages the results and allows the user to see how they did in each game
 * 
 * 
 * This also keeps track of the current week and results of each game.
 *
 * @author mockl
 */
public class CalendarUIController implements Initializable {


    private int currentWeek = 1;
    @FXML
    private Label actionTarget;
    
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
    private Text currentWeekLabel;
    
    private int matchIndex;
    @FXML
    private Text actionTarget1;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCalendar();
        matchList = ListController.getInstance().getTheMatchList().getTheMatchList();
        currentWeek = ListController.getInstance().getTheMatchList().getCurrentWeek();
        //printList();
        findMatch(ListController.getInstance().getTheTeamList().getCurrentUserTeam());
        layOutCalendar();
    }

    /**
     * This class is designed to update the current week label with the current
     * week
     */
    private void updateCalendar() {
        String currentWeekText = Integer.toString(currentWeek);
        currentWeekLabel.setText("It is currently week " + currentWeekText);
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
        setUpScoreLabels(1, weekOneResult);
        setUpScoreLabels(2, weekTwoResult);
        setUpScoreLabels(3, weekThreeResult);
        setUpScoreLabels(4, weekFourResult);
        setUpScoreLabels(5, weekFiveResult);
        setUpScoreLabels(6, weekSixResult);
        setUpScoreLabels(7, weekSevenResult);
        setUpScoreLabels(8, weekEightResult);
        setUpScoreLabels(9, weekNineResult);
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
    
    /**
     * Prints out list of matches for testing purposes...
     * no real use but debugging
     */
    public void printList()
    {
        for (int i = 0; i < getMatchList().size(); i++)
        {
            System.out.print(getMatchList().get(i).getTeam1() + "VS");
            System.out.print(getMatchList().get(i).getTeam2() + "  ");
            System.out.print(getMatchList().get(i).getWeek() + "  ");
            System.out.println(getMatchList().get(i).getMatchNumber());
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
        for(int i = 0; i < getMatchList().size(); i++)
        {
            if ((getMatchList().get(i).getWeek() == currentWeek) && //if the match is this week and has the team name 
                    (getMatchList().get(i).getTeam1().equals(teamName)))
            {
               teamNamer = (getMatchList().get(i).getTeam2());
                       setNextMatch(getMatchList().get(i));
                setMatchIndex(i);
            }
            else if ((getMatchList().get(i).getWeek() == currentWeek) &&
                    getMatchList().get(i).getTeam2().equals(teamName))
            {
               teamNamer = (getMatchList().get(i).getTeam1());
                       setNextMatch(getMatchList().get(i));
                setMatchIndex(i);
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
        for (int i = 0; i < getMatchList().size(); i++)
        {
            if ((getMatchList().get(i).getTeam1().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && getMatchList().get(i).getWeek() == week)
            {
                match = "@ home " + " vs " + getMatchList().get(i).getTeam2(); 
      
            }        
            else if ((getMatchList().get(i).getTeam2().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && getMatchList().get(i).getWeek() == week)
            {
                match = "@ " + getMatchList().get(i).getTeam1();  
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
        
        for (int i = 0; i < getMatchList().size(); i++)
        {
            if ((getMatchList().get(i).getTeam1().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && getMatchList().get(i).getWeek() == week)
            {
                //need to add W/L/D tag
                match = scoreLabel.getText() + "  " +  getMatchList().get(i).getTeam1Score() + " - " + getMatchList().get(i).getTeam2Score();
                            scoreLabel.setText(match); 
      
            }        
            else if ((getMatchList().get(i).getTeam2().equals(ListController.getInstance().
                    getTheTeamList().getCurrentUserTeam())) && getMatchList().get(i).getWeek() == week)
            {
                match = scoreLabel.getText() + "  " + getMatchList().get(i).getTeam1Score() + " - " + getMatchList().get(i).getTeam2Score();
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

    /**
     * @return the matchIndex
     */
    public int getMatchIndex() {
        return matchIndex;
    }

    /**
     * @param matchIndex the matchIndex to set
     */
    public void setMatchIndex(int matchIndex) {
        this.matchIndex = matchIndex;
    }

    /**
     * @return the matchList
     */
    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    /**
     * @param matchList the matchList to set
     */
    public void setMatchList(ArrayList<Match> matchList) {
        this.matchList = matchList;
    }

    @FXML
    private void viewHelp(ActionEvent event) {
            alert.setTitle("Help!");
            alert.setHeaderText("This is the Calendar section");
            alert.setContentText("Welcome to the Calendar! \n"
                    + "You can see who you will play against in each week \n "
            + "You can also see the results after the match has taken place \n"
            + "Results are always your score then their score");

            alert.showAndWait();

    }

}
