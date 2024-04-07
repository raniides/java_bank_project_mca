
package bankmanagement;

/**
 *
 * @author raani
 */
import java.sql.*;  

public class Conn {
    Connection c;
    Statement s;
    Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            c = DriverManager.getConnection("jdbc:mysql://Adershs-MacBook-Air.local:3306/bankmanagementsystem", "root", "Ranisona@13");
            
            s =c.createStatement();  
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}