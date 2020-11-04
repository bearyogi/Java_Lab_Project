package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tools.Clock;

import java.io.IOException;

public class ViewToursController {

  /*
  TODO: place rest of @FXML annotations
   */
  @FXML
  Label clockLabel;
    @FXML
    public void initialize(){
        Clock clk = new Clock(clockLabel);
        Thread th = new Thread(clk::run);
        th.start();
    }
    @FXML
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml");
    }
}