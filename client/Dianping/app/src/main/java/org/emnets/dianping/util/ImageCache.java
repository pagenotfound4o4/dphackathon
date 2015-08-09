package org.emnets.dianping.util;

import android.graphics.Bitmap;

public interface ImageCache {
    public Bitmap getBitmap(String url);
    public void putBitmap(String url, Bitmap bitmap);
}