package org.emnets.dianping.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.emnets.dianping.R;
import org.emnets.dianping.adapter.FavouriteListAdapter;
import org.emnets.dianping.model.Business;
import org.emnets.dianping.network.GetMyFavouriteAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String URL = "http://10.128.53.109:8080/takeme/favo";
    private String uid = "1";

    private SwipeRefreshLayout refresh_layout;
    private ListView main_list;
    private FavouriteListAdapter adapter;
    private List<Business> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_home_fragment, container, false);

        refresh_layout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeResources(android.R.color.holo_orange_light);

        main_list = (ListView)view.findViewById(R.id.main_list);
        data = new ArrayList<>();
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item_test, data);
        main_list.setAdapter(adapter);

        return view;
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
        adapter = new FavouriteListAdapter(getActivity(), R.layout.list_item_test, data);
        main_list.setAdapter(adapter);
    }
}
