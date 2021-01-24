package test.client.client;

import client.java.controllers.client.Main;
import client.java.controllers.client.RegisterController;
import client.java.controllers.tools.Tour;
import client.java.controllers.tools.User;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MainTest {
    //given
    Thread thread;
    Main main;

    @Test
    public void shouldStart(){

        //then
        assertTrue(thread.isAlive(),"Thread should be running, but its not.");
    }

    @Test
    public void shouldSetAndGetUser(){
        //given
        User user = new User(1,"1","1","1","1","1");

        //when
        main.setUser(user);

        //then
        assertEquals(user,main.getUser(),"Should set and get user properly,but did not.");
    }

    @Test
    public void shouldSetAndGetTour(){
        //given
        Tour tour = new Tour(1,"1","1","1",1,1,1,1);

        //when
        main.setTour(tour);

        //then
        assertEquals(tour,main.getTour(),"Should set and get tour properly,but did not.");
    }


    @Before
    public void configurateThread(){
         thread = new Thread(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                   main = new Main();
                   main.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        thread.start();
    }
}
