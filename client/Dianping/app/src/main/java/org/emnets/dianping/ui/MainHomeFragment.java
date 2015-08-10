package org.emnets.dianping.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
import org.emnets.dianping.network.TimelineBuyAsyncTask;
import org.emnets.dianping.network.TimelineConfirmAsyncTask;
import org.emnets.dianping.service.ConfirmService;
import org.emnets.dianping.service.InviteService;
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
    private ConfirmReciever confirmReciever;
    private InviteReciever inviteReciever;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_home_fragment, container, false);

        initialSwitchButton(view);
        initialzeFilterMenu(view);
        imageUtil = new ImageUtil(getActivity());

        refresh_layout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeResources(android.R.color.holo_orange_light);

        initialListView(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startrefresh(uid);
        Intent inviteIntent = new Intent(getActivity(), InviteService.class);
        inviteIntent.putExtra("uid", uid);
        Log.i("dp", "start invite service=" + inviteIntent);
        getActivity().startService(inviteIntent);

        inviteReciever = new InviteReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction("org.emnets.dianping.INVITE");
        getActivity().registerReceiver(inviteReciever, filter);
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
                try {
                    if (RowIndex == 0) {
                        startrefresh(uid);
                    } else if (ColumnIndex == 0) {
                    } else if (ColumnIndex == 1) {
                        filterByPrice(uid, (RowIndex - 1) * 100, RowIndex * 100);
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialListView(View view) {
        main_list = (ListView)view.findViewById(R.id.main_list);
        data = new ArrayList<>();
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item, imageUtil, data);
        main_list.setAdapter(adapter);
        main_list.setEmptyView(view.findViewById(R.id.empty_img));
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                final Business item = (Business) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.popup_theme));
                builder.setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_content);
                builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("dp", "dialog ok " + item.getName());
                        // send bid to server
                        new TimelineBuyAsyncTask().execute(uid, item.getBid());
                        Log.i("dp", "1111111");

                        // update listview
                        ((Business) parent.getItemAtPosition(position)).setBstate(1);
                        adapter.notifyDataSetChanged();
                        Log.i("dp", "222222");

                        // register broadcast
                        confirmReciever = new ConfirmReciever();
                        IntentFilter filter = new IntentFilter();
                        filter.addAction("org.emnets.dianping.CONFIRM");
                        getActivity().registerReceiver(confirmReciever, filter);
                        Log.i("dp", "3333333");

                        // start pull service
                        if ("friend".equals(state)) {
                            Intent intent = new Intent(getActivity(), ConfirmService.class);
                            Log.i("dp", "uid=" + uid);
                            intent.putExtra("uid", uid);
                            Log.i("dp", "bid=" + item.getBid());
                            intent.putExtra("bid", item.getBid());
                            getActivity().startService(intent);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        main_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition = (main_list == null || main_list.getChildCount() == 0) ? 0
                        : main_list.getChildAt(0).getTop();
                refresh_layout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });
    }

    @Override
    public void onRefresh() {
        try {
            new GetAllFavouriteAsyncTask(this, uid, state).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            new GetAllFavouriteAsyncTask(this, uid, state).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterByPrice(String uid, int minv, int maxv) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        try {
            new GetFilterdPriceFavouriteAsyncTask(this, uid, state).execute(minv, maxv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortedByTime(String order) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        try {
            new GetSortedTimeFavouriteAsyncTask(this, uid, state).execute(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortedByPrice(String order) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        try {
            new GetSortedPriceFavouriteAsyncTask(this, uid, state).execute(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortedByLocation(double longitude, double latitude) {
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        try {
            new GetSortedLocationFavouriteAsyncTask(this, uid, state).execute(longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ConfirmReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String bid = intent.getStringExtra("bid");
            Log.i("dp", "recieved " + bid);
            for (Business item : data) {
                if (item.getBid().equals(bid)) {
                    item.setBstate(2);
                    break;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    class InviteReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String bid = intent.getStringExtra("bid");
            Log.i("dp", "recieved invite " + bid);
            handleInviteEvent(bid);
        }
    }

    void handleInviteEvent(final String bid) {
        if (!"mine".equals(state)) {
            state = "mine";
            startrefresh(uid);
        }
        for (Business item : data) {
            if (item.getBid().equals(bid)) {
                item.setBstate(1);
                adapter.notifyDataSetChanged();
                break;
            }
        }

        // create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),
                R.style.popup_theme));
        builder.setTitle(R.string.confirm_dialog_title)
                .setMessage(R.string.confirm_dialog_content);
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notifyServer(uid, bid);
                for (Business item : data) {
                    if (item.getBid().equals(bid)) {
                        item.setBstate(2);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void notifyServer(String uid, String bid) {
        new TimelineConfirmAsyncTask().execute(uid, bid);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().unregisterReceiver(confirmReciever);
        getActivity().unregisterReceiver(inviteReciever);
    }
}
