package com.example.movie_ticket;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ImageSlide implements Initializable {

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    private int currentImageIndex = 0;

    private FadeTransition fadeOutTransition;
    private FadeTransition fadeInTransition;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set initial images
//        Image i1 = new Image("file:C:\\Users\\ADMIN\\Desktop\\idea_projects\\Movie_Ticket\\src\\main\\java\\image\\img1.jpg", 1280, 720, false, true);
//        image1.setImage(i1);
        Image i1 = new Image("https://i.pinimg.com/originals/e8/63/a7/e863a7e41c5e318153a213b81f77bba9.png",
                1280, 720, false, true);
        image1.setImage(i1);
        Image i2 = new Image("https://hdwallpaperim.com/wp-content/uploads/2017/09/07/466505-your_name.-kimi_no_na_wa.jpg",
                1280, 720, false, true);
        image2.setImage(i2);
        Image i3 = new Image("https://wallpapers-clan.com/wp-content/uploads/2023/11/naruto-glowing-eyes-colorful-desktop-wallpaper-cover.jpg",
                1280, 720, false, true);
        image3.setImage(i3);
        Image i4 = new Image("https://m.media-amazon.com/images/I/911pwF+7rQL._AC_UF1000,1000_QL80_.jpg",
                1280, 720, false, true);
        image4.setImage(i4);

        // Initialize fade transitions
        fadeOutTransition = new FadeTransition(Duration.seconds(2));
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);

        fadeInTransition = new FadeTransition(Duration.seconds(2));
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);

        // Start slideshow
        startSlideShow();
    }

    private void startSlideShow() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), event -> {
            // Fade out current image
            fadeOutTransition.setNode(getCurrentImageView());
            fadeOutTransition.setOnFinished(e -> {
                // Fade in next image
                fadeInTransition.setNode(getNextImageView());
                fadeInTransition.play();
            });
            fadeOutTransition.play();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private ImageView getCurrentImageView() {
        switch (currentImageIndex) {
            case 0:
                return image1;
            case 1:
                return image2;
            case 2:
                return image3;
            case 3:
                return image4;
            default:
                return image1; // Default to first image
        }
    }

    private ImageView getNextImageView() {
        currentImageIndex = (currentImageIndex + 1) % 4; // Loop back to first image
        return getCurrentImageView();
    }
}

