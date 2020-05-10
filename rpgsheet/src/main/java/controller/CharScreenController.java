package controller;

import characters.CharacterBase;
import helpers.MakeRandomCharacter;
import helpers.CharSaver;
import helpers.CharacterSaveLogic;
import helpers.ClassSkills;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CharScreenController {

    private CharacterBase ActiveChar;
    private String loadedCharName;

    @FXML
    private TextField nameTxf;
    @FXML
    private TextField ageTxf;
    @FXML
    private TextField levelTxf;

    @FXML
    private TextArea itemsTxf;

    @FXML
    private Label skillsTxf;
    @FXML
    private Label profLabel;

    @FXML
    private ChoiceBox<Enum> genderChoice;
    @FXML
    private ChoiceBox<Enum> classChoice;
    @FXML
    private ChoiceBox<Enum> raceChoice;





    /**
     * When initializing, builds elements of ChoiceBoxes and creates listener to update the page when changed.
     */
    public void initialize() {

        raceChoice.getItems().addAll(CharacterBase.Race.values());
        raceChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                ActiveChar.setRace(CharacterBase.Race.valueOf(raceChoice.getItems().get((Integer) number2).toString()));
                refresh();
            }
        });

        genderChoice.getItems().addAll(CharacterBase.Gender.values());
        genderChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                ActiveChar.setGender(CharacterBase.Gender.valueOf(genderChoice.getItems().get((Integer) number2).toString()));
                refresh();
            }
        });

        classChoice.getItems().addAll(CharacterBase.Rpgclass.values());
        classChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                ActiveChar.setRpgclass(CharacterBase.Rpgclass.valueOf(classChoice.getItems().get((Integer) number2).toString()));
                refresh();
            }
        });

    }

    /**
     * Catches savefile name from the loading screen then loads it.
     * @param loadCharName Name to load.
     */
    public void initdata(String loadCharName) {

        this.loadedCharName = loadCharName;
        ActiveChar = CharSaver.load(loadedCharName);
        refresh();
    }

    /**
     * Generates random character then displays it.
     */
    public void randomCharGenSet(ActionEvent actionEvent) {

        ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();
        CharSaver.save(ActiveChar,"lastSave");
        refresh();
    }

    /**
     * Refreshes actively shown data.
     * Skills are built with ClassSkills helper.
     * Items are a string regex.
     * Proficiency (profLabel) is one-way basic math, not saved in file.
     */
    public void refresh(){

        nameTxf.setText(ActiveChar.getName());
        levelTxf.setText(ActiveChar.getLevel().toString());
        ageTxf.setText(ActiveChar.getAge().toString());
        genderChoice.setValue(ActiveChar.getGender());
        classChoice.setValue(ActiveChar.getRpgclass());
        raceChoice.setValue(ActiveChar.getRace());
        skillsTxf.setText(ClassSkills.SkillsToString(ClassSkills.GetSkills(ActiveChar.getRpgclass(),ActiveChar.getLevel())));
        itemsTxf.setText(String.join(",", ActiveChar.getItems()).replace(",",",\n"));
        profLabel.setText(new StringBuilder().append("+").append(2+ActiveChar.getLevel()/3).toString());

        for (int i = 0; i < ActiveChar.getAbilities().size(); i++) {
            ActiveChar.getAbilities().get(i);



        }

        Logger.trace("Page refreshed.");

    }

    /**
     * Manual save button calls save.
     * @param event Button press event
     * @throws IOException Button press exception
     */
    public void saveButton(ActionEvent event)throws IOException {
        refreshToFile();
        Logger.trace("Save was manual.");
    }

    /**
     * Saves data to file and calls refresh to update displayed data.
     */
    public void refreshToFile(){

        Logger.trace("Saving to file...");

        CharacterSaveLogic.SaveLogic(
                nameTxf.getText(),
                levelTxf.getText(),
                ageTxf.getText(),
                genderChoice.getValue(),
                raceChoice.getValue(),
                classChoice.getValue(),
                ActiveChar.getSkillsList(),
                itemsTxf.getText(),
                ActiveChar.getStats(),
                ActiveChar.getAbilities());

        ActiveChar = CharSaver.load("lastSave");
        refresh();
    }

    /**
     * Saves data to memory and calls refresh to update displayed data.
     */
    public void refreshTemp(){

        Logger.trace("Saving to temporary memory.");

        ActiveChar.setName(nameTxf.getText());
        ActiveChar.setLevel(Integer.parseInt(levelTxf.getText()));
        ActiveChar.setAge(Integer.parseInt(ageTxf.getText()));
        ActiveChar.setGender(CharacterBase.Gender.valueOf(genderChoice.getValue().toString()));
        ActiveChar.setRace(CharacterBase.Race.valueOf(raceChoice.getValue().toString()));
        ActiveChar.setRpgclass(CharacterBase.Rpgclass.valueOf(classChoice.getValue().toString()));
        ActiveChar.setSkills(ActiveChar.getSkillsList());
        ActiveChar.setItems(Arrays.asList(itemsTxf.getText().replace("\n", "").split(",")));
        ActiveChar.setStats(ActiveChar.getStats());
        ActiveChar.setAbilities(ActiveChar.getAbilities());
        refresh();
    }


    /**
     * Button to return to the load screen.
     * @param event Button event
     * @throws IOException Button exception
     */
    public void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoadScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        root.getStylesheets().add("/css/stylesheet.css");
        stage.show();
    }

}


