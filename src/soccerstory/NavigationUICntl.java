 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListController.getInstance();
        getTeamName();
        updateDate();
    }
    
    private void updateDate()
    {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("CalendarUI.fxml"));
        Parent root = (Parent) loader.load();
            setCalendarController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String date = "week ";
        date = date + getCalendarController().getCurrentWeek();  
        weekLabel.setText(date);
        setNextMatch(getCalendarController().getNextMatch());
        getMatchInfo();
        teamLabel.setText(otherTeam);
        
    }
    
    private void getMatchInfo()
    {
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
    
    private void getTeamName()
    {
        getTester().setText(ListController.getInstance().getTheTeamList().getCurrentUserTeam());
    }

    @FXML
    private void startMatch(ActionEvent event) {
        getActionTarget().setText("match button pressed");
        Stage theStage = (Stage) getActionTarget().getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpMatchScene();
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
    
}
