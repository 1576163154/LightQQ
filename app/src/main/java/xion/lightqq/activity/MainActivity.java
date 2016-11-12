package xion.lightqq.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import xion.lightqq.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_message, tv_contact, tv_me;//主页菜单 消息，联系人，我
    private View messageline, contactline, meline;
    private ImageView iv_add;//标题栏添加按钮
    private SearchView sv_main;//标题栏搜索框

    //这3者统一用来v4.app包下的frament
    private Fragment[] fragments;//fragment数组
    private FragmentManager fragmentManager;//fragment管理对象
    private FragmentTransaction fragmentTransaction;//fragment事务操作对象

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv_main = (SearchView) findViewById(R.id.sv_main);
        initWidget();
        setWidget();
        initFragment();
    }

    private void initWidget() {
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_me = (TextView) findViewById(R.id.tv_me);
        //各个菜单下面的线条
        messageline = findViewById(R.id.messageline);
        contactline = findViewById(R.id.contactline);
        meline = findViewById(R.id.meline);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        sv_main = (SearchView) findViewById(R.id.sv_main);
    }

    private void setWidget() {
        tv_contact.setOnClickListener(this);
        tv_message.setOnClickListener(this);
        tv_me.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        if (sv_main != null){
            int svImageId = sv_main.getContext().getResources().getIdentifier("android:id/search_mag_icon",null,null);
            ImageView searchButton = (ImageView) sv_main.findViewById(svImageId);
            searchButton.setImageResource(R.drawable.search);
        }
//        sv_main.setIconifiedByDefault(false);
        sv_main.setIconified(true);
    }
    private void initFragment() {
        fragments = new Fragment[3];//分别代表 消息，联系人，我
        fragmentManager = getSupportFragmentManager();
        //通过fragmentManager找到对应的fragment
        fragments[0] = fragmentManager.findFragmentById(R.id.frag_message);
        fragments[1] = fragmentManager.findFragmentById(R.id.frag_contact);
        fragments[2] = fragmentManager.findFragmentById(R.id.frag_me);
        fragmentTransaction = fragmentManager.beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
        fragmentTransaction.show(fragments[0]).commit();//通过fragmentTransaction来控制fragment的显示和隐藏
    }
    @Override
    public void onClick(View v) {
        //默认不显示
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
        switch (v.getId()) {
            case R.id.tv_message:
                fragmentTransaction.show(fragments[0]).commit();
                tv_message.setTextColor(Color.parseColor("#09bef0"));
                tv_contact.setTextColor(Color.parseColor("#121213"));
                tv_me.setTextColor(Color.parseColor("#121213"));
                messageline.setBackgroundColor(Color.parseColor("#2e9ccf"));
                contactline.setBackgroundColor(Color.parseColor("#ffffff"));
                meline.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.tv_contact:
                fragmentTransaction.show(fragments[1]).commit();
                tv_message.setTextColor(Color.parseColor("#121213"));
                tv_contact.setTextColor(Color.parseColor("#09bef0"));
                tv_me.setTextColor(Color.parseColor("#121213"));
                messageline.setBackgroundColor(Color.parseColor("#ffffff"));
                contactline.setBackgroundColor(Color.parseColor("#2e9ccf"));
                meline.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.tv_me:
                fragmentTransaction.show(fragments[2]).commit();
                tv_message.setTextColor(Color.parseColor("#121213"));
                tv_contact.setTextColor(Color.parseColor("#121213"));
                tv_me.setTextColor(Color.parseColor("#09bef0"));
                messageline.setBackgroundColor(Color.parseColor("#ffffff"));
                contactline.setBackgroundColor(Color.parseColor("#ffffff"));
                meline.setBackgroundColor(Color.parseColor("#2e9ccf"));
                break;
            case R.id.iv_add:
                showPopupMenu(iv_add);
                break;
            default:
                break;
        }
    }

    private void showPopupMenu(ImageView iv_add) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, iv_add);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.mainmenu, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = (String) item.getTitle();
                switch (title) {
                    case "多人聊天":
                        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case "添加":
                        startActivity(new Intent(MainActivity.this,AddActivity.class));
                        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case "扫一扫":
                        startActivity(new Intent(MainActivity.this,QRScanActivity.class));
                        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case "面对面":
                        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


        popupMenu.show();
    }
}
