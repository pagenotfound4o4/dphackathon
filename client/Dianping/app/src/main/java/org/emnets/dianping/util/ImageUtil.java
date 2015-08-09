package org.emnets.dianping.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ImageUtil {
    private RequestQueue mQueue;
    private LruImageCache mCache;
    private ImageLoader loader;

    public ImageUtil(Context context) {
        mQueue = Volley.newRequestQueue(context);
        mCache = LruImageCache.getInstance();
        loader = new ImageLoader(mQueue, mCache);
    }

    public ImageLoader getImageLoader() {
        return this.loader;
    }
}
