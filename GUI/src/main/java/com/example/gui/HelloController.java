package com.example.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;


public class HelloController {
    @FXML
    private Label welcomeText;
    private Label LbHomePage;
    private Label LbPost;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void HomePageClick(MouseEvent event) {
        // Xử lý sự kiện khi nhấp chuột vào label
        // Ví dụ: Chuyển đến một trang khác
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void PostClick(MouseEvent event) {
        // Xử lý sự kiện khi nhấp chuột vào label
        // Ví dụ: Chuyển đến một trang khác
        try {
            Parent root = FXMLLoader.load(getClass().getResource("posts-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


