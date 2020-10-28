package main.java.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.util.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import tools.Clock;
public class UserController {

    @FXML
    Label clockLabel;
    @FXML
    public void initialize(){
        Clock clock = new Clock(clockLabel);
        clock.initClock();
    }
    @FXML
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml");
    }

    @FXML
    public void editButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/EditCredensialsScene.fxml");
    }

    @FXML
    public void viewToursButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ViewToursScene.fxml");
    }

    @FXML
    public void removeReservationButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/RemoveReservationScene.fxml");
    }

    @FXML
    public void contactButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ContactScene.fxml");
    }

    @FXML
    public void viewReservationButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ManageToursScene.fxml");
    }

}