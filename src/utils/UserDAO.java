/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.User;
import java.sql.PreparedStatement;
import static utils.BaseDao.DB_HOST;
import static utils.BaseDao.DB_NAME;
import static utils.BaseDao.DB_PASS;
import static utils.BaseDao.DB_USER;
/**
 *
 * @author Acer
 */
public class UserDAO {
    private String url;
    private String uname;
    private String pass;
    private String query;
    private Connection con;
    private Statement stmt;
    
    public UserDAO(){
        url="jdbc:mysql://localhost/dbdata";
        uname="root";
        pass="";
        setConnectionAndStatement();
        
    }
    private void setConnectionAndStatement(){
        try{
            con=DriverManager.getConnection(url,uname,pass);
            stmt=con.createStatement();
            
        }
        catch(SQLException ex){
            System.err.print(ex.getMessage());
            System.exit(1);
        }
    }
    public void save(User user){
        try{
            query = "INSERT INTO user VALUES('%s','%s','%d','%s')";
            query = String.format(query,user.getNama(),user.getPassword(),user.getUsia(),user.getGender());
            stmt.executeUpdate(query);
            System.out.println("Berhasil menambahkan data!");
        }
        catch(SQLException ex){
            System.err.print("Error inserting data:"+ex.getMessage());
            System.exit(1);
        }
    }
}
