package client.java.controllers.employee;

import client.java.controllers.client.Main;
import client.java.controllers.client.SceneCreator;
import client.java.controllers.tools.Clock;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class ViewToursControllerAdmin {

    @FXML
    Label clockLabel;
    @FXML
    ScrollPane scrollPane;
    @FXML
    GridPane gridPane;
    @FXML
    AnchorPane anchorPane2;
    @FXML
    Image image;
    @FXML
    ImageView picture;
    @FXML
    Button editButton;
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;

    HBox hBox = new HBox();
    Clock clk;
    Thread th;

    int toursNumber;
    int gridPaneRows;
    int gridPaneColumns = 4;
    int iterator = 1;

    @FXML
    public void initialize() throws IOException {
        Main.getTour().setId(0);
        clk = new Clock(clockLabel);
        th = new Thread(clk);
        th.start();

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gridPane.setPadding(new Insets(7,7,7,7));
        gridPane.setHgap(15);
        gridPane.setVgap(10);
        toursNumber = getToursNumber();
        gridPaneRows = toursNumber/4 + 1;

        for (int i = 0 ; i < gridPaneRows; i++) {
            for (int j = 0; j < gridPaneColumns; j++) {
                if(iterator <= toursNumber){
                    addToGridPane(getTourFromServer(iterator),j, i);
                    iterator++;
                }
            }
        }
    }
    @FXML
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/LogInScene.fxml", Main.getUser());
        shutdown();
    }
    @FXML
    public void goBackButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/AdminScene.fxml",Main.getUser());
        shutdown();
    }
    @FXML
    public void editButton(MouseEvent event) throws IOException {
        if(Main.getTour().getId() != 0){
            SceneCreator.launchScene("../../../resources/fxml-files/EditTourScene.fxml",Main.getUser());
        }
    }
    @FXML
    public void addButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/AddTourScene.fxml",Main.getUser());
        shutdown();
    }
    @FXML
    public void deleteButton(MouseEvent event) throws IOException {
    if(Main.getTour().getId() != 0){
        deleteTour();
        SceneCreator.launchScene("../../../resources/fxml-files/ViewToursSceneAdmin.fxml",Main.getUser());
        shutdown();
    }
        Main.getTour().setId(0);
    }
    public void shutdown(){
        clk.terminate();
    }

    public String getTourFromServer(int id) throws IOException {
        String query = "getTour " + id;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        return bf.readLine();
    }
    public void deleteTour() throws IOException{
        String query = "deleteTour " + Main.getTour().getId();
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();
        confirmPopup();
    }
    public int getToursNumber() throws IOException {
        String query = "getToursNumber";
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();

        return Integer.parseInt(str);
    }

    public void addToGridPane(String tour, int columnNumber, int rowNumber){
        String[] s = tour.split("\\s+");
            try{
                image = new Image("/client/resources/images/" + s[7]);
            }catch (IllegalArgumentException e){
                image = new Image("/client/resources/images/noImageIcon.jpg");
            }


        picture = new ImageView();
        picture.setFitWidth(230);
        picture.setFitHeight(250);
        picture.setImage(image);
        picture.setId(s[0]);
        hBox.getChildren().add(picture);
        GridPane.setConstraints(picture, columnNumber, rowNumber, 1,1, HPos.CENTER, VPos.CENTER);
        gridPane.getChildren().addAll(picture);
        picture.setOnMouseClicked(e -> {
            Main.getTour().setId(Integer.parseInt(s[0]));
            Main.getTour().setTitle(s[1]);
            Main.getTour().setText(s[2]);
            Main.getTour().setDistance(Integer.parseInt(s[3]));
            Main.getTour().setDays(Integer.parseInt(s[4]));
            Main.getTour().setPrice(Integer.parseInt(s[5]));
            Main.getTour().setAvailableTickets(Integer.parseInt(s[6]));
            Main.getTour().setImage(s[7]);
            editButton.setStyle("-fx-opacity: 0.7;-fx-background-color:  #0066ff");
            deleteButton.setStyle("-fx-opacity: 0.7; -fx-background-color:  #e70d0d");
        });
    }
    public void confirmPopup() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Usunięcie");
        alert.setContentText("Pomyślnie usunięto wycieczkę o id: " + Main.getTour().getId() + "!");
        alert.setX(750);
        alert.setY(384);
        Optional<ButtonType> result = alert.showAndWait();
    }
}