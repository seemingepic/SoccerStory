/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

/**
 *
 * @author mockl
 */
public class Team {
    
    private String teamName;
    private String ownerName;
    private String coach;

    public Team(String newTeamName, String newOwnerName, String newCoach)
    {
        this.teamName = newTeamName;
        this.ownerName = newOwnerName;
        this.coach = newCoach;
    }
    /**
     * @return the teamName
     */
    private String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    private void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the ownerName
     */
    private String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    private void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the coach
     */
    private String getCoach() {
        return coach;
    }

    /**
     * @param coach the coach to set
     */
    private void setCoach(String coach) {
        this.coach = coach;
    }
    


    
    
    
}
