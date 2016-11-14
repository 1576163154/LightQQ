package xion.lightqq.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Arrays;

import xion.lightqq.R;
import xion.lightqq.widget.WheelView;

/**
 * Created by Administrator on 2016/10/26.
 */

public class FindByActivity extends Activity implements View.OnClickListener{
    private SearchView sv_nickname;
    //性别年龄wheelview所需原始数据
    private final String[] sex = {"男","女","不限"};
    private final String[] age = {"不限","18岁以下","18-22岁","23-26岁","27-35岁","35岁以上"};
    private RelativeLayout rl_findby_sex;
    private TextView tv_findby_sex;
    private RelativeLayout rl_findby_age;
    private TextView tv_findby_age;
    private AlertDialog sexDialog;//选择性别对话框

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findby_layout);
        initWidget();
        setWidget();
    }
    private void initWidget() {
        sv_nickname = (SearchView) findViewById(R.id.sv_nicknameorkeyword);
        rl_findby_sex = (RelativeLayout) findViewById(R.id.rl_findby_sex);
        rl_findby_age = (RelativeLayout) findViewById(R.id.rl_findby_age);
        tv_findby_age = (TextView) findViewById(R.id.tv_findby_age);
        tv_findby_sex = (TextView) findViewById(R.id.tv_findby_sex);
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

        rl_findby_sex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置 性别，年龄相关点击dialog和wheelview事件
            case R.id.rl_findby_sex:
                View WVL = LayoutInflater.from(this).inflate(R.layout.wheel_view,null);
                WheelView sexWV = (WheelView) WVL.findViewById(R.id.wv);
                sexWV.setOffset(1);//设置初始偏移量
                sexWV.setItems(Arrays.asList(sex));
                sexWV.setSeletion(1);//设置初始选中索引为1的选项
                sexWV.setOnWheelViewListener(new WheelView.OnWheelViewListener(){
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        tv_findby_sex.setText(item);
                    }
                });
                View dialogtitleView =LayoutInflater.from(this).inflate(R.layout.dialog_title,null);
                TextView tv_dialog_title = (TextView) dialogtitleView.findViewById(R.id.tv_dialog_title);
                tv_dialog_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sexDialog.dismiss();

                    }
                });
                sexDialog = new AlertDialog.Builder(this)
                        .setCustomTitle(dialogtitleView)
                        .setView(WVL)
                        .show();
                break;
            case R.id.rl_findby_age:
        }
    }
}
