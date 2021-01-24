package client.java.controllers.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    private int idUser;
    private String userLogin;
    private String userName;
    private String userSurname;
    private String userEmail;

    @Override
    public String toString(){return idUser + " | " + userName + " | " + userSurname + " | " + userEmail;}
}
