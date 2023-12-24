package org.example.readFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Post.Post;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GetPostFromFile {

    public static List<Post> getPostsFromFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();


            objectMapper.registerModule(new JavaTimeModule());


            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Post.class);


            List<Post> posts = objectMapper.readValue(new File(filePath), listType);

            return posts;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
