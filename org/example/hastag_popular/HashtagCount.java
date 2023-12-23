package org.example.hastag_popular;

import java.time.LocalDate;
public class HashtagCount {
    private String hashtag;
    private LocalDate date;

    public void setReact(int react) {
        this.react = react;
    }

    private int react;

    public HashtagCount(String hashtag, LocalDate date, int react) {
        this.hashtag = hashtag;
        this.date = date;
        this.react = react;
    }

    public String getHashtag() {
        return hashtag;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getReact() {
        return react;
    }
}
