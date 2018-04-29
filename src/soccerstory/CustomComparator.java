
package soccerstory;

import java.util.Comparator;

/**
 * This class is used to compare the points for each team
 * This will sort them in DESCENDING ORDER (e.g 6 points > 5 points = 1st place)
 * @author mockl
 */
public class CustomComparator implements Comparator<Team>{
    
    @Override
    public int compare(Team o1, Team o2)
    {
        return o2.getPoints() - o1.getPoints();
    }
    
}
