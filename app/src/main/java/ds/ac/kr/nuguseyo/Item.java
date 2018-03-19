package ds.ac.kr.nuguseyo;

/**
 * Created by Owner on 2018-03-16.
 */

public class Item {

    boolean isScrap;

    String scrapCount;
    String imgUrl;

    public Item(boolean isScrap, String scrapCount, String imgUrl) {
        this.isScrap = isScrap;
        this.scrapCount = scrapCount;
        this.imgUrl = imgUrl;
    }

    public boolean isScrap() {
        return isScrap;
    }

    public String getScrapCount() {
        return scrapCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
