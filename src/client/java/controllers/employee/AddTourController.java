package client.java.controllers.employee;

import client.java.controllers.client.Main;
import client.java.controllers.client.SceneCreator;
import client.java.controllers.tools.Clock;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.Socket;

public class AddTourController {

    @FXML
    Label clockLabel;
    @FXML
    Label titleLabel;
    @FXML
    Label descLabel;
    @FXML
    Label distanceLabel;
    @FXML
    Label daysLabel;
    @FXML
    Label priceLabel;
    @FXML
    Label ticketLabel;
    @FXML
    public TextField titleInput;
    @FXML
    public TextField descInput;
    @FXML
    public TextField distanceInput;
    @FXML
    public TextField daysInput;
    @FXML
    public TextField priceInput;
    @FXML
    public TextField ticketInput;
    @FXML
    public Label errorLabel;
    @FXML
    ImageView imageView;

    Clock clk;
    Thread th;
    Image image;
    String imageName;

    @FXML
    public void initialize(){
        clk = new Clock(clockLabel);
        th = new Thread(clk);
        th.start();
        titleLabel.setText("Tytuł");
        descLabel.setText("Opis");
        distanceLabel.setText("Odległość [km]");
        daysLabel.setText("Ilość dni");
        priceLabel.setText("Cena");
        ticketLabel.setText("Ilość dostępnych biletów");
        imageView.setFitWidth(200);
        imageView.setFitHeight(250);
        image = new Image("/client/resources/images/noImageIcon.jpg");
        imageView.setImage(image);
        restrictInput(distanceInput);
        restrictInput(daysInput);
        restrictInput(priceInput);
        restrictInput(ticketInput);
    }
    public void restrictInput(TextField input){
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                input.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    public void logOutButton(MouseEvent event) throws IOException {
        SceneCreator.launchScene("../../../resources/fxml-files/LogInScene.fxml",Main.getUser());
        shutdown();
    }
    public void confirmButton(MouseEvent event) throws IOException {
        if (titleInput.getText().isEmpty() || descInput.getText().isEmpty() || distanceInput.getText().isEmpty() || daysInput.getText().isEmpty()|| priceInput.getText().isEmpty() || ticketInput.getText().isEmpty()){
            errorLabel.setText("Należy wypełnić wszystkie tabele z danymi!");
        } else {
            communicateWithServer();

        }

    }
    public void goBackButton(MouseEvent event) throws IOException {
        Main.getUser().setId(1);
        Main.getUser().setName("");
        Main.getUser().setSurname("");
        Main.getUser().setNick("Admin");
        Main.getUser().setEmail("");
        Main.getUser().setPassword("");
        SceneCreator.launchScene("../../../resources/fxml-files/AdminScene.fxml",Main.getUser());
        shutdown();
    }
    public void communicateWithServer() throws IOException {
        String details = descInput.getText().replaceAll(" ","_");
        String result = "addTour " + titleInput.getText() + " " + details + " " + distanceInput.getText() + " " + daysInput.getText() + " " + priceInput.getText() +  " " + ticketInput.getText()+ " " + imageName;
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(result);
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        System.out.println("server : " + str);
        if (str.equals("Accepted")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Potwierdzenie");
            alert.setHeaderText(null);
            alert.setContentText("Nowa wycieczka została pomyślnie wprowadzona!");
            alert.setX(750);
            alert.setY(384);
            alert.showAndWait();
        }
        SceneCreator.launchScene("../../../resources/fxml-files/ViewToursSceneAdmin.fxml",Main.getUser());
        shutdown();
    }
    @FXML
    public void updateDesc(){
        descLabel.setText(descInput.getText());
    }
    @FXML
    public void updateTitle(){titleLabel.setText(titleInput.getText());}
    @FXML
    public void updatePrice(){priceLabel.setText(priceInput.getText());}
    @FXML
    public void updateDays(){daysLabel.setText(daysInput.getText());}
    @FXML
    public void updateDistance(){distanceLabel.setText(distanceInput.getText());}
    @FXML
    public void updateTicket(){ticketLabel.setText(ticketInput.getText());}
    @FXML
    public void uploadImageButton() throws IOException {


            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images(png,jpg)", "*.jpg", "*.png"));
        File selectedImage = fc.showOpenDialog(null);
            if (selectedImage == null)
                return;
            else if (ImageIO.read(selectedImage) == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Załaduj plik w formacie JPG lub PNG!",
                        ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    return;
                }
            } else {
                Image img = SwingFXUtils.toFXImage(ImageIO.read(selectedImage), null);
                imageView.setImage(img);
                imageName = selectedImage.getName();
            }

    }

    public void shutdown(){
        clk.terminate();
    }
}
