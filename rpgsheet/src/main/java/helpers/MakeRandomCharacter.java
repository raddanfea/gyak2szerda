package helpers;


import characters.CharacterBase;
import characters.Stats;
import com.github.javafaker.Faker;
import helpers.ClassSkills;
import org.tinylog.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MakeRandomCharacter {

    public CharacterBase MakeRandomCharacter(){


        Faker faker = new Faker();

        CharacterBase ActiveChar = new CharacterBase();

        ActiveChar.setName(faker.name().fullName());

        int levelbuffer = faker.number().numberBetween(1,20);
        ActiveChar.setLevel(levelbuffer);

        ActiveChar.setAge(faker.number().numberBetween(150,200) * levelbuffer / 20);
        ActiveChar.setGender(faker.options().option(CharacterBase.Gender.values()));
        ActiveChar.setRace(faker.options().option(CharacterBase.Race.values()));

        ActiveChar.setRpgclass(faker.options().option(CharacterBase.Rpgclass.values()));
        ActiveChar.setSkills(ClassSkills.GetSkills(faker.options().option(CharacterBase.Rpgclass.values()),levelbuffer));

        ActiveChar.setItems(List.of("Starting Items,Use Comma for New Line"));

        ArrayList<Stats> statsbuffer = new ArrayList<>();

        for (Stats.Stattypes iteredtype : Stats.Stattypes.values()){
            statsbuffer.add(new Stats(iteredtype.toString(),faker.number().numberBetween(-2,6)+10));
        }

        ActiveChar.setStats(statsbuffer);

        Logger.trace("Random Character Generated.");

        return ActiveChar;
    }
}


