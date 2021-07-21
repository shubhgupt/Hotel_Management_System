
package hotel_management_system;

import java.sql.*;

public class Conn {
    public Connection con;
    public Statement st;
    
    public Conn(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hotelManagementSystem";
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public Statement getSt() {
        return st;
    }
    
}
