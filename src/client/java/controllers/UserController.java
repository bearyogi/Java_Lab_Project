package client.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import java.io.IOException;

import client.resources.tools.Clock;
public class UserController {

    @FXML
    Label clockLabel;
    @FXML
    public void initialize(){
        Clock clk = new Clock(clockLabel);
        Thread th = new Thread(clk);
        th.start();
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