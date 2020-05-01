package characters;


import com.github.javafaker.Faker;

public class MakeRandomCharacter {

    public CharacterBase MakeRandomCharacter(){
        Faker faker = new Faker();

        CharacterBase ActiveChar = new CharacterBase();
        ActiveChar.setName(faker.name().fullName());
        ActiveChar.setLevel(faker.number().numberBetween(1,20));
        ActiveChar.setAge(faker.number().numberBetween(15,60));
        ActiveChar.setGender(faker.options().option(CharacterBase.Gender.values()));
        ActiveChar.setRpgclass(faker.options().option(CharacterBase.Rpgclass.values()));

        return ActiveChar;
    }
}


