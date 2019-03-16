import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import Model.Availability;
import Model.Customer;
import Model.Employee;
import Model.Person;
import Model.Rating;
import Model.ReservationInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminControllerVapino {

    private Connection connection = ConnectionFactory.getMYSQLConnection();
    private OperationVapino operation = new OperationVapino(connection);

    @FXML
    private Label titleLabel;
    @FXML
    private GridPane mainGridPane;
    @FXML
    private Button restaurantButton;
    @FXML
    private Button reservationsButton;

    @FXML
    private Button customersButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button ratingButton;
    @FXML
    private Button archiveButton;

    @FXML
    private GridPane contentPane;

    // all the images for the buttons
    private ImageView restaurantIMV;
    private ImageView reservationsIMV;
    private ImageView customersIMV;
    private ImageView employeesIMV;
    private ImageView ratingIMV;
    private ImageView archiveIMV;


    // all the images for selected buttons
    private ImageView reservationsSelectedIMV;
    private ImageView customersSelectedIMV;
    private ImageView employeesSelectedIMV;
    private ImageView ratingSelectedIMV;
    private ImageView archiveSelectedIMV;

    @FXML
    void initialize() {
        getAllImageViewsForButtons();
        configureWidthHeightForImageViews();
        configureButtons();
        contentPane.setAlignment(Pos.CENTER);

//show all customers and all employees
        titleLabel.setText("All Customers and All Employees");
        TableView table = new TableView();

        TableColumn fnCol = new TableColumn("First Name");
        fnCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn lnCol = new TableColumn("Last Name");
        lnCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn emailCol = new TableColumn("email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(fnCol, lnCol, emailCol);

        ObservableList<Person> data = FXCollections.observableArrayList(operation.getAllCustomersAndEmployees());
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        contentPane.getChildren().add(table);
    }

    private void configureWidthHeightForImageViews() {
        //Restaurant
        restaurantIMV.setFitWidth(51);
        restaurantIMV.setFitHeight(41);

        //reservations
        reservationsIMV.setFitWidth(63);
        reservationsIMV.setFitHeight(84);
        reservationsSelectedIMV.setFitWidth(63);
        reservationsSelectedIMV.setFitHeight(84);

        //customers
        customersIMV.setFitWidth(53);
        customersIMV.setFitHeight(65);
        customersSelectedIMV.setFitWidth(53);
        customersSelectedIMV.setFitHeight(65);

        // employees
        employeesIMV.setFitWidth(53);
        employeesIMV.setFitHeight(53);
        employeesSelectedIMV.setFitWidth(53);
        employeesSelectedIMV.setFitHeight(53);

        // rating
        ratingIMV.setFitWidth(32);
        ratingIMV.setFitHeight(69);
        ratingSelectedIMV.setFitWidth(32);
        ratingSelectedIMV.setFitHeight(69);

        // archive
        archiveIMV.setFitWidth(37);
        archiveIMV.setFitHeight(51);
        archiveSelectedIMV.setFitWidth(37);
        archiveSelectedIMV.setFitHeight(51);

    }

    private void configureButtons() {
        //Restaurant button
        restaurantButton.setGraphic(restaurantIMV);
        restaurantButton.setStyle("-fx-background-color: transparent");

        //reservations button
        reservationsButton.setGraphic(reservationsIMV);
        reservationsButton.setStyle("-fx-background-color: transparent");

        //customers button
        customersButton.setGraphic(customersIMV);
        customersButton.setStyle("-fx-background-color: transparent");

        //employees button
        employeesButton.setGraphic(employeesIMV);
        employeesButton.setStyle("-fx-background-color: transparent");