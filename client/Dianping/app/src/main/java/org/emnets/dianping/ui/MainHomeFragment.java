package org.emnets.dianping.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;

import org.emnets.dianping.R;
import org.emnets.dianping.adapter.FavouriteListAdapter;
import org.emnets.dianping.model.Business;
import org.emnets.dianping.network.GetAllFavouriteAsyncTask;
import org.emnets.dianping.network.GetFilterdPriceFavouriteAsyncTask;
import org.emnets.dianping.network.GetSortedLocationFavouriteAsyncTask;
import org.emnets.dianping.network.GetSortedPriceFavouriteAsyncTask;
import org.emnets.dianping.network.GetSortedTimeFavouriteAsyncTask;
import org.emnets.dianping.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private String uid = "1";
    private String state = "mine";

    private SwipeRefreshLayout refresh_layout;
    private ListView main_list;
    private FavouriteListAdapter adapter;
    private List<Business> data;
    private Button btn_me, btn_friend;
    private ImageUtil imageUtil;
    private DropDownMenu menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_home_fragment, container, false);

        initialSwitchButton(view);
        initialzeFilterMenu(view);
        imageUtil = new ImageUtil(getActivity());

        refresh_layout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeResources(android.R.color.holo_orange_light);

        main_list = (ListView)view.findViewById(R.id.main_list);
        data = new ArrayList<>();
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item, imageUtil, data);
        main_list.setAdapter(adapter);
        main_list.setEmptyView(view.findViewById(R.id.empty_img));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //startrefresh(uid);
    }

    private void initialSwitchButton(View view) {
        btn_me = (Button)view.findViewById(R.id.btn_me);
        btn_friend = (Button)view.findViewById(R.id.btn_friend);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_me.setBackgroundResource(R.drawable.zeus_tab_left_press);
                btn_me.setTextColor(getResources().getColor(R.color.btn_pressed_color_light));
                btn_friend.setBackgroundResource(R.drawable.zeus_tab_right_normal);
                btn_friend.setTextColor(getResources().getColor(R.color.btn_default_color_light));
                if (!"mine".equals(state)) {
                    state = "mine";
                    startrefresh(uid);
                }
            }
        });
        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_me.setBackgroundResource(R.drawable.zeus_tab_left_normal);
                btn_me.setTextColor(getResources().getColor(R.color.btn_default_color_light));
                btn_friend.setBackgroundResource(R.drawable.zeus_tab_right_press);
                btn_friend.setTextColor(getResources().getColor(R.color.btn_pressed_color_light));
                if (!"friend".equals(state)) {
                    state = "friend";
                    startrefresh(uid);
                }
            }
        });
    }

    private void initialzeFilterMenu(View view) {
        menu = (DropDownMenu)view.findViewById(R.id.filter_menu);
        menu.setMenuCount(3);//Menu的个数
        menu.setShowCount(6);//Menu展开list数量太多是只显示的个数
        menu.setShowCheck(true);//是否显示展开list的选中项
        menu.setMenuTitleTextSize(16);//Menu的文字大小
        menu.setMenuTitleTextColor(Color.BLACK);//Menu的文字颜色
        menu.setMenuListTextSize(16);//Menu展开list的文字大小
        menu.setMenuListTextColor(Color.BLACK);//Menu展开list的文字颜色
        menu.setMenuBackColor(Color.WHITE);//Menu的背景颜色
        menu.setMenuPressedBackColor(Color.WHITE);//Menu按下的背景颜色
        menu.setCheckIcon(R.drawable.ico_make);//Menu展开list的勾选图片
        menu.setUpArrow(R.drawable.arrow_up);//Menu默认状态的箭头
        menu.setDownArrow(R.drawable.arrow_down);//Menu按下状态的箭头

        // fill column content
        menu.setDefaultMenuTitle(getResources().getStringArray(R.array.filter_column_name));
        List<String[]> items = new ArrayList<>();
        items.add(getResources().getStringArray(R.array.dist_filter));
        items.add(getResources().getStringArray(R.array.price_filter));
        items.add(getResources().getStringArray(R.array.intelli_sort));
        menu.setMenuItems(items);

        menu.setIsDebug(false);

        menu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            //Menu展开的list点击事件  RowIndex：list的索引  ColumnIndex：menu的索引
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {
                Log.i("dp", String.format("row=%d,column=%d", RowIndex, ColumnIndex));
                if (RowIndex == 0) {
                    startrefresh(uid);
                } else if (ColumnIndex == 0) {
                } else if (ColumnIndex == 1) {
                    filterByPrice(uid, (RowIndex-1)*100, RowIndex*100);
                } else if (ColumnIndex == 2) {
                    if (RowIndex == 1) {
                        sortedByTime("asc");
                    } else if (RowIndex == 2) {
                        sortedByTime("desc");
                    } else if (RowIndex == 3) {
                        sortedByPrice("asc");
                    } else if (RowIndex == 4) {
                        sortedByPrice("desc");
                    } else if (RowIndex == 5) {
                        sortedByLocation(120.0, 30.0);
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        new GetAllFavouriteAsyncTask(this, uid, state).execute();
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return this.refresh_layout;
    }

    public void updateData(List<Business> ndata) {
        this.data = ndata;
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item, imageUtil, data);
        main_list.setAdapter(adapter);
    }

    public void startrefresh(String uid) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        new GetAllFavouriteAsyncTask(this, uid, state).execute();
    }

    public void filterByPrice(String uid, int minv, int maxv) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        new GetFilterdPriceFavouriteAsyncTask(this, uid, state).execute(minv, maxv);
    }

    public void sortedByTime(String order) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        new GetSortedTimeFavouriteAsyncTask(this, uid, state).execute(order);
    }

    public void sortedByPrice(String order) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        new GetSortedPriceFavouriteAsyncTask(this, uid, state).execute(order);
    }

    public void sortedByLocation(double longitude, double latitude) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        new GetSortedLocationFavouriteAsyncTask(this, uid, state).execute(longitude, latitude);
    }
}
