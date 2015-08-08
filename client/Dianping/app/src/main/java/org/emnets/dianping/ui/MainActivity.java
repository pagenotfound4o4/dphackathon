package org.emnets.dianping.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.emnets.dianping.R;
import org.emnets.dianping.model.Business;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Business> myFavouriteList;
    private List<Business> friendFavouriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setMyFavouriteList(List<Business> list) {
        this.myFavouriteList = list;
    }

    public void setFriendFavouriteList(List<Business> list) {
        this.friendFavouriteList = list;
    }
}
