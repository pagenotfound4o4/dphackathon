package org.emnets.dianping.model;

import android.location.Address;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by kea on 15-8-10.
 */
public class TimeLineItem {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private String mDate;
    private String mAddress;
    private String mName;
    public TimeLineItem() {}

    public TimeLineItem(Long date, String address, String name) {
        mDate = sDateFormatter.format(date);
        mAddress = address;
        mName = name;
    }

    public String getDate() {
        return mDate;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getName() { return mName; }
}
