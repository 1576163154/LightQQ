package xion.lightqq.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class LoginActivity extends Activity {
    private TextView tv_newuser;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initWidget();
        setWidget();
    }
    private void initWidget() {
        tv_newuser = (TextView) findViewById(R.id.tv_newuser);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    private void setWidget() {
        tv_newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }

}
