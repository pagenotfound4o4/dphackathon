package org.emnets.dianping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.emnets.dianping.R;
import org.emnets.dianping.model.Business;

import java.util.List;

public class FavouriteListAdapter extends BaseAdapter {
    private Context context;
    private int resId;
    private List<Business> data;

    public FavouriteListAdapter(Context context, int resId, List<Business> data) {
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resId, null);
            holder.title = (TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.title.setText(data.get(position).getBid());
        return convertView;
    }

    final class ViewHolder {
        TextView title;
    }
}
