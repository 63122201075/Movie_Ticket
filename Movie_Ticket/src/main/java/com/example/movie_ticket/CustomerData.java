package com.example.movie_ticket;

import java.sql.Date;

public class CustomerData {
    private Integer customer_id;
    private String type;
    private double total;
    private String movieTitle;
    private Integer quantity;
    private Date date;

    // Constructor, getter และ setter ต่าง ๆ มีไว้เพื่อให้คุณเข้าถึงข้อมูลและกำหนดค่าให้กับฟิลด์ได้

    // Constructor
    public CustomerData(Integer customer_id, String type, double total, String movieTitle, Integer quantity, Date date) {
        this.customer_id = customer_id;
        this.type = type;
        this.total = total;
        this.movieTitle = movieTitle;
        this.quantity = quantity;
        this.date = date;
    }

    // Getter methods
    public Integer getCustomer_id() {
        return customer_id;
    }

    public String getType() {
        return type;
    }

    public double getTotal() {
        return total;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }
}
