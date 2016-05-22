package com.imooc.myhttp_02Webview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by SUN on 2016/5/22.
 */
public class HttpThread1 extends Thread {
    String url;
    String name;
    String age;

    public HttpThread1(String url, String name, String age){
        this.url = url;
        this.name = name;
        this.age = age;

    }

    public void doGet(){
        url = url + "?name="+name+"&age="+age;
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

            System.out.println("result: "+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void doPost(){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");

            OutputStream out = conn.getOutputStream();
            String content= "name="+name+"&age="+age;
            out.write(content.getBytes());

            final StringBuffer sb = new StringBuffer();
            String str;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((str = br.readLine())!= null){
                sb.append(str);
            }

            System.out.println("result: "+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
//        doGet();
        doPost();
    }
}
