package tools;
import javafx.scene.control.Label;
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
            LocalTime currentTime = LocalTime.now();
            final String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            Platform.runLater( () -> {
                clockLabel.setText(time);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}