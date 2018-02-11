/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.Random;
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
public class PlayerListUIController implements Initializable {


    ObservableList<Player> theTotalPlayerList = FXCollections.observableArrayList();
    ObservableList<Player> thePlayerList = FXCollections.observableArrayList();
    private String currentTeamName;
    @FXML
    private TableView<?> benchTable;
    @FXML
    private TableColumn<?, ?> nameBenchColumn;
    @FXML
    private TableColumn<?, ?> positionBenchColumn;
    @FXML
    private Text actionTarget;
    @FXML
    private TableView<Player> attackerTable;
    @FXML
    private TableView<Player> midfieldTable;
    @FXML
    private TableView<Player> defenderTable;
    @FXML
    private TableView<Player> goalieTable;
    @FXML
    private TableColumn<Player, String> attackerNameColumn;
    @FXML
    private TableColumn<Player, String> attackerPositionColumn;
    @FXML
    private TableColumn<Player, String> defenderNameColumn;
    @FXML
    private TableColumn<Player, String> defenderPositionColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        theTotalPlayerList = ListController.getInstance().getThePlayerList().getThePlayerList();
        currentTeamName = ListController.getInstance().getTheTeamList().getCurrentUserTeam();
        getPlayers();
        setUpAttackerList();
        // TODO
    }  
    
    public void setUpAttackerList()
    {
        attackerNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        attackerPositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));

        
        attackerTable.setItems(thePlayerList);
    }
    


    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
    
    /**
     * maybe turn this into hash stuff for better performance
     */
    public void getPlayers()
    {
        for(int i = 0; i < theTotalPlayerList.size(); i++)
        {
            if(theTotalPlayerList.get(i).getTeamPlayFor().equals(currentTeamName))
            {
                thePlayerList.add(theTotalPlayerList.get(i));
            }
        }
        
    }
    


}
