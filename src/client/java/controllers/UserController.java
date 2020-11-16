package client.java.controllers;

import client.resources.tools.User;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.Socket;

import client.resources.tools.Clock;
public class UserController {
User activeUser = Main.getUser();
    @FXML
    Label clockLabel;
    @FXML
    Label helloUser;
    @FXML
    Label nameLabel;
    @FXML
    Label surnameLabel;
    @FXML
    Label nickLabel;
    @FXML
    Label emailLabel;
    @FXML
    Label countLabel;
    @FXML
    public void initialize() {
        Clock clk = new Clock(clockLabel);
        Thread th = new Thread(clk);
        th.start();
        helloUser.setText("Witaj, " + activeUser.getName());
        nameLabel.setText(activeUser.getName());
        surnameLabel.setText(activeUser.getSurname());
        nickLabel.setText(activeUser.getNick());
        emailLabel.setText(activeUser.getEmail());
    }
    @FXML
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml",Main.getUser());
    }

    @FXML
    public void editButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/EditCredensialsScene.fxml",Main.getUser());
    }

    @FXML
    public void viewToursButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ViewToursScene.fxml",Main.getUser());
    }

    @FXML
    public void removeReservationButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/RemoveReservationScene.fxml",Main.getUser());
    }

    @FXML
    public void contactButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ContactScene.fxml",Main.getUser());
    }

    @FXML
    public void viewReservationButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ManageToursScene.fxml",Main.getUser());
    }

}