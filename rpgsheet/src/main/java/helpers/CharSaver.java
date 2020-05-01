package helpers;

import jaxb.JAXBHelper;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class CharSaver{
    public static void save(Object o, String filename)throws Exception{

        String output = "Saves/" + filename + ".xml"; //build filename

        JAXBHelper.toXML(o, new FileOutputStream(output)); //throws to JAXBHelper
    }
}
