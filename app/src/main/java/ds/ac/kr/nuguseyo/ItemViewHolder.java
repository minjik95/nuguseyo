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

    public ItemViewHolder(View itemView) {
        super(itemView);

        isScrap = (CheckBox) itemView.findViewById(R.id.cb_scrap);
        scrapCount = (TextView) itemView.findViewById(R.id.tv_scrapcount);
        img = (ImageView) itemView.findViewById(R.id.iv_img);
    }
}
