package helpers;

import characters.CharacterBase;
import characters.Skills;
import characters.Stats;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CharacterSaveLogic {
    public static void SaveLogic(String nametosave,
                                 String leveltosave,
                                 String agetosave,
                                 Enum gendertosave,
                                 Enum racetosave,
                                 Enum rpgclasstosave,
                                 List<Skills> skillstosave,
                                 String itemstosave,
                                 ArrayList<Stats> stattosave,
                                 List<Boolean> abilitiestosave){

        try {
            CharacterBase ActiveChar = new CharacterBase();
            ActiveChar.setName(nametosave);
            ActiveChar.setLevel(Integer.parseInt(leveltosave));
            ActiveChar.setAge(Integer.parseInt(agetosave));
            ActiveChar.setGender(CharacterBase.Gender.valueOf(gendertosave.toString()));
            ActiveChar.setRace(CharacterBase.Race.valueOf(racetosave.toString()));

            ActiveChar.setRpgclass(CharacterBase.Rpgclass.valueOf(rpgclasstosave.toString()));

            ActiveChar.setSkills(skillstosave);
            ActiveChar.setItems(Arrays.asList(itemstosave.replace("\n", "").split(",")));
            ActiveChar.setStats(stattosave);

            ActiveChar.setAbilities(abilitiestosave);

            CharSaver.save(ActiveChar, "lastSave");
            CharSaver.save(ActiveChar,nametosave);
        }
        catch (Exception e){
            Logger.error("CharacterSaveLogic Failed. Invalid Values?\n{}", e);
        }
    }
}
