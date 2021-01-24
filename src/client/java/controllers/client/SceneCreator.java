package client.java.controllers.client;

import client.java.controllers.tools.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneCreator {

    public static void launchScene (String sceneName, User activeUser) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(sceneName));
        Main.setRoot(loader.load());
        Scene scene = new Scene(Main.getRoot());
        Main.getStage().setScene(scene);
        Main.getStage().show();
        Main.setUser(activeUser);
    }

}
