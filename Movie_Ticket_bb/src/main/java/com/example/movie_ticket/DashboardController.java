package com.example.movie_ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class DashboardController implements Initializable {
    //AddMovie
    @FXML
    private AnchorPane addMovies_form;
    @FXML
    private Button addMovies_button;
    @FXML
    private Button addMovies_add;
    @FXML
    private Button addMovies_update;
    @FXML
    private Button addMovies_clear;
    @FXML
    private Button addMovies_delete;
    @FXML
    private ComboBox<String> addMovies_current;
    @FXML
    private DatePicker addMovies_date;
    @FXML
    private TableView<MoviesData> addMovies_tableView;
    @FXML
    private TableColumn<MoviesData, String> addMovies_col_current;
    @FXML
    private TableColumn<MoviesData, String> addMovies_col_date;
    @FXML
    private TableColumn<MoviesData, String> addMovies_col_duration;
    @FXML
    private TableColumn<MoviesData, String> addMovies_col_genre;
    @FXML
    private TableColumn<MoviesData, String> addMovies_col_movieTitle;

    @FXML
    private TextField addMovies_duration;
    @FXML
    private TextField addMovies_genre;
    @FXML
    private ImageView addMovies_imageView;
    @FXML
    private Button addMovies_import;
    @FXML
    private TextField addMovies_movieTitle;
    @FXML
    private TextField addMovies_search;

    //AvailableMovies
    @FXML
    private AnchorPane seat_select_Anchor;
    @FXML
    private Button availableMovies_button;

    @FXML
    private Button availableMovies_buy;

    @FXML
    private Button availableMovies_clear;

    @FXML
    private TableView<MoviesData> availableMovies_tableView;

    @FXML
    private TableColumn<MoviesData, String> availableMovies_col_genre;

    @FXML
    private TableColumn<MoviesData, String> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<MoviesData, String> availableMovies_col_showingDate;

    @FXML
    private TextField availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private TextField availableMovies_genre;

    @FXML
    private TextField availableMovies_search;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private TextField availableMovies_movieTitle;

    @FXML
    private Label availableMovies_title;

    //Customers
    @FXML
    private Button customers_button;


    @FXML
    private TableView<CustomerData> customers_tableView;

    @FXML
    private TableColumn<CustomerData, Integer> customers_col_ticketNumber;

    @FXML
    private TableColumn<CustomerData, String> customers_col_type;

    @FXML
    private TableColumn<CustomerData, Double> customers_col_total;

    @FXML
    private TableColumn<CustomerData, Double> customers_col_movieTitle;

    @FXML
    private TableColumn<CustomerData, Integer> customers_col_quan;

    @FXML
    private TableColumn<CustomerData, Date> customers_col_date;

    @FXML
    private TableColumn<CustomerData, String> customers_col_username;



    @FXML
    private Button customers_delete;

    @FXML
    private AnchorPane customers_form;


    @FXML
    private TextField customers_search;

    @FXML
    private TextField customers_ticketNumber;

    //dashboard
    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Button dashboard_button;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Label dashboard_totalSold;

    @FXML
    private Button log_out_button;

    @FXML
    private Label username;

    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    private AnchorPane image_slider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData.type = new HashSet<>();
        displayUsername();
        showAddMoviesList();
        comboBox();
        showAvailableMovies();
        showCustomer();
        displayTotalSoldTicket();
//        image = new Image("file:C:\\Users\\ADMIN\\Desktop\\idea_projects\\Movie_Ticket\\src\\main\\java\\image\\img1.jpg", 200, 256, false, true);
//        addMovies_imageView.setImage(image);
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("image_slide.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image_slider.getChildren().setAll(pane);


    }

    public void displayUsername() {
        username.setText(getData.usernamedata);

//        String isAdmin = getData.usernamedata;
//        if (!isAdmin.equals("a")) {
//            customers_button.setVisible(false);
//            addMovies_button.setVisible(false);
//        }


    }

    //    public void  switchForm(ActionEvent event){
//        if(event.getSource() == dashboard_button){
//            dashboard_form.setVisible(true);
//            addMovies_form.setVisible(false);
//            availableMovies_form.setVisible(false);
//            editScreening_form.setVisible(false);
//            customers_form.setVisible(false);
//            dashboard_button.setStyle("-fx-background-color:linear-gradient(to top left, #D3A1E7, #CF6CF7)");;
//
//        }
//    }
    //เปลี่ยนหน้าจอ
    public void switchForm(ActionEvent event) {
        Map<Button, AnchorPane> buttonForm = new HashMap<>();
        buttonForm.put(dashboard_button, dashboard_form);
        buttonForm.put(addMovies_button, addMovies_form);
        buttonForm.put(availableMovies_button, availableMovies_form);
        buttonForm.put(customers_button, customers_form);

        for (Map.Entry<Button, AnchorPane> buttonF : buttonForm.entrySet()) {
            if (event.getSource() == buttonF.getKey()) {
                buttonF.getValue().setVisible(true);
                showAddMoviesList();
                showAvailableMovies();
                showCustomer();
                displayTotalSoldTicket();
            } else {
                buttonF.getValue().setVisible(false);
            }
        }
    }

    private double x = 0;
    private double y = 0;

    public void logout() {
//        log_out_button.getScene().getWindow().hide();
        Stage stage_old = (Stage) log_out_button.getScene().getWindow();
        stage_old.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.setTitle("Movie Ticket Booking Management!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Add movies TableView
    public ObservableList<MoviesData> addMoviesList() {
        ObservableList<MoviesData> moviesList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movie";

        try (Connection conn = database.getConnection(); PreparedStatement prepare = conn.prepareStatement(sql); ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                MoviesData moviesData = new MoviesData(
                        result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));

                // เพิ่มข้อมูลของหนังลงใน ObservableList
                moviesList.add(moviesData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moviesList;
    }

    private ObservableList<MoviesData> listAddMovies;

    public void showAddMoviesList() {
        listAddMovies = addMoviesList();
        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        addMovies_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));


        addMovies_tableView.setItems(listAddMovies);
    }

    private static Integer getId(MoviesData moviesData) {
        return moviesData.getId();
    }

    public void selectAddMoviesList() {
        MoviesData moviesData = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        getData.movieId = getId(moviesData);

        addMovies_movieTitle.setText(moviesData.getMovieTitle());
        addMovies_genre.setText(moviesData.getGenre());
        addMovies_duration.setText(moviesData.getDuration());

        String getDate = String.valueOf(moviesData.getDate());
        addMovies_date.setValue(moviesData.getDate().toLocalDate());

        getData.path = moviesData.getImage();
        String uri = "file:" + moviesData.getImage();
        image = new Image(uri, 200, 256, true, true);
        addMovies_imageView.setImage(image);
    }


    public void importImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString(), 200, 256, true, true);
            addMovies_imageView.setImage(image);
            getData.path = file.getAbsolutePath();//เพื่อ get image path
        }

    }



    public void insertAddMovies() {
        String sql1 = "SELECT * FROM movie WHERE movieTitle = '" + addMovies_movieTitle.getText() + "'";
        connect = database.getConnection();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql1);

            if (result.next()) {//ตรวจสอบถ้ามีหนังเรื่องนี้อยู่แล้ว
                showAlert_ERROR("Error Message", addMovies_movieTitle.getText() + "was already exit!");
            } else {
                if (addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null) {
                    showAlert_ERROR("Error Message", "Please fill all blank fields!");
                } else {
                    String sql = "INSERT INTO movie (movieTitle,genre,duration,image,date,current) VALUES (?,?,?,?,?,?)";
                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addMovies_movieTitle.getText());
                    prepare.setString(2, addMovies_genre.getText());
                    prepare.setString(3, addMovies_duration.getText());
                    prepare.setString(4, uri);
                    prepare.setString(5, String.valueOf(addMovies_date.getValue()));
                    prepare.setString(6, addMovies_current.getSelectionModel().getSelectedItem());
                    prepare.execute();
                    showAlert_INFORMATION("Information Message", "Successfully add new movie!");
                    clearAddMoviesList();
                    showAddMoviesList();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            showAlert_ERROR("Error Message", "Something Wrong");

        }
    }

    public void updateAddMovies() {
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE movie SET movieTitle = '" + addMovies_movieTitle.getText()
                + "', genre = '" + addMovies_genre.getText() + "', duration = '"
                + addMovies_duration.getText() + "', image = '" + uri + "' , date = '"
                + addMovies_date.getValue() + " ', current = '"
                + addMovies_current.getSelectionModel().getSelectedItem()
                + "' WHERE id = '" + String.valueOf(getData.movieId) + " ' ";
        connect = database.getConnection();
        try {
            statement = connect.createStatement();
            if (addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null
                    || addMovies_current.getSelectionModel().isEmpty()) {
                showAlert_ERROR("Error Message", "Please select the movie first");

            } else {
                statement.executeUpdate(sql);
                showAlert_INFORMATION("Infomation Massage", "Successfully update " + addMovies_movieTitle.getText());
                clearAddMoviesList();
                showAddMoviesList();
            }


        } catch (Exception e) {
            e.printStackTrace();
            showAlert_ERROR("Error Message", "Please select the movie first");
        }

    }

    public void deleteAddMovies() {
        String sql = "DELETE FROM movie WHERE movieTitle = '" + addMovies_movieTitle.getText() + "'";
        connect = database.getConnection();
        try {
            statement = connect.createStatement();
            if (addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null) {
                showAlert_ERROR("Error Message", "Please select the movie first");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete " + addMovies_movieTitle.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (ButtonType.OK.equals(option.get())) {
                    statement.executeUpdate(sql);
                    showAlert_INFORMATION("Information Message", "Successfully delete!");
                    showAddMoviesList();
                    clearAddMoviesList();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchAddMovies() {
        FilteredList<MoviesData> filter = new FilteredList<>(listAddMovies, e -> true);
        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicateMoviesData.getMovieTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDate().toString().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getCurrent().toLowerCase().equals(keySearch)) {
                    return true;
                }
                return false;
            });
//            SortedList<MoviesData> sortData =filter.sorted();
            SortedList<MoviesData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());
            addMovies_tableView.setItems(sortData);
        });


    }

    public void clearAddMoviesList() {
        getData.type = new HashSet<>();
        getData.movieId = null;
        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);
        //


        availableMovies_movieTitle.setText("");
        availableMovies_date.setText("");
        availableMovies_genre.setText("");
        availableMovies_title.setText("");
        availableMovies_imageView.setImage(null);
        getData.quantity = 0;

        customers_ticketNumber.setText("");
        //Anchor seat clear
        seat_select_Anchor.getChildren().clear();


    }


    private String[] currentList = {"Showing", "End Showing"};

    public void comboBox() {
        List<String> listCurrent = new ArrayList<>();
        for (String data : currentList) {
            listCurrent.add(data);
        }
        ObservableList<String> listC = FXCollections.observableArrayList(listCurrent);
        addMovies_current.setItems(listC);
    }


    public ObservableList<MoviesData> availableMoviesList() {
        ObservableList<MoviesData> availablelist = FXCollections.observableArrayList();
        connect = database.getConnection();
        String sql = "SELECT * FROM movie WHERE current = 'Showing' ";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                MoviesData moviesData = new MoviesData(
                        result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                availablelist.add(moviesData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availablelist;
    }

    private ObservableList<MoviesData> availableMoviesL;

    public void showAvailableMovies() {
        availableMoviesL = availableMoviesList();

        availableMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showingDate.setCellValueFactory(new PropertyValueFactory<>("current"));

        availableMovies_tableView.setItems(availableMoviesL);

    }

    public void selectAvailableMovies() {
        MoviesData moviesData = availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        availableMovies_title.setText(moviesData.getMovieTitle());
        availableMovies_genre.setText(moviesData.getGenre());
        availableMovies_date.setText(moviesData.getDate().toLocalDate().toString());
        getData.path = moviesData.getImage();
        getData.title = moviesData.getMovieTitle();
        String uri = "file:" + getData.path;
        image = new Image(uri, 200, 256, true, true);
        availableMovies_imageView.setImage(image);
        availableMovies_movieTitle.setText(getData.title);


        String sql1 = "SELECT type FROM customer_history WHERE movieTitle = '" + availableMovies_movieTitle.getText() + "'";
        connect = database.getConnection();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql1);
            Set<String> types = new HashSet<>();

            while (result.next()) {//ตรวจสอบถ้ามีหนังเรื่องนี้อยู่แล้ว
                String[] type = result.getString("type").split(","); // ดึงค่า type จาก ResultSet
                for (String t : type) {
                    types.add(t.trim()); // เพิ่มค่า type ลงใน Set โดยลบช่องว่างข้างหน้าและหลังที่ได้
                }
            }
            getData.type = types;
            AnchorPane seat_pane = null;
            seat_pane = FXMLLoader.load(getClass().getResource("seat-view.fxml"));
            seat_select_Anchor.getChildren().setAll(seat_pane);
            System.out.println(types);
//            System.out.println(getData.type);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert_ERROR("Error Message", "Something Wrong");

        }
    }

    public void buy() {
        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        connect = database.getConnection();
        try {
//            check user selectmovie
            if (availableMovies_imageView.getImage() == null || availableMovies_movieTitle.getText().isEmpty()) {
                showAlert_ERROR("Error Message", "Please select the movie first");
            } else if (getData.quantity == 0) {
                showAlert_ERROR("Error Message", "Please select the quantity of ticket");
            } else {
                showAlert_INFORMATION("Information Message", "Successfully purchase!");
                String sql2 = "INSERT INTO customer_history (type,total,movieTitle,quantity,date,username) VALUES(?,?,?,?,?,?)";
                prepare = connect.prepareStatement(sql2);
                prepare.setString(1, getData.seats_book);
                prepare.setString(2, String.valueOf(getData.total_price));
                prepare.setString(3, availableMovies_movieTitle.getText());
                prepare.setString(4, String.valueOf(getData.quantity));
                prepare.setString(5, String.valueOf(setDate));
                prepare.setString(6, String.valueOf(getData.usernamedata));
                prepare.execute();
                clearAddMoviesList();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void searchAvailableMovies() {
        FilteredList<MoviesData> filter = new FilteredList<>(availableMoviesL, e -> true);
        availableMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicateMoviesData.getMovieTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getCurrent().toLowerCase().contains(keySearch)) {
                    return true;
                }
                return false;
            });
//            SortedList<MoviesData> sortData =filter.sorted();
            SortedList<MoviesData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(availableMovies_tableView.comparatorProperty());
            availableMovies_tableView.setItems(sortData);
        });


    }

    public ObservableList<CustomerData> customerList() {
        ObservableList<CustomerData> customerlist = FXCollections.observableArrayList();
        connect = database.getConnection();
        String sql = "SELECT * FROM customer_history";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                CustomerData customerdata = new CustomerData(
                        result.getInt("customer_id"),
                        result.getString("type"),
                        result.getDouble("total"),
                        result.getString("movieTitle"),
                        result.getInt("quantity"),
                        result.getDate("date"),
                        result.getString("username"));

                customerlist.add(customerdata);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerlist;
    }

    private ObservableList<CustomerData> customerL;

    public void showCustomer() {
        customerL = customerList();

        customers_col_ticketNumber.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        customers_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        customers_col_quan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));

        customers_tableView.setItems(customerL);

    }

    public void selectCustomer() {
        CustomerData customerData = customers_tableView.getSelectionModel().getSelectedItem();
        int num = customers_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        customers_ticketNumber.setText(customerData.getCustomer_id().toString());
    }

    public void deleteHistory() {
        String sql = "DELETE FROM customer_history WHERE customer_id = '" + customers_ticketNumber.getText() + "'";
        connect = database.getConnection();
        try {
            statement = connect.createStatement();
            if (customers_ticketNumber.getText().isEmpty()) {
                showAlert_ERROR("Error Message", "Please select the history first");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (ButtonType.OK.equals(option.get())) {
                    statement.executeUpdate(sql);
                    showAlert_INFORMATION("Information Message", "Successfully delete!");
                    showCustomer();
                    clearAddMoviesList();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchHistory() {
        FilteredList<CustomerData> filter = new FilteredList<>(customerL, e -> true);
        customers_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCustomerData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicateCustomerData.getCustomer_id().toString().contains(keySearch)) {
                    return true;
                } else if (predicateCustomerData.getUsername().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateCustomerData.getMovieTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateCustomerData.getType().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateCustomerData.getQuantity().toString().contains(keySearch)) {
                    return true;
                }else if (predicateCustomerData.getDate().toString().contains(keySearch)) {
                    return true;
                }
                return false;
            });
//            SortedList<MoviesData> sortData =filter.sorted();
            SortedList<CustomerData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(customers_tableView.comparatorProperty());
            customers_tableView.setItems(sortData);
        });


    }

    private int soldTicket;

    public void countTicket() {
        String sql = "SELECT count(customer_id) FROM customer_history";
        connect = database.getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                soldTicket = result.getInt("count(customer_id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double totalIncome;

    public void totalIncomeToday() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(total) FROM customer_history WHERE date = '" + String.valueOf(sqlDate) + "'";
        connect = database.getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                totalIncome = result.getDouble("SUM(total)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int totalMovies;

    public void totalAvailableMovies() {
        String sql = "SELECT COUNT(movieTitle) FROM movie WHERE current = 'Showing'";
        connect = database.getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                 totalMovies = result.getInt("COUNT(movieTitle)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalSoldTicket() {
        countTicket();
        totalIncomeToday();
        totalAvailableMovies();
        dashboard_totalSold.setText(String.valueOf(soldTicket));
        dashboard_totalIncome.setText(String.valueOf(totalIncome));
        dashboard_availableMovies.setText(String.valueOf(totalMovies));
    }

    public void showAlert_INFORMATION(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showAlert_ERROR(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}

