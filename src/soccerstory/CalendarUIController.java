/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * This class is designed to keep tracked of the weeks for the game.
 * It also will be used for the player to see whats goin on next week
 * 
 * @author mockl
 */
public class CalendarUIController implements Initializable {

    @FXML
    private Label weekLabel;
    
    private int currentWeek = 1;
    @FXML
    private Label actionTarget;
    @FXML
    private Label litfam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCalendar();
    }

    /**
     * This class is designed to update the current week label with the
     * current week
     */
    private void updateCalendar()
    {
        String currentWeekLabel = weekLabel.getText();
        currentWeekLabel = currentWeekLabel + Integer.toString(currentWeek);
        weekLabel.setText(currentWeekLabel);
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
     * @param event 
     */
    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
           
    
}
