package modules.post;

public class RedditPost extends Post{

    // Attribute
    private String sumContent;
    private int ups;
    private int downs;
    private int numComments;
    private String created;
    private String keyword;
    private String timeFrame;
    // Constructor
    public RedditPost(String title, String author, String link, String sumContent, int ups, int downs, int numComments, String created, String keyword, String timeFrame) {
        this.title = title;
        this.link = link;
        setAuthor(author);
        setSumContent(sumContent);
        setUps(ups);
        setDowns(downs);
        setNumComments(numComments);
        setCreated(created);
        setKeyword(keyword);
        setTimeFrame(timeFrame);
    }

    // Method to calculate react
    public void calculateReact() {
        setReact(ups + downs + numComments);
    }

    // Method to check if post contain the keyword
    public boolean check(String keyWord) {
        return getTitle().toLowerCase().contains(keyWord.toLowerCase())
                || getSumContent().toLowerCase().contains(keyWord.toLowerCase());
    }

    public String getTitle() {
        return super.getTitle();
    }

    // Getter and Setter
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSumContent() {
        return sumContent;
    }

    public void setSumContent(String sumContent) {
        this.sumContent = sumContent;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }
}