package soccerstory;

import com.github.javafaker.Faker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * Class: MatchList
 * Purpose: This class is designed to create a list of matches for the season.
 * Each team will play each other one time 
 * Each result will be stored in a match
 *
 * @author mockl
 */
public class MatchList implements Serializable{
    
    private ArrayList<Match> theMatchList; // list of matches
    private int currentWeek = 1; //curent week 
    
    public MatchList()
    {
        theMatchList = listMatches(ListController.getInstance().getTheTeamList().getTeamNames()); //creates the matches
    }
    
    
        /**
     * Creates a calander to be stored into another class 
     * Based on a round robin system
     * Code partially from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
     * @param listTeam - list of team names to be stored
     */
    public ArrayList<Match> listMatches(List<String> listTeam) {

        int numWeeks = listTeam.size() - 1;
        int halfSize = listTeam.size() / 2;

        List<String> teams = new ArrayList<String>();
        ArrayList<Match> newMatches = new ArrayList<Match>();

        teams.addAll(listTeam); // Add teams to List and remove the first team
        teams.remove(0); //remove first index to base calander off of

        int teamsSize = teams.size(); //how many teams?

        for (int week = 0; week < numWeeks; week++) { //same as int i but change it to weeks as this is based off weeks
            
            System.out.println("Week {" + (week + 1) + ")"); //Prints out what week the matches are being created for

            int teamIdx = week % teamsSize; //get first value of idx, add to it later

            Match newMatch = new Match(teams.get(teamIdx), listTeam.get(0), week + 1, 1); //center around first time in the original list, 
            newMatches.add(newMatch);
            
            //Match newMatch = new Match(teams.get(teamIdx).getTeamName());

            for (int idx = 1; idx < halfSize; idx++) {
                int firstTeam = (week + idx) % teamsSize; //get first team from first half of list
                int secondTeam = (week + teamsSize - idx) % teamsSize; //get second team from second half of list
                //this will switch home/away when the week is > 5 to make it more balanced
                Match newerMatch = new Match(teams.get(firstTeam), teams.get(secondTeam), week + 1, idx + 1);
                newMatches.add(newerMatch);
            }
        }
        return newMatches;
    }
    
    /**
     * This class updates the scores of the match object based on the result
     * of the simulation 
     * @param newHomeTeam - home team name
     * @param newHomeScore = home team score
     * @param newAwayScore  - away team score
     * Only needs home team name to find match in list
     */
    public void updateScores(String newHomeTeam, int newHomeScore, int newAwayScore)
    {
        for (int i = 0; i < theMatchList.size(); i++) //searches through match list for the home team with current week
        {
            if ((theMatchList.get(i).getTeam1().equals(newHomeTeam)) 
                    && theMatchList.get(i).getWeek() == getCurrentWeek())
            {
                //need to add W/L/D tag
                theMatchList.get(i).setTeam1Score(newHomeScore); //set scores based on home/away 
                theMatchList.get(i).setTeam2Score(newAwayScore);
      
            }        
            else if ((theMatchList.get(i).getTeam2().equals(newHomeTeam)) && theMatchList.get(i).getWeek() == getCurrentWeek()) //if the match does not have team name in team1, check for team 2
            {
                theMatchList.get(i).setTeam1Score(newHomeScore);
                theMatchList.get(i).setTeam2Score(newAwayScore);
            }                  
        }  
    }
    
    /**
     * Function: Used because persistent data does not load week number
     * This gets the current week for the game
     */
    public void getUpdatedWeekNumber()
    {
        for (int i = 0; i < theMatchList.size(); i++) //searches through match list for the home team with current week
        {
            if (theMatchList.get(i).getTeam1Score() > 0 || theMatchList.get(i).getTeam2Score() > 0) //if the game has been played
            {
                this.currentWeek = theMatchList.get(i).getWeek() + 1; //store the week + 1
            }
        }  
    }
 
    /**
     * @return the theMatchList
     */
    public ArrayList<Match> getTheMatchList() {
        return theMatchList;
    }

    /**
     * @param theMatchList the theMatchList to set
     */
    public void setTheMatchList(ArrayList<Match> theMatchList) {
        this.theMatchList = theMatchList;
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
    
    
}
