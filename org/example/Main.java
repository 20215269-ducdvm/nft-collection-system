package org.example;

import org.example.Post.*;
import org.example.NFT.*;
import org.example.readFile.*;
import org.example.hastag_popular.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        // Tạo danh sách bài viết
        List<Blog> blogList = new ArrayList<>();
        List<News> newsList = new ArrayList<>();
        List<Tweet> tweetList = new ArrayList<>();
        List<Post> postList = new ArrayList<>();


        // Tạo và thêm các bài viết vào danh sách (mô phỏng dữ liệu)
        Blog blog1 = new Blog();
        blog1.setAuthor("Nguyen Van A");
        blog1.setPublishDate(new Date());
        blog1.setContent("This is the content of my blog about Java and Programming.");
        blog1.setReact(100);
        blog1.setTags(Arrays.asList("Java", "Programming", "Blog"));

        Tweet tweet1 = new Tweet();
        tweet1.setAuthor("TwitterUser1");
        tweet1.setPublishDate(new Date());
        tweet1.setContent("Short tweet about #Java and #Programming.");
        tweet1.setReact(50);
        tweet1.setHashtags(Arrays.asList("Java", "Programming", "Tweet"));

        News news1 = new News();
        news1.setAuthor("Reporter A");
        news1.setPublishDate(new Date());
        news1.setContent("This is a news article with tags BreakingNews and WorldNews.");
        news1.setReact(200);
        news1.setTags(Arrays.asList("BreakingNews", "WorldNews"));

        blogList.add(blog1);
        tweetList.add(tweet1);
        newsList.add(news1);
        postList.add(blog1);
        postList.add(tweet1);
        postList.add(news1);



        // Tạo và thêm các bài viết vào danh sách


        boolean searchByTag = false; // Có thể thay đổi giá trị này dựa trên người dùng
        boolean searchByHashTag = false; // Có thể thay đổi giá trị này dựa trên người dùng
        boolean searchByKeyword = true; // Có thể thay đổi giá trị này dựa trên người dùng
        // Tìm bài viết theo tag trên Blog nếu người dùng chọn tìm kiếm theo tag

        boolean topHashtag = true; // sử dụng chức năng lấy top Hashtag
        if (searchByTag) {

            String tagToSearch = "Blog"; // Thay đổi giá trị này dựa trên tag người dùng muốn tìm kiếm
            List<Blog> filteredBlogs = filterBlogsByTag(blogList, tagToSearch);
            List<News> filteredNews = filterNewsByTag(newsList, tagToSearch);
            // Hiển thị danh sách bài viết được lọc
            System.out.println("Bài viết với tag #" + tagToSearch + ":");
            for (Blog blog : filteredBlogs) {
                System.out.println("Author: " + blog.getAuthor());
                System.out.println("Publish Date: " + blog.getPublishDate());
                System.out.println("Content: " + blog.getContent());
                System.out.println("React: " + blog.getReact());
                System.out.println();
            }

            for (News news : filteredNews) {
                System.out.println("Author: " + news.getAuthor());
                System.out.println("Publish Date: " + news.getPublishDate());
                System.out.println("Content: " + news.getContent());
                System.out.println("React: " + news.getReact());
                System.out.println();
            }
        } else if (searchByHashTag)  {
            String hashTagToSearch = "Tag cần tìm kiếm"; // Thay đổi giá trị này dựa trên tag người dùng muốn tìm kiếm
            List<Tweet> filteredTweet = filterTweetByHashTag(tweetList, hashTagToSearch);
            for (Tweet tweet : filteredTweet) {
                System.out.println("Author: " + tweet.getAuthor());
                System.out.println("Publish Date: " + tweet.getPublishDate());
                System.out.println("Content: " + tweet.getContent());
                System.out.println("React: " + tweet.getReact());
                System.out.println();
            }
        } else if (searchByKeyword){
            String keywordToSearch = "Java and Programming";
            List<Post> filteredPosts = filterPostsByKeyword(postList, keywordToSearch);
            for (Post post : filteredPosts) {
                if (post instanceof Blog) {
                    System.out.println("Blog - Author: " + post.getAuthor());
                } else if (post instanceof Tweet) {
                    System.out.println("Tweet - Author: " + post.getAuthor());
                } else if (post instanceof News) {
                    System.out.println("News - Author: " + post.getAuthor());
                }

                System.out.println("Publish Date: " + post.getPublishDate());
                System.out.println("Content: " + post.getContent());
                System.out.println("React: " + post.getReact());
                System.out.println();
            }

        }

        HashtagTracker hashtagTracker = new HashtagTracker();
        // Mô phỏng việc theo dõi sử dụng của các hashtag
        hashtagTracker.trackHashtag("Java", LocalDate.now(), 10);
        hashtagTracker.trackHashtag("Programming", LocalDate.now(), 5);
        hashtagTracker.trackHashtag("Java", LocalDate.now().minusDays(1), 8);
        hashtagTracker.trackHashtag("Programming", LocalDate.now().minusDays(1), 12);

        // Test phương thức getTopHashtags
        int topN = 2;
        LocalDate currentDate = LocalDate.now();
        List<HashtagCount> topHashtags = hashtagTracker.getTopHashtags(topN, currentDate);

        if (topHashtag){
            System.out.println("Top " + topN + " hashtags on " + currentDate + ":");
            for (HashtagCount hashtagCount : topHashtags) {
                System.out.println("Hashtag: " + hashtagCount.getHashtag());
                System.out.println("Date: " + hashtagCount.getDate());
                System.out.println("React: " + hashtagCount.getReact());
                System.out.println();
            }
        }


    }

    // Phương thức lọc bài viết theo tag trên Blog
    private static List<Blog> filterBlogsByTag(List<Blog> blogs, String tag) {
        List<Blog> filteredBlogs = new ArrayList<>();
        for (Blog blog : blogs) {
            if (blog.getTags().contains(tag)) {
                filteredBlogs.add(blog);
            }
        }
        return filteredBlogs;
    }


    // Phương thức tìm kiếm theo tag trên News
   private static List<News> filterNewsByTag(List<News> newsList, String tag) {
        List<News> filteredNews = new ArrayList<>();
        for (News news : newsList) {
            if (news.getTags().contains(tag)) {
                filteredNews.add(news);
            }
        }
        return filteredNews;
    }


    // Phương thức tìm kiếm theo hashtag trên Tweet
    private static List<Tweet> filterTweetByHashTag(List<Tweet> Tweets, String hashtag) {
        List<Tweet> filteredTweet = new ArrayList<>();
        for (Tweet tweet : Tweets) {
            if (tweet.getHashtags().contains(hashtag)) {
                filteredTweet.add(tweet);
            }
        }
        return filteredTweet;
    }


    // Phương thức lọc bài viết theo keyword
    private static List<Post> filterPostsByKeyword(List<Post> posts, String keyword) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }




}
