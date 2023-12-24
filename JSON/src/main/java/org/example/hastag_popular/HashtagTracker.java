package org.example.hastag_popular;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HashtagTracker {
    private Map<String, List<HashtagCount>> hashtagCounts;

    public HashtagTracker() {
        hashtagCounts = new HashMap<>();
    }

    public void trackHashtag(String hashtag, LocalDate date, int react) {
        hashtagCounts.computeIfAbsent(hashtag, k -> new ArrayList<>());
        List<HashtagCount> countsForHashtag = hashtagCounts.get(hashtag);

        // Kiểm tra xem đã có thông tin sử dụng cho ngày date hay chưa
        boolean existingEntry = countsForHashtag.stream().anyMatch(entry -> entry.getDate().equals(date));

        if (existingEntry) {
            // Nếu đã có thông tin sử dụng cho ngày date, cập nhật lại react
            countsForHashtag.stream()
                    .filter(entry -> entry.getDate().equals(date))
                    .findFirst()
                    .ifPresent(entry -> entry.setReact(entry.getReact() + react));
        } else {
            // Nếu chưa có thông tin sử dụng cho ngày date, thêm mới entry
            countsForHashtag.add(new HashtagCount(hashtag, date, react));
        }
    }

    public List<HashtagCount> getTopHashtags(int n, LocalDate date) {
        return hashtagCounts.entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .filter(hashtagCount -> hashtagCount.getDate().equals(date))
                        .findFirst()
                        .orElse(new HashtagCount(entry.getKey(), date, 0))
                )
                .sorted((hashtagCount1, hashtagCount2) -> Integer.compare(hashtagCount2.getReact(), hashtagCount1.getReact()))
                .limit(n)
                .collect(Collectors.toList());
    }
}

