package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.Semaphore;

public class Server {

    static final int PORT = 4999;

    public static void main(String[] args) throws IOException, SQLException {

        String host = "jdbc:mysql://localhost:3306/filmdb?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
        String userName = "root";
        String userPassword = "Minotaur21#";
        Connection connection = DriverManager.getConnection(host,userName,userPassword);

        Socket socket;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Semaphore semaphore = new Semaphore(1);
       while(true){
           socket = serverSocket.accept();
           new MyThread(socket,connection,semaphore).start();
       }

    }
}

class MyThread extends Thread{
    protected Socket socket;
    protected Connection connection;
    protected Semaphore semaphore;
    public MyThread(Socket clientSocket, Connection connection, Semaphore semaphore){
        this.socket = clientSocket;
        this.connection = connection;
        this.semaphore = semaphore;
    }
    public void run(){
        InputStream inputStream;
        BufferedReader bufferedReader;
        DataOutputStream dataOutputStream;

        try{
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            semaphore.acquire();
        }catch(IOException | InterruptedException e){
            return;
        }

        String line;
        while(true){
            try{
                line = bufferedReader.readLine();

                if((line == null) || line.equalsIgnoreCase("QUIT")){
                    socket.close();
                }else {
                    String[] s = line.split("\\s+");
                    switch (s[0]) {
                        case "users":
                            makeLoginQuery(dataOutputStream, s);
                            break;
                        case "register":
                            makeRegisterQuery(dataOutputStream, s);
                            break;
                        case "getUserData":
                            getUserDataQuery(dataOutputStream, s);
                            break;
                        case "changeUserData":
                            changeUserDataQuery(dataOutputStream, s);
                            break;
                        default:
                            dataOutputStream.writeBytes(line + "\n\r");
                            dataOutputStream.flush();
                            break;
                    }
                    socket.close();
                    semaphore.release();
                }
                return;
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
        } else {
            dataOutputStream.writeBytes("Rejected" + "\n\r");
        }
        dataOutputStream.flush();
    }

    public void makeRegisterQuery(DataOutputStream dataOutputStream, String[] s) throws SQLException, IOException {
        Statement stat = connection.createStatement();
        String sql = "select * from filmdb." + "users" + " where userLogin = '" + s[1] + "' or userEmail = '" + s[5] + "'";
        System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next()) {
            dataOutputStream.writeBytes("Rejected" + "\n\r");
        } else {
            sql = "select * from filmdb." + "users" + " where idUser = (select max(idUser) from users);";
            System.out.println(sql);
            rs = stat.executeQuery(sql);
            rs.next();
            int biggestId = rs.getInt("idUser") + 1;
            sql = "insert into filmdb." + "users" + " (idUser, userLogin, userPassword, userName, userSurname, userEmail) values (\"" + biggestId + "\",\"" + s[1]+ "\",\"" + s[2]+ "\",\"" + s[3]+ "\",\"" + s[4]+ "\",\"" + s[5] + "\");";
            System.out.println(sql);
            stat.executeUpdate(sql);
            dataOutputStream.writeBytes("Accepted" + "\n\r");
        }
        dataOutputStream.flush();
    }

    public void getUserDataQuery(DataOutputStream dataOutputStream, String[] s) throws SQLException, IOException {
        Statement stat = connection.createStatement();
        String sql = "select * from filmdb." + "users" + " where userLogin = '" + s[1] + "';";
        System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next()) {
            dataOutputStream.writeBytes(rs.getString("userLogin") + " " +rs.getString("userPassword") + " " + rs.getString("userName") + " " + rs.getString("userSurname") +" " + rs.getString("userEmail") + "\n\r");
            dataOutputStream.flush();
        }
    }

    public void changeUserDataQuery(DataOutputStream dataOutputStream, String[] s) throws SQLException, IOException {
        Statement stat = connection.createStatement();

        String sql = "select * from filmdb." + "users" + " where userEmail = '" + s[3] + "';";
        System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            dataOutputStream.writeBytes(  "Rejected"+ "\n\r");
        }else{
            sql = "update filmdb.users set userName = " + "\"" + s[1] + "\", userSurname = \"" + s[2] + "\", userEmail = \"" + s[3] + "\", userPassword = \"" + s[4] + "\"" + " where (userLogin = \"" + s[5] + "\");";
            System.out.println(sql);
            stat.executeUpdate(sql);
            dataOutputStream.writeBytes(  "Accepted"+ "\n\r");
        }
        dataOutputStream.flush();


    }


}
