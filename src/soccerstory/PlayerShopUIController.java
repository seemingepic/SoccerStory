/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import com.github.javafaker.Faker;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mockl
 */
public class PlayerShopUIController implements Initializable {

    @FXML
    private Text actionTarget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * When the player presses a button this is the player that is created
     * @param event 
     */
    @FXML
    private void buyAveragePlayer(ActionEvent event) {
        developNewPlayer(1);
    }
    
    private void developNewPlayer(int i)
    {
        Faker faker = new Faker();

        Player testPlayer = new Player();
        
        testPlayer = new PlayerBuilder().
                setBallskill(randomStat(i)).
                setDefense(randomStat(i)).
                 setSpeed(randomStat(i))
                .setGoalie(randomStat(i))
                .setPassing(randomStat(i))
                .setShooting(randomStat(i))
                .setPosition(pickRandomPosition())
                .setBenched(true)
                .setTeamPlayFor(ListController.getInstance().getTheTeamList().getCurrentUserTeam()).
                setName(faker.name().firstName() + " " + faker.name().
                        lastName()).getPlayer();
        
        ListController.getInstance().getThePlayerList().getThePlayerList().add(testPlayer);
    }
    
   /**
    * This generates the random stat chance depending on who the player purchases
    * @param i
    * @return 
    */
    private int randomStat(int i)
    {
        int randomNum = 0;
        Random r = new Random();
        if (i == 1) //average player
        {
            int max = 82;
            int min = 50; //average = 66 between skills

            randomNum = r.nextInt((max-min) + 1) + min;

        }
        else if (i == 2)
        {
            int max = 90;
            int min = 71; //average = 80 between skills

            randomNum = r.nextInt((max-min) + 1) + min;
        }
        else //average = 91
        {
            int max = 99;
            int min = 83;
           
            randomNum = r.nextInt((max-min) + 1) + min;
        }
        return randomNum;
    }  

    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();

        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }
    
    private String pickRandomPosition()
    {
        String[] positionArray = 
        {"A", "A", "M", "M", "M", "M", "D", "D", "D", "D", "G"};
        
         int max = 10;
         int min = 0; //average = 66 between skills
         Random r = new Random();

         int randomNum = r.nextInt((max-min) + 1) + min;
         
         String position = positionArray[randomNum];
         return position;
    }

    @FXML
    private void buyGoodPlayer(ActionEvent event) {
        developNewPlayer(2);
    }

    @FXML
    private void buyBestPlayer(ActionEvent event) {
        developNewPlayer(3);
    }
    
}
