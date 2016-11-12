package xion.lightqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/25.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_addForG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);
        initWidget();
        setWidget();
    }

    private void initWidget() {
        ll_addForG = (LinearLayout) findViewById(R.id.ll_addFofG);
    }
    private void setWidget(){
        ll_addForG.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_addFofG:
                startActivity(new Intent(AddActivity.this,AddFriendorGroupActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
