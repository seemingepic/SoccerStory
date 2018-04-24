
package soccerstory;

import com.github.javafaker.Faker;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *  Purpose: This controls the PlayerSHopUI.fxml
 * A user can buy different players here
 * This class generates those players for the team
 * 
 *
 * @author mockl
 */
public class PlayerShopUIController implements Initializable {

    @FXML
    private Text actionTarget;
    @FXML
    private Text moneyAmount;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private Text actionTarget1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayMoney();
    }    
    
    /**
     * This displays how much money the team currently has
     */
    private void displayMoney()
    {
        int teamMoney = ListController.getInstance().getTheTeamList().getUserTeam().getCapSpace(); //get money
        String displayMoney = Integer.toString(teamMoney); //set money to string
        moneyAmount.setText("You currently have " + displayMoney + " coins"); //display money
    }

    /**
     * When the player presses a button this is the player that is created
     * If team has funds,
     * Subtract 100 coins from account
     * @param event 
     */
    @FXML
    private void buyAveragePlayer(ActionEvent event) {
        int currentMoney = ListController.getInstance().getTheTeamList().getUserTeam().getCapSpace(); //get current money
        if (currentMoney < 100) { //if cannot buy player, tell them why
            alert.setTitle("Cannot buy player");
            alert.setHeaderText("Low Funds");
            alert.setContentText("You do not have enough money to buy a player!");
            alert.showAndWait();
        } else {
            ListController.getInstance().getTheTeamList().getUserTeam().setCapSpace(currentMoney - 100); //change money
            developNewPlayer(1); // create player
            displayMoney(); //update money
        }
    }
    
    /**
     * Based on the type of player to generate, this does it
     * @param i  - the type of player which determines the stats used
     */
    public void developNewPlayer(int i)
    {
        Faker faker = new Faker();

        Player testPlayer = new Player();
        
        testPlayer = new PlayerBuilder(). //create the player and set each stat
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
        displayPlayerInfo(testPlayer); //displays player info
    }
    
   /**
    * This generates the random stat chance depending on who the player purchases
    * @param i - the type of player
    * @return - random stat generated
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
            int max = 88;
            int min = 70; //average = 80 between skills

            randomNum = r.nextInt((max-min) + 1) + min;
        }
        else if (i == 3)//average = 91
        {
            int max = 99;
            int min = 83;
           
            randomNum = r.nextInt((max-min) + 1) + min;
        }
        else if (i == 4) //Sure bet in the draft
        {
            int max = 75;
            int min = 74; //average = 66 between skills

            randomNum = r.nextInt((max - min) + 1) + min;

        } else if (i == 5) //Has potential 
        {
            int max = 90;
            int min = 65; //average = 80 between skills

            randomNum = r.nextInt((max - min) + 1) + min;
        } else //Super risk player
        {
            int max = 99;
            int min = 58;

            randomNum = r.nextInt((max - min) + 1) + min;
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
    
    /**
     * This generates a random position for the player based on the 
     * line up from the team (e.g 2/11 chance to get an attacker)
     * @return 
     */
    private String pickRandomPosition()
    {
        String[] positionArray = 
        {"A", "A", "M", "M", "M", "M", "D", "D", "D", "D", "G"}; //list of positions based on the field
        
         int max = 10;
         int min = 0; //average = 66 between skills
         Random r = new Random();

         int randomNum = r.nextInt((max-min) + 1) + min;
         
         String position = positionArray[randomNum];
         return position;
    }


    /**
     * Function: To display an alert with the player information 
     * 
     * @param testPlayer - player that was created
     * Creates alert
     */
    private void displayPlayerInfo(Player testPlayer)
    {
        alert.setTitle("You bought a player!");
        alert.setHeaderText(testPlayer.getName() + " Has Joined!");
        alert.setContentText("Overall:          " + testPlayer.getOverall() + "\n"
                    + "BallSkill:         " + testPlayer.getBallskill() + "\n"
                    + "Defense:        " + testPlayer.getDefense() + "\n"
                    + "Goal Skill:      " + testPlayer.getGoalie() + "\n"
                    + "Passing:         " + testPlayer.getPassing() + "\n"
                    + "Shooting:       " + testPlayer.getShooting() + "\n"
                    + "Speed:           " + testPlayer.getSpeed() + "\n");

        alert.showAndWait();
    }

        /**
     * When the player presses a button this is the player that is created
     * If team has funds,
     * Subtract 500 coins from account
     * @param event 
     */
    @FXML
    private void buyGoodPlayer(ActionEvent event) {
        int currentMoney = ListController.getInstance().getTheTeamList().getUserTeam().getCapSpace(); //get current money
        if (currentMoney < 100) { //if cannot buy player, tell them why
            alert.setTitle("Cannot buy player");
            alert.setHeaderText("Low Funds");
            alert.setContentText("You do not have enough money to buy a player!");
            alert.showAndWait();
        } else {
            ListController.getInstance().getTheTeamList().getUserTeam().setCapSpace(currentMoney - 500); //change money
            developNewPlayer(2); // create player
            displayMoney(); //update money
        }
    }

    /**
     * When the player presses a button this is the player that is created
     * If team has funds,
     * Subtract 1000 coins from account
     * @param event 
     */
    @FXML
    private void buyBestPlayer(ActionEvent event) {
        int currentMoney = ListController.getInstance().getTheTeamList().getUserTeam().getCapSpace(); //get current money
        if (currentMoney < 100) { //if cannot buy player, tell them why
            alert.setTitle("Cannot buy player");
            alert.setHeaderText("Low Funds");
            alert.setContentText("You do not have enough money to buy a player!");
            alert.showAndWait();
        } else {
            ListController.getInstance().getTheTeamList().getUserTeam().setCapSpace(currentMoney - 1000); //change money
            developNewPlayer(3); // create player
            displayMoney(); //update money
        }
    }

    @FXML
    private void viewHelp(ActionEvent event) {
            alert.setTitle("Help!");
            alert.setHeaderText("This is the Shop Section");
            alert.setContentText("Welcome to the Shop! \n"
                    + "This is where you can buy players to improve your team\n "
            + "You can use your coins which are listed above, to buy a player \n"
            + "Each option gives you a new player \n "
            + "The more expensive the option, the better chances of a good player \n ");

            alert.showAndWait();
    }
    
}
