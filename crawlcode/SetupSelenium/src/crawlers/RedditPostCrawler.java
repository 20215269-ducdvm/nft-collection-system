package crawlcode.SetupSelenium.src.crawlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import modules.post.RedditPost;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class RedditPostCrawler extends WebCrawler<RedditPost> {
    private static final String JSON_PATH = "data/RedditPost.json";
    private static final String[] keyWords = {
            "NFT Gaming",
            "NFTs",
            "Blockchain",
            "Crypto Art",
            "Cryptocurrency",
            "Crypto Wallets",
            "NFT Investment",
            "Tokenization",
            "Digital Collectibles",
            "Smart Contracts",
            "Crypto Artists",
            "NFT Platforms",
            "Immutable Ledger",
            "Rare Tokens",
            "NFT dead",
            "NFT Marketplaces",
            "Ethereum",
            "NFT alive",
            "Digital Ownership",
            "Tokenized Assets"};

    public RedditPostCrawler(String json_file_path, String... page_urls) {
        super(json_file_path, page_urls);
    }

    public static void main(String[] args) throws IOException {
        String[] urls = makeUrls();
        RedditPostCrawler postCrawler = new RedditPostCrawler(JSON_PATH, urls);
        postCrawler.execute();
    }

    public static String[] makeUrls() {
        String BASE_URL = "https://www.reddit.com/search.json?";
        // Each keyword requires requests for time periods: 24 hours, Month, and Year
        int numOfUrls = keyWords.length * 3;
        String[] urls = new String[numOfUrls];
        String[] timeFrames = {"day", "month", "year"};

        for (int i = 0; i < numOfUrls; i++) {
            int keywordIndex = i % keyWords.length;
            int timeIndex = i / keyWords.length;
            // Construct the URL by replacing spaces with "%20" in keywords
            urls[i] = BASE_URL + "q=" + keyWords[keywordIndex].replace(" ", "%20") + "&t=" + timeFrames[timeIndex] + "&type=link&limit=100&sort=relevance";
        }
        return urls;
    }

    public String getJsonResponse(String urlRequest) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String jsonResponse = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlRequest))
                    .GET()
                    .build();
            // Send the GET request and receive the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Get JSON value from response body
                jsonResponse = response.body();
            } else {
                System.out.println("Request failed with status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    @Override
    public void crawlData() {
        for (String PAGE_URL : PAGE_URLs) {
            System.out.println(PAGE_URL);
            // Send an HTTP request to fetch JSON response
            String jsonResponse = getJsonResponse(PAGE_URL);

            // Parse the JSON response into a JsonObject and extract the array of posts from the JSON response
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonResponse);
            JsonArray responsePosts = jsonObject.getAsJsonObject("data").getAsJsonArray("children");

            for (JsonElement responsePost : responsePosts) {
                // convert to JsonObject and extract detailed information of the post from the JsonObject
                JsonObject objectPost = responsePost.getAsJsonObject().getAsJsonObject("data");

                String title = objectPost.get("title").getAsString();
                String author = objectPost.get("subreddit_name_prefixed").getAsString();
                String link = "https://www.reddit.com" + objectPost.get("permalink").getAsString();
                String sumContent = objectPost.get("selftext").getAsString();
                int ups = objectPost.get("ups").getAsInt();
                int downs = objectPost.get("downs").getAsInt();
                int numComments = objectPost.get("num_comments").getAsInt();

                // Convert the post creation time from epoch format to ISO_LOCAL_DATE_TIME
                String created = Instant.ofEpochSecond((long) Double.parseDouble(objectPost.get("created").getAsString()))
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                // Extract url to get keyword and timeFrames
                String[] params = PAGE_URL.split("[?&]");
                String keyWord = null;
                String timeFrame = null;
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = keyValue[1];

                        if ("t".equals(key)) {
                            keyWord = value;
                        } else if ("q".equals(key)) {
                            timeFrame = URLDecoder.decode(value, StandardCharsets.UTF_8);
                        }
                    }
                }
                objects.add(new RedditPost(title, author, link, sumContent, ups, downs, numComments, created, timeFrame, keyWord));
            }
        }
    }
}