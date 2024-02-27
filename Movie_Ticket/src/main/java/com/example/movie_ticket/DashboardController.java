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

    @FXML
    private Button addMovies_add;

    @FXML
    private Button addMovies_button;

    @FXML
    private Button addMovies_clear;
    @FXML
    private TableView<MoviesData> addMovies_tableView;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_date;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<MoviesData, String> addMovies_col_movieTitle;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private Button addMovies_delete;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

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


    @FXML
    private Button addMovies_update;

    @FXML
    private Button availableMovies_button;

    @FXML
    private Button availableMovies_buy;

    @FXML
    private Button availableMovies_clear;
    @FXML
    private Spinner<Integer> availableMovies_specialClass_quan;

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
    private ImageView availableMovies_imageView;

    @FXML
    private TextField availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<Integer> availableMovies_normalClass_quan;

    @FXML
    private Button availableMovies_receipt;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_totalPrice;

    @FXML
    private Button customers_button;

    @FXML
    private Button customers_clear;

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
    private TextField customers_date;

    @FXML
    private Button customers_delete;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private TextField customers_genre;

    @FXML
    private TextField customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private TextField customers_ticketNumber;

    @FXML
    private TextField customers_time;

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
    private Button editScreening_button;

    @FXML
    private TableView<MoviesData> editScreening_tableView;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<MoviesData, String> editScreening_col_movieTitle;

    @FXML
    private ComboBox<String> editScreening_current;

    @FXML
    private Button editScreening_delete;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_update;

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
        displayUsername();
        showAddMoviesList();
        showEditScreening();
        comboBox();
        showAvailableMovies();
        showSpinnerValue();
        showCustomer();
        displayTotalSoldTicket();
        image = new Image("file:C:\\Users\\ADMIN\\Desktop\\idea_projects\\Movie_Ticket\\src\\main\\java\\image\\img1.jpg", 200, 256, false, true);
        addMovies_imageView.setImage(image);

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("image_slide.fxml"));
//        try {
//            image_slider = loader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("image_slide.fxml"));
//        ImageSlide controller = new ImageSlide(); // แทนคลาส Controller ของคุณที่คุณสร้างขึ้น
//        loader.setController(controller);
//        loader.setControllerFactory(param -> controller);
//
//        try {
//            AnchorPane imageSlider = loader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //AnchorPane load another fxml
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

        String isAdmin = getData.usernamedata;
        if(!isAdmin.equals("a")){
            customers_button.setVisible(false);
            addMovies_button.setVisible(false);
            editScreening_button.setVisible(false);
        }



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
        buttonForm.put(editScreening_button, editScreening_form);
        buttonForm.put(customers_button, customers_form);

        for (Map.Entry<Button, AnchorPane> buttonF : buttonForm.entrySet()) {
            if (event.getSource() == buttonF.getKey()) {
                buttonF.getValue().setVisible(true);
                showAddMoviesList();
                showEditScreening();
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
        log_out_button.getScene().getWindow().hide();
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

        try (Connection conn = database.getConnection();
             PreparedStatement prepare = conn.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                MoviesData moviesData = new MoviesData(result.getInt("id"),
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

        addMovies_tableView.setItems(listAddMovies);
    }

    public void selectAddMoviesList() {
        MoviesData moviesData = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        getData.movieId = moviesData.getId();

        addMovies_movieTitle.setText(moviesData.getMovieTitle());
        addMovies_genre.setText(moviesData.getGenre());
        addMovies_duration.setText(moviesData.getDuration());

        String getDate = String.valueOf(moviesData.getDate());
        addMovies_date.setValue(moviesData.getDate().toLocalDate());

        getData.path = moviesData.getImage();
        String uri = "file:" + moviesData.getImage();
        image = new Image(uri, 200, 256, false, true);
        addMovies_imageView.setImage(image);
    }

    public void importImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString(), 200, 256, false, true);
            addMovies_imageView.setImage(image);
            getData.path = file.getAbsolutePath();//เพื่อ get image path
        }

    }


    public void movieId() {
        String sql = "SELECT count(id) FROM movie";
        connect = database.getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                getData.movieId = result.getInt("count(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAddMovies() {
        String sql1 = "SELECT * FROM movie WHERE movieTitle = '"
                + addMovies_movieTitle.getText() + "'";
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
                    prepare.setString(6, "Showing");
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
                + "', genre = '" + addMovies_genre.getText()
                + "', duration = '" + addMovies_duration.getText()
                + "', image = '" + uri
                + "' , date = '" + addMovies_date.getValue()
                + "' WHERE id = '" + String.valueOf(getData.movieId) + " ' ";
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
                statement.executeUpdate(sql);
                showAlert_INFORMATION("Infomation Massage", "Successfully update " + addMovies_movieTitle.getText());
                clearAddMoviesList();
                showAddMoviesList();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteAddMovies() {
        String sql = "DELETE FROM movie WHERE movieTitle = '"
                + addMovies_movieTitle.getText() + "'";
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
        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);
        //
        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
//        editScreening_current.setSelectionModel(null);
        //
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);//minimum, maximum
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        availableMovies_specialClass_quan.setValueFactory(spinner1);
        availableMovies_normalClass_quan.setValueFactory(spinner2);

        availableMovies_specialClass_price.setText("0.0");
        availableMovies_normalClass_price.setText("0.0");
        availableMovies_totalPrice.setText("0.0");
        availableMovies_movieTitle.setText("");
        availableMovies_date.setText("");
        availableMovies_genre.setText("");
        availableMovies_title.setText("");
        availableMovies_imageView.setImage(null);


    }

    public ObservableList<MoviesData> editScreeningList() {
        ObservableList<MoviesData> editList = FXCollections.observableArrayList();
        connect = database.getConnection();
        String sql = "SELECT * FROM movie ";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                MoviesData moviesData = new MoviesData(result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                editList.add(moviesData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editList;
    }

    private ObservableList<MoviesData> editScreeningL;

    public void showEditScreening() {
        editScreeningL = editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        editScreening_tableView.setItems(editScreeningL);

    }

    private String[] currentList = {"Showing", "End Showing"};

    public void comboBox() {
        List<String> listCurrent = new ArrayList<>();
        for (String data : currentList) {
            listCurrent.add(data);
        }
        ObservableList<String> listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    public void updateEditScreening() {
        String sql = "UPDATE movie SET current = '"
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "' WHERE movieTitle = '" + editScreening_title.getText() + "'";
        connect = database.getConnection();
        try {
            statement = connect.createStatement();
            if (editScreening_title.getText().isEmpty() || editScreening_imageView.getImage() == null || editScreening_current.getSelectionModel().isEmpty()) {
                showAlert_ERROR("Error Message", "Please select the movie first");
            } else {
                statement.executeUpdate(sql);
                showAlert_INFORMATION("Information Message", "Successfully Update!");
                showEditScreening();
                clearAddMoviesList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchEditScreening() {
        FilteredList<MoviesData> filter = new FilteredList<>(editScreeningL, e -> true);
        editScreening_search.textProperty().addListener((observable, oldValue, newValue) -> {
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
            SortedList<MoviesData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(editScreening_tableView.comparatorProperty());
            editScreening_tableView.setItems(sortData);
        });


    }

    public void selectEditScreening() {
        MoviesData moviesData = editScreening_tableView.getSelectionModel().getSelectedItem();
        int num = editScreening_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        editScreening_title.setText(moviesData.getMovieTitle());
        getData.path = moviesData.getImage();
        String uri = "file:" + moviesData.getImage();
        image = new Image(uri, 200, 256, false, true);
        editScreening_imageView.setImage(image);
    }

    public ObservableList<MoviesData> availableMoviesList() {
        ObservableList<MoviesData> availablelist = FXCollections.observableArrayList();
        connect = database.getConnection();
        String sql = "SELECT * FROM movie WHERE current = 'Showing' ";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                MoviesData moviesData = new MoviesData(result.getInt("id"),
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

    public void selectvailableMovies() {
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
        image = new Image(uri, 200, 256, false, true);
        availableMovies_imageView.setImage(image);
        availableMovies_movieTitle.setText(getData.title);
    }

    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;

    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int quan1 = 0;
    private int quan2 = 0;

    private int quan;

    public void showSpinnerValue() {

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);//minimum, maximum
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        availableMovies_specialClass_quan.setValueFactory(spinner1);
        availableMovies_normalClass_quan.setValueFactory(spinner2);
    }

    public void getSpinnerValue() {
        quan1 = availableMovies_specialClass_quan.getValue();
        quan2 = availableMovies_normalClass_quan.getValue();
        price1 = (quan1 * 400);
        price2 = (quan2 * 200);
        total = (price1 + price2);

        availableMovies_specialClass_price.setText(String.valueOf(price1));
        availableMovies_normalClass_price.setText(String.valueOf(price2));
        availableMovies_totalPrice.setText(String.valueOf(total));
    }

    public void buy() {
        String type = "";
        if (price1 > 0 && price2 == 0) {
            type = "Special Class";
        } else if (price2 > 0 && price1 == 1) {
            type = "Normal Class";
        } else if (price1 > 0 && price2 > 0) {
            type = "Special & Normal Class";
        }
        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        connect = database.getConnection();
        try {
            quan = quan1 + quan2;
//            check user selectmovie
            if (availableMovies_imageView.getImage() == null || availableMovies_movieTitle.getText().isEmpty()) {
                showAlert_ERROR("Error Message", "Please select the movie first");
            } else if (price1 == 0 && price2 == 0) {
                showAlert_ERROR("Error Message", "Please select the quantity of ticket");
            } else {
                showAlert_INFORMATION("Information Message", "Successfully purchase!");
                String sql1 = "SELECT * FROM customer";

                String sql2 = "INSERT INTO customer_info (type,total,movieTitle,quantity,date) VALUES(?,?,?,?,?)";
                prepare = connect.prepareStatement(sql2);
                prepare.setString(1, type);
                prepare.setString(2, String.valueOf(total));
                prepare.setString(3, availableMovies_movieTitle.getText());
                prepare.setString(4, String.valueOf(quan));
                prepare.setString(5, String.valueOf(setDate));
                prepare.execute();
                clearAddMoviesList();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<CustomerData> customerList() {
        ObservableList<CustomerData> customerlist = FXCollections.observableArrayList();
        connect = database.getConnection();
        String sql = "SELECT * FROM customer_info";
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
                        result.getDate("date"));

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

        customers_tableView.setItems(customerL);

    }

    private int soldTicket;

    public void countTicket() {
        String sql = "SELECT count(customer_id) FROM customer_info";
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
        String sql = "SELECT SUM(total) FROM customer_info WHERE date = '"
                + String.valueOf(sqlDate) + "'";
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

//    public void showAlert_CONFIRMATION(String title1, String content1, String title2, String content2) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(title1);
//        alert.setHeaderText(null);
//        alert.setContentText(content1);
//        Optional<ButtonType> option = alert.showAndWait();
//        if (ButtonType.OK.equals(option.get())) {
//            alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle(title2);
//            alert.setHeaderText(null);
//            alert.setContentText(content2);
//            alert.showAndWait();
//        } else {
//            return;
//        }
//
//    }


}

