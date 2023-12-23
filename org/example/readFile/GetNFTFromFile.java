package org.example.readFile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetNFTFromFile extends readFile {
    public void parseJSONObject(Object jsonObject) {
        // Kiểm tra xem đối tượng có phải là JSONObject không
        if (jsonObject instanceof JSONObject) {
            // Ép kiểu đối tượng về JSONObject để sử dụng các phương thức của nó
            JSONObject json = (JSONObject) jsonObject;

            // Lấy các giá trị từ JSONObject
            String name = json.getString("name");
            String volume = json.getString("volume");
            String change = json.getString("change");
            String floorPrice = json.getString("floorPrice");
            String owners = json.getString("owners");
            String items = json.getString("items");

            // Bây giờ bạn có thể sử dụng các biến này theo cách bạn muốn
        } else {
            // Nếu đối tượng không phải là JSONObject, in ra thông báo lỗi
            System.err.println("Đối tượng JSON không hợp lệ");
        }
    }

    public static void main(String[] args) {

        try {
            // Đọc nội dung từ tệp JSON
            String content = new String(Files.readAllBytes(Paths.get("D:\\Hust-IT\\OOP\\readFile\\JSON\\src\\main\\java\\org\\example\\Collection.json")));

            // Tạo đối tượng JSON từ chuỗi nội dung
            Object json = new org.json.JSONTokener(content).nextValue();

            // Kiểm tra xem đối tượng có phải là JSONObject hoặc JSONArray không
            if (json instanceof JSONObject) {
                // Nếu là JSONObject, in nó
                System.out.println(((JSONObject) json).toString(2));
            } else if (json instanceof JSONArray) {
                // Nếu là JSONArray, in từng phần tử
                JSONArray jsonArray = (JSONArray) json;
                for (int i = 0; i < jsonArray.length(); i++) {
                    System.out.println(jsonArray.getJSONObject(i).toString(2));
                }
            } else {
                // Nếu không phải cả hai, in ra thông báo lỗi
                System.err.println("Đối tượng JSON không hợp lệ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
