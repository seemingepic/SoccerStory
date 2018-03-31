package soccerstory;

/**
 * This object is created in the CalendarUIController 
 * This object stores the score of the game, the teams that are playing, the match number of the week
 * and the week of the game that will be played
 * @author mockl
 */
public class Match {
    
    private String team1;
    private String team2;
    private int week;
    private int matchNumber;
    private int team1Score;
    private int team2Score;
    
    /**
     * A match object stores the following 
     * @param newTeam1 - team 1 (hometeam)
     * @param newTeam2 - team 2 (awayteam)
     * @param newWeek - week number
     * @param newMatch  - match number for the week
     */
    public Match(String newTeam1, String newTeam2, int newWeek, int newMatch){
        this.team1 = newTeam1;
        this.team2 = newTeam2;
        this.week = newWeek;
        this.matchNumber = newMatch;
        this.team1Score = 0;
        this.team2Score = 0;
    }

    /**
     * @return the team1
     */
    public String getTeam1() {
        return team1;
    }

    /**
     * @param team1 the team1 to set
     */
    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    /**
     * @return the team2
     */
    public String getTeam2() {
        return team2;
    }

    /**
     * @param team2 the team2 to set
     */
    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    /**
     * @return the week
     */
    public int getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * @return the matchNumber
     */
    public int getMatchNumber() {
        return matchNumber;
    }

    /**
     * @param matchNumber the matchNumber to set
     */
    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    /**
     * @return the team1Score
     */
    public int getTeam1Score() {
        return team1Score;
    }

    /**
     * @param team1Score the team1Score to set
     */
    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    /**
     * @return the team2Score
     */
    public int getTeam2Score() {
        return team2Score;
    }

    /**
     * @param team2Score the team2Score to set
     */
    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }
    
    
   
    
}
