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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               ListController.getInstance();
    }    

    @FXML
    private void viewPlayers(ActionEvent event) {

        actionTarget.setText("movie button pressed");
        Stage theStage = (Stage) actionTarget.getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpPlayerScene();
    }

    @FXML
    private void viewTeams(ActionEvent event) {
        actionTarget.setText("movie button pressed");
        Stage theStage = (Stage) actionTarget.getScene().getWindow();
        theStage.hide();
        NavigationCntl.getNavigationCntl(theStage).setUpTeamScene();
    }
    
}
