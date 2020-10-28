package tools;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock {
Label clockLabel;
    public Clock(Label clockLabel){
        this.clockLabel = clockLabel;
    }
    public void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            clockLabel.setText(formattedTime);
        }),
                new KeyFrame(Duration.seconds(1.0))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
