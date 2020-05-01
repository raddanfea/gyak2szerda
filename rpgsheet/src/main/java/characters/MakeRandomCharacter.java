package characters;


import com.github.javafaker.Faker;
import helpers.ClassSkills;
import lombok.extern.flogger.Flogger;
import org.tinylog.Logger;

import java.util.ArrayList;

public class MakeRandomCharacter {

    public CharacterBase MakeRandomCharacter(){


        Faker faker = new Faker();

        CharacterBase ActiveChar = new CharacterBase();
        ActiveChar.setName(faker.name().fullName());
        ActiveChar.setLevel(faker.number().numberBetween(1,20));
        ActiveChar.setAge(faker.number().numberBetween(15,60));
        ActiveChar.setGender(faker.options().option(CharacterBase.Gender.values()));
        ActiveChar.setRace(faker.options().option(CharacterBase.Race.values()));

        ActiveChar.setRpgclass(faker.options().option(CharacterBase.Rpgclass.values()));
        ActiveChar.setSkills(ClassSkills.GetSkills(faker.options().option(CharacterBase.Rpgclass.values())));

        Logger.trace("Random Character Generated.");

        return ActiveChar;
    }
}


