package com.js.gate;

import com.js.gate.Constants.HttpConstants;
import com.js.gate.util.GateHttpUtils;
import com.js.gate.util.PropertiesUtils;
import com.js.gate.vo.HttpClientResult;
import org.apache.http.HttpException;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Test111 {
    @Test
    public void test222() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String SECRET = PropertiesUtils.getProperty("SECRET");
        String KEY = PropertiesUtils.getProperty("KEY");
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes("UTF-8"), "HmacSHA512");
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(key);
    }

    @Test
    public void test123() {
        String url = "https://data.gateio.co/api2/1/tickers";
        try {
            HttpClientResult httpClientResult = GateHttpUtils.doRequest(HttpConstants.GET,url,null);
            System.out.println(httpClientResult.getContent());
        } catch (HttpException | IOException e) {
            e.printStackTrace();
        }
    }
}
