package ds.ac.kr.nuguseyo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Owner on 2018-03-16.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

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

        holder.scrapCount.setText(item.getScrapCount());
        Glide.with(mContext).load(imgUrl).into(holder.img);
        holder.content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}

