package ds.ac.kr.nuguseyo;

/**
 * Created by Owner on 2018-03-16.
 */

public class Item {

    public static class Scraps {
        int count;
        boolean userScraped;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isUserScraped() {
            return userScraped;
        }

        public void setUserScraped(boolean userScraped) {
            this.userScraped = userScraped;
        }
    }

    int postId;
    Scraps scrapCount;

    boolean isScrap;

    String userId;
    String imgUrl;
    String content;

    public Item(int postId, boolean isScrap, String userId, String imgUrl, String content) {
        this.postId = postId;
        //this.scrapCount = scrapCount;
        this.isScrap = isScrap;
        this.userId = userId;
        this.imgUrl = imgUrl;
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Scraps getScrapCount() {
        return scrapCount;
    }

    public void setScrapCount(Scraps scrapCount) {
        this.scrapCount = scrapCount;
    }

    public boolean isScrap() {
        return isScrap;
    }

    public void setScrap(boolean scrap) {
        isScrap = scrap;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
