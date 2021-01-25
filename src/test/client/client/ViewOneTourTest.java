package test.client.client;

import client.java.controllers.client.Main;
import client.java.controllers.client.MainController;
import client.java.controllers.client.ViewOneTourController;
import client.java.controllers.tools.Tour;
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
public class ViewOneTourTest extends ApplicationTest {
    private ViewOneTourController viewOneTourController;
    static final String SCENE = "LogInScene.fxml";
    static final String SCENE_2 = "ViewOneTourScene.fxml";
    //given
    @Override
    public void start(Stage stage) throws Exception {
        String sceneName = "fxml-files/" + SCENE;
        FXMLLoader loader = new FXMLLoader(MainController.class.getClassLoader().getResource(sceneName));
        Parent mainNode = loader.load();
        MainController mainController = loader.getController();
        stage.setScene(new Scene(mainNode));
        Main.setUser(new User(1,"Tester","Testowy","tt@gmail.com","test","test"));
        Main.setTour(new Tour(1,"test","test","test",1,1,1,1));
        sceneName = "fxml-files/" + SCENE_2;
        loader = new FXMLLoader(ViewOneTourController.class.getClassLoader().getResource(sceneName));
        mainNode = loader.load();
        viewOneTourController = loader.getController();
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
    public void shouldDisplayTourTitle() {
        //when
        String name = Main.getTour().getTitle();
        //then
        assertEquals(viewOneTourController.titleLabel.getText(), name, "Should display proper title, but did not.");
    }
    @Test
    public void shouldDisplayTourText() {
        //when
        String name = Main.getTour().getTitle();
        //then
        assertEquals(viewOneTourController.textLabel.getText(), name, "Should display proper text, but did not.");
    }
    @Test
    public void shouldDisplayTourDays() {
        //when
        String name ="Czas trwania: " + Main.getTour().getDays()+" dni";
        //then
        assertEquals(viewOneTourController.daysLabel.getText(), name, "Should display proper days, but did not.");
    }
    @Test
    public void shouldDisplayTourPrice() {
        //when
        String name = "Cena na 1 osobę: " + Main.getTour().getPrice() + " zł";
        //then
        assertEquals(viewOneTourController.priceLabel.getText(), name, "Should display proper price, but did not.");
    }
    @Test
    public void shouldDisplayTourDistance() {
        //when
        String name ="Przebyta trasa: " + Main.getTour().getDistance() + " km";
        //then
        assertEquals(viewOneTourController.distanceLabel.getText(), name, "Should display proper distance, but did not.");
    }
    @Test
    public void shouldDisplayTourAvalaible() {
        //when
        String name = "Ilość wolnych miejsc: " + Main.getTour().getAvailableTickets();
        //then
        assertEquals(viewOneTourController.availableLabel.getText(), name, "Should display proper tickets number, but did not.");
    }

}