package tools;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.application.Platform;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock implements Runnable{
Label clockLabel;
    public Clock(Label clockLabel){
        this.clockLabel = clockLabel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LocalTime currentTime = LocalTime.now();
            final String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            Platform.runLater( () -> {
                clockLabel.setText(time);
            });
        }
    }
}