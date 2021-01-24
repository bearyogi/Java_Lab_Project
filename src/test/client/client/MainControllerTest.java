package test.client.client;

import client.java.controllers.client.MainController;
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

import static org.testng.Assert.assertEquals;

@org.testng.annotations.Test
public class MainControllerTest extends ApplicationTest {
    private MainController mainController;
    static final String SCENE = "LogInScene.fxml";

    //given
    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(MainController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        mainController = loader.getController();
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
    public void shouldEmptyPasswordShowError() {

        //given
        String expected = "Pole nazwa użytkownika i hasło nie mogą być puste!";
        //when
        clickOn("#loginTextField");
        write("Test_user_name");
        clickOn("#loginField");
        assertEquals(mainController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }

    @Test
    public void shouldEmptyLoginShowError() {

        //given
        String expected = "Pole nazwa użytkownika i hasło nie mogą być puste!";
        //when
        clickOn("#passwordTextField");
        write("Test_user_name");
        clickOn("#loginField");
        assertEquals(mainController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }
    @Test
    public void shouldEmptyBothShowError() {

        //given
        String expected = "Pole nazwa użytkownika i hasło nie mogą być puste!";
        //when
        clickOn("#loginField");
        assertEquals(mainController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }
    @Test
    public void shouldNotFindUser() {

        //given
        String expected = "Wprowadzone dane są niepoprawne, lub użytkownik o podanych danych nie istnieje.";
        //when
        clickOn("#loginTextField");
        write("Test_user_name");
        clickOn("#passwordTextField");
        write("Test_user_name");
        clickOn("#loginField");
        assertEquals(mainController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }

}