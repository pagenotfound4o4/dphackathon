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
public class TimeLineActivity extends Activity {
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
        TimeLineItem date1 = new TimeLineItem(1439144299000l, "徐汇区中山西路2006号", "拉亚汉堡经典餐厅");
        TimeLineItem date2 = new TimeLineItem(1439146361000l, "杨浦区淞沪路303号(创智天地三期)2楼", "胡姬•兰");
        TimeLineItem date3 = new TimeLineItem(1439146361000l, "杨浦区伟德路8号", "AGOGO Kitchen & Bar");
        TimeLineItem date4 = new TimeLineItem(1439319161000l, "浦东新区陆家嘴环路1288号上海凯宾斯基大酒店1楼", "元素西餐厅");
        TimeLineItem date5 = new TimeLineItem(1439319161000l, "徐汇区肇嘉浜路1000号汇金百货5楼", "香草山西餐厅");
        TimeLineItem date6 = new TimeLineItem(1439405561000l, "卢湾区徐家汇路618号日月光中心广场2楼", "又见鱼");
        TimeLineItem date7 = new TimeLineItem(1439491961000l, "长宁区长宁路1018号5楼5087长宁龙之梦", "锦逸");
        TimeLineItem date8 = new TimeLineItem(1439491961000l, "闵行区闵行区虹桥镇银亭路66号5楼", "野外烧烤");
        TimeLineItem date9 = new TimeLineItem(1439578361000l, "卢湾区徐家汇路618号日月光广场4楼4楼TK06室", "宋记香辣蟹");
        TimeLineItem date10 = new TimeLineItem(1439578361000l, "静安区陕西北路66号科恩国际中心1楼免费停车", "HARU KITCHEN本店");
        TimeLineItem date11 = new TimeLineItem(1439664761000l, "徐汇区南丹东路207号", "蓝翠坊LaTree");

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
    }

    class DateComparator implements Comparator<TimeLineItem> {

        @Override
        public int compare(TimeLineItem lhs, TimeLineItem rhs) {
            return rhs.getDate().compareTo(lhs.getDate());
        }
    }
}
