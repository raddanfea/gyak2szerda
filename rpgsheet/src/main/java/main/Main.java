package main;

import characters.CharacterBase;
import characters.MakeRandomCharacter;
import com.github.javafaker.Faker;
import helpers.CharSaver;
import org.tinylog.Logger;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args){


        CharacterBase ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();

        CharSaver.save(ActiveChar,"random");

        Logger.trace(ActiveChar);

    }
}
