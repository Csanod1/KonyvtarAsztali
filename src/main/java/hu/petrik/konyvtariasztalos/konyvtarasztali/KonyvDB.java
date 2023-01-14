package hu.petrik.konyvtariasztalos.konyvtarasztali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KonyvDB {
    Connection conn;

    public static String DB_DRIVER = "mysql";
    public static String DB_USER = "root";
    public static String DB_PASS = "";


    public KonyvDB() throws SQLException {
        String url = String.format("");
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);


    }
}
