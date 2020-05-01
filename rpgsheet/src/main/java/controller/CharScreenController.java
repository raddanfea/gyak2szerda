package controller;

import characters.CharacterBase;
import characters.MakeRandomCharacter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.StyleContext;
import java.io.IOException;

public class CharScreenController {

    @FXML
    private TextField nameTxf;

    @FXML
    private TextField ageTxf;

    @FXML
    private TextField raceTxf;

    @FXML
    private TextField levelTxf;

    @FXML
    private TextField classTxf;

    @FXML
    private TextField skillsTxf;

    @FXML
    private TextField itemsTxf;

    @FXML
    private ChoiceBox<Enum> genderChoice;

    public void initialize() {

        CharacterBase ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();
        refresh(ActiveChar);

        genderChoice.getItems().addAll(CharacterBase.Gender.values());
    }

    public void randomCharGenSet(ActionEvent actionEvent) {

        CharacterBase ActiveChar = new MakeRandomCharacter().MakeRandomCharacter();
        refresh(ActiveChar);
;
    }

    public void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }



    public void refresh(CharacterBase ActiveChar){
        nameTxf.setText(ActiveChar.getName());
        levelTxf.setText(ActiveChar.getLevel().toString());
        ageTxf.setText(ActiveChar.getAge().toString());

        genderChoice.setValue(ActiveChar.getGender());
        raceTxf.setText(ActiveChar.getRace().toString());

        classTxf.setText(ActiveChar.getRpgclass().toString());


    }
}


