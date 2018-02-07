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
public class ListController {
    
    private PlayerList thePlayerList;
    private TeamList theTeamList;
    private static ListController instance = null;
    
    /**
     * create the lists 
     */
    private ListController()
    {
        thePlayerList = new PlayerList();
        theTeamList = new TeamList();
    }
    
    /**
     * if the controller isnt created create it, else just return it
     * @return 
     */
    public static ListController getInstance()
    {
        if(instance == null)
        {
            instance = new ListController();
        }
        
        return instance;
    }
    

    /**
     * @return the thePlayerList
     */
    public PlayerList getThePlayerList() {
        return thePlayerList;
    }

    /**
     * @param thePlayerList the thePlayerList to set
     */
    public void setThePlayerList(PlayerList thePlayerList) {
        this.thePlayerList = thePlayerList;
    }

    /**
     * @return the theTeamList
     */
    public TeamList getTheTeamList() {
        return theTeamList;
    }

    /**
     * @param theTeamList the theTeamList to set
     */
    public void setTheTeamList(TeamList theTeamList) {
        this.theTeamList = theTeamList;
    }

}
