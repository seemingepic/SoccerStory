/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *  This class is used to control the TeamSTandingsUI.fxml and show how each team is doing in the league
 *  
 * @author mockl
 */
public class TeamStandingsUIController implements Initializable {

    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, String> teamColumn;
    @FXML
    private TableColumn<Team, String> gamesPlayedColumn;
    @FXML
    private TableColumn<Team, String> pointsColumn;
    
     ObservableList<Team> theObservableTeamList = FXCollections.observableArrayList();
     ArrayList<Team> theTeamList;
    @FXML
    private Text actionTarget;
    @FXML
    private Text actionTarget1;
    @FXML
    private Text currentPlace;
     Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theObservableTeamList = ListController.getInstance().getTheTeamList().getTeamData(); //grabs the teamlist
        theTeamList = ListController.getInstance().getTheTeamList().getTheListOfTeams();
        setUpList();
        displayPlace();
        // TODO
    }
    
    /**
     * Sets up list of teams to be displayed in the team table
     */
    public void setUpList()
    {
        teamColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("teamName"));
        gamesPlayedColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("gamesPlayed"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("points"));


        
        teamTable.setItems(theObservableTeamList);
    }
    
    /**
     * This function gets what place the team is in, then displays it in a text box
     */
    private void displayPlace() {
        String userTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam(); //get user team
        String ending = "th"; // ending like 1st, 2nd, 3rd
        Collections.sort(theTeamList, new CustomComparator() ); //sort teams in descending order
        int teamPos = 0;
        for (int i = 0; i < theTeamList.size(); i++) { //go thhrough list of teams
            if (theTeamList.get(i).getTeamName().equals(userTeam)) { //if current team = user team
                teamPos = i+1; //save place

            }
        }
        String displayPlace = Integer.toString(teamPos); //set place int to string
        if (teamPos == 1) {
            ending = "st";
        } else if (teamPos == 2) {
            ending = "nd";
        } else if (teamPos == 3) {
            ending = "rd";
        } else {
            ending = "th";
        }
        currentPlace.setText("Your team is currently in " + displayPlace + ending + " place"); //display place
    }

    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    @FXML
    private void viewHelp(ActionEvent event) {
            alert.setTitle("Help!");
            alert.setHeaderText("This is the team section");
            alert.setContentText("Welcome to the Standings!! \n"
                    + "You can see every team in the season here! \n "
            + "Every win = 3 points, Draw = 1 point, Loss = 0 points \n"
            + "Teams are positined based on toal points \n" 
            + "Your ranking is listed in white in the middle of the screen ");

            alert.showAndWait();
    }
        
    
}
