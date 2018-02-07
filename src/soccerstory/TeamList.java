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
        for (int i = 0; i < 10; i++) {
            Team newTeam = new Team(i + "name", i + "owner", i + "coach");
            testTeams.add(newTeam);
        }
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
     * @return the theListOfTeams
     */
    private ArrayList<Team> getTheListOfTeams() {
        return theListOfTeams;
    }

    /**
     * @param theListOfTeams the theListOfTeams to set
     */
    private void setTheListOfTeams(ArrayList<Team> theListOfTeams) {
        this.theListOfTeams = theListOfTeams;
    }

    
}
