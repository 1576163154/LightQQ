package xion.lightqq.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xion.lightqq.R;
import xion.lightqq.adapter.GroupListAdapter;
import xion.lightqq.widget.ClearEditText;

/**
 * Created by Administrator on 2016/11/1.
 */

public class GroupManageActivity extends Activity {
    private ListView lv_groupmanage_grouplist;
    private LinearLayout ll_addgroup;//添加好友列表
    private GroupListAdapter adapter;
    private List<String> receiveList;
    private TextView tv_groupmanage_finish;//完成按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupmanage);
        initWidget();
        initData();
        setWidget();

    }

    private void initWidget() {
        lv_groupmanage_grouplist = (ListView) findViewById(R.id.lv_groupmanage_grouplist);
        ll_addgroup = (LinearLayout) findViewById(R.id.ll_addgroup);
        tv_groupmanage_finish = (TextView) findViewById(R.id.tv_groupmanage_finish);
    }

    private void initData() {
        receiveList = getIntent().getStringArrayListExtra("friendListName");
        receiveList.remove(0);//在这里将 我的设备 删除，以便不让 其显示出来
    }

    private void setWidget() {
        adapter = new GroupListAdapter(receiveList, GroupManageActivity.this);
        lv_groupmanage_grouplist.setAdapter(adapter);
        ll_addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog addDialog = new AlertDialog.Builder(GroupManageActivity.this).create();
                addDialog.show();//dialog 先show再加载相关布局
                Window window = addDialog.getWindow();
                window.setContentView(R.layout.groupmanage_add_dialog);
                //清除之前还需获取alertdialog的窗口
                addDialog.getWindow().
                        clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                //清除alertDialog默认的flag（不显示软键盘）
                Button btn_grouopmanage_add_ok;
                Button btn_grouopmanage_add_cancel;
                final ClearEditText cet_groupmanage_add;
                btn_grouopmanage_add_cancel = (Button) window.findViewById(R.id.btn_grouopmanage_add_cancel);
                btn_grouopmanage_add_ok = (Button) window.findViewById(R.id.btn_grouopmanage_add_ok);
                cet_groupmanage_add = (ClearEditText) window.findViewById(R.id.cet_groupmanage_add);
                btn_grouopmanage_add_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDialog.dismiss();
                    }
                });
                btn_grouopmanage_add_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cet_groupmanage_add.getText().toString().equals("")) {
                            receiveList.add("未知分组");
                        } else {
                            receiveList.add(cet_groupmanage_add.getText().toString());
                        }
                        adapter.notifyDataSetChanged();
                        addDialog.dismiss();
                    }
                });
            }
        });

        tv_groupmanage_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("modifyFriendListName", (ArrayList<String>) receiveList);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }


}
