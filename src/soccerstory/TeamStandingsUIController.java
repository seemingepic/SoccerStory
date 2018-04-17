/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
     ObservableList<Team> theTeamList = FXCollections.observableArrayList();
    @FXML
    private Text actionTarget;
    @FXML
    private Text actionTarget1;
    @FXML
    private Text currentPlace;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theTeamList = ListController.getInstance().getTheTeamList().getTeamData(); //grabs the teamlist
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


        
        teamTable.setItems(theTeamList);
    }
    
    /**
     * This function gets what place the team is in, then displays it in a text box
     */
    private void displayPlace() {
        String userTeam = ListController.getInstance().getTheTeamList().getCurrentUserTeam(); //get user team
        for (int i = 0; i < theTeamList.size(); i++) { //go thhrough list of teams
            if (theTeamList.get(i).getTeamName().equals(userTeam)) { //if current team = user team
                int teamPos = i; //save place
                String displayPlace = Integer.toString(i); //set place int to string 
                currentPlace.setText("Your team is currently in " + displayPlace + " place"); //display place
            }
        }
    }

    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
        
    
}
