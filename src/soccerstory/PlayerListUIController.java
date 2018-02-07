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
public class PlayerListUIController implements Initializable {

    @FXML
    private TableView<Player> starterTable;
    @FXML
    private TableColumn<Player, String> nameColumn;
    @FXML
    private TableColumn<Player, String> positionColumn;
    
        ObservableList<Player> thePlayerList = FXCollections.observableArrayList();
    @FXML
    private TableView<?> benchTable;
    @FXML
    private TableColumn<?, ?> nameBenchColumn;
    @FXML
    private TableColumn<?, ?> positionBenchColumn;
    @FXML
    private Text actionTarget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        thePlayerList = ListController.getInstance().getThePlayerList().getThePlayerList();
        setUpList();
        // TODO
    }  
    
    public void setUpList()
    {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));

        
        starterTable.setItems(thePlayerList);
    }

    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
    
}
