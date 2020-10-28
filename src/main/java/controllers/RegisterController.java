package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tools.Clock;

import java.io.IOException;

public class RegisterController {

  /*
  TODO: place rest of @FXML annotations
   */
  @FXML
  Label clockLabel;
    @FXML
    public void initialize(){
        Clock clock = new Clock(clockLabel);
        clock.initClock();
    }
    @FXML
    public void registerButton(MouseEvent event) throws IOException {
        //TODO: registerButton logic, add new user to databese, check if credensials are right (password, email etc)
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml");
    }
    public void goBackButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml");
    }
}