package client.java.controllers.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import client.java.controllers.tools.Clock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

public class ViewOneTourController {
    @FXML
    Label clockLabel;
    @FXML
    public Label titleLabel;
    @FXML
    public Label textLabel;
    @FXML
    public Label daysLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public Label availableLabel;
    @FXML
    public Label distanceLabel;
    @FXML
    public ImageView imageView;
    @FXML
    public ChoiceBox<Integer> choiceBox;
    @FXML
    public Label errorLabel;

    Clock clk;
    Thread th;
    Image image;
    String details = Main.getTour().getText().replaceAll("_"," ");
    ObservableList<Integer> choiceBoxList = FXCollections.observableArrayList(1,2,3,4,5,6,7);
    @FXML
    public void initialize(){
        clk = new Clock(clockLabel);
        th = new Thread(clk);
        th.start();
        titleLabel.setText(Main.getTour().getTitle());
        textLabel.setText(details);
        daysLabel.setText("Czas trwania: " + Main.getTour().getDays()+" dni");
        priceLabel.setText("Cena na 1 osobę: "+Main.getTour().getPrice()+" zł");
        availableLabel.setText("Ilość wolnych miejsc: " + Main.getTour().getAvailableTickets()+"");
        distanceLabel.setText("Przebyta trasa: "+Main.getTour().getDistance()+" km");
        try{
            image = new Image("/client/resources/images/" + Main.getTour().getImage());
        }catch (IllegalArgumentException e){
            image = new Image("/client/resources/images/noImageIcon.jpg");
        }

        imageView.setFitWidth(300);
        //imageView.setFitHeight(300);
        imageView.setImage(image);
        choiceBox.setItems(choiceBoxList);
        choiceBox.setValue(1);
    }
    @FXML
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/LogInScene.fxml",Main.getUser());
        shutdown();
    }
    @FXML
    public void goBackButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/ViewToursScene.fxml",Main.getUser());
        shutdown();
    }
    @FXML
    public void makeReservationButton(MouseEvent event) throws IOException {
        if(choiceBox.getValue() <= Main.getTour().getAvailableTickets()){
            changeAvailableTickets();
            makeReservation();
            SceneCreator.launchScene("../../../resources/fxml-files/UserScene.fxml",Main.getUser());
            shutdown();
        }
        else {
            errorLabel.setText("Ilość wybranych miejsc nie może przekraczać liczby dostępnych biletów!");
        }
    }

    public void shutdown(){
        clk.terminate();
    }
    public void confirmPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Rezerwacja została złożona! Pamiętaj o opłacie");
        alert.setX(750);
        alert.setY(384);
        alert.showAndWait();
    }

    public void changeAvailableTickets() throws IOException {
        int availableAfterReservation = Main.getTour().getAvailableTickets() - choiceBox.getValue();
        String result = "changeAvailableTickets "+Main.getTour().getId()+" "+availableAfterReservation;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        if (str.equals("Accepted")) {
            Main.getTour().setAvailableTickets(availableAfterReservation);
        }
    }
    public void makeReservation() throws IOException {
        java.util.Date datetest = new java.util.Date();
        Date data = new Date(datetest.getTime());
        String result = "makeReservation " + Main.getUser().getId() + " " + Main.getTour().getId() + " " + choiceBox.getValue()*Main.getTour().getPrice() + " " + data.toString() + " " + "doZaplaty";
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        System.out.println("server : " + str);
        if (str.equals("Accepted")) {
            confirmPopup();
        }
    }

}
