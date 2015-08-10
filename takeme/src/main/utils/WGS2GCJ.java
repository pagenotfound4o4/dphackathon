package main.utils;


public class WGS2GCJ {
	static double ee = 0.00669342162296594323;
	static double a = 6378245.0;
	private final static double PI = 3.14159265358979323; // Բ����
    private final static double R = 6371229; // ����İ뾶

    
	public static int getDistance(double lat1, double lng1, double lat2, double lng2) {
		double x, y, distance;
        x = (lng2 - lng1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        
		return (int)distance;
	}
	
    public static LatLng transformFromWGSToGCJ(LatLng wgLoc) {

            //����ڹ��⣬��Ĭ�ϲ�����ת��
            if (outOfChina(wgLoc.latitude, wgLoc.longitude)) {
                    return new LatLng(wgLoc.latitude, wgLoc.longitude);
            }
            double dLat = transformLat(wgLoc.longitude - 105.0,
                            wgLoc.latitude - 35.0);
            double dLon = transformLon(wgLoc.longitude - 105.0,
                            wgLoc.latitude - 35.0);
            double radLat = wgLoc.latitude / 180.0 * Math.PI;
            double magic = Math.sin(radLat);
            magic = 1 - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0)/ ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
            dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);

            return new LatLng(wgLoc.latitude + dLat, wgLoc.longitude + dLon);
    }

    public static double transformLat(double x, double y) {
            double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                            + 0.2 * Math.sqrt(x > 0 ? x : -x);
            ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x
                            * Math.PI)) * 2.0 / 3.0;
            ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0
                            * Math.PI)) * 2.0 / 3.0;
            ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y
                            * Math.PI / 30.0)) * 2.0 / 3.0;
            return ret;
    }

    public static double transformLon(double x, double y) {
            double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                            * Math.sqrt(x > 0 ? x : -x);
            ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x
                            * Math.PI)) * 2.0 / 3.0;
            ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0
                            * Math.PI)) * 2.0 / 3.0;
            ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x
                            / 30.0 * Math.PI)) * 2.0 / 3.0;
            return ret;
    }
    
    public static boolean outOfChina(double lat, double lon) {
            if (lon < 72.004 || lon > 137.8347)
                    return true;
            if (lat < 0.8293 || lat > 55.8271)
                    return true;
            return false;
    }
}
