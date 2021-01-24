package test.client.client;

import client.java.controllers.client.ContactController;
import client.java.controllers.client.Main;
import client.java.controllers.client.SceneCreator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;


public class ContactControllerTest extends ApplicationTest {
    private ContactController contactController;


    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
        main.start(new Stage());
        SceneCreator.launchScene("../../../resources/fxml-files/ContactScene.fxml", Main.getUser());
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[] {});
    }

    @Test
    public void shouldEmptyPasswordShowError() {
        //clickOn("#userName");
        //write("Test_user_name");
        //clickOn("#signInButton");

    }



}