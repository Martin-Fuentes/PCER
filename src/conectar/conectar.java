/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectar;

/**
 *
 * @author Martin Fuentes
 */
import java.sql.Connection;
import java.sql.DriverManager;
 
public class conectar {
    public static final String URL = "jdbc:mysql://iq1m0y9u5qaa.us-east-4.psdb.cloud/iptriohato?sslMode=VERIFY_IDENTITY";
    public static final String USER = "sc3guix3nbia";
    public static final String CLAVE = "pscale_pw_hM6Xobq7ggSCoXVM-jwumC4FtZ2AZ1SrsbM9AZ7h7as";
     
    public Connection getConexion(){
        Connection con = null;
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
    
}
