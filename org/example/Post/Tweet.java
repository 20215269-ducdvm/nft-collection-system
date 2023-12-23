package org.example.Post;

import java.util.List;
public class Tweet extends Post{
    private List<String> hashtags;

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
}
