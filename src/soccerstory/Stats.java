
package soccerstory;

import java.io.Serializable;

/**
 * This class is designed to store stats for the player
 * Instead of putting all the ints into the player class, I broke it up to make it easier
 *
 * 
 */
public class Stats implements Serializable {
    
    private int goals;
    private int shots;
    private int passes;
    private int shotsAgainst;
    private int goalsAllowed;
    
    public Stats()
    {
        this.goals = 0;
        this.shots = 0;
        this.passes = 0;
        this.shotsAgainst = 0;
        this.goalsAllowed = 0;
    }

    /**
     * @return the goals
     */
    public int getGoals() {
        return goals;
    }

    /**
     * @param goals the goals to set
     */
    public void setGoals(int goals) {
        this.goals = goals;
    }

    /**
     * @return the shots
     */
    public int getShots() {
        return shots;
    }

    /**
     * @param shots the shots to set
     */
    public void setShots(int shots) {
        this.shots = shots;
    }

    /**
     * @return the passes
     */
    public int getPasses() {
        return passes;
    }

    /**
     * @param passes the passes to set
     */
    public void setPasses(int passes) {
        this.passes = passes;
    }

    /**
     * @return the shotsAgainst
     */
    public int getShotsAgainst() {
        return shotsAgainst;
    }

    /**
     * @param shotsAgainst the shotsAgainst to set
     */
    public void setShotsAgainst(int shotsAgainst) {
        this.shotsAgainst = shotsAgainst;
    }

    /**
     * @return the goalsAllowed
     */
    public int getGoalsAllowed() {
        return goalsAllowed;
    }

    /**
     * @param goalsAllowed the goalsAllowed to set
     */
    public void setGoalsAllowed(int goalsAllowed) {
        this.goalsAllowed = goalsAllowed;
    }
    
    
    
}
