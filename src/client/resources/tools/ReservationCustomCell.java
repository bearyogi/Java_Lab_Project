package client.resources.tools;

import client.java.controllers.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReservationCustomCell extends ListCell<Reservation> {
    HBox hbox = new HBox();
    Label label = new Label("");
    Pane pane = new Pane();
    Button button = new Button("Usuń");
    int tourId;
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    public ReservationCustomCell() {
        super();
        button.setMinSize(100,35);
        button.setMaxSize(100,35);
        button.setStyle("-fx-background-color: #a30a0a; -fx-opacity: 80%; -fx-text-fill: #ffffff;");


        hbox.setSpacing(8);
        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);

        button.setOnAction(event -> {
            try {
                getTourId(getItem().getReservationId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                giveBackToTour();
                deleteReservation();
                getAllReservations();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void updateItem(Reservation item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            label.setTextFill(Color.WHITE);
            label.setText(item.toString());
            setGraphic(hbox);
        }
    }

    public void deleteReservation() throws IOException {

        String result = "deleteReservation " + getItem().getReservationId();
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        for(Reservation el: getListView().getItems()){
            if(el.getReservationId() == tourId) {
                getListView().getItems().remove(el);
                break;
            }
        }
        getListView().setItems(getListView().getItems());
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
        String[] all = str.split("#");
        for(String reservation: all){
            String[] one = reservation.split("@");
            Reservation listReservation = new Reservation(Integer.parseInt(one[0]),one[1],Integer.parseInt(one[2]),one[3],one[4]);
            list.add(listReservation);

        }
        getListView().setItems(list);
        getListView().setCellFactory(param -> new ReservationCustomCell());
    }

    public void giveBackToTour() throws IOException{
        String result = "giveBack " + getItem().getReservationId();
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
    }

    public void getTourId(int id) throws IOException {
        String query = "getTourId " + id;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        tourId = Integer.parseInt(str);
    }
}
