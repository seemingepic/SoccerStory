/* Class: PlayerListUIController
 * This class allows the player to manage the line up for their team.
 *
 *
 *
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PlayerListUIController implements Initializable {


    ObservableList<Player> theTeamPlayerList = FXCollections.observableArrayList();
    ObservableList<Player> thePlayerList = FXCollections.observableArrayList();
    private String currentTeamName;
    @FXML
    private TableView<Player> benchTable;
    @FXML
    private TableColumn<Player, String> nameBenchColumn;
    @FXML
    private TableColumn<Player, String> positionBenchColumn;
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
    
    private ObservableList<Player> attackerListTable = FXCollections.observableArrayList();
    private ObservableList<Player> defenderListTable = FXCollections.observableArrayList();
    private ObservableList<Player> midfieldListTable = FXCollections.observableArrayList();
    private ObservableList<Player> goalieListTable = FXCollections.observableArrayList();
    private ObservableList<Player> benchListTable = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Player, String> midfieldNameColumn;
    @FXML
    private TableColumn<Player, String> midfieldPositionColumn;
    @FXML
    private TableColumn<Player, String> goalieNameColumn;
    @FXML
    private TableColumn<Player, String> goaliePositionColumn;
    
    Player clickedPlayer;
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        currentTeamName = ListController.getInstance().getTheTeamList().
                getCurrentUserTeam(); //Gets current team name from when you logged on
        
        theTeamPlayerList = FXCollections.observableArrayList(ListController.
                getInstance().getThePlayerList().getPlayersFromTeam(currentTeamName)); //Gets all the players from team name
        
        filterPlayers(); //Puts players into proper position
        setUpLists(); //Sets up list based on what position the players are in
        // TODO
    }  
    /**
     * This method calls all the set up methods to simplfy code
     */
    private void setUpLists()
    {
        setUpAttackerList();
        setUpMidfieldList();
        setUpDefenderList();
        setUpGoalieList();
        setUpBench(); 
    }
    
    /**
     * All the methods with setUp get all the players from the proper table,
     * and then put them into the prober observablelist table
     */
    public void setUpAttackerList()
    {
        attackerNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        attackerPositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        
        attackerTable.setItems(attackerListTable);
    }
    
    public void setUpMidfieldList()
    {
        midfieldNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        midfieldPositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        
        midfieldTable.setItems(midfieldListTable);
    }
    
    public void setUpDefenderList()
    {
        defenderNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        defenderPositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        
        defenderTable.setItems(defenderListTable);
    }

    public void setUpGoalieList()
    {
        goalieNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        goaliePositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        
        goalieTable.setItems(goalieListTable);
    }
    
    public void setUpBench()
    {
        nameBenchColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        positionBenchColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        
        benchTable.setItems(benchListTable);     
    }
    /**
     * Filter players goes through the list of players that was refrenced from the initialize screen
     * and then puts them in the appropriate table based on their position and bench attributes
     */
    private void filterPlayers()
    {

        for (int i = 0; i < theTeamPlayerList.size(); i++)
        {
            if (theTeamPlayerList.get(i).getPosition().equals("A") && !(theTeamPlayerList.get(i).isBenched()))
                attackerListTable.add(theTeamPlayerList.get(i)); //If the position && player is not benched, add to appropriate table
            else if(theTeamPlayerList.get(i).getPosition().equals("M") && !(theTeamPlayerList.get(i).isBenched()))
                midfieldListTable.add(theTeamPlayerList.get(i));
            else if(theTeamPlayerList.get(i).getPosition().equals("D") && !(theTeamPlayerList.get(i).isBenched()))
                defenderListTable.add(theTeamPlayerList.get(i));
            else if(theTeamPlayerList.get(i).getPosition().equals("G") && !(theTeamPlayerList.get(i).isBenched()))
                goalieListTable.add(theTeamPlayerList.get(i));
            else
                benchListTable.add(theTeamPlayerList.get(i));
              
        }
    }
    

    /**
     * This method is called when the player clicks the button to move to starter
     * This gets the player selected, checks their position, then moves them to
     * that players position
     * @param name 
     */
    public void movePlayerToAppropriateTable(String name) {
        for (int i = theTeamPlayerList.size() - 1; i >= 0; i--) {
            if (theTeamPlayerList.get(i).getName().equals(name)) //find player name in table
            {
                if (theTeamPlayerList.get(i).isBenched()) //if the player is benched
                {
                    switch (theTeamPlayerList.get(i).getPosition()) { //add them to the correct table based on position
                        case "A":
                            if (attackerListTable.size() < 2) { //limits size of team in order to make sure lineup works
                                attackerListTable.add(theTeamPlayerList.get(i));
                                removeFromBench(name); //remove them from the bench 
                                updateMainList(name); //updates player position
                            } else
                                displayError(); //if players are already maxed xplain why
                            break;
                        case "M":
                            if (midfieldListTable.size() < 4) {
                                midfieldListTable.add(theTeamPlayerList.get(i));
                                removeFromBench(name);
                                updateMainList(name);
                            } else
                                displayError();
                            break;
                        case "D":
                            if (defenderListTable.size() < 4) {
                                defenderListTable.add(theTeamPlayerList.get(i));
                                removeFromBench(name);
                                updateMainList(name);
                            }else
                                displayError();
                            break;
                        default:
                            if (goalieListTable.size() < 1) {
                                goalieListTable.add(theTeamPlayerList.get(i));
                                removeFromBench(name);
                                updateMainList(name);
                            }else
                                displayError();
                            break;
                    }
                } else { //if they are not benched
                    benchListTable.add(theTeamPlayerList.get(i)); //move them to the bench
                    clickedPlayer = null; //reset who is clicked 
                    switch (theTeamPlayerList.get(i).getPosition()) { //put them in the right position table
                        case "A":
                            removeFromStarter(name, attackerListTable);
                            break;
                        case "M":
                            removeFromStarter(name, midfieldListTable);
                            break;
                        case "D":
                            removeFromStarter(name, defenderListTable);
                            break;
                        default:
                            removeFromStarter(name, goalieListTable);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Removes the player from the starter table
     * @param name
     * @param table 
     */
    public void removeFromStarter(String name, ObservableList<Player> table) 
    {
        for(int i = table.size()-1; i>= 0; i--)
        {
            if (table.get(i).getName().equals(name))
            {
                table.remove(i);
            }
        }
        
    }
    
    /**
     * removes the player passed to remove from bench
     * @param name 
     */
    public void removeFromBench(String name)
    {
        for (int i = benchListTable.size()-1; i>=0; i--)
        {
            if (benchListTable.get(i).getName().equals(name))
            {
                benchListTable.remove(i);
            }
        }
    }

    /**
     * Sends user home if they have a full team
     * @param event 
     */
    @FXML
    private void goHome(ActionEvent event) {
        if (checkForFullRoster()) { //if roster is full then move them home
            actionTarget.setText("log on pressed");
            Stage stage = (Stage) actionTarget.getScene().getWindow();
            stage.hide();

            NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
        } else { //if the roster is not full, tell them and do not let them leave (may need to add this before a match is initated too )
            alert.setTitle("You cannot go home!");
            alert.setHeaderText("Invalid Roster");
            alert.setContentText("Your team does not have enough players! \n"
                    + "Make sure you have 2 attackers, 4 midfielders, "
                    + "4 defenders, and 1 goalie!");

            alert.showAndWait();
        }
    }

    /**
     * Sends selected player to the play area
     * @param event 
     */
    @FXML
    private void moveToPlay(ActionEvent event) {

        String name = benchTable.getSelectionModel().getSelectedItem().getName();
        movePlayerToAppropriateTable(name);

        setUpLists();

    }

    /**
     * Sends selected player to the bench area
     * @param event 
     */
    @FXML
    private void moveToBench(ActionEvent event) {
        String name = clickedPlayer.getName();
        System.out.println(clickedPlayer);
        movePlayerToAppropriateTable(name);
        updateMainList(name);
        setUpLists();
        
    }
    
    private void updateMainList(String name)
    {
        ListController.getInstance().getThePlayerList().changePlayerBench(name);
    }

    @FXML
    private void getSelectedClick(MouseEvent event) {
        clickedPlayer = attackerTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void getSelectedMidfielder(MouseEvent event) {
        clickedPlayer = midfieldTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void getSelectedDefender(MouseEvent event) {
        clickedPlayer = defenderTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void getSelectedGoalie(MouseEvent event) {
       clickedPlayer = goalieTable.getSelectionModel().getSelectedItem();
    }
    
    
    private void displayError()
    {
            alert.setTitle("Player cannot be added");
            alert.setHeaderText("Player was not moved to start" );
            alert.setContentText("You already have enough players there!");

            alert.showAndWait();
    }
    
    /**
     * Checks the lists to make sure they are full
     * A full team roster is required to play
     * @return 
     */
    public boolean checkForFullRoster()
    {
        if (!(attackerListTable.size() == 2))
        {
            return false;
        }
        else if (!(midfieldListTable.size() == 4))
        {
            return false;
        }
        else if (!(defenderListTable.size() == 4))
        {
            return false;
        }
        else if (!(goalieListTable.size() == 1))
        {
            return false;
        }
        else
            return true;
    }
    


}
