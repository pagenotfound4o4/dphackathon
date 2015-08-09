package org.emnets.dianping.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.emnets.dianping.R;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kea on 15-8-10.
 */
public class DateAdapter extends BaseAdapter {
    private Context mContext;
    private List<TimeLineItem> mItems;

    public DateAdapter(Context context, List<TimeLineItem> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        if (mItems == null) {
            return 0;
        } else {
            return mItems.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (mItems == null) {
            return  null;
        } else {
            return mItems.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.time_line_item, parent, false);
            holder.date = (TextView) convertView
                    .findViewById(R.id.txt_date_time);
            holder.content = (TextView) convertView
                    .findViewById(R.id.txt_date_content);
            holder.line = (View) convertView.findViewById(R.id.v_line);
            holder.title = (RelativeLayout) convertView
                    .findViewById(R.id.rl_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();

        if (position == 0) {
            holder.title.setVisibility(View.VISIBLE);
            holder.date.setText(mItems.get(position).getDate());
            params.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
            params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_content);
        } else { // 不是第一条数据
            // 本条数据和上一条数据的时间戳相同，时间标题不显示
            if (mItems.get(position).getDate()
                    .equals(mItems.get(position - 1).getDate())) {
                holder.title.setVisibility(View.GONE);
                params.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_content);
                params.addRule(RelativeLayout.ALIGN_BOTTOM,
                        R.id.txt_date_content);
            } else {
                //本条数据和上一条的数据的时间戳不同的时候，显示数据
                holder.title.setVisibility(View.VISIBLE);
                holder.date.setText(mItems.get(position).getDate());
                params.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                params.addRule(RelativeLayout.ALIGN_BOTTOM,
                        R.id.txt_date_content);
            }
        }
        holder.line.setLayoutParams(params);
        holder.content.setText(mItems.get(position).getText());
        return convertView;
    }

    public static class ViewHolder {
        RelativeLayout title;
        View line;
        TextView date;
        TextView content;
    }
}
