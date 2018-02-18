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
    private int jerseyNumber;
    private boolean benched;
    
    private int speed;
    private int ballskill;
    private int shooting;
    private int defense;
    private int passing;
    private int goalie;

    public Player(String newName, String newTeam, String newPosition, int jerseyNumber)
    {
        this.name = newName;
        this.teamPlayFor = newTeam;
        this.position = newPosition;
        this.jerseyNumber = jerseyNumber;
        this.speed = 50;
        this.ballskill = 50;
        this.shooting = 50;
        this.defense = 50;
        this.passing = 50;
        this.goalie = 50;
        this.benched = false;
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
        this.setJerseyNumber((int) jerseyNumber);
    }

    /**
     * @param jerseyNumber the jerseyNumber to set
     */
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the ballskill
     */
    public int getBallskill() {
        return ballskill;
    }

    /**
     * @param ballskill the ballskill to set
     */
    public void setBallskill(int ballskill) {
        this.ballskill = ballskill;
    }

    /**
     * @return the shooting
     */
    public int getShooting() {
        return shooting;
    }

    /**
     * @param shooting the shooting to set
     */
    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * @return the passing
     */
    public int getPassing() {
        return passing;
    }

    /**
     * @param passing the passing to set
     */
    public void setPassing(int passing) {
        this.passing = passing;
    }

    /**
     * @return the goalie
     */
    public int getGoalie() {
        return goalie;
    }

    /**
     * @param goalie the goalie to set
     */
    public void setGoalie(int goalie) {
        this.goalie = goalie;
    }

    /**
     * @return the benched
     */
    public boolean isBenched() {
        return benched;
    }

    /**
     * @param benched the benched to set
     */
    public void setBenched(boolean benched) {
        this.benched = benched;
    }
 
    public int overall(Player player) {
        int overall;
        switch (player.getPosition()) {
            case "A":
                overall = attackerOverall(player);
                break;
            case "M":
                overall = midfieldOverall(player);
                break;
            case "D":
                overall = defenseOverall(player);
                break;
            default:
                overall = goalieOverall(player);
                break;

        }
        return overall;
    }
    
    public int attackerOverall(Player player)
    {
        double overall = (player.getShooting()* .287) + (player.getSpeed() * .2045) +
                (player.getBallskill() * .1704) + (player.getPassing() * .1336) +
                (player.getGoalie() * .1136) + (player.getDefense() * .091);
        return (int)overall;
    }
    
    public int midfieldOverall(Player player)
    {
        double overall = (player.getPassing()* .367) + (player.getSpeed() * .2045) +
                (player.getBallskill() * .1704) + (player.getDefense() * .1336) +
                (player.getShooting() * .1136) + (player.getGoalie() * .01);
        return (int)overall;
    }
    
    public int defenseOverall(Player player) {
        double overall = (player.getDefense() * .287) + (player.getPassing() * .2045)
                + (player.getSpeed() * .1704) + (player.getBallskill() * .1336)
                + (player.getGoalie() * .1136) + (player.getShooting() * .091);
        return (int) overall;
    }
    
    public int goalieOverall(Player player)
    {
        double overall = (player.getGoalie() * .287) + (player.getPassing() * .2045)
                + (player.getDefense() * .1704) + (player.getBallskill() * .1336)
                + (player.getSpeed() * .1136) + (player.getShooting() * .091);
        return (int) overall;

    }



}
