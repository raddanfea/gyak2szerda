package characters;


import com.github.javafaker.Faker;

public class MakeRandomCharacter {

    public CharacterBase MakeRandomCharacter(){
        Faker faker = new Faker();

        CharacterBase ActiveChar = new CharacterBase();
        ActiveChar.setName(faker.name().fullName());

        return ActiveChar;
    }
}


