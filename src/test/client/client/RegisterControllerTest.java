package test.client.client;

import client.java.controllers.client.ContactController;
import client.java.controllers.client.RegisterController;
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
public class RegisterControllerTest extends ApplicationTest {
    //given
    private RegisterController registerController;
    static final String SCENE = "RegisterScene.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(ContactController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        registerController = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void shouldOnlyLoginShowError() {

        //given
        String expected = "Wszystkie pola muszą zostać uzupełnione!";
        //when
        clickOn("#loginField");
        write("Test");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }
    @Test
    public void shouldOnlyPasswordShowError() {

        //given
        String expected = "Wszystkie pola muszą zostać uzupełnione!";
        //when
        clickOn("#passwordField");
        write("Test");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }
    @Test
    public void shouldOnlySurnameShowError() {

        //given
        String expected = "Wszystkie pola muszą zostać uzupełnione!";
        //when
        clickOn("#surnameField");
        write("Test");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }

    @Test
    public void shouldOnlyNameShowError(){

        //given
        String expected = "";
        //when
        clickOn("#registerField");
        clickOn("#nameField");
        write("heeeeloo");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }

    @Test
    public void shouldOnlyEmailShowError() {

        //given
        String expected = "Wszystkie pola muszą zostać uzupełnione!";
        //when
        clickOn("#emailField");
        write("Test");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }
    @Test
    public void shouldWrongEmailShowError() {

        //given
        String expected = "Podaj poprawny adres E-mail!";
        //when
        clickOn("#emailField");
        write("Test");
        clickOn("#surnameField");
        write("Test");
        clickOn("#nameField");
        write("Test");
        clickOn("#loginField");
        write("Test");
        clickOn("#passwordField");
        write("Test");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }
    @Test
    public void shouldExistingUserShowError() {

        //given
        String expected = "Użytkownik o podanych danych (email,nick) już istnieje!";
        //when
        clickOn("#emailField");
        write("mmichal1999@gmail.com");
        clickOn("#surnameField");
        write("Test");
        clickOn("#nameField");
        write("Test");
        clickOn("#loginField");
        write("yogi");
        clickOn("#passwordField");
        write("Test");
        clickOn("#registerField");
        assertEquals(registerController.errorLabel.getText(),expected,"Should return proper error prompt, but did not.");
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[] {});
    }

}