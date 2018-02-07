/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mockl
 */
public class Player {
    
    private String name;
    private String teamPlayFor;
    private String position;
    private Integer jerseyNumber;

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
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the teamPlayFor
     */
    public String getTeamPlayFor() {
        return teamPlayFor;
    }

    /**
     * @param teamPlayFor the teamPlayFor to set
     */
    public void setTeamPlayFor(String teamPlayFor) {
        this.teamPlayFor = teamPlayFor;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the jerseyNumber
     */
    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * @param jerseyNumber the jerseyNumber to set
     */
    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }


}
