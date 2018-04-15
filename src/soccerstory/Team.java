
package soccerstory;

import com.github.javafaker.Faker;
import java.io.Serializable;
import java.util.Random;

/**
 * This class stores all the details for the team
 * Each team will have a team name, points, gamesplayed, an ownername and a coach
 *
 * @author mockl
 */
public class Team implements Serializable{
    
    private String teamName;
    private int points;
    private int gamesPlayed;
    private String ownerName;
    private String coach;
    private Boolean playerTeam;

    /**
     * Constructor for the team 
     * @param newTeamName - name of the team 
     * @param newOwnerName - owner of the team
     * @param newCoach  - coach of the team
     * Owner/coach do not really do much right now
     */
    public Team(String newTeamName, String newOwnerName, String newCoach)
    {
        this.teamName = newTeamName;
        this.ownerName = newOwnerName;
        this.coach = newCoach;
        this.points = 0;
        this.gamesPlayed = 0;
        this.playerTeam = false;
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
    

    private int randomStat()
    {
        int max = 90;
        int min = 20;
        
        Random r = new Random();
        int randomNum = r.nextInt((max-min) + 1) + min;
        return randomNum;
    }

    /**
     * @return the playerTeam
     */
    public Boolean getPlayerTeam() {
        return playerTeam;
    }

    /**
     * @param playerTeam the playerTeam to set
     */
    public void setPlayerTeam(Boolean playerTeam) {
        this.playerTeam = playerTeam;
    }
    
    
}
