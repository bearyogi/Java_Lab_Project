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
           new MyThread(socket,connection).start();
       }

    }
}

class MyThread extends Thread{
    protected Socket socket;
    protected Connection connection;
    public MyThread(Socket clientSocket, Connection connection){
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
                    if (s[0].equals("users")) {
                        makeLoginQuery(dataOutputStream, s);
                    }else if(s[0].equals("register")){
                        makeRegisterQuery(dataOutputStream, s);
                    }

                    else{
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

        if (rs.next()) {
            dataOutputStream.writeBytes("Accepted" + "\n\r");
            dataOutputStream.flush();
        } else {
            dataOutputStream.writeBytes("Rejected" + "\n\r");
            dataOutputStream.flush();
        }
    }

    public void makeRegisterQuery(DataOutputStream dataOutputStream, String[] s) throws SQLException, IOException {
        Statement stat = connection.createStatement();
        String sql = "select * from filmdb." + "users" + " where userLogin = '" + s[1] + "' or userEmail = '" + s[5] + "'";
        System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next()) {
            dataOutputStream.writeBytes("Rejected" + "\n\r");
            dataOutputStream.flush();
        } else {
            sql = "select * from filmdb." + "users" + " where idUser = (select max(idUser) from users);";
            System.out.println(sql);
            rs = stat.executeQuery(sql);
            rs.next();
            int biggestId = rs.getInt("idUser") + 1;
            sql = "insert into filmdb." + "users" + " (idUser, userLogin, userPassword, userName, userSurname, userEmail) values (\"" + biggestId + "\",\"" + s[1]+ "\",\"" + s[2]+ "\",\"" + s[3]+ "\",\"" + s[4]+ "\",\"" + s[5] + "\");";
            System.out.println(sql);
            int us = stat.executeUpdate(sql);
            dataOutputStream.writeBytes("Accepted" + "\n\r");
            dataOutputStream.flush();
        }
    }



}
