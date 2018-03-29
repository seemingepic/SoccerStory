/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mockl
 */
public class MatchList {
    
    private ArrayList<Match> theMatchList;
    private int currentWeek = 1;
    
    public MatchList()
    {
        theMatchList = listMatches(ListController.getInstance().getTheTeamList().getTeamNames());
    }
    
    
        /**
     * Creates a calander to be stored into another class 
     * Code partially from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
     * @param listTeam
     * @param ListTeam 
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
    
    public void updateScores(String newHomeTeam, int newHomeScore, int newAwayScore)
    {
        for (int i = 0; i < theMatchList.size(); i++)
        {
            if ((theMatchList.get(i).getTeam1().equals(newHomeTeam)) 
                    && theMatchList.get(i).getWeek() == getCurrentWeek())
            {
                //need to add W/L/D tag
                theMatchList.get(i).setTeam1Score(newHomeScore);
                theMatchList.get(i).setTeam2Score(newAwayScore);
      
            }        
            else if ((theMatchList.get(i).getTeam2().equals(newHomeTeam)) && theMatchList.get(i).getWeek() == getCurrentWeek())
            {
                theMatchList.get(i).setTeam1Score(newHomeScore);
                theMatchList.get(i).setTeam2Score(newAwayScore);
            }                  
        }  
        currentWeek++;
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
    
}
