/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
 * Class name: GameLoaderController
 * Purpose: This is where the user can create their team name and start 
 * the game
 *
 * 
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (loadTeamData())
        {
            getGameInputOne().setVisible(false);
            createGame.setVisible(false);
            getGameNameTxt1().setText(ListController.getInstance().getTheTeamList().getUserTeam().getTeamName());
        }
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
        if (checker(getGameInputOne().getText()))
        {
            getGameNameTxt1().setText(getGameInputOne().getText());
            getGameInputOne().setVisible(false);
            getGameInputOne().setText("");
            createGame.setVisible(false);
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
    /**
     * Sets up scene to go to nav cntl
     */
    public void setUpNewScene()
    {
        Stage stage = (Stage) getGameNameTxt1().getScene().getWindow();
        stage.hide();
        createTeam();
        NavigationCntl.getNavigationCntl(stage);
        
    }

    /**
     * If the user clicks load game 1, this will load the game
     * @param event 
     */
    @FXML
    private void loadGame1(ActionEvent event) {
        if (getGameNameTxt1().getText().length() > 0)
        {
            this.setCurrentGameName(getGameNameTxt1().getText());
            setUpNewScene();
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
    
    /**
     * This checks if there is already team data on file, if there is then load it
     */
    private boolean loadTeamData()
    {
        if (new File("teamList.ser").exists()){ //if there is a file, get the list of users from there
        ArrayList<Team> theListOfTeams = (ArrayList<Team>) PersistentDataCntl.deserialize("teamList.ser");
        ListController.getInstance().getTheTeamList().setTheListOfTeams(theListOfTeams);
        return true;
        }
        return false;

    }
    
    /**
     * This creates the team in the master list
     */
    private void createTeam()
    {
        Team newTeam = new Team(currentGameName, "", "");
        newTeam.setPlayerTeam(Boolean.TRUE);
        ListController.getInstance().getTheTeamList().getTheListOfTeams().add(newTeam);
        ListController.getInstance().getTheTeamList().setCurrentUserTeam(currentGameName);
        createGame.setVisible(false);
    }

    /**
     * @return the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @return the createGame
     */
    public Button getCreateGame() {
        return createGame;
    }

    /**
     * @param createGame the createGame to set
     */
    public void setCreateGame(Button createGame) {
        this.createGame = createGame;
    }

    /**
     * @return the loadGame
     */
    public Button getLoadGame() {
        return loadGame;
    }

    /**
     * @param loadGame the loadGame to set
     */
    public void setLoadGame(Button loadGame) {
        this.loadGame = loadGame;
    }

    /**
     * @return the gameListOne
     */
    public Text getGameListOne() {
        return gameListOne;
    }

    /**
     * @param gameListOne the gameListOne to set
     */
    public void setGameListOne(Text gameListOne) {
        this.gameListOne = gameListOne;
    }

    /**
     * @return the gameName1
     */
    public String getGameName1() {
        return gameName1;
    }

    /**
     * @param gameName1 the gameName1 to set
     */
    public void setGameName1(String gameName1) {
        this.gameName1 = gameName1;
    }

    /**
     * @return the gameNameTxt1
     */
    public Text getGameNameTxt1() {
        return gameNameTxt1;
    }

    /**
     * @param gameNameTxt1 the gameNameTxt1 to set
     */
    public void setGameNameTxt1(Text gameNameTxt1) {
        this.gameNameTxt1 = gameNameTxt1;
    }

    /**
     * @return the gameInputOne
     */
    public TextField getGameInputOne() {
        return gameInputOne;
    }

    /**
     * @param gameInputOne the gameInputOne to set
     */
    public void setGameInputOne(TextField gameInputOne) {
        this.gameInputOne = gameInputOne;
    }

    /**
     * @return the currentGameName
     */
    public String getCurrentGameName() {
        return currentGameName;
    }

    /**
     * @param currentGameName the currentGameName to set
     */
    public void setCurrentGameName(String currentGameName) {
        this.currentGameName = currentGameName;
    }

    
    
}
