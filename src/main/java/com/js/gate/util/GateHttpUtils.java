package com.js.gate.util;

import com.js.gate.Constants.HttpConstants;
import com.js.gate.vo.HttpClientResult;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GateHttpUtils {
    private static String SECRET = "your secret";
    private static String KEY = "you key";

    private static String convertMap2PostData(Map<String,String> params){
        if(MapUtils.isEmpty(params)) return null;
        StringBuilder postData = new StringBuilder();
        for (String key : params.keySet()) {
            if(postData.length()>0) {
                postData.append("&");
            }
            postData.append(key).append("=").append(params.get(key));
        }
        return postData.toString();
    }

    public static HttpClientResult doRequest(String requestType, String url, Map< String, String > arguments )  throws HttpException, IOException {
        Mac mac;
        SecretKeySpec key;
        String postData = "";
        HttpClientResult httpClientResult = new HttpClientResult();
        try {
            // Create a new secret key
            key = new SecretKeySpec( SECRET.getBytes( "UTF-8" ), "HmacSHA512" );
            mac = Mac.getInstance( "HmacSHA512" );
            mac.init( key );
            if(MapUtils.isNotEmpty(arguments)) {
                postData = convertMap2PostData(arguments);
            }
            Map<String,String> headers = new HashMap<>();
            headers.put("Key",KEY);
            headers.put("Sign",Hex.encodeHexString( mac.doFinal( postData.getBytes( "UTF-8" ))));
            if(requestType.equals(HttpConstants.GET)) {
               httpClientResult = HttpClientUtils.doGet(url,headers,arguments);
            } else {
               httpClientResult = HttpClientUtils.doPost(url,headers,arguments);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return httpClientResult;
    }

}
