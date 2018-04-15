/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mockl
 */
public class Player implements Serializable{
    
    private String name;
    private String teamPlayFor;
    private String position;
    private boolean benched;
    
    private int speed;
    private int ballskill;
    private int shooting;
    private int defense;
    private int passing;
    private int goalie;
    
    private int goals;
    private Stats stats;
    
    public Player()
    {
        
    }

    public Player(String newName, String newTeam, String newPosition)
    {
        this.name = newName;
        this.teamPlayFor = newTeam;
        this.position = newPosition;
        this.speed = 50;
        this.ballskill = 50;
        this.shooting = 50;
        this.defense = 50;
        this.passing = 50;
        this.goalie = 50;
        this.benched = false;
    }
    
    public Player(String newName, String newTeam, String newPosition, boolean newIsBenched, int newSpeed,
    int newBallskill, int newShooting, int newDefense, int newPassing, int newGoalie)
    {
        this.name = newName;
        this.teamPlayFor = newTeam;
        this.position = newPosition;
        this.speed = newSpeed;
        this.ballskill = newBallskill;
        this.shooting = newShooting;
        this.defense = newDefense;
        this.passing = newPassing;
        this.goalie = newGoalie;
        this.benched = newIsBenched;
        this.stats = new Stats();
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
     * @param jerseyNumber the jerseyNumber to set
     */
    public void setJerseyNumber(Integer jerseyNumber) {
        this.setJerseyNumber((int) jerseyNumber);
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
    
 
    public int getOverall() {
        int overall;
        switch (getPosition()) {
            case "A":
                overall = attackerOverall();
                break;
            case "M":
                overall = midfieldOverall();
                break;
            case "D":
                overall = defenseOverall();
                break;
            default:
                overall = goalieOverall();
                break;

        }
        return overall;
    }
    
    /**
     * Determines overall for the attacker using a weight based system
     * that is based off point distrubution in racing
     * @param player
     * @return 
     */
    public int attackerOverall()
    {
        double overall = (this.getShooting()* .287) + (this.getSpeed() * .2045) +
                (this.getBallskill() * .1704) + (this.getPassing() * .1336) +
                (this.getGoalie() * .1136) + (this.getDefense() * .091);
        return (int)overall;
    }
    
        /**
     * Determines overall for the modfield using a weight based system
     * that is based off point distrubution in racing
     * @param player
     * @return 
     */
    public int midfieldOverall()
    {
        double overall = (this.getPassing()* .367) + (this.getSpeed() * .2045) +
                (this.getBallskill() * .1704) + (this.getDefense() * .1336) +
                (this.getShooting() * .1136) + (this.getGoalie() * .01);
        return (int)overall;
    }
    /**
     * Determines overall for the defense using a weight based system
     * that is based off point distrubution in racing
     * @param player
     * @return 
     */
    public int defenseOverall() {
        double overall = (this.getDefense() * .287) + (this.getPassing() * .2045)
                + (this.getSpeed() * .1704) + (this.getBallskill() * .1336)
                + (this.getGoalie() * .1136) + (this.getShooting() * .091);
        return (int) overall;
    }

     /**
     * Determines overall for the goalie using a weight based system
     * that is based off point distrubution in racing
     * @param player
     * @return 
     */
    public int goalieOverall()
    {
        double overall = (this.getGoalie() * .287) + (this.getPassing() * .2045)
                + (this.getDefense() * .1704) + (this.getBallskill() * .1336)
                + (this.getSpeed() * .1136) + (this.getShooting() * .091);
        return (int) overall;

    }

    /**
     * @return the stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }



}
