package org.example;

import org.example.Post.Post;
import org.example.readFile.GetPostFromFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private List<Post> posts;

    public Main() {
        String filePath = "D:\\Hust-IT\\OOP\\readFile\\JSON\\src\\main\\resources\\RedditPost.json";
        posts = GetPostFromFile.getPostsFromFile(filePath);
    }

    public void run() {
        boolean searchByTitle = false;
        boolean hotPostByDay = false;
        boolean hotPostByMonth = true;
        boolean hotPostByYear = false;

        if (searchByTitle) {
            String titleToSearch = "NFTs";
            printPostsByTitle(titleToSearch);
        }
        if (hotPostByDay) {
            printHottestPostsByDay();
        }
        if (hotPostByMonth) {
            printHottestPostsByMonth();
        }
        if (hotPostByYear) {
            printHottestPostsByYear();
        }

    }

    private void printPostsByTitle(String title) {
        if (posts != null) {
            for (Post post : posts) {
                if (post.getSumContent().contains(title)) {
                    printPostDetails(post);
                }
            }
        } else {
            System.out.println("No posts available.");
        }
    }

    private void printPostDetails(Post post) {
        System.out.println("Title: " + post.getSumContent());
        System.out.println("Author: " + post.getAuthor());
        System.out.println("Link: " + post.getLink());
        System.out.println("Ups: " + post.getUps());
        System.out.println("Downs: " + post.getDowns());
        System.out.println("Num Comments: " + post.getNumComments());
        System.out.println("Created: " + post.getCreated());
        System.out.println("Keyword: " + post.getKeyword());
        System.out.println("Time Frame: " + post.getTimeFrame());
        System.out.println();
    }

    private void printHottestPostsByDay() {
        if (posts != null) {
            List<Post> hottestByDay = posts.stream()
                    .sorted(Comparator.comparing(Post::getUps).reversed())
                    .filter(post -> post.getCreated().toLocalDate().isEqual(LocalDate.now()))
                    .collect(Collectors.toList());

            System.out.println("Hottest Posts Today:");
            printTopPosts(hottestByDay);
        } else {
            System.out.println("No posts available.");
        }
    }

    private void printHottestPostsByMonth() {
        if (posts != null) {
            List<Post> hottestByMonth = posts.stream()
                    .sorted(Comparator.comparing(Post::getUps).reversed())
                    .filter(post -> post.getCreated().toLocalDate().getMonth().equals(LocalDate.now().getMonth()))
                    .collect(Collectors.toList());

            System.out.println("Hottest Posts This Month:");
            printTopPosts(hottestByMonth);
        } else {
            System.out.println("No posts available.");
        }
    }

    private void printHottestPostsByYear() {
        if (posts != null) {
            List<Post> hottestByYear = posts.stream()
                    .sorted(Comparator.comparing(Post::getUps).reversed())
                    .filter(post -> post.getCreated().toLocalDate().getYear() == LocalDate.now().getYear())
                    .collect(Collectors.toList());

            System.out.println("Hottest Posts This Year:");
            printTopPosts(hottestByYear);
        } else {
            System.out.println("No posts available.");
        }
    }

    private void printTopPosts(List<Post> topPosts) {
        int count = Math.min(topPosts.size(), 5); // Print at most 5 posts
        for (int i = 0; i < count; i++) {
            printPostDetails(topPosts.get(i));
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
