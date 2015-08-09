package org.emnets.dianping.model;

import org.emnets.dianping.ui.TimeLineTest;

import java.sql.Time;

/**
 * Created by kea on 15-8-10.
 */
public class TimeLineItem {
    private String mDate;
    private String mText;
    public TimeLineItem() {}

    public TimeLineItem(String date, String text) {
        mDate = date;
        mText = text;
    }

    public String getDate() {
        return mDate;
    }

    public String getText() {
        return mText;
    }
}
