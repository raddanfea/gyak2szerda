package main;

import characters.CharacterBase;
import characters.MakeRandomCharacter;
import com.github.javafaker.Faker;
import helpers.CharSaver;
import org.tinylog.Logger;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Application.launch(MyApplication.class, args);
    }
}
