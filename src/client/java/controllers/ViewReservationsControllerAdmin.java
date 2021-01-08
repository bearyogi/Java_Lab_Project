package client.java.controllers;

import client.resources.tools.Clock;
import client.resources.tools.Reservation;
import client.resources.tools.ReservationCustomCell;
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

public class ViewReservationsControllerAdmin {
    @FXML
    Label clockLabel;

    @FXML
    ListView<Reservation> manageList;
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    Clock clk;
    Thread th;

    @FXML
    public void initialize() throws IOException {
        clk = new Clock(clockLabel);
        th = new Thread(clk);
        th.start();
        getAllReservations();
    }
    public void goBackButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/ViewTourAndReservationsSceneAdmin.fxml",Main.getUser());
        shutdown();
    }
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../resources/fxml-files/LogInScene.fxml",Main.getUser());
        shutdown();
    }
    public void getAllReservations() throws IOException {
        String result = "getAllReservationsTour " + Main.getTour().getId();
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();

        try {
            String[] all = str.split("#");
            for (String reservation : all) {
                String[] one = reservation.split("@");
                Reservation listReservation = new Reservation(Integer.parseInt(one[0]), one[1], Integer.parseInt(one[2]), one[3], one[4]);
                list.add(listReservation);

            }
            this.manageList.setItems(list);
            manageList.setCellFactory(param -> new ReservationCustomCell());
        }catch(NullPointerException e){
            e.printStackTrace();
        }

    }

    public void shutdown(){
        clk.terminate();
    }



}
