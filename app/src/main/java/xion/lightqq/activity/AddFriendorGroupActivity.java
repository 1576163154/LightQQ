package xion.lightqq.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/25.
 */

public class AddFriendorGroupActivity extends AppCompatActivity {
    private SearchView sv_addForG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addforg_layout);
        initWidget();
        setWidget();
    }
    private void initWidget() {
        sv_addForG = (SearchView) findViewById(R.id.sv_addForG);
    }

    private void setWidget() {
        sv_addForG.onActionViewExpanded();//设置searchview 默认展开
    }
}
