package xion.lightqq.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xion.lightqq.R;
import xion.lightqq.adapter.MessageListAdapter;
import xion.lightqq.entity.MessageInfo;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MessageFragment extends Fragment {
    private List<MessageInfo> messageList;
    private ListView lv_message;
    private MessageListAdapter adapter;
    private SwipeRefreshLayout srl_fragmessage;
    private String str;//存储所获取子项的名字

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.fragment_message,null);
        lv_message = (ListView) messageView.findViewById(R.id.lv_message);
        srl_fragmessage = (SwipeRefreshLayout) messageView.findViewById(R.id.srl_fragmessage);
        messageList = new ArrayList<MessageInfo>();
        initData();
        setListView();
        setSwipeRefresh();
        return messageView;
    }
    private void initData() {
        //为消息页面添加12条信息
        for (int i = 0; i < 12; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setName("小明"+i);
            messageInfo.setTime("2016-10-11");
            messageInfo.setRecentMessage("你们已经是好友了，现在开始聊天吧");
            messageInfo.setPortraitId(R.drawable.sherlock);
            messageList.add(messageInfo);
        }
        adapter = new MessageListAdapter(messageList);
        lv_message.setAdapter(adapter);
    }
    private void setListView() {
        lv_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageInfo messageInfo = messageList.get(position);//获取所点击的子项的位置
                str = messageInfo.getName();
            }
        });
        lv_message.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                MessageInfo messageInfo =messageList.get(position);
                str = messageInfo.getName();
                final AlertDialog messageDialog = new AlertDialog.Builder(getActivity()).create();
                messageDialog.show();
                Window window = messageDialog.getWindow();
                window.setContentView(R.layout.messagedialog_layout);
                Button btn_message_top;
                Button btn_message_delete;
                TextView tv_message_dialog_title;
                tv_message_dialog_title = (TextView) window.findViewById(R.id.tv_message_dialog_title);
                tv_message_dialog_title.setText(str);
                btn_message_top = (Button) window.findViewById(R.id.btn_message_top);
                btn_message_delete = (Button) window.findViewById(R.id.btn_message_delete);
                btn_message_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        messageList.remove(position);
                        messageDialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }
    private void setSwipeRefresh() {
        srl_fragmessage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新操作
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setName("小黑");
                messageInfo.setTime("2016-10-21");
                messageInfo.setRecentMessage("你们已经是好友了，现在开始聊天吧");
                messageInfo.setPortraitId(R.drawable.portrait);
                messageList.add(messageInfo);

                //让刷新具有一定延迟
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        srl_fragmessage.setRefreshing(false);//停止动画
                        Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
                    }
                },3000);
            }
        });
        srl_fragmessage.setProgressViewOffset(false,50,100);//分别为是否进行 缩放，
    }
}
