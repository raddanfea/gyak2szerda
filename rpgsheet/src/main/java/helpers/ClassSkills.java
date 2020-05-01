package helpers;

import characters.CharacterBase;
import characters.Skills;
import org.tinylog.Logger;

import java.util.ArrayList;

public class ClassSkills {
    public static ArrayList<Skills> GetSkills(CharacterBase.Rpgclass whichclass){

        ArrayList<Skills> skills = new ArrayList<>();


        switch(whichclass.toString()) {
            case "Warrior":
                skills.add(new Skills("Warrior Skill 1", 2,"Effect 1"));
                skills.add(new Skills("Warrior Skill 2", 2,"Effect 2"));
                skills.add(new Skills("Warrior Skill 3", 2,"Effect 2"));
                break;
            case "Mage":
                skills.add(new Skills("Mage Skill 1", 3, "Effect 1"));
                skills.add(new Skills("Mage Skill 2", 2, "Effect 2"));
                skills.add(new Skills("Mage Skill 3", 1, "Effect 2"));
                break;
            default:
                skills.add(new Skills("Error!", 404,"This is an anti-crash measure."));
                Logger.error("Class skills could not be selected.");
        }
        Logger.trace("Class skills selection finished.");

        return skills;

    }
}
