package org.example.GUI;
import org.example.NFT.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.readFile.GetNFTFromFile;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NFTApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        // Đọc dữ liệu từ file JSON
        String jsonContent = readJsonFile("D:\\Hust-IT\\OOP\\readFile\\JSON\\src\\main\\java\\org\\example\\readFile\\Collection.json");

        // Chuyển đổi chuỗi JSON thành đối tượng JSONObject
        JSONObject jsonObject = new JSONObject(jsonContent);

        // Tạo một đối tượng NFTCollection và điền dữ liệu từ JSONObject
        NFTCollection nftCollection = new NFTCollection();
        nftCollection.setName(jsonObject.getString("name"));
        nftCollection.setVolume(jsonObject.getDouble("volume"));
        nftCollection.setFloorPrice(jsonObject.getDouble("floorPrice"));
        nftCollection.setOwners(jsonObject.getInt("owners"));
        nftCollection.setItems(jsonObject.getInt("items"));

        // Tạo giao diện JavaFX
        VBox root = new VBox(10);
        root.getChildren().addAll(
                new Label("Name: " + nftCollection.getName()),
                new Label("Volume: " + nftCollection.getVolume()),
                new Label("Floor Price: " + nftCollection.getFloorPrice()),
                new Label("Owners: " + nftCollection.getOwners()),
                new Label("Items: " + nftCollection.getItems())
        );

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("NFT Collection Information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String readJsonFile(String filePath) {
        try {
            // Đọc nội dung file JSON thành chuỗi
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

