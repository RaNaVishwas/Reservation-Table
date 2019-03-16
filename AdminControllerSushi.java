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

public class AdminControllerSushi {

    private Connection connection = ConnectionFactory.getMYSQLConnection();
    private OperationSushi operation = new OperationSushi(connection);

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


        //rating button
        ratingButton.setGraphic(ratingIMV);
        ratingButton.setStyle("-fx-background-color: transparent");


        //archive button
        archiveButton.setGraphic(archiveIMV);
        archiveButton.setStyle("-fx-background-color: transparent");

    }
 private void getAllImageViewsForButtons() {
        restaurantIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/sushipng.png")));
        reservationsIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_reservations.png")));
        customersIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_customers.png")));
        employeesIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_employees.png")));
        ratingIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_rating.png")));
        archiveIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_archive.png")));


        reservationsSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_reservations_selected.png")));
        customersSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_customers_selected.png")));
        employeesSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_employees_selected.png")));
        ratingSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_rating_selected.png")));
        archiveSelectedIMV = new ImageView(new Image(getClass().getResourceAsStream("Graphics/a_archive_selected.png")));


    }
@FXML
    private void goHomeScreen(ActionEvent event) {

        //get reference to WelcomeScreen stage
        Stage stage = (Stage) mainGridPane.getScene().getWindow();

        //load up WelcomeScene FXML document
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("WelcomeSushiScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1024, 720);
        stage.setTitle("Restaurant Reservation System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reservationsButtonClicked(ActionEvent event) {
        configureButtons();
        reservationsButton.setGraphic(reservationsSelectedIMV);

        //Clear the content of contentPane
        contentPane.getChildren().clear();

        //Set title
        titleLabel.setText("Reservations");

        // Make box for weekly availability and reservations
        VBox box = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPrefWidth(900);

        // box for availability
        VBox abox = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPrefWidth(900);

        // box for reservations
        VBox resbox = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPrefWidth(900);

        //set up for weekly table availability
        Text aHeader = new Text("Tables still available");
        aHeader.setFont(new Font("System", 24));

        TableView aTable = new TableView();
        TableColumn aDateCol = new TableColumn("Date");
        aDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn tablesCol = new TableColumn("Tables Available");
        tablesCol.setCellValueFactory(new PropertyValueFactory<>("tablesAvailable"));

        //add table and title to availability box
        abox.getChildren().addAll(aHeader, aTable);
        abox.setSpacing(5);

        //set width for all cols
        aDateCol.setMinWidth(100);
        tablesCol.setMinWidth(100);

         aTable.getColumns().addAll(aDateCol, tablesCol);
        //Populate data to the table view
        operation.callDates();
        ObservableList<Availability> adata = FXCollections.observableArrayList(operation.getWeeklyAvailability());
        aTable.setItems(adata);
        aTable.setMaxHeight(235);
        aTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //set up for reservations
        Text resHeader = new Text("All Reservations");
        resHeader.setFont(new Font("System", 24));

        TableView resTable = new TableView();
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn tidCol = new TableColumn("tID");
        tidCol.setCellValueFactory(new PropertyValueFactory<>("tID"));
        TableColumn partySizeCol = new TableColumn("Party Size");
        partySizeCol.setCellValueFactory(new PropertyValueFactory<>("partySize"));
        TableColumn seatsCol = new TableColumn("Seats");
        seatsCol.setCellValueFactory(new PropertyValueFactory<>("seats"));
        TableColumn dateCol = new TableColumn("Reservation Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        //set width for all cols
        firstNameCol.setMinWidth(100);
        lastNameCol.setMinWidth(100);
        tidCol.setMinWidth(100);
        partySizeCol.setMinWidth(100);
        seatsCol.setMinWidth(100);
        dateCol.setMinWidth(300);

        //add table and title to availability box
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(resTable);
        scrollPane.setFitToWidth(true);
        resbox.getChildren().addAll(resHeader, scrollPane);
        resbox.setSpacing(5);

        resTable.getColumns().addAll(firstNameCol, lastNameCol, tidCol, partySizeCol, seatsCol, dateCol);
        //Populate data to the table view
        ObservableList<ReservationInfo> resdata = FXCollections.observableArrayList(operation.getAllReservations());
        resTable.setItems(resdata);
        resTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);