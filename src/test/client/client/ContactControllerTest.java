package test.client.client;

import client.java.controllers.client.ContactController;
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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ContactControllerTest extends ApplicationTest {
    //given
    private ContactController contactController;
    static final String SCENE = "ContactScene.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(ContactController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        contactController = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void shouldStartClk(){
        //when

        //then
        assertTrue(contactController.clk != null,"Should initialize clock, but did not.");
    }
    @Test
    public void shouldStopClk(){
        //when
        contactController.shutdown();
        //then
        assertFalse(contactController.clk.flag,"Should stop clock, but did not.");
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[] {});
    }

}