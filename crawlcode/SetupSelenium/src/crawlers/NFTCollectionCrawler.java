package crawlcode.SetupSelenium.src.crawlers;

import modules.nft.NFTCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NFTCollectionCrawler extends WebCrawler<NFTCollection> {
    private static final String URL = "https://www.binance.com/en/nft/ranking?tab=collection";
    private static final String JSON_PATH = "data/Collection.json";

    public NFTCollectionCrawler(String json_file_path, String... page_urls) {
        super(json_file_path, page_urls);
    }

    public static void main(String[] args) throws IOException {
        NFTCollectionCrawler binanceNFTCrawler = new NFTCollectionCrawler(JSON_PATH, URL);
        binanceNFTCrawler.execute();
    }

    // The order in Collection.json is 1H 6H 7D 30D All 24H
    @Override
    public void crawlData() {
        for (String PAGE_URL : PAGE_URLs) {
            driver.get(PAGE_URL);
            waitForPresenceOfElements(new String[]{"css-11cvlnv", "css-1eb531i"});
            List<WebElement> filterEls = driver.findElements(By.className("css-11cvlnv"));
            filterEls.add(driver.findElement(By.className("css-1eb531i")));
            List<WebElement> filterByHours = filterEls.subList(filterEls.size() - 6, filterEls.size());

            for (WebElement filterByHour : filterByHours) {
                filterByHour.click();
                waitForPresenceOfElements(new String[]{"css-kyd4a2"});
                List<WebElement> webElements = driver.findElements(By.className("css-kyd4a2"));

                for (int i = 0; i < webElements.size(); i++) {
                    String[] webElementContents = webElements.get(i).getText().split("\n");
                    String[] filteredContents = Arrays.stream(webElementContents)
                            .filter(element -> !element.contains("%"))
                            .toArray(String[]::new);
                    System.out.println(Arrays.toString(filteredContents));
                    String name = i < 3 ? filteredContents[0] : filteredContents[1];
                    String volume = i < 3 ? filteredContents[1] : filteredContents[2];
                    String change = i < 3 ? filteredContents[2] : filteredContents[3];
                    String floorPrice = i < 3 ? filteredContents[3] : filteredContents[4];
                    String owners = i < 3 ? filteredContents[5] : filteredContents[6];
                    String items = i < 3 ? filteredContents[6] : filteredContents[7];

                    objects.add(new NFTCollection(name, volume, change, floorPrice, owners, items));
                }
            }
        }
    }


}
