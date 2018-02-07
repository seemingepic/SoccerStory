/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import javafx.beans.property.StringProperty;

/**
 *
 * @author mockl
 */
public class Tester {
    
    private StringProperty name;
    private StringProperty position;
    
    public Tester()
    {
        this.name.set("test");
        this.position.set("test");
    }
    
    public Tester(String name, String position)
    {
        this.name.set(name);
        this.position.set(position);
    }

    /**
     * @return the name
     */
    private StringProperty getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    private void setName(StringProperty name) {
        this.name = name;
    }

    /**
     * @return the position
     */
    private StringProperty getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    private void setPosition(StringProperty position) {
        this.position = position;
    }
    
    
    
   
    
}
