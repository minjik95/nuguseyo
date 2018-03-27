package ds.ac.kr.nuguseyo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Owner on 2018-03-16.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    CheckBox isScrap;
    TextView scrapCount;
    ImageView img;
    TextView content;

    public ItemViewHolder(View itemView) {
        super(itemView);

        isScrap = itemView.findViewById(R.id.cb_scrap);
        scrapCount = itemView.findViewById(R.id.tv_scrapcount);
        img = itemView.findViewById(R.id.iv_img);
        content = itemView.findViewById(R.id.tv_content);
    }
}
