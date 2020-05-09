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
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;

public class CharScreenController {

    private CharacterBase ActiveChar;
    private String loadedCharName;

    @FXML
    private TextField nameTxf;

    @FXML
    private TextField ageTxf;

    @FXML
    private TextArea itemsTxf;

    @FXML
    private TextField levelTxf;

    @FXML
    private Label skillsTxf;


    @FXML
    private ChoiceBox<Enum> genderChoice;

    @FXML
    private ChoiceBox<Enum> classChoice;

    @FXML
    private ChoiceBox<Enum> raceChoice;

    @FXML
    private Button backButton;


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

    public void initdata(String loadCharName) {

        this.loadedCharName = loadCharName;
        ActiveChar = CharSaver.load(loadedCharName);
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
        itemsTxf.setText(String.join(",", ActiveChar.getItems()).replace(",",",\n"));

        Logger.trace("Page refreshed.");

    }

    public void saveButton(ActionEvent event)throws IOException {
        refreshToFile();
        Logger.trace("Save was manual.");
    }

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


    public void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoadScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        root.getStylesheets().add("/css/stylesheet.css");
        stage.show();
    }

}


