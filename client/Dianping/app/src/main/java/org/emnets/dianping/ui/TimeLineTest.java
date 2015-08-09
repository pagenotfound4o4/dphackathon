package org.emnets.dianping.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dafruits.android.library.widgets.ExtendedListView;

import org.emnets.dianping.R;
import org.emnets.dianping.model.DateAdapter;
import org.emnets.dianping.model.TimeLineItem;

import java.sql.Time;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kea on 15-8-9.
 */
public class TimeLineTest extends Activity {
    private ListView listView;
    private List<TimeLineItem> items = new LinkedList<TimeLineItem>();
    private DateAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_line_layout);
        listView = (ListView)findViewById(R.id.time_line_list_view);

        addData();

        DateComparator comparator = new DateComparator();
        Collections.sort(items, comparator);

        adapter = new DateAdapter(this, items);
        listView.setAdapter(adapter);

    }

    private void addData() {
        TimeLineItem date1 = new TimeLineItem("20140710", "撒大声地");
        TimeLineItem date2 = new TimeLineItem("20140709", "撒萨达");
        TimeLineItem date3 = new TimeLineItem("20140726", "撒大ADS");
        TimeLineItem date4 = new TimeLineItem("20140710", "撒达到对萨达撒地");
        TimeLineItem date5 = new TimeLineItem("20140711", "撒大阿瑟的萨达地");
        TimeLineItem date6 = new TimeLineItem("20140713", "撒撒大大地");
        TimeLineItem date7 = new TimeLineItem("20140712", "撒大鼎折覆餗地");
        TimeLineItem date8 = new TimeLineItem("20140714", "撒大请问阿尔阿斯顿");
        TimeLineItem date9 = new TimeLineItem("20140709", "撒大亲爱额问问乔恩地");
        TimeLineItem date10 = new TimeLineItem("20140705", "撒 请问请问地");
        TimeLineItem date11 = new TimeLineItem("20140729", "撒请问额外确认声地");
        TimeLineItem date12 = new TimeLineItem("20140725", "撒大按时打算");
        TimeLineItem date13 = new TimeLineItem("20140716", "撒大爱上大声地");
        TimeLineItem date14 = new TimeLineItem("20140711", "撒其味无穷地");
        TimeLineItem date15 = new TimeLineItem("20140710", "撒大请问我期待地");
        TimeLineItem date16 = new TimeLineItem("20140711", "撒大声萨达");
        TimeLineItem date17 = new TimeLineItem("20140712", "阿斯达");
        TimeLineItem date18 = new TimeLineItem("20140711", "撒大声大声道");
        TimeLineItem date19 = new TimeLineItem("20140715", "阿斯顿撒打算23 ");
        TimeLineItem date20 = new TimeLineItem("20140723", "范德萨发生");
        TimeLineItem date21 = new TimeLineItem("20140718", "阿萨德飞洒");
        TimeLineItem date22 = new TimeLineItem("20140706", "撒打算打算");
        TimeLineItem date23 = new TimeLineItem("20140714", "撒打算");
        TimeLineItem date24 = new TimeLineItem("20140726", "轻微的城市的方式");
        TimeLineItem date25 = new TimeLineItem("20140725", "阿斯达阿斯达现在");
        TimeLineItem date26 = new TimeLineItem("20140723", "代购费多少自行车");
        TimeLineItem date27 = new TimeLineItem("20140721", "多撒阿萨德时打算");
        TimeLineItem date28 = new TimeLineItem("20140716", "爱上大声地啊地");
        TimeLineItem date29 = new TimeLineItem("20140712", "阿斯蒂芬当我师傅");
        TimeLineItem date30 = new TimeLineItem("20140710", "撒大声大声道");
        items.add(date1);
        items.add(date2);
        items.add(date3);
        items.add(date4);
        items.add(date5);
        items.add(date6);
        items.add(date7);
        items.add(date8);
        items.add(date9);
        items.add(date10);
        items.add(date11);
        items.add(date12);
        items.add(date13);
        items.add(date14);
        items.add(date15);
        items.add(date16);
        items.add(date17);
        items.add(date18);
        items.add(date19);
        items.add(date20);
        items.add(date21);
        items.add(date22);
        items.add(date23);
        items.add(date24);
        items.add(date25);
        items.add(date26);
        items.add(date27);
        items.add(date28);
        items.add(date29);
        items.add(date30);
    }

    class DateComparator implements Comparator<TimeLineItem> {

        @Override
        public int compare(TimeLineItem lhs, TimeLineItem rhs) {
            return rhs.getDate().compareTo(lhs.getDate());
        }
    }
}
