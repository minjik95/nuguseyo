package ds.ac.kr.nuguseyo;

/**
 * Created by Owner on 2018-04-03.
 */

public class Comment {
    int commentID;
    String postID;
    String userID;
    String commentContent;

    public Comment(int commentID, String postID, String userID, String commentContent) {
        this.commentID = commentID;
        this.postID = postID;
        this.userID = userID;
        this.commentContent = commentContent;
    }
}
