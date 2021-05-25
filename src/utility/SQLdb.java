package utility;

import delivery.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLdb {

    private static Connection dbConn;
    private static SQLdb db = null;

    private SQLdb() {
        String url = "jdbc:mysql://localhost:3306/DeliverySystem";
        String user = "root";
        String pass = "";
        try {
            dbConn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static SQLdb getDatabase() {
        if (db == null) {
            db = new SQLdb();
        }
        return db;
    }

    public void addUser(User u) throws SQLException {

        String query = "insert into Users VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prepstat = dbConn.prepareStatement(query);

        prepstat.setString(1, u.getName());
        prepstat.setInt(2, u.getAge());
        prepstat.setString(3, u.getAddress());
        prepstat.setString(4, u.getUsername());
        prepstat.setString(5, u.getPassword());

        prepstat.executeUpdate();
    }

    public ArrayList<User> readUsers() throws SQLException {
        String query = "select * from Users";
        PreparedStatement prepstat = dbConn.prepareStatement(query);

        ResultSet res = prepstat.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (res.next()) {
            String name = res.getString("Name");
            int age = res.getInt("Age");
            String address = res.getString("Address");
            String username = res.getString("Username");
            String pass = res.getString("Password");
            userList.add(new User(name, age, address, username, pass));
        }
        return userList;
    }

    public void updateUserPass(User u, String newPass) throws SQLException {
        String query = "update Users set Password=? where Username=?";
        PreparedStatement prepstat = dbConn.prepareStatement(query);
        prepstat.setString(1, newPass);
        prepstat.setString(2, u.getUsername());
        prepstat.executeUpdate();
    }

    public void removeUser(User u) throws SQLException {
        String query = "delete from Users where Username=?";
        PreparedStatement prepstat = dbConn.prepareStatement(query);
        prepstat.setString(1, u.getUsername());
        prepstat.executeUpdate();
    }
}
