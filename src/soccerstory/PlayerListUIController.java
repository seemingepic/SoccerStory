/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mockl
 */
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        currentTeamName = ListController.getInstance().getTheTeamList().
                getCurrentUserTeam();
        
        theTeamPlayerList = FXCollections.observableArrayList(ListController.
                getInstance().getThePlayerList().getPlayersFromTeam(currentTeamName));
        
        filterPlayers();
        setUpLists();
        // TODO
    }  
    
    private void setUpLists()
    {
        setUpAttackerList();
        setUpMidfieldList();
        setUpDefenderList();
        setUpGoalieList();
        setUpBench(); 
    }
    
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
    
    private void filterPlayers()
    {

        for (int i = 0; i < theTeamPlayerList.size(); i++)
        {
            if (theTeamPlayerList.get(i).getPosition().equals("A") && !(theTeamPlayerList.get(i).isBenched()))
                attackerListTable.add(theTeamPlayerList.get(i));
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
    

    
    public void movePlayerToAppropriateTable(String name)
    {
       for (int i = theTeamPlayerList.size()-1; i >= 0; i--)
        {
            if (theTeamPlayerList.get(i).getName().equals(name))
            {
                if (theTeamPlayerList.get(i).isBenched())
                {
                    removeFromBench(name);

                    switch (theTeamPlayerList.get(i).getPosition()) {
                        case "A":
                            attackerListTable.add(theTeamPlayerList.get(i));
                            break;
                        case "M":
                            midfieldListTable.add(theTeamPlayerList.get(i));
                            break;
                        case "D":
                            defenderListTable.add(theTeamPlayerList.get(i));
                            break;
                        default:
                            goalieListTable.add(theTeamPlayerList.get(i));
                            break;
                    }
                }
                else
                {
                    benchListTable.add(theTeamPlayerList.get(i));
                    clickedPlayer = null;
                    switch (theTeamPlayerList.get(i).getPosition()) {
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
     * Sends user home
     * @param event 
     */
    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();
            
        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    /**
     * Sends selected player to the play area
     * @param event 
     */
    @FXML
    private void moveToPlay(ActionEvent event) {
        String name = benchTable.getSelectionModel().getSelectedItem().getName();
        movePlayerToAppropriateTable(name);
        updateMainList(name);
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
    
    


}
