package org.emnets.dianping.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.TextView;

import org.emnets.dianping.R;
import org.emnets.dianping.model.Business;

import java.util.List;

public class MainActivity extends FragmentActivity {
    private static final String TAB_TAG_FIND = "find";
    private static final String TAB_TAG_HOME = "home";
    private static final String TAB_TAG_MINE = "mine";
    private static final String TAB_TAG_TIME = "time";

    private FragmentTabHost mTabHost;

    private List<Business> myFavouriteList;
    private List<Business> friendFavouriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeTabHost();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initializeTabHost() {
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setBackground(ResourcesCompat.getDrawable(getResources(),
                R.drawable.tab_bar_bg, null));

        View home_indicator = getIndicator(getString(R.string.tab_home),
                R.layout.tab_indicator_home);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_HOME).setIndicator(home_indicator),
                MainHomeFragment.class, null);
        View tuan_indicator = getIndicator(getString(R.string.tab_time),
                R.layout.tab_indicator_tuan);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_TIME).setIndicator(tuan_indicator),
                TimelineFragment.class, null);
//        View find_indicator = getIndicator(getString(R.string.tab_find),
//                R.layout.tab_indicator_search);
//        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_FIND).setIndicator(find_indicator),
//                MainFindFragment.class, null);
//        View user_indicator = getIndicator(getString(R.string.tab_mine),
//                R.layout.tab_indicator_my);
//        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_MINE).setIndicator(user_indicator),
//                UserFragment.class, null);
    }

    private View getIndicator(String name, int layoutId) {
        View v = getLayoutInflater().inflate(layoutId, null);
        TextView tv = (TextView)v.findViewById(android.R.id.title);
        tv.setText(name);
        return v;
    }

    public void setMyFavouriteList(List<Business> list) {
        this.myFavouriteList = list;
    }

    public void setFriendFavouriteList(List<Business> list) {
        this.friendFavouriteList = list;
    }
}
