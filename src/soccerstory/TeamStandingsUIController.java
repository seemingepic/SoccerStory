
package soccerstory;

import java.net.URL;
import java.util.ArrayList;
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
 *  Displays each teams points, name and game played 
 * @author mockl
 */
public class TeamStandingsUIController implements Initializable {

    @FXML
    private TableView<Team> teamTable; //Table of all the teams 
    @FXML
    private TableColumn<Team, String> teamColumn; //where the name is displayed 
    @FXML
    private TableColumn<Team, String> gamesPlayedColumn; //how many games the team has played 
    @FXML
    private TableColumn<Team, String> pointsColumn; //how many points the team has 
    
     ObservableList<Team> theObservableTeamList = FXCollections.observableArrayList();
     ArrayList<Team> theTeamList; //List of teams 
    @FXML
    private Text actionTarget;
    @FXML
    private Text actionTarget1;
    @FXML
    private Text currentPlace; //Current place of the team 
     Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theObservableTeamList = ListController.getInstance().getTheTeamList().getTeamData(); //grabs the observablelist teamlist
        theTeamList = ListController.getInstance().getTheTeamList().getTheListOfTeams(); //Grabs an arrayList of team 
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

    /**
     * Sends user back to navUi 
     * @param event - player clicks home button
     */
    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    /**
     * Displays help for the user to read
     * @param event -- player clicks help button
     */
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
