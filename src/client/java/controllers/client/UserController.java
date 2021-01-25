package client.java.controllers.client;

import client.java.controllers.tools.Clock;
import client.java.controllers.tools.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserController {
User activeUser = Main.getUser();

    @FXML
    Label clockLabel;
    @FXML
    Label helloUser;
    @FXML
    public Label nameLabel;
    @FXML
    public Label surnameLabel;
    @FXML
    public Label nickLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label countLabel;

    Thread th;
    Clock clk;
    @FXML
    public void initialize() throws IOException {
        clk = new Clock(clockLabel);
        th = new Thread(clk);
        th.start();
        helloUser.setText("Witaj, " + activeUser.getName());
        nameLabel.setText(activeUser.getName());
        surnameLabel.setText(activeUser.getSurname());
        nickLabel.setText(activeUser.getNick());
        emailLabel.setText(activeUser.getEmail());
        countLabel.setText(countUserTours());
    }

    public String countUserTours() throws IOException{
        String result = "countUserTours " + Main.getUser().getId();
        Socket s = new Socket("localhost", 4999);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        return bf.readLine();
    }
    @FXML
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/LogInScene.fxml",Main.getUser());
        shutdown();
    }

    @FXML
    public void editButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/EditCredensialsScene.fxml",Main.getUser());
        shutdown();
    }

    @FXML
    public void viewToursButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/ViewToursScene.fxml",Main.getUser());
        shutdown();
    }

    @FXML
    public void removeReservationButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/RemoveReservationScene.fxml",Main.getUser());
        shutdown();
    }

    @FXML
    public void contactButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/ContactScene.fxml",Main.getUser());
        shutdown();
    }

    @FXML
    public void viewReservationButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/ManageToursScene.fxml",Main.getUser());
        shutdown();
    }

    public void shutdown(){
        clk.terminate();
    }
}