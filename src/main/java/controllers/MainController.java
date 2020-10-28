package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController {

  /*
  TODO: place rest of @FXML annotations
   */

    @FXML
    public void loginButton(MouseEvent event) throws IOException {
        //TODO: check for user credensials
        //TODO: employee stage

        SceneCreator.launchScene("../../resources/fxml-files/UserScene.fxml");
    }
    public void registerButton(MouseEvent event) throws IOException {
        //TODO: register logic
        //TODO: register stage

        SceneCreator.launchScene("../../resources/fxml-files/RegisterScene.fxml");
    }

}
