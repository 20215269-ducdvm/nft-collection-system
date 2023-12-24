package modules.nft;

import modules.post.Blog;

import java.util.ArrayList;
import java.util.List;

public class NFTCollection {
    // Attribute
    private String name;
    private String volume;
    private String change;
    private String floorPrice;
    private String owners;
    private String items;

    // Constructor
    public NFTCollection() {
    }

    public NFTCollection(String name, String volume, String change, String floorPrice, String owners, String items) {
        this.name = name;
        this.volume = volume;
        this.change = change;
        this.floorPrice = floorPrice;
        this.owners = owners;
        this.items = items;
    }

//     Method to get list of Blog by Collection
//    public List<Blog> getBlogByCollection(List<Blog> blogList){
//        List<Blog> filteredBlog = new ArrayList<>();
//        for (Blog blog: blogList){
//            if(blog.getCollection().equals(name)){
//                filteredBlog.add(blog);
//            }
//        }
//        return filteredBlog;
//    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getFloorPrice() {
        return floorPrice;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}