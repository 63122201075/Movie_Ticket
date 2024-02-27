package com.example.movie_ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//สร้างฐานข้อมูลชื่อ mov_book

//สร้าง ตาราง admin
//create table admin (
//       email VARCHAR(100),
//username VARCHAR(100),
//password VARCHAR(100));

//insert admin data
//INSERT INTO admin (email, username, password)VALUES ('admin@gmail.com', 'admin', 'admin');

//สร้าง ตาราง movie
//CREATE TABLE movie (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        movieTitle VARCHAR(100),
//genre VARCHAR(100),
//duration VARCHAR(100),
//image VARCHAR(500),
//date DATE NULL DEFAULT NULL,
//current VARCHAR(100) NOT NULL
//);

//INSERT INTO movie (movieTitle, genre, duration,image,date)VALUES ('admin@gmail.com', 'admin', 'admin',null,null);

//สร้างตาราง customer_info

//create table customer_info (
//        customer_id int auto_increment primary key ,
//        type varchar(100),
//total double not null,
//movieTitle varchar(100),
//quantity int(100),
//date Date null default null
//        );

//ต้องแก้ไข
//sign up
//        add movie


public class database {
    private static final String DB_URL_PREFIX = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "mov_book";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";


    public static Connection getConnection() {
        String url = DB_URL_PREFIX + DB_NAME;
        try {
            Connection connect = DriverManager.getConnection(url, USERNAME, PASSWORD);
            return connect;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    } //end
}
