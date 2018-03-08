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
public class Match {
    
    private String team1;
    private String team2;
    private int week;
    private int matchNumber;
    
    public Match(String newTeam1, String newTeam2, int newWeek, int newMatch){
        this.team1 = newTeam1;
        this.team2 = newTeam2;
        this.week = newWeek;
        this.matchNumber = newMatch;
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
    
    
   
    
}
