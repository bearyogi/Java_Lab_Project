package client.java.controllers.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tour {
    private int id;
    private String image;
    private String title;
    private String text;
    private int distance;
    private int days;
    private int price;
    private int availableTickets;

}
