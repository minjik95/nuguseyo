package ds.ac.kr.nuguseyo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> listItems = new ArrayList<>();

//        for(int i = 0; i < 5; i++) {
//            Item items = new Item(true, "123", "http://img.theqoo.net/img/IpAiy.gif");
//            listItems.add(i, items);
//        }

        Item item1 = new Item(true, "125",
                "http://res.heraldm.com/content/image/2015/12/15/20151215000161_0.jpg");
        listItems.add(item1);

        Item item2 = new Item(true, "125",
                "http://img.tenasia.hankyung.com/webwp_kr/wp-content/uploads/2017/11/2017110720122915751.jpg");
        listItems.add(item2);

        Item item3 = new Item(true, "125",
                "http://img.insight.co.kr/static/2017/12/24/700/ta07wn8fb2n2n7581oc9.jpg");
        listItems.add(item3);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ItemAdapter itemAdapter = new ItemAdapter(this, listItems);
        recyclerView.setAdapter(itemAdapter);
    }

}
