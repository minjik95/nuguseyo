package ds.ac.kr.nuguseyo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Owner on 2018-03-16.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<Item> items;

    Item item;

    int postID;
    String commentContent;
    public ItemAdapter(Context context, ArrayList<Item> listItems) {
        mContext = context;
        items = listItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View baseView = View.inflate(mContext, R.layout.item, null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(baseView);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        item = items.get(position);

        String imgUrl = "http://minjik95.cafe24.com/" + item.getImgUrl();
        Log.d("imgUrl","" + imgUrl);

        Glide.with(mContext).load(imgUrl).into(holder.img);
        //holder.scrapCount.setText(item.getScrapCount().getCount());
        holder.scrapCount.setText("123");
        holder.userID.setText(item.getUserId());
        holder.content.setText(item.getContent());
        //holder.isScrap.setChecked(item.getScrapCount().isUserScraped());

        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentContent = holder.comment.getText().toString();
                Log.d("commentContent : ", "" + commentContent);

                postID = holder.getLayoutPosition();
                Log.d("postID : ", "" + postID);

                commentRequest();

                Intent commentIntent = new Intent(mContext, CommentActivity.class);
                commentIntent.putExtra("postID", String.valueOf(postID));
                commentIntent.putExtra("userID", item.getUserId());
                commentIntent.putExtra("commentContent", commentContent);

                mContext.startActivity(commentIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private void commentRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "http://minjik95.cafe24.com/Comment.php",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("postID", String.valueOf(postID));
                map.put("userID", item.getUserId());
                map.put("commentContent", commentContent);

                Log.d("map","" + map);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }







    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        CheckBox isScrap;
        TextView scrapCount;
        TextView userID;
        TextView content;
        EditText comment;
        TextView send;

        public ItemViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.iv_img);
            isScrap = itemView.findViewById(R.id.cb_scrap);
            scrapCount = itemView.findViewById(R.id.tv_scrapcount);
            userID = itemView.findViewById(R.id.tv_userID);
            content = itemView.findViewById(R.id.tv_content);
            comment = itemView.findViewById(R.id.et_comment);
            send = itemView.findViewById(R.id.iv_send);
        }

    }
}

