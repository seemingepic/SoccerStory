
package soccerstory;

/**
 * This class creates and is the control point for getting
 * access to the playerList and teamList and matchList
 * 
 */
public class ListController {
    
    private PlayerList thePlayerList; 
    private TeamList theTeamList;
    private MatchList theMatchList;
    private static ListController instance = null;
    
    /**
     * create the lists 
     */
    private ListController()
    {
        theTeamList = new TeamList(); //Creates the TeamList first to be displayed 
    }
    
    /**
     * Singleton initialization 
     * @returns the listController
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
    
    /**
     * Create player list 
     */
    public void createPlayerList()
    {
        thePlayerList = new PlayerList();
    }
    
    /**
     * Create the list of matches
     */
    public void createMatchList()
    {
        theMatchList = new MatchList();
    }

    /**
     * @return the theMatchList
     */
    public MatchList getTheMatchList() {
        return theMatchList;
    }

    /**
     * @param theMatchList the theMatchList to set
     */
    public void setTheMatchList(MatchList theMatchList) {
        this.theMatchList = theMatchList;
    }
    

}
