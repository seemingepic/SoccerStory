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
    @FXML
    private Button playersButton;
    @FXML
    private Text actionTarget;
    private GameLoaderController GameLoaderController;
    @FXML
    private Label weekLabel;
    @FXML
    private Label teamLabel;
    private CalendarUIController calendarController;

    private String otherTeam;
    private Match nextMatch;
    private Boolean home;
    @FXML
    private Label homeOrAway;
    @FXML
    private Button matchButton;
    private PlayerShopUIController playerController;
    
    private static Boolean hasDrafted = false;
    @FXML
    private Button newSeasonButton;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CalendarUI.fxml"));
            Parent root = (Parent) loader.load();
            setCalendarController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (getCalendarController().getCurrentWeek() < 10) { 
            String date = "week ";
            date = date + getCalendarController().getCurrentWeek();
            weekLabel.setText(date);
            setNextMatch(getCalendarController().getNextMatch());
            getMatchInfo();
            teamLabel.setText(otherTeam);
        } else {
            String date = "SEASON OVER";
            weekLabel.setText(date);
            setNextMatch(getCalendarController().getNextMatch());
            teamLabel.setText("NONE");
            matchButton.setVisible(false);
            
            if (hasDrafted == false)
            {
                draftDay();
                hasDrafted = true;
            }
            newSeasonButton.setVisible(true);
            
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
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("It's Draft Day!");
        alert.setHeaderText("You have the oppurtunity to pick between three players");
        alert.setContentText("Choose who you want");

        ButtonType buttonTypeOne = new ButtonType("Sure Bet");
        ButtonType buttonTypeTwo = new ButtonType("Good and has potential");
        ButtonType buttonTypeThree = new ButtonType("Lots of upside");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);
        Optional<ButtonType> result = alert.showAndWait();

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

    @FXML
    private void viewPlayers(ActionEvent event) {

        getActionTarget().setText("player button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpPlayerScene();
    }

    @FXML
    private void viewTeams(ActionEvent event) {
        getActionTarget().setText("team button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpTeamScene();
    }

    private void getTeamName() {
        getTester().setText(ListController.getInstance().getTheTeamList().getCurrentUserTeam());
    }

    @FXML
    private void startMatch(ActionEvent event) {
        getActionTarget().setText("match button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpMatchScene();
        Simulation simulator = new Simulation();
        simulator.simulateOtherMatches();
    }

    @FXML
    private void viewCalendar(ActionEvent event) {
        getActionTarget().setText("movie button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpCalendarScene();
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

    @FXML
    private void viewShop(ActionEvent event) {
        getActionTarget().setText("team button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpShopScene();
    }

    @FXML
    private void newSeason(ActionEvent event) {
        ListController.getInstance().getTheMatchList().getTheMatchList().clear();
        ListController.getInstance().createMatchList();
        updateDate();
        matchButton.setVisible(true);
        newSeasonButton.setVisible(false);
        ListController.getInstance().getTheTeamList().resetPoints();
    }

}
