package xion.lightqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/25.
 */

public class ScanResultActivity extends AppCompatActivity {
    private TextView tv_codecontent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanresult_layout);
        initWidget();
        setData();
    }
    private void initWidget() {
        tv_codecontent = (TextView) findViewById(R.id.tv_codecontent);
    }
    private void setData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = bundle.getString("result");
        tv_codecontent.setText(str);
    }
}
