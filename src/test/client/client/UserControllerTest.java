package test.client.client;

import client.java.controllers.client.Main;
import client.java.controllers.client.MainController;
import client.java.controllers.client.UserController;
import client.java.controllers.client.ViewToursController;
import client.java.controllers.tools.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static org.testng.Assert.assertEquals;

@org.testng.annotations.Test
public class UserControllerTest extends ApplicationTest {
    private MainController mainController;
    private UserController userController;
    static final String SCENE = "LogInScene.fxml";
    static final String SCENE_2 = "UserScene.fxml";
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
        loader = new FXMLLoader(MainController.class.getClassLoader().getResource(sceneName));
        mainNode = loader.load();
        userController = loader.getController();
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
    public void shouldDisplayUserName() {
        //when
        String name = Main.getUser().getName();
        //then
        assertEquals(userController.nameLabel.getText(),name,"Should display proper name, but did not.");
    }
    @Test
    public void shouldDisplayUserSurname() {
        //when
        String name = Main.getUser().getSurname();
        //then
        assertEquals(userController.surnameLabel.getText(),name,"Should display proper surname, but did not.");

    }
    @Test
    public void shouldDisplayUserEmail() {
        //when
        String name = Main.getUser().getEmail();
        //then
        assertEquals(userController.emailLabel.getText(),name,"Should display proper email, but did not.");

    }
    @Test
    public void shouldDisplayUserNick() {
        //when
        String name = Main.getUser().getNick();
        //then
        assertEquals(userController.nickLabel.getText(),name,"Should display proper nick, but did not.");

    }
    @Test
    public void shouldDisplayUserTourNumber() throws IOException {
        //when
        String userTourCount = userController.countUserTours();
        //then
        assertEquals(userController.countLabel.getText(),userTourCount,"Should display proper number, but did not.");

    }

    @Test
    public void shouldGoToTours(){
        //given

        //when
        clickOn("#tourButton");
        //then
    }
    @Test
    public void shouldGoToReservation(){
        clickOn("#reservationButton");
    }
    @Test
    public void shouldGoToRemoveReservation(){
        clickOn("#removeReservationButton");
    }
    @Test
    public void shouldGoToContact(){
        clickOn("#contactButton");
    }

}