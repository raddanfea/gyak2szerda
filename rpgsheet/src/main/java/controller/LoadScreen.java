package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoadScreen {

    @FXML
    private ChoiceBox<String> loadBox;

    public void initialize() {
        try{
            List<String> results = new ArrayList<String>();

            File[] files = new File("Saves").listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    loadBox.getItems().add(file.getName().substring(0, file.getName().lastIndexOf('.')));
                }
            }
        }
        catch(Exception e){
            Logger.trace("Error while searching for savefiles. \n{}",e);
        }
        if(loadBox.getItems().contains("lastSave")){
            loadBox.setValue("lastSave");
        }
    }

    public void MakeAndShowRandomChar(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CharScreen.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<CharScreenController>getController().initdata(loadBox.getValue());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        root.getStylesheets().add("/css/stylesheet.css");
        stage.show();

        Logger.trace("Loading Character: {}", this.loadBox.getValue());
    }
}