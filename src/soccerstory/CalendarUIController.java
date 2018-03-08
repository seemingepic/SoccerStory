/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class This class is designed to keep tracked of the weeks for
 * the game. It also will be used for the player to see whats goin on next week
 *
 * @author mockl
 */
public class CalendarUIController implements Initializable {

    @FXML
    private Label weekLabel;

    private int currentWeek = 1;
    @FXML
    private Label actionTarget;
    @FXML
    private Label litfam;
    
    private ArrayList<Match> matchList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCalendar();
        listMatches(ListController.getInstance().getTheTeamList().getTeamNames());
        printList();
    }

    /**
     * This class is designed to update the current week label with the current
     * week
     */
    private void updateCalendar() {
        String currentWeekLabel = weekLabel.getText();
        currentWeekLabel = currentWeekLabel + Integer.toString(currentWeek);
        weekLabel.setText(currentWeekLabel);
    }

    /**
     * @return the currentWeek
     */
    public int getCurrentWeek() {
        return currentWeek;
    }

    /**
     * @param currentWeek the currentWeek to set
     */
    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    /**
     * sends user back home by calling nav cntl
     *
     * @param event
     */
    @FXML
    private void goHome(ActionEvent event) {
        actionTarget.setText("log on pressed");
        Stage stage = (Stage) actionTarget.getScene().getWindow();
        stage.hide();

        NavigationCntl.getNavigationCntl(stage).setUpNavigationScene();
    }

    private void updateMatches() {
        ArrayList<String> teams = new ArrayList<String>();
    }

    /**
     * Creates a calander to be stored into another class 
     * Code partially from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
     * @param ListTeam 
     */
    public void listMatches(List<String> listTeam) {

        int numWeeks = listTeam.size() - 1;
        int halfSize = listTeam.size() / 2;

        List<String> teams = new ArrayList<String>();

        teams.addAll(listTeam); // Add teams to List and remove the first team
        teams.remove(0); //remove first index to base calander off of

        int teamsSize = teams.size(); //how many teams?

        for (int week = 0; week < numWeeks; week++) { //same as int i but change it to weeks as this is based off weeks
            
            System.out.println("Week {" + (week + 1) + ")"); //Prints out what week the matches are being created for

            int teamIdx = week % teamsSize; //Split the group in half

            System.out.println("(" + teams.get(teamIdx) + ") " + "vs" + " (" + listTeam.get(0) + ")");
            Match newMatch = new Match(teams.get(teamIdx), listTeam.get(0), week, 1);
            matchList.add(newMatch);
            
            //Match newMatch = new Match(teams.get(teamIdx).getTeamName());

            for (int idx = 1; idx < halfSize; idx++) {
                int firstTeam = (week + idx) % teamsSize;
                int secondTeam = (week + teamsSize - idx) % teamsSize;
                Match newerMatch = new Match(teams.get(firstTeam), teams.get(secondTeam), week, idx + 1);
                matchList.add(newerMatch);
                System.out.println("(" + teams.get(firstTeam) + ")" + " vs " + "(" + teams.get(secondTeam) + ")");
            }
        }
    }
    
    
    /**
     * Prints out list of matches for testing purposes...
     * no real use but debugging
     */
    public void printList()
    {
        for (int i = 0; i < matchList.size(); i++)
        {
            System.out.print(matchList.get(i).getTeam1() + "VS");
            System.out.print(matchList.get(i).getTeam2() + "  ");
            System.out.print(matchList.get(i).getWeek() + "  ");
            System.out.println(matchList.get(i).getMatchNumber());
        }
    }

}
