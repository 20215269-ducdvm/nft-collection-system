package org.example.GUI;
import org.example.NFT.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NFTAppController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label volumeLabel;

    @FXML
    private Label floorPriceLabel;

    @FXML
    private Label ownersLabel;

    @FXML
    private Label itemsLabel;

    public void initialize() {
        // Đây là nơi bạn có thể thực hiện cài đặt ban đầu, nếu cần
    }

    public void setNFTCollectionInfo(NFTCollection nftCollection) {
        nameLabel.setText("Name: " + nftCollection.getName());
        volumeLabel.setText("Volume: " + nftCollection.getVolume());
        floorPriceLabel.setText("Floor Price: " + nftCollection.getFloorPrice());
        ownersLabel.setText("Owners: " + nftCollection.getOwners());
        itemsLabel.setText("Items: " + nftCollection.getItems());
    }
}
