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
        calendarController=loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String date = "week ";
        date = date + calendarController.getCurrentWeek();
        weekLabel.setText(date);
    }


    @FXML
    private void viewPlayers(ActionEvent event) {

        actionTarget.setText("player button pressed");
        Stage theStage = (Stage) actionTarget.getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpPlayerScene();
    }

    @FXML
    private void viewTeams(ActionEvent event) {
        actionTarget.setText("team button pressed");
        Stage theStage = (Stage) actionTarget.getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpTeamScene();
    }
    
    private void getTeamName()
    {
        tester.setText(ListController.getInstance().getTheTeamList().getCurrentUserTeam());
    }

    @FXML
    private void startMatch(ActionEvent event) {
        actionTarget.setText("match button pressed");
        Stage theStage = (Stage) actionTarget.getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpMatchScene();
    }

    @FXML
    private void viewCalendar(ActionEvent event) {
        actionTarget.setText("movie button pressed");
        Stage theStage = (Stage) actionTarget.getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpCalendarScene();
    }
    
}
