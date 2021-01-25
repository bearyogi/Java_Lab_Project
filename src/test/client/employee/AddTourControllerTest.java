package test.client.client;

import client.java.controllers.client.Main;
import client.java.controllers.client.MainController;
import client.java.controllers.employee.AddTourController;
import client.java.controllers.tools.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static org.testng.Assert.assertEquals;

@org.testng.annotations.Test
public class AddTourControllerTest extends ApplicationTest {
    private MainController mainController;
    private AddTourController addTourController;
    static final String SCENE = "LogInScene.fxml";
    static final String SCENE_2 = "AddTourScene.fxml";
    //given
    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(MainController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        mainController = loader.getController();
        stage.setScene(new Scene(mainNode));
        Main.setUser(new User(1,"Tester","Testowy","tt@gmail.com","test","test"));

        sceneName = "fxml-files/" + SCENE_2;
        loader = new FXMLLoader(AddTourController.class.getClassLoader().getResource(sceneName));
        mainNode = loader.load();
        addTourController = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[] {});
    }

    @Test
    public void shouldDisplayErrorWhenOnlyTitle() {
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#titleBox");
        write("test");
        clickOn("#confirmButton");
        //then
        assertEquals(addTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWhenOnlyText() {
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#textBox");
        write("test");
        clickOn("#confirmButton");
        //then
        assertEquals(addTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWhenOnlyDays() {
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#daysBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(addTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWhenOnlyPrice() {
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#priceBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(addTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldDisplayErrorWhenOnlyTickets() {
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#ticketBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(addTourController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldRestrictInputPrice() {
        //given
        //when
        clickOn("#priceBox");
        write("test");
        //then
        assertEquals(addTourController.priceInput.getText(),"","Should restrict input, but did not.");
    }
    @Test
    public void shouldRestrictInputDays() {
        //given
        //when
        clickOn("#daysBox");
        write("test");
        //then
        assertEquals(addTourController.priceInput.getText(),"","Should restrict input, but did not.");
    }
    @Test
    public void shouldRestrictInputTickets() {
        //given
        //when
        clickOn("#ticketBox");
        write("test");
        //then
        assertEquals(addTourController.priceInput.getText(),"","Should restrict input, but did not.");
    }
    @Test
    public void shouldConfirm() {
        //given
        //when
        clickOn("#ticketBox");
        write("1");
        clickOn("#priceBox");
        write("1");
        clickOn("#daysBox");
        write("1");
        clickOn("#titleBox");
        write("1");
        clickOn("#textBox");
        write("1");
        clickOn("#distanceBox");
        write("1");
        clickOn("#confirmButton");
        //then
        assertEquals(addTourController.errorLabel.getText(),"","Should not display proper error, but did not.");
    }

}