package horsestat;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;

public class HorseStatController {

    @FXML
    private TableView<Horse> tableView;

    @FXML
    private TextField name, speed, jump, health;// , color, pattern;

    @FXML
    private Button addButton, sortButton, deleteButton;

    @FXML
    private TableColumn<Horse, String> nameColumn;// , colorColumn, patternColumn;

    @FXML
    private TableColumn<Horse, Float> speedColumn, jumpColumn, healthColumn, averageColumn;

    protected HorseListManager HLM;
    private ObservableList<Horse> horses;

    @FXML
    private void addClick() throws IOException {
        try {
            HLM.addHorse(new Horse(name.getText(), Double.parseDouble(speed.getText()),
                    Double.parseDouble(jump.getText()), Double.parseDouble(health.getText())));// , color.getText(),
                                                                                               // pattern.getText()));
        } catch (NumberFormatException e) {
            System.out.println("Failed adding horse");
            e.printStackTrace();
        }
        update();
    }

    @FXML
    private void sortClick() throws IOException {
        HLM.sortList();
        update();
    }

    @FXML
    private void deleteClick() throws IOException {
        int index = tableView.getSelectionModel().getSelectedIndex();

        if (index == -1) {
            System.out.println("No selection");
            return;
        }

        HLM.removeHorse(index);

        update();
    }

    @FXML
    public void initialize() throws IOException {

        HLM = new HorseListManager("./horses.txt");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("RealSpeed"));
        jumpColumn.setCellValueFactory(new PropertyValueFactory<>("RealJump"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<>("RealHealth"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("RealAverage"));
        // colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        // patternColumn.setCellValueFactory(new PropertyValueFactory<>("pattern"));
        update();
    }

    public void update() {
        horses = FXCollections.observableArrayList(HLM.getHorses());

        tableView.setItems(horses);
    }
}
