module hu.petrik.konyvtariasztalos.konyvtarasztali {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.konyvtariasztalos.konyvtarasztali to javafx.fxml;
    exports hu.petrik.konyvtariasztalos.konyvtarasztali;
}