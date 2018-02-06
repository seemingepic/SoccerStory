/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author mockl
 */
public class GameLoaderController implements Initializable {
    
    private Label label;
    @FXML
    private Button createGame;
    @FXML
    private Button loadGame;
    @FXML
    private Text gameListOne;
    
    private String gameName1 = "";
    @FXML
    private Text gameNameTxt1;
    @FXML
    private TextField gameInputOne;
    
    private String currentGameName;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * This is the method that deals with creating a new game
     * It checks if the user has inputted a name, and if it has 
     * sets that name to be permenant
     * @param event 
     */
    
    @FXML
    private void gameCreation(ActionEvent event) {
        /**
         * checks to make sure game name (team name ) is valid
         */
        if (checker(gameInputOne.getText()))
        {
            gameNameTxt1.setText(gameInputOne.getText());
            gameInputOne.setVisible(false);
            gameInputOne.setText("");
        }
        else //if not valid send them a little message telling em
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Team Name");
            alert.setHeaderText("Team Name not Valid");
            alert.setContentText("Please enter a name in one of the fields!");

            alert.showAndWait();
        }
        
    }
    
    /**
     * Checks to ensure team name is long enough, if not return false
     * @param input
     * @return 
     */
    public boolean checker(String input)
    {
        if (input.length() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setUpNewScene()
    {
        Stage stage = (Stage) gameNameTxt1.getScene().getWindow();
        stage.hide();
        NavigationCntl.getNavigationCntl(stage);
        
    }

    @FXML
    private void loadGame1(ActionEvent event) {
        if (gameNameTxt1.getText().length() > 0)
        {
            setUpNewScene();
            this.currentGameName = gameNameTxt1.getText();
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No game create");
            alert.setHeaderText("not created name" );
            alert.setContentText("not created game");

            alert.showAndWait();
        }
        
    }
    
    
}
