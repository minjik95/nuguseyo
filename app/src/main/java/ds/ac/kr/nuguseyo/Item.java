package ds.ac.kr.nuguseyo;

/**
 * Created by Owner on 2018-03-16.
 */

public class Item {

    boolean isScrap;

    String scrapCount;
    String imgUrl;
    String content;

    public Item(boolean isScrap, String scrapCount, String imgUrl, String content) {
        this.isScrap = isScrap;
        this.scrapCount = scrapCount;
        this.imgUrl = imgUrl;
        this.content = content;
    }

    public boolean isScrap() {
        return isScrap;
    }

    public void setScrap(boolean scrap) {
        isScrap = scrap;
    }

    public String getScrapCount() {
        return scrapCount;
    }

    public void setScrapCount(String scrapCount) {
        this.scrapCount = scrapCount;
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
