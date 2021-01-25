package test.client.employee;

import client.java.controllers.client.*;
import client.java.controllers.employee.EditTourController;
import client.java.controllers.tools.Tour;
import client.java.controllers.tools.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.testng.Assert.assertEquals;


@org.testng.annotations.Test
public class EditTourControllerTest extends ApplicationTest {
    private MainController mainController;
    private EditTourController editTourController;
    static final String SCENE = "LogInScene.fxml";
    static final String SCENE_2 = "EditTourScene.fxml";
    //given

    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(MainController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        mainController = loader.getController();
        stage.setScene(new Scene(mainNode));


        Main.setUser(new User(1,"Tester","Testowy","tt@gmail.com","test","test"));
        Main.setTour(new Tour(1,"noImageIcon.jpg","test","test",1,1,1,1));

        sceneName = "fxml-files/" + SCENE_2;
        loader = new FXMLLoader(EditTourController.class.getClassLoader().getResource(sceneName));
        mainNode = loader.load();
        editTourController = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
        editTourController.daysInput.setText("");
        editTourController.priceInput.setText("");
        editTourController.titleInput.setText("");
        editTourController.descInput.setText("");
        editTourController.distanceInput.setText("");
        editTourController.ticketInput.setText("");
        Image image = new Image("/client/resources/images/noImageIcon.jpg");
        editTourController.imageView.setImage(image);
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[] {});
    }

    @Test
    public void shouldDisplayErrorWithJustTitle(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#titleBox");
        write("test");
        clickOn("#confirmButton");
        //then
        assertEquals(editTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWithJustText(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#textBox");
        write("test");
        clickOn("#confirmButton");
        //then
        assertEquals(editTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWithJustPrice(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#priceBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(editTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWithJustDays(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#daysBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(editTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWithJustTicket(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#ticketBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(editTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWithJustDistance(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#distanceBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(editTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldRestrictDistance(){
        //given
        String expected = "";
        //when
        clickOn("#distanceBox");
        write("test");
        //then
        assertEquals(editTourController.titleInput.getText(),expected,"Should be empty, but did not.");
    }
    @Test
    public void shouldRestrictPrice(){
        //given
        String expected = "";
        //when
        clickOn("#priceBox");
        write("test");
        //then
        assertEquals(editTourController.titleInput.getText(),expected,"Should be empty, but did not.");
    }
    @Test
    public void shouldRestrictDays(){
        //given
        String expected = "";
        //when
        clickOn("#daysBox");
        write("test");
        //then
        assertEquals(editTourController.titleInput.getText(),expected,"Should be empty, but did not.");
    }
    @Test
    public void shouldRestrictTicket(){
        //given
        String expected = "";
        //when
        clickOn("#ticketBox");
        write("test");
        //then
        assertEquals(editTourController.titleInput.getText(),expected,"Should be empty, but did not.");
    }
    @Test
    public void shouldConfirmChange(){
        //given
        //when
        clickOn("#titleBox");
        write("new");
        clickOn("#textBox");
        write("new");
        clickOn("#priceBox");
        write("1");
        clickOn("#daysBox");
        write("1");
        clickOn("#distanceBox");
        write("1");
        clickOn("#ticketBox");
        write("1");
        clickOn("#confirmButton");
    }


}