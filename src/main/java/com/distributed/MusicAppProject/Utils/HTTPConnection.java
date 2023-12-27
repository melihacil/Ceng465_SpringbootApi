package com.distributed.MusicAppProject.Utils;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class HTTPConnection implements  IHTTPConnection{
    private static final String USER_AGENT = "Mozilla/5.0";

    @Override
    public String getConnect(String url) throws IOException {

        CloseableHttpClient client = HttpClients
                .custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", "application/json");
        BufferedReader rd;
        CloseableHttpResponse response = client.execute(request);

        rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {

            result.append(line);
        }
        client.close();
        response.close();


        return result.toString();

    }
}
