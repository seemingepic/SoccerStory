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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mockl
 */
public class HelpUIController implements Initializable {

    @FXML
    private Text actionTarget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewHelp(MouseEvent event) {
    }

    @FXML
    private void viewPlayers(MouseEvent event) {
    }

    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    @FXML
    private void viewShop(MouseEvent event) {
    }

    @FXML
    private void viewTeams(MouseEvent event) {
    }

    @FXML
    private void startMatch(MouseEvent event) {
    }
    
}
