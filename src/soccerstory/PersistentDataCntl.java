/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccerstory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author mockl
 */
public class PersistentDataCntl {
    
    private static PersistentDataCntl thePersistentDataCntl;
    
    
    private PersistentDataCntl(){
        
    }
    
    public static PersistentDataCntl getInstance(){
        if(thePersistentDataCntl == null){
            thePersistentDataCntl = new PersistentDataCntl();
        }
        
        return thePersistentDataCntl;
    }
    
    /**
     * reads list from file
     * @param fileName
     * @return 
     */
    public static ArrayList<?> deserialize(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<?> obj = new ArrayList<>();
        try{
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            obj = (ArrayList<?>)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
        
    }
    /**
     * writes serialized list to file
     * @param obj
     * @param fileName 
     */
    public static void serialize(ArrayList<?> obj, String fileName){
        
        FileOutputStream fos;
        ObjectOutputStream oos;
        try{
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
   

}
