package com.haobi.androidasynchttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Android-Async-Http的Get和Post请求的使用
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_get;
    private Button btn_post;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_get = (Button)findViewById(R.id.btn_get);
        btn_post = (Button)findViewById(R.id.btn_post);
        tv = (TextView)findViewById(R.id.tv);
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_get:
                Android_Async_Http_Get();
                break;
            case R.id.btn_post:
                Android_Async_Http_Post();
                break;
            default:
                break;
        }
    }

    //Get请求
    private void Android_Async_Http_Get(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=fef8795fcfa0a1977582d8c31b529112";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showResponse(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Get请求失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Post请求
    private void Android_Async_Http_Post(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://apis.juhe.cn/mobile/get?";
        RequestParams params = new RequestParams();
        params.put("phone", "18826593245");
        params.put("key", "fef8795fcfa0a1977582d8c31b529112");
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showResponse(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Post请求失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResponse(final String response){
        //Activity不允许在子线程中进行UI操作
        //通过该方法可以将线程切换到主线程，然后再更新UI元素
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(response);
            }
        });
    }
}
