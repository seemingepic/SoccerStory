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
    private int points;
    private int gamesPlayed;
    private String ownerName;
    private String coach;

    public Team(String newTeamName, String newOwnerName, String newCoach)
    {
        this.teamName = newTeamName;
        this.ownerName = newOwnerName;
        this.coach = newCoach;
        this.points = 0;
        this.gamesPlayed = 0;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the gamesPlayed
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * @param gamesPlayed the gamesPlayed to set
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the coach
     */
    public String getCoach() {
        return coach;
    }

    /**
     * @param coach the coach to set
     */
    public void setCoach(String coach) {
        this.coach = coach;
    }

    
    
}
