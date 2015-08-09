package org.emnets.dianping.ui;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.emnets.dianping.R;
import org.emnets.dianping.adapter.FavouriteListAdapter;
import org.emnets.dianping.model.Business;
import org.emnets.dianping.network.GetMyFavouriteAsyncTask;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_home_fragment, container, false);

        initialSwitchButton(view);
        imageUtil = new ImageUtil(getActivity());

        refresh_layout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeResources(android.R.color.holo_orange_light);

        main_list = (ListView)view.findViewById(R.id.main_list);
        data = new ArrayList<>();
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item, imageUtil, data);
        main_list.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh_layout.post(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(true);
            }
        });
        new GetMyFavouriteAsyncTask(this).execute(uid);
    }

    private void initialSwitchButton(View view) {
        btn_me = (Button)view.findViewById(R.id.btn_me);
        btn_friend = (Button)view.findViewById(R.id.btn_friend);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = "mine";
                btn_me.setBackgroundResource(R.drawable.zeus_tab_left_press);
                btn_me.setTextColor(getResources().getColor(R.color.btn_pressed_color_light));
                btn_friend.setBackgroundResource(R.drawable.zeus_tab_right_normal);
                btn_friend.setTextColor(getResources().getColor(R.color.btn_default_color_light));
            }
        });
        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = "friend";
                btn_me.setBackgroundResource(R.drawable.zeus_tab_left_normal);
                btn_me.setTextColor(getResources().getColor(R.color.btn_default_color_light));
                btn_friend.setBackgroundResource(R.drawable.zeus_tab_right_press);
                btn_friend.setTextColor(getResources().getColor(R.color.btn_pressed_color_light));
            }
        });
    }

    @Override
    public void onRefresh() {
        new GetMyFavouriteAsyncTask(this).execute(uid);
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return this.refresh_layout;
    }

    public void updateData(List<Business> ndata) {
        this.data = ndata;
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item, imageUtil, data);
        main_list.setAdapter(adapter);
    }
}
