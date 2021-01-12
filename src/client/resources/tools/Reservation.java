package client.resources.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {

    @Override
    public String toString() {
        return "| " + title + " |   " + "ID: " + reservationId + ", Koszt: " + totalPrice + "zł, Data wyjazdu: " + date + ", Status: " + status;
    }
    private int reservationId;
    private String title;
    private int totalPrice;
    private String date;
    private String status;


}
