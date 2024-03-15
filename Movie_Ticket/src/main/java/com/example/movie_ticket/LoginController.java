package com.example.movie_ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private Button login_button;

    @FXML
    private Hyperlink login_create_Ac;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;

    @FXML
    private Hyperlink signUp_HaveAcc;

    @FXML
    private Button signUp_button;

    @FXML
    private TextField signUp_email;

    @FXML
    private AnchorPane signUp_form;

    @FXML
    private TextField signUp_username;

    @FXML
    private PasswordField signUp_password;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private double x = 0;
    private double y = 0;

        public void login() {
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";
        connect = database.getConnection(); //เชื่อมต่อฐานข้อมูล โดยอ้างจาก database.java

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_username.getText());
            prepare.setString(2, login_password.getText());

            result = prepare.executeQuery();


//            ตรวจสอบ username and password ว่าง
            if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
                showAlert_ERROR("Error Message", "Please fill in information");
            } else {
                if (result.next()) {
                    getData.usernamedata = login_username.getText(); //รับข้อมูล username
                    showAlert_INFORMATION("Information Message", "Successfully Login!");

//                    ไปหน้า dashboard
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
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


                    stage.setScene(scene);
                    stage.show();

                     //ปิดหน้า login
                    Stage stage_old = (Stage) login_button.getScene().getWindow();
                    stage_old.close();
                } else {
                    showAlert_ERROR("Error Message", "Wrong Username/Password");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public void login() throws IOException {
//
//        try {
//
//
//            getData.usernamedata = "Test"; //รับข้อมูล username
//
//            Parent root = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//
//            root.setOnMousePressed((MouseEvent event) -> {
//                x = event.getSceneX();
//                y = event.getSceneY();
//            });
//
//            root.setOnMouseDragged((MouseEvent event) -> {
//                stage.setX(event.getScreenX() - x);
//                stage.setY(event.getScreenY() - y);
//            });
//
//
//            stage.setScene(scene);
//            stage.show();
//
//            login_button.getScene().getWindow().hide(); //ซ่อนหน้า login
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void signUp() {
        String sql = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        String sql2 = "SELECT username FROM user WHERE username = '" + signUp_username.getText() + "'";
        ;
        connect = database.getConnection(); // Get connection from database.java

        try {


            if (signUp_email.getText().isEmpty() || signUp_username.getText().isEmpty() || signUp_password.getText().isEmpty()) {
                showAlert_ERROR("Error Message", "Please fill in information");
            } else if (signUp_password.getText().length() < 5) {
                showAlert_ERROR("Error Message", "Password must be more than 5 characters.");
            } else if (signUp_username.getText().length() > 10) {
                showAlert_ERROR("Error Message", "Username must not exceed 10 characters.");

            } else {

                if (validEmail()) {
                    prepare = connect.prepareStatement(sql2);
                    result = prepare.executeQuery();

                    if (result.next()) {
                        showAlert_ERROR("Error Message", "Username : " + signUp_username.getText() + " was already exist!");
                    } else {
                        //สร้าง account ใหม่สำเร็จ
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, signUp_username.getText());
                        prepare.setString(2, signUp_password.getText());
                        prepare.setString(3, signUp_email.getText());
                        prepare.execute();
                        showAlert_INFORMATION("Information Message", "Successfully signed up!");

                        signUp_email.setText("");
                        signUp_username.setText("");
                        signUp_password.setText("");
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean validEmail() {
        //รูปแบบ email sippakorn_24@gmail.com
//        Pattern pattern = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]@[a-zA-z0-9]+([.][a-zA-Z]+)+");
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher match = pattern.matcher(signUp_email.getText());
        if (match.find() && match.group().equals(signUp_email.getText())
        ) {
            return true;
        } else {
            showAlert_ERROR("Error Message", "Invalid email");
            return false;
        }
    }


    //เปลี่ยนหน้าจอ ระหว่าง หน้า login กับ หน้า sigh up
    public void switchForm(ActionEvent event) {
        if (event.getSource() == login_create_Ac) {
            login_form.setVisible(false);
            signUp_form.setVisible(true);
        } else if (event.getSource() == signUp_HaveAcc) {
            login_form.setVisible(true);
            signUp_form.setVisible(false);
        }
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
