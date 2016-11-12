package xion.lightqq.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xion.lightqq.R;
import xion.lightqq.entity.MessageInfo;
import xion.lightqq.widget.CirclePortraitView;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MessageListAdapter extends BaseAdapter {
    private List<MessageInfo> messageList;

    public MessageListAdapter(List<MessageInfo> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageInfo messageInfo = (MessageInfo) getItem(position);//获取相应位置的子项
        View messageView = null;
        ViewHolder viewHolder = null;
        if(convertView == null){
            messageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_message_listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) messageView.findViewById(R.id.tv_sendername);
            viewHolder.tv_time = (TextView) messageView.findViewById(R.id.tv_messagetime);
            viewHolder.tv_recent = (TextView) messageView.findViewById(R.id.tv_messagerecent);
            viewHolder.iv_portrait = (CirclePortraitView) messageView.findViewById(R.id.piv_mpic);
            messageView.setTag(viewHolder);
        }else {
            messageView = convertView;
            viewHolder = (ViewHolder) messageView.getTag();
        }
        viewHolder.tv_name.setText(messageInfo.getName());
        viewHolder.tv_time.setText(messageInfo.getTime());
        viewHolder.tv_recent.setText(messageInfo.getRecentMessage());
        viewHolder.iv_portrait.setImageResource(messageInfo.getPortraitId());
        return messageView;
    }
    class ViewHolder{
        TextView tv_time,tv_name,tv_recent;
        CirclePortraitView iv_portrait;
    }
}
