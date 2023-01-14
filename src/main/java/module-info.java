module hu.petrik.konyvtariasztalos.konyvtarasztali {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires jdk.internal.le;


    opens hu.petrik.konyvtariasztalos.konyvtarasztali to javafx.fxml;
    exports hu.petrik.konyvtariasztalos.konyvtarasztali;
}