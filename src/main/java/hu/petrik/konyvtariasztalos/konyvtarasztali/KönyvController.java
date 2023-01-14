package hu.petrik.konyvtariasztalos.konyvtarasztali;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import java.io.IOException;

import static jdk.internal.org.jline.utils.Log.error;
import static sun.security.ssl.SSLLogger.warning;

public class KönyvController {

    @FXML
    private Button deleteBtton;
    @FXML
    private TableColumn cimCol;
    @FXML
    private TableColumn szerzoCol;
    @FXML
    private TableColumn kiadasCol;
    @FXML
    private TableColumn oldalszamCol;

    @FXML
    protected void onHelloButtonClick() {}

    @FXML
    private void initialize() {
        // getName() függvény eredményét írja ki
        cimCol.setCellValueFactory(new PropertyValueFactory<>("cim"));
        szerzoCol.setCellValueFactory(new PropertyValueFactory<>("szerzo"));
        kiadasCol.setCellValueFactory(new PropertyValueFactory<>("kiadasEve"));
        oldalszamCol.setCellValueFactory(new PropertyValueFactory<>("oldalszam"));
        Platform.runLater(() -> {
            try {
                loadPeopleFromServer();
            } catch (IOException e) {
                error("Couldn't get data from server", e.getMessage());
                Platform.exit();
            }
        });
    }
    private void loadPeopleFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Konyv[] konyvek = converter.fromJson(content, Konyv[].class);
        peopleTable.getItems().clear();
        for (Konyv konyv : konyvek) {
            peopleTable.getItems().add(konyv);
        }
    }
    @FXML
    public void deleteButton(ActionEvent actionEvent) {
        int selectedIndex = peopleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            warning("Please select a person from the list first");
            return;
        }

        Person selected = peopleTable.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(String.format("Are you sure you want to delete %s?", selected.getName()));
        Optional<ButtonType> optionalButtonType = confirmation.showAndWait();
        if (optionalButtonType.isEmpty()) {
            System.err.println("Unknown error occurred");
            return;
        }
        ButtonType clickedButton = optionalButtonType.get();
        if (clickedButton.equals(ButtonType.OK)) {
            String url = App.BASE_URL + "/" + selected.getId();
            try {
                RequestHandler.delete(url);
                loadPeopleFromServer();
            } catch (IOException e) {
                error("An error occurred while communicating with the server");
            }
        }
    }
}