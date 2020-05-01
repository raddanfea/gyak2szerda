package helpers;

import jaxb.JAXBHelper;
import org.tinylog.Logger;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CharSaver{
    public static void save(Object o, String filename){

        String output = "Saves/" + filename + ".xml"; //build filename

        try {
            JAXBHelper.toXML(o, new FileOutputStream(output)); //throws to JAXBHelper

            Logger.trace("Character Saved to file at {}", output);
        }
        catch(JAXBException | FileNotFoundException e){
            Logger.trace("Character could not be saved.");
        }
    }
}
