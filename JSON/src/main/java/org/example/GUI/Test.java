package org.example.GUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        // Chuỗi ngày giờ đầu vào
        String input = "2023-12-15T00:17:32";

        // Định dạng của chuỗi ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        // Chuyển đổi chuỗi thành LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);

        // In ra giá trị LocalDateTime
        System.out.println("LocalDateTime: " + dateTime);
    }
}

