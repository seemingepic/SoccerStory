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
public class Player {
    
    private String name;
    private String teamPlayFor;
    private String position;
    private int jerseyNumber;

    public Player(String newName, String newTeam, String newPosition, int jerseyNumber)
    {
        this.name = newName;
        this.teamPlayFor = newTeam;
        this.position = newPosition;
        this.jerseyNumber = jerseyNumber;
    }
    /**
     * @return the name
     */
    private String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * @return the teamPlayFor
     */
    private String getTeamPlayFor() {
        return teamPlayFor;
    }

    /**
     * @param teamPlayFor the teamPlayFor to set
     */
    private void setTeamPlayFor(String teamPlayFor) {
        this.teamPlayFor = teamPlayFor;
    }

    /**
     * @return the position
     */
    private String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    private void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the jerseyNumber
     */
    private int getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * @param jerseyNumber the jerseyNumber to set
     */
    private void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }


    
    
    
    
    
}
