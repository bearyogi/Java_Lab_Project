package test.client.employee;
import client.java.controllers.client.Main;
import client.java.controllers.client.MainController;
import client.java.controllers.employee.AdminController;
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

@org.testng.annotations.Test
public class AdminControllerTest extends ApplicationTest {
    static final String SCENE = "LogInScene.fxml";
    static final String SCENE_2 = "AdminScene.fxml";
    //given
    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(MainController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        MainController mainController = loader.getController();
        stage.setScene(new Scene(mainNode));
        Main.setUser(new User(1,"Tester","Testowy","tt@gmail.com","test","test"));

        sceneName = "fxml-files/" + SCENE_2;
        loader = new FXMLLoader(AdminController.class.getClassLoader().getResource(sceneName));
        mainNode = loader.load();
        AdminController adminController = loader.getController();
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
    public void shouldGoToTours(){
        //given

        //when
        clickOn("#toursBox");
        //then
    }
    @Test
    public void shouldGoToReservation(){
        clickOn("#reservationBox");
    }
    @Test
    public void shouldGoToRemoveReservation(){
        clickOn("#clientBox");
    }


}