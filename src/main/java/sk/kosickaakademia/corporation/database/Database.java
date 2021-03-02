package sk.kosickaakademia.corporation.database;

import sk.kosickaakademia.corporation.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String url = "jdbc:mysql://itsovy.sk:3306/company";
    private String username = "mysqluser";
    private String password = "Kosice2021!";

    Log log = new Log();
public Connection getConnection() {
    try{
        //Properties props

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        log.print("Connection : succes");
        return con;

    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }
    return null;
}
public void closeConnection (Connection con){
    if(con!=null) {
        try {
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
}
