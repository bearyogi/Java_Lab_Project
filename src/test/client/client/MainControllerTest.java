package test.client.client;

import client.java.controllers.client.Main;
import client.java.controllers.client.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.testng.Assert.assertEquals;


public class MainControllerTest extends ApplicationTest {
    private MainController mainController;
    static final String SCENE = "LogInScene.fxml";
    //given
    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
        main.start(new Stage());
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource(sceneName));
        mainController = loader.load();

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[] {});
    }

    @Test
    public void shouldEmptyPasswordShowError() {

        System.out.println(mainController.errorLabel.getText());
        //given
        String expected = "Pole nazwa użytkownika i hasło nie mogą być puste!";
        //when
        clickOn("#loginTextField");
        write("Test_user_name");
        clickOn("#loginField");
        assertEquals(mainController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }



}