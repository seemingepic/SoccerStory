
package soccerstory;

/**
 * This class ias based of the builder design pattern
 * This makes creating players a lot easier to understand
 * Used in generating randomplayers along with the faker library
 *
 * @author mockl
 */
public class PlayerBuilder {
    
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
    
    private Stats stats;

    /**
     * @param name the name to set
     */
    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param teamPlayFor the teamPlayFor to set
     */
    public PlayerBuilder setTeamPlayFor(String teamPlayFor) {
        this.teamPlayFor = teamPlayFor;
        return this;
    }

    /**
     * @param position the position to set
     */
    public PlayerBuilder setPosition(String position) {
        this.position = position;
        return this;
    }


    /**
     * @param benched the benched to set
     */
    public PlayerBuilder setBenched(boolean benched) {
        this.benched = benched;
        return this;
    }

    /**
     * @param speed the speed to set
     */
    public PlayerBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    /**
     * @param ballskill the ballskill to set
     */
    public PlayerBuilder setBallskill(int ballskill) {
        this.ballskill = ballskill;
        return this;
    }

    /**
     * @param shooting the shooting to set
     */
    public PlayerBuilder setShooting(int shooting) {
        this.shooting = shooting;
        return this;
    }

    /**
     * @param defense the defense to set
     */
    public PlayerBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    /**
     * @param passing the passing to set
     */
    public PlayerBuilder setPassing(int passing) {
        this.passing = passing;
        return this;
    }

    /**
     * @param goalie the goalie to set
     */
    public PlayerBuilder setGoalie(int goalie) {
        this.goalie = goalie;
        return this;
    }
    
    /**
     * Creates the player object with the data from playerbuilder
     * @return  - player 
     */
    public Player getPlayer()
    {
        return new Player(name, teamPlayFor, position, benched, speed, 
                ballskill, shooting, defense, passing, goalie);
    }
    
    
}
