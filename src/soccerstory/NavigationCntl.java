/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mockl
 */
public class NavigationCntl {
       
    
    private Stage stage;
    private static NavigationCntl theNavigationCntl;
    
    
    //sets up constructor 
    private NavigationCntl(Stage theExistingStage){
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
 
    
}
