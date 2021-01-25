package test.client.client;

import client.java.controllers.client.*;
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

import static org.testng.Assert.assertEquals;


@org.testng.annotations.Test
public class EditCredensialsControllerTest extends ApplicationTest {
    private MainController mainController;
    private EditCredensialsController editCredensialsController;
    static final String SCENE = "LogInScene.fxml";
    static final String SCENE_2 = "EditCredensialsScene.fxml";
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
        loader = new FXMLLoader(EditCredensialsController.class.getClassLoader().getResource(sceneName));
        mainNode = loader.load();
        editCredensialsController = loader.getController();
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
    public void shouldDisplayProperName(){
        //given
        String expected = "Tester";
        //when
        //then
        assertEquals(editCredensialsController.nameLabel.getText(),expected,"Should display proper name, but did not.");
    }
    @Test
    public void shouldDisplayProperSurname(){
        //given
        String expected = "Testowy";
        //when
        //then
        assertEquals(editCredensialsController.surnameLabel.getText(),expected,"Should display proper surname, but did not.");
    }
    @Test
    public void shouldDisplayProperEmail(){
        //given
        String expected = "tt@gmail.com";
        //when
        //then
        assertEquals(editCredensialsController.emailLabel.getText(),expected,"Should display proper email, but did not.");
    }
    @Test
    public void shouldDisplayProperNick(){
        //given
        String expected = "test";
        //when
        //then
        assertEquals(editCredensialsController.nickLabel.getText(),expected,"Should display proper nick, but did not.");
    }
    @Test
    public void shouldDisplayErrorWhileFieldEmpty(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#confirmButton");
        //then
        assertEquals(editCredensialsController.errorLabel.getText(),expected,"Should display proper error, but did not.");
    }
    @Test
    public void shouldConfirmChange(){
        //given
        String expected = "Należy wypełnić wszystkie tabele z danymi!";
        //when
        clickOn("#nameBox");
        write("new");
        clickOn("#surnameBox");
        write("new");
        clickOn("#emailBox");
        write("new@gmail.com");
        clickOn("#passwordBox");
        write("new");
        clickOn("#confirmButton");
    }


}