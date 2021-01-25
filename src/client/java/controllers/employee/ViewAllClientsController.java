package client.java.controllers.employee;

import client.java.controllers.client.Main;
import client.java.controllers.client.SceneCreator;
import client.java.controllers.tools.Client;
import client.java.controllers.tools.ClientCustomCell;
import client.java.controllers.tools.Clock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ViewAllClientsController {
    @FXML
    Label clockLabel;

    @FXML
    ListView<Client> manageList;
    ObservableList<Client> list = FXCollections.observableArrayList();
    Clock clk;
    Thread th;

    @FXML
    public void initialize() throws IOException {
        clk = new Clock(clockLabel);
        th = new Thread(clk);
        th.start();
        getAllClients();
    }
    public void goBackButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/AdminScene.fxml", Main.getUser());
        shutdown();
    }
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/LogInScene.fxml",Main.getUser());
        shutdown();
    }
    public void getAllClients() throws IOException {
        String result = "getAllClients ";
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        String[] all = str.split("#");
        for(String reservation: all){
            String[] one = reservation.split("@");
            Client listReservation = new Client(Integer.parseInt(one[0]),one[1],one[2],one[3],one[4]);
            list.add(listReservation);

        }
        this.manageList.setItems(list);
        manageList.setCellFactory(param -> new ClientCustomCell("Edytuj", "Usuń"));
    }

    public void shutdown(){
        clk.terminate();
    }



}
