package org.example.Post;

import org.example.readFile.GetBlogFromFile;
import java.util.List;
public class Blog extends Post{
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
