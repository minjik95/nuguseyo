package ds.ac.kr.nuguseyo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

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
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);

        String imgUrl = "http://minjik95.cafe24.com/" + item.getImgUrl();
        Log.d("imgUrl","" + imgUrl);

        Glide.with(mContext).load(imgUrl).into(holder.img);
        //holder.scrapCount.setText(item.getScrapCount().getCount());
        holder.scrapCount.setText("123");
        holder.userID.setText(item.getUserId());
        holder.content.setText(item.getContent());
        //holder.isScrap.setChecked(item.getScrapCount().isUserScraped());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }







    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        CheckBox isScrap;
        TextView scrapCount;
        TextView userID;
        TextView content;

        public ItemViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.iv_img);
            isScrap = itemView.findViewById(R.id.cb_scrap);
            scrapCount = itemView.findViewById(R.id.tv_scrapcount);
            userID = itemView.findViewById(R.id.tv_userID);
            content = itemView.findViewById(R.id.tv_content);

        }

    }
}

