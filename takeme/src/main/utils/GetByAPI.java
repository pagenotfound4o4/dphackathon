package main.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import main.model.Favorite;
import main.model.Timeline;


public class GetByAPI {
	
	public static String getBusinesses(List<Timeline> lst) {
		StringBuilder sb = new StringBuilder();
		
		for (Timeline fav : lst) {
			sb.append(fav.getBid());
			sb.append(",");
		}
		return getBusinesses(sb);
	}
	public static String getBusinesses(StringBuilder sb) {
		if (!sb.equals("")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		
		String apiUrl = "http://api.dianping.com/v1/business/get_batch_businesses_by_id";
        String appKey = "5589931241";
        String secret = "16adbf199c38458f847f4c99d25cab4d";
        
        Map<String, String> paramMap = new HashMap<String, String>();
        
        paramMap.put("business_ids", sb.toString());
        paramMap.put("platform", "2");
        
        String requestResult = DemoApiTool.requestApi(apiUrl, appKey, secret, paramMap);
        
        //System.out.println(requestResult);
		
		return requestResult;
	}
	
	
}
