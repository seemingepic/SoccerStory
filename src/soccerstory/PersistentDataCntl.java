
package soccerstory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class uses singleton framework
 * Used to save the lists to .ser file
 * Saves the data after every match played 
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
     * @param fileName --The name of the file that was  saved 
     * @return An arrayList of the object that was stored 
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
     * @param obj --The list that will be saved 
     * @param fileName --what the list will be called 
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
