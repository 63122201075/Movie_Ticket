package com.example.movie_ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SeatSelectionController {
    @FXML
    private Text total_text;

    @FXML
    private AnchorPane seatSelectionPane;

    @FXML
    private Button seat_buy;


    private Set<String> selectedSeats = new HashSet<>();
    private Map<String, Integer> seatPrices = new HashMap<>();
    Map<String, Boolean> seatBookedStatus = new HashMap<>();
    private Set<String> already_book = new HashSet<>();


    @FXML
    private void initialize() {
        updateTotalText();
        // กำหนดราคาตั๋วสำหรับแต่ละที่นั่ง
        // ราคาตั๋ว special สำหรับที่นั่ง A2
        seatPrices.put("A1", 200);
        seatPrices.put("A2", 200);
        seatPrices.put("A3", 200);
        seatPrices.put("A4", 200);
        seatPrices.put("A5", 200);
        seatPrices.put("B1", 100);
        seatPrices.put("B2", 100);
        seatPrices.put("B3", 100);
        seatPrices.put("B4", 100);
        seatPrices.put("B5", 100);
        seatPrices.put("C1", 100);
        seatPrices.put("C2", 100);
        seatPrices.put("C3", 100);
        seatPrices.put("C4", 100);
        seatPrices.put("C5", 100);

//        String[] already_book = {"A1", "A2", "A3"};
        already_book = getData.type;
//        already_book.add("A1");
        // กำหนดสถานะการจองของที่นั่งที่มีการจองแล้ว
        for (String seat : already_book){
            seatBookedStatus.put(seat, true);
        }

        // กำหนด event handler สำหรับทุกปุ่มใน seatSelectionPane
        seatSelectionPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button button = (Button) node;
                String seat = button.getText(); // รับตำแหน่งที่นั่ง
                if (seatBookedStatus.getOrDefault(seat, false)) {
                    button.setDisable(true); // ปิดใช้งานปุ่มที่นั่งที่ถูกจองแล้ว
                } else {
                button.setOnAction(event -> {
                    if (!selectedSeats.contains(seat)) {
                        button.setStyle("-fx-background-color: lightgreen;");
                        selectedSeats.add(seat); // เพิ่มที่นั่งที่เลือกลงในเซ็ต
                    } else {
                        button.setStyle("");
                        selectedSeats.remove(seat); // ลบที่นั่งที่ไม่ได้เลือกออกจากเซ็ต

                    }
                    updateTotalText(); // อัพเดตข้อความราคารวม

                });
            }
        }});

        // กำหนด event handler สำหรับปุ่ม buy
//        seat_buy.setOnAction(event -> {
//            if (!selectedSeats.isEmpty()) {
//                Double totalPrice = calculateTotalPrice();
//                System.out.println("Total price: $" + getData.total_price);
//                System.out.println("Selected seats: " + selectedSeats.toString());
//                System.out.println("Selected seats: " + selectedSeats.size());
//            } else {
//                System.out.println("No seats selected!");
//            }
//        });
    }
    // เมทอดสำหรับอัพเดตข้อความที่แสดงราคารวมเมื่อมีการเลือกหรือยกเลิกที่นั่ง
    private void updateTotalText() {
        double totalPrice = calculateTotalPrice();
        total_text.setText("Total: "+totalPrice);
        getData.total_price = totalPrice;
        getData.quantity = selectedSeats.size();
        getData.seats_book = String.join(", ", selectedSeats); // แปลง Set เป็น String ต่อกันด้วย ,

    }

    // เมทอดสำหรับคำนวณราคารวม
    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (String seat : selectedSeats) {
            totalPrice += seatPrices.get(seat); // เพิ่มราคาของที่นั่งที่เลือกลงในราคารวม
        }
        return totalPrice;
    }


}
