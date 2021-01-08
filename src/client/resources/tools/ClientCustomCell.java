package client.resources.tools;

import client.java.controllers.Main;
import client.java.controllers.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class ClientCustomCell extends ListCell<Client> {
    HBox hbox = new HBox();
    Label label = new Label("");
    Pane pane = new Pane();
    Button editButtom = new Button();
    Button deleteButton = new Button();
    String clientData;
    ObservableList<Client> list = FXCollections.observableArrayList();
    int clientId;
    public ClientCustomCell(String name, String name2) {
        super();
        editButtom.setMinSize(100,35);
        editButtom.setMaxSize(100,35);
        editButtom.setStyle("-fx-background-color: #62d813; -fx-opacity: 80%; -fx-text-fill: white;");
        editButtom.setText(name);

        deleteButton.setMinSize(100,35);
        deleteButton.setMaxSize(100,35);
        deleteButton.setStyle("-fx-background-color: #0066ff; -fx-opacity: 80%; -fx-text-fill: white;");
        deleteButton.setText(name2);

        hbox.setSpacing(8);
        hbox.getChildren().addAll(label, pane, editButtom, deleteButton);
        HBox.setHgrow(pane, Priority.ALWAYS);

        editButtom.setOnAction(event -> {
            try {
                getClientId(getItem().getIdUser());
                getClientById(clientId);
                SceneCreator.launchScene("../../resources/fxml-files/EditCredensialsSceneAdmin.fxml", Main.getUser());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                getClientId(getItem().getIdUser());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                deleteClientFromServer(clientId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                confirmPopup();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                getAllClients();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void updateItem(Client item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            label.setTextFill(Color.WHITE);
            label.setText(item.toString());
            setGraphic(hbox);
        }
    }

    public void getClientId(int id) throws IOException {
        String query = "getClientId " + id;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        clientId = Integer.parseInt(str);
    }
    public void getClientById(int id) throws IOException {
        String query = "getClientById " + id;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        String[] all = str.split(" ");
        Main.getUser().setId(Integer.parseInt(all[0]));
        Main.getUser().setNick(all[1]);
        Main.getUser().setPassword(all[2]);
        Main.getUser().setName(all[3]);
        Main.getUser().setSurname(all[4]);
        Main.getUser().setEmail(all[5]);
    }
    public void deleteClientFromServer(int id) throws IOException {
        String query = "deleteClient " + id;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(query);
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
    }
    public void getAllClients() throws IOException{
        String result = "getAllClients ";
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        String[] all = str.split("#");
        for(String client: all){
            String[] one = client.split("@");
            Client listClient = new Client(Integer.parseInt(one[0]),one[1],one[2],one[3],one[4]);
            list.add(listClient);

        }
        getListView().setItems(list);
        getListView().setCellFactory(param -> new ClientCustomCell("Edytuj", "Usuń"));
    }
    public void confirmPopup() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Usunięcie");
        alert.setContentText("Usunięto klienta o id: " + getItem().getIdUser() + "!");
        alert.setX(750);
        alert.setY(384);
        Optional<ButtonType> result = alert.showAndWait();
    }
}
