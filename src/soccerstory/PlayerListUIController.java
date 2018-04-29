/* Class: PlayerListUIController
 * This class allows the player to manage the line up for their team.
 * The team is broken down into midfield, attack, defense, and goalie by the computer
    Each position has a different table, each table is managed seperately
    The player must have a full lineup before leaving
 */
package soccerstory;

import java.net.URL;
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
    private String currentTeamName; //Users team name 
    @FXML
    private TableView<Player> benchTable; //Where all the players on the bench are stored 
    @FXML
    private TableColumn<Player, String> nameBenchColumn; //names for benched players
    @FXML 
    private TableColumn<Player, String> positionBenchColumn; //what position the benched players are 
    @FXML
    private Text actionTarget; //used for going to nav ui
    @FXML
    private TableView<Player> attackerTable; //all the attackers are displayed here 
    @FXML
    private TableView<Player> midfieldTable;
    @FXML
    private TableView<Player> defenderTable;
    @FXML
    private TableView<Player> goalieTable;
    @FXML
    private TableColumn<Player, String> attackerNameColumn; //name of attackers 
 
    @FXML
    private TableColumn<Player, String> attackerPositionColumn; //position of attackers 
    @FXML
    private TableColumn<Player, String> defenderNameColumn;
    @FXML
    private TableColumn<Player, String> defenderPositionColumn;
    
    private ObservableList<Player> attackerListTable = FXCollections.observableArrayList(); //List of each table 
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
    
    Player clickedPlayer; //Player that has been clicked on 
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //Alert to display info 
    @FXML
    private TableColumn<Player, Integer> attackerOverallColumn; // Player Overall for each of the columns 
    @FXML
    private TableColumn<Player, Integer> midfieldOverallColumn;
    @FXML
    private TableColumn<Player, Integer> defenderOverallColumn;
    @FXML
    private TableColumn<Player, Integer> goalieOverallColumn;
    @FXML
    private TableColumn<Player, Integer> benchOverallColumn;
    @FXML
    private Text actionTarget1;

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
        attackerOverallColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("overall"));
        
        attackerTable.setItems(attackerListTable);
    }
    
    /**
     * Displays all of the starting midfielders 
     */
    public void setUpMidfieldList()
    {
        midfieldNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        midfieldPositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        midfieldOverallColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("overall"));
        
        midfieldTable.setItems(midfieldListTable);
    }
    
    /**
     * Displays all the starting defenseman
     */
    public void setUpDefenderList()
    {
        defenderNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        defenderPositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        defenderOverallColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("overall"));
        
        defenderTable.setItems(defenderListTable);
    }

    /**
     * Displays the starting goalie 
     */
    public void setUpGoalieList()
    {
        goalieNameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        goaliePositionColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        goalieOverallColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("overall"));
        
        goalieTable.setItems(goalieListTable);
    }
    
    /**
     * Displays all of the players on the bench
     */
    public void setUpBench()
    {
        nameBenchColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        positionBenchColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("position"));
        benchOverallColumn.setCellValueFactory(new PropertyValueFactory<Player,Integer>("overall"));
        
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
     * @param name -- Name of player that is moving
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
     * @param name -- Name of player 
     * @param table - The table that the player is being move from
     */
    public void removeFromStarter(String name, ObservableList<Player> table) 
    {
        for(int i = table.size()-1; i>= 0; i--)
        {
            if (table.get(i).getName().equals(name)) //if the names match 
            {
                table.remove(i); //remove the player
            }
        }
        
    }
    
    /**
     *  Removes a player from the bench 
     * @param name -- name of player being moved 
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
     * 
     * Sends user home if they have a full team
     * If not, display an error
     * @param event  -- The user clicking the go home button
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
     * Grabs the clicked player
     * Displays all of the stats of the player
     */
    private void viewPlayerInfo()
    {
            alert.setTitle("Player Stats");
            alert.setHeaderText("1-99");
            alert.setContentText("Overall:          " + clickedPlayer.getOverall() + "\n"
                    + "BallSkill:         " + clickedPlayer.getBallskill() + "\n"
                    + "Defense:        " + clickedPlayer.getDefense() + "\n"
                    + "Goal Skill:      " + clickedPlayer.getGoalie() + "\n"
                    + "Passing:         " + clickedPlayer.getPassing() + "\n"
                    + "Shooting:       " + clickedPlayer.getShooting() + "\n"
                    + "Speed:           " + clickedPlayer.getSpeed() + "\n");
        //The code below gives the proper stats based on whatever the position is, the spacing makes it look good
        switch (clickedPlayer.getPosition()) {
            case "A":
                alert.setContentText(alert.getContentText() + "Goals             " + clickedPlayer.getStats().getGoals());
                break;
            case "G":
                alert.setContentText(alert.getContentText() + "Shots against:          " + clickedPlayer.getStats().getShotsAgainst());
                alert.setContentText(alert.getContentText() + "Goals against:          " + clickedPlayer.getStats().getGoalsAllowed());
                break;
            case "M":
                alert.setContentText(alert.getContentText() + "Successful Passes:          " + clickedPlayer.getStats().getPasses());
                break;
            default:
                break;
        }

            alert.showAndWait();
        System.out.println(clickedPlayer.getDefense());
    }

    /**
     * Sends selected player to the correct player table
     * @param event - Click the Move Player button 
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
    
    /**
     * Update player list with new attribute for if the player is benched or not
     * Used if the player gets moved from the starter table or moved from the bench table
     * 
     * @param name - name of player
     */
    private void updateMainList(String name)
    {
        ListController.getInstance().getThePlayerList().changePlayerBench(name);
    }

    /**
     * Gets and saves the player that has been clicked in the attacker table 
     * @param event - mouse click
     */
    @FXML
    private void getSelectedClick(MouseEvent event) {
        clickedPlayer = attackerTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets selected player from the midfield table 
     * @param event - mouse click
     */
    @FXML
    private void getSelectedMidfielder(MouseEvent event) {
        clickedPlayer = midfieldTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets selected defender from the defense table
     * @param event - mouse click
     */
    @FXML
    private void getSelectedDefender(MouseEvent event) {
        clickedPlayer = defenderTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets selected goalie from the goalie table
     * @param event - mouse click
     */
    @FXML
    private void getSelectedGoalie(MouseEvent event) {
       clickedPlayer = goalieTable.getSelectionModel().getSelectedItem();
    }
    
    /**
     * Gets selected player from the bench 
     * @param event - mouse click
     */
    @FXML
    private void getSelectedBench(MouseEvent event) {
       clickedPlayer = benchTable.getSelectionModel().getSelectedItem();
    }
    
    
    /**
     * Displays an error if the table already has too many players
     */
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

    /**
     * Views stats of player if the button is clicked 
     * @param event - view stats button
     */
    @FXML
    private void viewStats(ActionEvent event) {
        viewPlayerInfo();
    }

    /**
     * Displays help for the player 
     * @param event - help button
     */
    @FXML
    private void viewHelp(ActionEvent event) {
        alert.setTitle("Help Information");
        alert.setContentText("Hello! Welcome to Player List! \n"
                + "First, you must select a player! \n"
                + "There are then three buttons you can select \n"
                + "The Move To Play moves a selected bench player on the bench to play  \n"
                + "The Move to Bench moves a selected starter to the bench \n"
                + "the View All Stats shows you the important stats for that player \n"
                + "To go back to the home press go home in the upper right \n"
                + "You must have 11 players. 2 Attackers 4 Mid 4 Def 1 Goalie \n");
        alert.showAndWait();
    }

    @FXML
    private void viewPlayers(MouseEvent event) {
    }


    


}
