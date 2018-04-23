package ds.ac.kr.nuguseyo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CommentActivity extends AppCompatActivity {

    ArrayList<Comment> listComments;
    ArrayList<String> stringComments;

    ArrayAdapter adapter;

    JSONArray commentArray;

    String postID;
    String userID;
    String commentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        listComments = new ArrayList<>();
        stringComments = new ArrayList<>();

        adapter = new ArrayAdapter(this, R.layout.listitem, R.id.listitem_textview, stringComments);

        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        postID = intent.getExtras().getString("postID");
        userID = intent.getExtras().getString("userID");
        commentContent = intent.getExtras().getString("commentContent");

        Log.d("결과값","postID : " + postID + ", userID : " + userID + ", commentContent : " + commentContent);

        LoadDataFromServer();
    }

    private void LoadDataFromServer() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("postID", postID)
                        .add("userID", userID)
                        .build();

                Request request = new Request.Builder()
                        .url("http://minjik95.cafe24.com/CommentData.php")
                        .post(requestBody)
                        .build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();

                    commentArray = new JSONArray(response.body().string());

                    for(int i = 0; i < commentArray.length(); i++) {
                        JSONObject object = commentArray.getJSONObject(i);

                        Comment comments = new Comment(object.getInt("commentID"), object.getString("postID"), object.getString("userID"), object.getString("commentContent"));

                        listComments.add(comments);
                        stringComments.add(object.getString("userID") + "   " + object.getString("commentContent"));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.d("End of posts","End of posts");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        };

        task.execute();
    }

}
