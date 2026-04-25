package horsestat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class HorseStatApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private TableView<Horse> tableView;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Horse Statistics App");

        // double totalWidth = 120 + 80 + 80 + 80 + 100;

        // tableView.setPrefWidth(totalWidth);
        // tableView.setMaxWidth(totalWidth);
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("App.fxml"))));
        primaryStage.show();
    }

}
