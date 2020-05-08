package helpers;

import characters.CharacterBase;
import org.tinylog.Logger;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CharSaver{
    public static void save(Object o, String filename){

        String output = "Saves/" + filename + ".xml"; /*build filename*/

        try {
            JAXBHelper.toXML(o, new FileOutputStream(output)); //throws to JAXBHelper

            Logger.trace("Character Saved to file at {}", output);
        }
        catch(JAXBException | FileNotFoundException e){
            Logger.error("Character could not be saved.\n{}",e);
        }
    }

    public static CharacterBase load(String filename){

        String input = "Saves/" + filename + ".xml"; /*build filename*/
        CharacterBase ActiveChar = null;

        try {
            ActiveChar = JAXBHelper.fromXML(CharacterBase.class, new FileInputStream(input));

            Logger.trace("Character Loaded from file at {}", input);
        }
        catch(JAXBException | FileNotFoundException e){
            Logger.error("Character {} not loaded because it could no be found.", input);
            if(filename == null) {
                ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();
                ActiveChar.setName("New Character");
                Logger.error("Last Save is missing, therefore returning a random character.");
            }
        }
        return ActiveChar;}


}
