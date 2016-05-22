package com.imooc.myhttp_02Webview;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SUN on 2016/5/22.
 */
public class HttpThread extends Thread {
    private String url;
    private WebView webView;
    private Handler handler;

    public HttpThread(String url, WebView webView, Handler handler){
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            final StringBuffer sb = new StringBuffer();
            String str;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((str = br.readLine())!= null){
                sb.append(str);
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadData(sb.toString(), "text/html;charset=utf-8", null);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
