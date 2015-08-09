package org.emnets.dianping.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.emnets.dianping.R;
import org.emnets.dianping.model.DateAdapter;
import org.emnets.dianping.model.TimeLineItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimelineFragment extends Fragment {
    private ListView listView;
    private List<TimeLineItem> items;
    private DateAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);
        listView = (ListView)view.findViewById(R.id.time_line_list_view);

        if (items == null) {
            items = getData();
        }

        DateComparator comparator = new DateComparator();
        Collections.sort(items, comparator);

        adapter = new DateAdapter(getActivity(), items);
        listView.setAdapter(adapter);
        return view;
    }

    private List<TimeLineItem> getData() {
        List<TimeLineItem> list = new ArrayList<>();
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

        list.add(date1);
        list.add(date2);
        list.add(date3);
        list.add(date4);
        list.add(date5);
        list.add(date6);
        list.add(date7);
        list.add(date8);
        list.add(date9);
        list.add(date10);
        list.add(date11);

        return list;
    }

    class DateComparator implements Comparator<TimeLineItem> {

        @Override
        public int compare(TimeLineItem lhs, TimeLineItem rhs) {
            return rhs.getDate().compareTo(lhs.getDate());
        }
    }

}
