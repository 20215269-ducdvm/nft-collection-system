package org.example.Post;

import java.util.List;

public class News extends Post {
    private List<String> tags;

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}
