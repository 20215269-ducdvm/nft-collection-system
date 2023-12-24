package org.example.readFile;

import org.json.JSONObject;

public class GetTweetFromFile extends readFile{
    public void parseJSONObject(Object jsonObject) {
        // Kiểm tra xem đối tượng có phải là JSONObject không
        if (jsonObject instanceof JSONObject) {
            // Ép kiểu đối tượng về JSONObject để sử dụng các phương thức của nó
            JSONObject json = (JSONObject) jsonObject;

            // Lấy các giá trị từ JSONObject
        } else {
            // Nếu đối tượng không phải là JSONObject, in ra thông báo lỗi
            System.err.println("Đối tượng JSON không hợp lệ");
        }
    }
}
