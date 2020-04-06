package legoset;

import jaxb.JAXBHelper;
import movie.Movie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception{
        LegoSet LegoSet = new LegoSet();
        LegoSet.setNumber(75211); /***String helyett int, mert hogynézne már ki***/
        LegoSet.setName("Imperial TIE Fighter");
        LegoSet.setTheme("Star Wars");
        LegoSet.setSubtheme("Solo");
        LegoSet.setYear(Year.of(2018));
        LegoSet.setPieces(519);

        LegoSet.setTags((Set.of("Starfighter", "Stormtrooper", "Star Wars", "Solo")));

        ArrayList<Minifig> figs = new ArrayList<>();
        figs.add(new Minifig("Imperial Mudtrooper", 2));
        figs.add(new Minifig("Imperial Pilot", 1));
        figs.add(new Minifig("Mimban Stormtrooper", 1));
        LegoSet.setMinifigs(figs);

        Weight weight = new Weight(0.89,"kg");
        LegoSet.setWeight(weight);
        LegoSet.setUrl("https://brickset.com/sets/75211-1/Imperial-TIE-Fighter"); /**Stringet kér a feladat**/


        JAXBHelper.toXML(LegoSet, System.out);

    }
}
