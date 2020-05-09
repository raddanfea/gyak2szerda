package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoadScreen {

    @FXML
    private ChoiceBox<String> loadBox;

    @FXML
    private ImageView nemeiah;
    @FXML
    private ImageView luran;

    public void initialize() {

        try{
            List<String> results = new ArrayList<String>();

            File[] files = new File("Saves").listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    loadBox.getItems().add(
                            file.getName().substring(
                                    0, file.getName().lastIndexOf('.')));
                }
            }
        } catch(Exception e){
            Logger.error("Error while searching for savefiles.{}",e);
        }

        /**
        Default ChoiceBox on "lastSave" if file is not missing.
         */
        if(loadBox.getItems().contains("lastSave")){   loadBox.setValue("lastSave");
        }

        try{
            nemeiah.setImage(new Image(getClass().getResource("/img/nemeiah.png").toExternalForm()));
        } catch(Exception e){
            Logger.error("Couldn't load images. {}",e);}
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