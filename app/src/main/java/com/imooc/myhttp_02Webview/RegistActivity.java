package com.imooc.myhttp_02Webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SUN on 2016/5/22.
 */
public class RegistActivity extends Activity {
    EditText name;
    EditText age;
    Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        regist = (Button) findViewById(R.id.regist);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new HttpThread1("http://192.168.1.104:8080/web/MyServlet", name.getText().toString(), age.getText().toString()).start();
            }
        });
    }
}
