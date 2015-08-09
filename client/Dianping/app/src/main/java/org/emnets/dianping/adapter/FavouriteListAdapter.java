package org.emnets.dianping.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.emnets.dianping.R;
import org.emnets.dianping.model.Business;
import org.emnets.dianping.util.ImageUtil;

import java.util.List;

public class FavouriteListAdapter extends BaseAdapter {
    private static final String IMG_BASE_URL = "http://i3.s3.dpfile.com";
    private Context context;
    private int resId;
    private List<Business> data;
    private ImageUtil util;

    public FavouriteListAdapter(Context context, int resId, ImageUtil util, List<Business> data) {
        this.context = context;
        this.resId = resId;
        this.data = data;
        this.util = util;
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
            holder.photo = (NetworkImageView)convertView.findViewById(R.id.item_img);
            holder.title = (TextView)convertView.findViewById(R.id.item_title);
            holder.price = (TextView)convertView.findViewById(R.id.item_price);
            holder.rating = (NetworkImageView)convertView.findViewById(R.id.item_rating);
            holder.location = (TextView)convertView.findViewById(R.id.item_location);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Business cur = data.get(position);
        holder.title.setText(cur.getName());
        holder.photo.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.photo.setErrorImageResId(R.mipmap.ic_launcher);
        holder.photo.setImageUrl(IMG_BASE_URL + cur.getS_photo_url(), util.getImageLoader());
        holder.rating.setDefaultImageResId(R.drawable.star0);
        holder.rating.setErrorImageResId(R.drawable.star0);
        holder.rating.setImageUrl(cur.getRating_s_img_url(), util.getImageLoader());
        holder.price.setText(String.format(context.getString(R.string.item_avg_price), cur.getAvg_price()));
        holder.location.setText(cur.getCity());
        return convertView;
    }

    final class ViewHolder {
        NetworkImageView photo;
        TextView title;
        NetworkImageView rating;
        TextView price;
        TextView location;
    }
}
