package controller;

import characters.CharacterBase;
import characters.MakeRandomCharacter;
import characters.Stats;
import helpers.CharSaver;
import helpers.CharacterSaveLogic;
import helpers.ClassSkills;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jaxb.JAXBHelper;
import org.tinylog.Logger;

import javax.swing.text.StyleContext;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class CharScreenController {

    @FXML
    private TextField nameTxf;

    @FXML
    private TextField ageTxf;


    @FXML
    private TextField levelTxf;


    @FXML
    private Label skillsTxf;

    @FXML
    private TextField itemsTxf;

    @FXML
    private ChoiceBox<Enum> genderChoice;

    @FXML
    private ChoiceBox<Enum> classChoice;

    @FXML
    private ChoiceBox<Enum> raceChoice;

    private CharacterBase ActiveChar;


    public void initialize() {

        raceChoice.getItems().addAll(CharacterBase.Race.values());

        genderChoice.getItems().addAll(CharacterBase.Gender.values());

        classChoice.getItems().addAll(CharacterBase.Rpgclass.values());
        classChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                ActiveChar.setRpgclass(CharacterBase.Rpgclass.valueOf(classChoice.getItems().get((Integer) number2).toString()));
                refresh();
            }
        });

        try {
            ActiveChar = CharSaver.load("lastSave");
        }
        catch (Exception e){
            Logger.error("No lastSave found, generating random placeholder.");
            ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();
        }
        refresh();
    }

    public void randomCharGenSet(ActionEvent actionEvent) {

        ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();
        CharSaver.save(ActiveChar,"lastSave");
        refresh();
    }


    public void refresh(){

        nameTxf.setText(ActiveChar.getName());
        levelTxf.setText(ActiveChar.getLevel().toString());
        ageTxf.setText(ActiveChar.getAge().toString());
        genderChoice.setValue(ActiveChar.getGender());
        classChoice.setValue(ActiveChar.getRpgclass());
        raceChoice.setValue(ActiveChar.getRace());
        skillsTxf.setText(ClassSkills.SkillsToString(ClassSkills.GetSkills(ActiveChar.getRpgclass(),ActiveChar.getLevel())));

        Logger.trace("Page refreshed.");

    }

    public void refreshToXML(ActionEvent event) throws IOException {

        CharacterSaveLogic.SaveLogic(
                nameTxf.getText(),
                levelTxf.getText(),
                ageTxf.getText(),
                genderChoice.getValue(),
                raceChoice.getValue(),
                classChoice.getValue(),
                ActiveChar.getSkillsList(),
                ActiveChar.getItems(),
                ActiveChar.getStats());

        Logger.trace("Save was manual.");
        ActiveChar = CharSaver.load("lastSave");
        refresh();
    }


    public void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}


