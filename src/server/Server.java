package server;

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

    static final int PORT = 4999;

    public static void main(String[] args) throws IOException, SQLException {

        String host = "jdbc:mysql://localhost:3306/filmdb?serverTimezone=UTC";
        String userName = "root";
        String userPassword = "Minotaur21#";
        Connection connection = DriverManager.getConnection(host,userName,userPassword);

        Socket socket = null;
        ServerSocket serverSocket = new ServerSocket(PORT);

       while(true){
           socket = serverSocket.accept();
           new EchoThread(socket,connection).start();
       }

    }
}

class EchoThread extends Thread{
    protected Socket socket;
    protected Connection connection;
    public EchoThread(Socket clientSocket, Connection connection){
        this.socket = clientSocket;
        this.connection = connection;
    }

    public void run(){
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        DataOutputStream dataOutputStream = null;

        try{
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch(IOException e){
            return;
        }

        String line;
        while(true){
            try{
                line = bufferedReader.readLine();

                if((line == null) || line.equalsIgnoreCase("QUIT")){
                    socket.close();
                    return;
                }else {
                    String[] s = line.split("\\s+");
                    if (s[0].equals("login")) {
                        makeLoginQuery(dataOutputStream, s);
                    }else{
                        dataOutputStream.writeBytes(line + "\n\r");
                        dataOutputStream.flush();
                    }
                }

            }catch(IOException | SQLException e){
                e.printStackTrace();
                return;
            }
        }
    }

    public void makeLoginQuery(DataOutputStream dataOutputStream, String[] s) throws SQLException, IOException {
        Statement stat = connection.createStatement();
        String sql = "select * from filmdb." + s[0] + " where userLogin = '" + s[1] + "' and userPassword = '" + s[2] + "'";
        System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next() != false) {
            dataOutputStream.writeBytes("Accepted" + "\n\r");
            dataOutputStream.flush();
        } else {
            dataOutputStream.writeBytes("Rejected" + "\n\r");
            dataOutputStream.flush();
        }
    }



}
