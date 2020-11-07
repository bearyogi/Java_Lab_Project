package client.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainController {

    @FXML
    TextField usernameBox;
    @FXML
    PasswordField passwordBox;
    @FXML
     Label errorLabel;
    @FXML
    public void loginButton(MouseEvent event) throws IOException {
        if(!usernameBox.getText().isEmpty() && !passwordBox.getText().isEmpty()) {
            communicateWithServer();
        }else{
            errorLabel.setText("Pole nazwa użytkownika i hasło nie mogą być puste!");
        }
    }


    public void registerButton(MouseEvent event) throws IOException {
        //TODO: register logic
        //TODO: employee stage
        SceneCreator.launchScene("../../resources/fxml-files/RegisterScene.fxml");
    }

    public void communicateWithServer() throws IOException {
        String result = "users " + usernameBox.getText() + " " + passwordBox.getText();
        Socket s = new Socket("localhost", 4999);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("server : " + str);
        if (str.equals("Accepted")) {
            if(usernameBox.getText().equals("admin")){
                SceneCreator.launchScene("../../resources/fxml-files/AdminScene.fxml");
            }else{
                SceneCreator.launchScene("../../resources/fxml-files/UserScene.fxml");
            }

        } else {
            errorLabel.setText("Wprowadzone dane są niepoprawne, lub użytkownik o podanych danych nie istnieje.");
        }
    }

}
