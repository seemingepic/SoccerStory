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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theTeamList = ListController.getInstance().getTheTeamList().getTeamData();
        setUpList();
        // TODO
    }
    
    public void setUpList()
    {
        teamColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("teamName"));
        gamesPlayedColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("gamesPlayed"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("points"));


        
        teamTable.setItems(theTeamList);
    }

    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
        
    
}
