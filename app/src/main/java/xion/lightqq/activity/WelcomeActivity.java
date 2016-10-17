package xion.lightqq.activity;

import android.app.Activity;
import android.os.Bundle;

import xion.lightqq.R;
import xion.lightqq.widget.SmartImageView;

/**
 * Created by Administrator on 2016/10/13.
 */

public class WelcomeActivity extends Activity {
    private SmartImageView smartImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        smartImageView = (SmartImageView) findViewById(R.id.siv_welcome);
        smartImageView.setRatio(1.67f);
    }
}
