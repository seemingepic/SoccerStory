/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mockl
 */
public class TeamList {
    
    private ArrayList<Team> theListOfTeams;
    private String currentUserTeam;
    
    
    public TeamList()
    {
        theListOfTeams = testTeams();
    }

    /**
     * create test teams for testing
     * @return 
     */
    public ArrayList<Team> testTeams()
    {
        ArrayList<Team> testTeams = new ArrayList();
        Team newTeam = new Team("Drunk Whales", "Bob", "Tom");
        Team newTeam2 = new Team("Horny Badgers", "Bob", "Tom");
        Team newTeam3 = new Team("German Shepherds", "Bob", "Tom");
        Team newTeam4 = new Team("NiteFort BR", "Bob", "Tom");
        Team newTeam5 = new Team("Pointless Pigeons", "Bob", "Tom");
        Team newTeam6 = new Team("The GPHers", "Bob", "Tom");
        Team newTeam7 = new Team("Jealous Deers", "Bob", "Tom");
        Team newTeam8 = new Team("Swag Gangstaz", "Bob", "Tom");
        Team newTeam9 = new Team("American Spanners", "Bob", "Tom");

        testTeams.add(newTeam);
        testTeams.add(newTeam2);
        testTeams.add(newTeam3);
        testTeams.add(newTeam4);
        testTeams.add(newTeam5);
        testTeams.add(newTeam6);
        testTeams.add(newTeam7);
        testTeams.add(newTeam8);
        testTeams.add(newTeam9);
        return testTeams;    
    }

    public ObservableList<Team> getTeamData() 
    {
        ObservableList<Team> theNewListOfTeams;
        List<Team> teamList = (List<Team>) getTheListOfTeams();
        theNewListOfTeams= FXCollections.observableList(teamList);
        return theNewListOfTeams;
    }
    
    /**
     * Prints out list of team names 
     * This is used for the calander feature
     * @return the team names as an arrayList
     */
    public ArrayList<String> getTeamNames()
    {
        ArrayList<String> teamNames = new ArrayList<>();
        for (int i = 0; i < theListOfTeams.size(); i++)
        {
            String newTeamName = theListOfTeams.get(i).getTeamName();
            teamNames.add(newTeamName);
        }
        return teamNames;
    }

    /**
     * @return the theListOfTeams
     */
    public ArrayList<Team> getTheListOfTeams() {
        return theListOfTeams;
    }

    /**
     * @param theListOfTeams the theListOfTeams to set
     */
    private void setTheListOfTeams(ArrayList<Team> theListOfTeams) {
        this.theListOfTeams = theListOfTeams;
    }

    /**
     * @return the currentUserTeam
     */
    public String getCurrentUserTeam() {
        return currentUserTeam;
    }

    /**
     * @param currentUserTeam the currentUserTeam to set
     */
    public void setCurrentUserTeam(String currentUserTeam) {
        this.currentUserTeam = currentUserTeam;
    }
    
    public void updateTeamPoints(String teamname, int points)
    {
        
        for (int i = 0; i < theListOfTeams.size(); i++)
        {
            if (theListOfTeams.get(i).getTeamName().equals(teamname))
            {
                theListOfTeams.get(i).setPoints(theListOfTeams.get(i).getPoints() + points);
                theListOfTeams.get(i).setGamesPlayed(theListOfTeams.get(i).getGamesPlayed() + 1);
            }
        }        
    }
    


    
}
