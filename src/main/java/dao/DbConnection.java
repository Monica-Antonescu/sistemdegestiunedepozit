package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public static Connection con;

    //conexiune baza de date mysql
    public void connect() {
        String db = "warehousemanagement";
        String userDb = "root";
        String passDb = "parola123";
        try {
            //folosirea clasei class.forName apeleaza incarcatorul de clase al masinii virtuale java
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + db +
                            "?user=" + userDb + "&password=" + passDb + "&autoReconnect=true&useSSL=false");
        } catch (Exception e) {
            System.out.println("Eroare conectare baza de date mysql");
            System.exit(0);
        }
    }
}
