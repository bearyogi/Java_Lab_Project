package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tools.Clock;

import java.io.IOException;

public class ManageToursController {
    @FXML
    Label clockLabel;
    @FXML
    public void initialize(){
        Clock clock = new Clock(clockLabel);
        clock.initClock();
    }
    public void goBackButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/UserScene.fxml");
    }
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml");
    }
}
