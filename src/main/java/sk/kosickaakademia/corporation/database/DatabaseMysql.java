package sk.kosickaakademia.corporation.database;

import sk.kosickaakademia.corporation.entity.Overview;
import sk.kosickaakademia.corporation.entity.User;
import sk.kosickaakademia.corporation.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseMysql {
    /* private String url = "jdbc:mysql://itsovy.sk:3306/company";
    private String username = "mysqluser";
    private String password = "Kosice2021!"; */

    Log log = new Log();
    private final String INSERTQUERY = "INSERT INTO user (fname, lname, age, gender) " +
            "VALUES ( ?, ?, ?, ?) ";

public Connection getConnection() {
    try{

        Properties props = new Properties ();
        InputStream loader = getClass().getClassLoader().getResourceAsStream("database.properties");
        props.load(loader);
        String url = props.getProperty("url");
        String username=props.getProperty("username");
        String password=props.getProperty("password");
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        log.print("Connection : succes");
        return con;

    } catch (SQLException | IOException throwables) {
        throwables.printStackTrace();
    }
    return null;
}
public void closeConnection (Connection con){
    if(con!=null) {
        try {
            con.close();
            log.print("Con. closed");

        } catch (SQLException e) {
            log.error(e.toString());
        }
    }
}
 public boolean insertNewUser(User user){
    Connection con = getConnection();
    if(con!=null){
        try{
            PreparedStatement ps = con.prepareStatement(INSERTQUERY);
            ps.setString(1,user.getFname());
            ps.setString(2,user.getLname());
            ps.setInt(3,user.getAge());
            ps.setInt(4,user.getGender().getValue());
            int result = ps.executeUpdate();
            closeConnection(con);
            log.print("New user has been added");
            return result == 1;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    return false;

 }
    public List<User> getFemales(){
        log.info(" Executing : getFemales() ");
        String sql = " SELECT * FROM user WHERE gender = 1 ";
        try (Connection con = getConnection()) {
            if(con!= null) {
                PreparedStatement ps = con.prepareStatement(sql);
                return executeSelect(ps);
            }
        }catch(Exception e){
            log.error(e.toString());
        }
        return null;
    }


    public List<User> getMales() {
        String sql = " SELECT * FROM user WHERE gender = 0 ";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            return executeSelect(ps);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;
    }
    public List<User> getUsersByAge(int to, int from ){
        if(to < from)
        return null;
        try {
            String sql = " SELECT * FROM user WHERE age >= ? AND age <= ? ";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1,from);
            ps.setInt(2, to);
            return executeSelect(ps);
        }catch(Exception e){
            log.error(e.toString());
        }
        return null;

        }
     private List<User> executeSelect(PreparedStatement ps) throws SQLException {
         ResultSet rs =  ps.executeQuery();
         List<User> list = new ArrayList<>();
         int count = 0 ;
         while(rs.next()){
             count ++;
             String fname = rs.getString(" fname ");
             String lname = rs.getString(" lname ");
             int age = rs.getInt(" age ");
             int id = rs.getInt(" id ");
             int gender = rs.getInt(" gender ");
             User u = new User(id,fname,lname,age,gender);
             list.add(u);
         }
         log.info("Number of records: " + count);
          return list;
     }

    public List<User> getUsersById(int Id) {


        Connection con = getConnection();

        if (con!= null) {
             String sql = "SELECT * FROM user WHERE id = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,Id);
                List<User> list = executeSelect(ps);
                User user = null;

                if (!list.isEmpty())
                    return null;

                else list.get(0);

                closeConnection(con);

            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement pst = getConnection().prepareStatement(sql);
            return executeSelect(pst);

        }catch(Exception e){
            log.error(e.toString());
        }
        return null;

    }
    public boolean changeAge(int id, int newAge){
        if (id > 0 && newAge > 0){
            Connection con = getConnection();
            String sql = "UPDATE user SET age = ? WHERE id = ?";
            try {
                PreparedStatement ps = getConnection().prepareStatement(sql);
                ps.setInt(1,id);
                ps.setInt(2,newAge);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return false;
    }
    public List<User> getUser(String pattern){

        return null;}

}
