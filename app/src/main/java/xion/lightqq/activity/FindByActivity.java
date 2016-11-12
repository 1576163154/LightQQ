package xion.lightqq.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/26.
 */

public class FindByActivity extends Activity {
    private SearchView sv_nickname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findby_layout);
        initWidget();
        setWidget();
    }
    private void initWidget() {
        sv_nickname = (SearchView) findViewById(R.id.sv_nicknameorkeyword);
    }

    private void setWidget() {
        if (sv_nickname == null){
            return;
        }else {
            int svImageId = sv_nickname.getContext().getResources().getIdentifier("android:id/search_button",null,null);
            ImageView searchButton = (ImageView) sv_nickname.findViewById(svImageId);
            searchButton.setImageResource(R.drawable.bluesearch);

        }
//        sv_nickname.onActionViewExpanded();
        sv_nickname.setIconified(false);
    }
}
