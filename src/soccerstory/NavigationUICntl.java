package soccerstory;

import com.github.javafaker.Faker;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class This is the "main screen" this passes control to each
 * of the controllers, holds some of the vital information to be passes to other
 * controllers like current match, current week,
 *
 * @author mockl
 */
public class NavigationUICntl implements Initializable {

    @FXML
    private Text tester;
    private Button playersButton; //Button to go to playerScreen 
    @FXML
    private Text actionTarget; //Used to get the current window 
    private GameLoaderController GameLoaderController;
    @FXML
    private Label weekLabel; // displays Current week 
    @FXML
    private Label teamLabel; //displays team name 
    private CalendarUIController calendarController;

    private String otherTeam; //Displays next team you will be playing 
    private Match nextMatch; //Displays next match info 
    private Boolean home; //If user is home or away next game 
    @FXML
    private Label homeOrAway;
    private PlayerShopUIController playerController;
    
    private static Boolean hasDrafted = false; //If the user has drafted at the end of the season 
    @FXML
    private ImageView newSeasonButton; //Restarts season button
    
    PersistentDataCntl thePersistentDataCntl;
    @FXML
    private ImageView matchButton;
    @FXML
    private Text newSeasonText;
    @FXML
    private Text playText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTeamName();
        updateDate();
    }

    /**
     * Grabs the current match from calendar and set it to the labesl on the
     * page
     */
    private void updateDate() {
        //Gets the calendar controller to grab the current week
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CalendarUI.fxml"));
            Parent root = (Parent) loader.load();
            setCalendarController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //If the season is not over, display the normal information
        if (getCalendarController().getCurrentWeek() < 10) { 
            String date = "week ";
            date = date + getCalendarController().getCurrentWeek();
            weekLabel.setText(date); //display week
            setNextMatch(getCalendarController().getNextMatch());
            getMatchInfo(); //Display match info for next week 
            teamLabel.setText(otherTeam); //set other team name 
        } else { //If the season is over 
            String date = "SEASON OVER"; 
            weekLabel.setText(date); 
            setNextMatch(getCalendarController().getNextMatch());
            teamLabel.setText("NONE"); 
            matchButton.setVisible(false);
            playText.setVisible(false);
            
            if (hasDrafted == false) //If the player has not drafted yer 
            { 
                draftDay(); //display a draft screen
                hasDrafted = true; 
            }
            
            newSeasonButton.setVisible(true);
            newSeasonText.setVisible(true);
            
        }
    }

    /**
     * At the end of season, the user is allowed to pick one player from the draft
     * There will be three selections prompted and each one will have a slightly different result
     * 
     */
    public void draftDay() {
        
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("PlayerShopUI.fxml"));
        Parent root = (Parent) loader.load();
        playerController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //Sets up alert 
        alert.setTitle("It's Draft Day!");
        alert.setHeaderText("You have the oppurtunity to pick between three players");
        alert.setContentText("Choose who you want");

        ButtonType buttonTypeOne = new ButtonType("Sure Bet");
        ButtonType buttonTypeTwo = new ButtonType("Good and has potential");
        ButtonType buttonTypeThree = new ButtonType("Lots of upside");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree); //Displays the three choice 
        Optional<ButtonType> result = alert.showAndWait(); //Stores the button users clicks 

        if (result.get() == buttonTypeOne) {
            playerController.developNewPlayer(4);// ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
            playerController.developNewPlayer(5);// ... user chose "Two"
        } else if (result.get() == buttonTypeThree) {
            playerController.developNewPlayer(6);
            // ... user chose "Three"
        }
    }

    /**
     * Extracts data from match to determine home or away
     */
    private void getMatchInfo() {
        if (getNextMatch().getTeam1().equals(ListController.getInstance().getTheTeamList().getCurrentUserTeam())) {
            otherTeam = getNextMatch().getTeam2();
            setHome((Boolean) true);
            homeOrAway.setText("Home");
        } else {
            otherTeam = getNextMatch().getTeam1();
            setHome((Boolean) false);
            homeOrAway.setText("Away");
        }
    }


    private void getTeamName() { //Get the team name to display 
        getTester().setText(ListController.getInstance().getTheTeamList().getCurrentUserTeam());
    }



    /**
     * @return the tester
     */
    public Text getTester() {
        return tester;
    }

    /**
     * @param tester the tester to set
     */
    public void setTester(Text tester) {
        this.tester = tester;
    }

    /**
     * @return the playersButton
     */
    public Button getPlayersButton() {
        return playersButton;
    }

    /**
     * @param playersButton the playersButton to set
     */
    public void setPlayersButton(Button playersButton) {
        this.playersButton = playersButton;
    }

    /**
     * @return the actionTarget
     */
    public Text getActionTarget() {
        return actionTarget;
    }

    /**
     * @param actionTarget the actionTarget to set
     */
    public void setActionTarget(Text actionTarget) {
        this.actionTarget = actionTarget;
    }

    /**
     * @return the GameLoaderController
     */
    public GameLoaderController getGameLoaderController() {
        return GameLoaderController;
    }

    /**
     * @param GameLoaderController the GameLoaderController to set
     */
    public void setGameLoaderController(GameLoaderController GameLoaderController) {
        this.GameLoaderController = GameLoaderController;
    }

    /**
     * @return the weekLabel
     */
    public Label getWeekLabel() {
        return weekLabel;
    }

    /**
     * @param weekLabel the weekLabel to set
     */
    public void setWeekLabel(Label weekLabel) {
        this.weekLabel = weekLabel;
    }

    /**
     * @return the teamLabel
     */
    public Label getTeamLabel() {
        return teamLabel;
    }

    /**
     * @param teamLabel the teamLabel to set
     */
    public void setTeamLabel(Label teamLabel) {
        this.teamLabel = teamLabel;
    }

    /**
     * @return the calendarController
     */
    public CalendarUIController getCalendarController() {
        return calendarController;
    }

    /**
     * @param calendarController the calendarController to set
     */
    public void setCalendarController(CalendarUIController calendarController) {
        this.calendarController = calendarController;
    }

    /**
     * @return the otherTeam
     */
    public String getOtherTeam() {
        return otherTeam;
    }

    /**
     * @param otherTeam the otherTeam to set
     */
    public void setOtherTeam(String otherTeam) {
        this.otherTeam = otherTeam;
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
     * @return the home
     */
    public Boolean getHome() {
        return home;
    }

    /**
     * @param home the home to set
     */
    public void setHome(Boolean home) {
        this.home = home;
    }


    private void newSeason(ActionEvent event) {
        ListController.getInstance().getTheMatchList().getTheMatchList().clear();
        ListController.getInstance().createMatchList();
        updateDate();
        matchButton.setVisible(true);
        newSeasonButton.setVisible(false);
        ListController.getInstance().getTheTeamList().resetPoints();
    }

    /**
     * Function: To view the PlayerListUI
     * This closes the current window, and calls the controller and 
     * .fxml file of the window that is supposed to be open
     * @param event mouse click
     */
    @FXML
    private void viewPlayers(MouseEvent event) {
        getActionTarget().setText("player button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpPlayerScene();
    }
    
    /**
     * Function: To view the MatchUI
     * This closes the current window, and calls the controller and 
     * .fxml file of the window that is supposed to be open
     * @param event mouse click
     */

    @FXML
    private void startMatch(MouseEvent event) {
        getActionTarget().setText("match button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpMatchScene();

    }
    /**
     * Function: To view the PlayerShopUI
     * This closes the current window, and calls the controller and 
     * .fxml file of the window that is supposed to be open
     * @param event mouse click
     */
    @FXML
    private void viewShop(MouseEvent event) {
        getActionTarget().setText("team button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpShopScene();
    }
    
        /**
     * Function: To view the TeamsUI
     * This closes the current window, and calls the controller and 
     * .fxml file of the window that is supposed to be open
     * @param event mouse click
     */
    @FXML
    private void viewTeams(MouseEvent event) {
        getActionTarget().setText("team button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpTeamScene();
    }

        /**
     * Function: To view the CalendarUI
     * This closes the current window, and calls the controller and 
     * .fxml file of the window that is supposed to be open
     * @param event mouse click
     */
    @FXML
    private void viewCalendar(MouseEvent event) {
        getActionTarget().setText("movie button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpCalendarScene();
    }

        /**
     * Function: To view the HelptUI
     * This closes the current window, and calls the controller and 
     * .fxml file of the window that is supposed to be open
     * @param event mouse click
     */
    @FXML
    private void viewHelp(MouseEvent event) {
        getActionTarget().setText("home button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpHelpScene();
    }

    @FXML
    private void newSeason(MouseEvent event) {
        ListController.getInstance().getTheMatchList().getTheMatchList().clear();
        ListController.getInstance().createMatchList();
        updateDate();
        matchButton.setVisible(true);
        playText.setVisible(true);
        newSeasonButton.setVisible(false);
        newSeasonText.setVisible(false);
        ListController.getInstance().getTheTeamList().resetPoints();
    }

}
