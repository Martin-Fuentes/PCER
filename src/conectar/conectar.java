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
    public static final String CLAVE = "";

    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://sql5.freesqldatabase.com:3306",
                    "sql5504956",
                    "5Af7kjiWv2");
            System.out.print("Llegue aqui");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }

}
