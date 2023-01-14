package hu.petrik.konyvtariasztalos.konyvtarasztali;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    public static final String BASE_URL = "http://localhost/phpmyadmin/index.php?route=/database/structure&db=konyvdb";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 240);
        stage.setTitle("Konyvek!");
        stage.setScene(scene);
        stage.show();

    }


        public static void main(String[] args) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/konyvdb","root","");

                Statement stmt=con.createStatement();

                ResultSet rs=stmt.executeQuery("select * from emp");

                while(rs.next())
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

                con.close();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        launch();
    }
}