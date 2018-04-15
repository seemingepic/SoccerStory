/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *  This is the controller that is used to pass controllers to each other from
 * .fxml to .fxml
 * @author mockl
 */
public class NavigationCntl {
       
    
    private Stage stage;
    private static NavigationCntl theNavigationCntl;

    
    
    //sets up constructor 
    private NavigationCntl(Stage theExistingStage) {
        if (new File("teamList.ser").exists()) { //if there is a file, get the list of users from there
            ArrayList<Player> theListOfPlayers = (ArrayList<Player>) PersistentDataCntl.deserialize("playerList.ser");
            ListController.getInstance().createPlayerList();
            ListController.getInstance().getThePlayerList().setThePlayerListFromArray(theListOfPlayers);
        } else {
            ListController.getInstance().createPlayerList();
        }

        if (new File("matchList.ser").exists()) { //if there is a file, get the list of users from there
            ArrayList<Match> theListOfMatches = (ArrayList<Match>) PersistentDataCntl.deserialize("matchList.ser");
            ListController.getInstance().createMatchList();
            ListController.getInstance().getTheMatchList().setTheMatchList(theListOfMatches);
        } else {
            ListController.getInstance().createMatchList();
        }

        stage = theExistingStage;
        this.setUpNavigationScene();
        stage.show();

    }
    
    //gets the navigation controller
    //singleton method
    public static NavigationCntl getNavigationCntl(Stage theStage){
        if(theNavigationCntl != null){
            return theNavigationCntl;
        }else{
            theNavigationCntl = new NavigationCntl(theStage);
            return theNavigationCntl;
        }
    }

    /**
     * This method is used to initially start the main screen,
     * This gets the controller calls it and creates it
     */
    public void setUpNavigationScene(){
        Parent root;
        Scene scene;
        try{
            root = FXMLLoader.load(getClass().getResource("NavigationUI.fxml"));
            scene = new Scene(root);
            stage.setTitle("Navigation");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        /**
     * This method is used to create the player management screen
     * This gets the controller calls it and creates it
     */
    public void setUpPlayerScene()
    {
        Parent root;
        Scene scene;
        try{
            root = FXMLLoader.load(getClass().getResource("PlayerListUI.fxml"));
            scene = new Scene(root);
            stage.setTitle("Players");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        } 
    }

        /**
     * This method is used to create the teamlist scene
     * This gets the controller calls it and creates it
     */
    public void setUpTeamScene()
    {
        Parent root;
        Scene scene;
        try{
            root = FXMLLoader.load(getClass().getResource("TeamStandingsUI.fxml"));
            scene = new Scene(root);
            stage.setTitle("Team");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    /**
     * This is used to create the MatchUI screen where the player can simulate their games
     */
    public void setUpMatchScene()
    {
        Parent root;
        Scene scene;
        try{
            root = FXMLLoader.load(getClass().getResource("MatchUI.fxml"));
            scene = new Scene(root);
            stage.setTitle("Team");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    /**
     * This is used to set up the CalendarUI screen where the player
     * can see how they have done in previous matches
     */
    public void setUpCalendarScene()
    {
        Parent root;
        Scene scene;
        try{
            root = FXMLLoader.load(getClass().getResource("CalendarUI.fxml"));
            scene = new Scene(root);
            stage.setTitle("Calendar");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    /**
     * This is where a user can buy players from the computer
     */
    public void setUpShopScene()
    {
        Parent root;
        Scene scene;
        try{
            root = FXMLLoader.load(getClass().getResource("PlayerShopUI.fxml"));
            scene = new Scene(root);
            stage.setTitle("Shop");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        } 
        
    }

 
    
}
