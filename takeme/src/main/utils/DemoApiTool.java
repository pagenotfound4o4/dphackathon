package main.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java�汾ʾ�����룬ʹ�ü�{@link DemoApiToolTest.java}
 * 
 */
public class DemoApiTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApiTool.class);

    public static String sign(String appKey, String secret, Map<String, String> paramMap)
    {
        // �Բ����������ֵ�����
        String[] keyArray = paramMap.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);

        // ƴ������Ĳ�����-ֵ��
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appKey);
        for (String key : keyArray)
        {
            stringBuilder.append(key).append(paramMap.get(key));
        }

        stringBuilder.append(secret);
        String codes = stringBuilder.toString();

        // SHA-1���룬 ����ʹ�õ���Apache
        // codec�����ɻ��ǩ��(shaHex()�����Ƚ�����ת��ΪUTF8����Ȼ�����sha1���㣬ʹ�������Ĺ��߰���ע��UTF8����ת��)
        /*
         * ����sha1ǩ������Ч����ͬ byte[] sha =
         * org.apache.commons.codec.digest.DigestUtils
         * .sha(org.apache.commons.codec
         * .binary.StringUtils.getBytesUtf8(codes)); String sign =
         * org.apache.commons
         * .codec.binary.Hex.encodeHexString(sha).toUpperCase();
         */
        String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();

        return sign;
    }

    public static String getQueryString(String appKey, String secret, Map<String, String> paramMap)
    {
        String sign = sign(appKey, secret, paramMap);

        // ���ǩ��
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("appkey=").append(appKey).append("&sign=").append(sign);
        for (Entry<String, String> entry : paramMap.entrySet())
        {
            stringBuilder.append('&').append(entry.getKey()).append('=').append(entry.getValue());
        }
        String queryString = stringBuilder.toString();
        return queryString;
    }

    public static String requestApi(String apiUrl, String appKey, String secret, Map<String, String> paramMap)
    {
        String queryString = getQueryString(appKey, secret, paramMap);

        StringBuffer response = new StringBuffer();
        HttpClientParams httpConnectionParams = new HttpClientParams();
        httpConnectionParams.setConnectionManagerTimeout(1000);
        HttpClient client = new HttpClient(httpConnectionParams);
        HttpMethod method = new GetMethod(apiUrl);

        try
        {
            if (StringUtils.isNotBlank(queryString))
            {
                // Encode query string with UTF-8
                String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8");
                LOGGER.debug("Encoded Query:" + encodeQuery);
                method.setQueryString(encodeQuery);
            }

            client.executeMethod(method);
            BufferedReader reader = new BufferedReader(
                                                       new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                response.append(line).append(System.getProperty("line.separator"));
            }
            reader.close();
        }
        catch (URIException e)
        {
            LOGGER.error("Can not encode query: " + queryString + " with charset UTF-8. ", e);
        }
        catch (IOException e)
        {
            LOGGER.error("Request URL: " + apiUrl + " failed. ", e);
        }
        finally
        {
            method.releaseConnection();
        }
        return response.toString();

    }
    
    public static String requestPostApi(String apiUrl, String appKey, String secret, Map<String, String> paramMap)
    {
        StringBuffer response = new StringBuffer();
        HttpClientParams httpConnectionParams = new HttpClientParams();
        httpConnectionParams.setConnectionManagerTimeout(1000);
        HttpClient client = new HttpClient(httpConnectionParams);
        PostMethod method = new PostMethod(apiUrl);
        
        try
        {
            String sign = sign(appKey, secret, paramMap);
            paramMap.put("sign", sign);
            paramMap.put("appkey", appKey);
            // ����HTTP Post����
            for (Map.Entry<String, String> entry : paramMap.entrySet())
            {
                method.addParameter(entry.getKey(), entry.getValue());
            }
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            client.executeMethod(method);
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                response.append(line).append(System.getProperty("line.separator"));
            }
            reader.close();
        }
        catch (IOException e)
        {
            LOGGER.error("Request URL: " + apiUrl + " failed. ", e);
        }
        finally
        {
            method.releaseConnection();
        }
        return response.toString();
        
    }
}