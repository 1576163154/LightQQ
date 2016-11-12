package xion.lightqq.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xion.lightqq.R;
import xion.lightqq.entity.ContactInfo;
import xion.lightqq.widget.CirclePortraitView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class FriendListAdapter extends BaseExpandableListAdapter {
    private int onlineNum = 0;//每组在线的人数
    private List<String> groupList;
    private List<List<ContactInfo>> childListList;

    public FriendListAdapter(List<String> groupList, List<List<ContactInfo>> childListList) {
        this.groupList = groupList;
        this.childListList = childListList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childListList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childListList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition * 100;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition * 100 + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View groupView = null;
        groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_group_item, null);
        TextView tv_friendlist_groupname, tv_friendlist_group_quantity;
        ImageView iv_friendlist_groupindicator;
        iv_friendlist_groupindicator = (ImageView) groupView.findViewById(R.id.iv_friendlist_groupindicator);
        if(isExpanded){
            iv_friendlist_groupindicator.setImageResource(R.drawable.expansion);
        }else {
            iv_friendlist_groupindicator.setImageResource(R.drawable.contraction);
        }
        tv_friendlist_groupname = (TextView) groupView.findViewById(R.id.tv_friendlist_groupname);
        tv_friendlist_group_quantity = (TextView) groupView.findViewById(R.id.tv_friendlist_group_quantity);
        tv_friendlist_groupname.setText(groupList.get(groupPosition));
        tv_friendlist_group_quantity.setText(0+"/" +3);
        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

       ContactInfo contactInfo = (ContactInfo) childListList.get(groupPosition).get(childPosition);
        View childView = null;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_child_item, null);
            viewHolder = new ViewHolder();
            viewHolder.cpv_friendlist_childportrait = (CirclePortraitView) childView.findViewById(R.id.cpv_friendlist_childportrait);
            viewHolder.iv_friendlist_childviporsvip = (ImageView) childView.findViewById(R.id.iv_friendlist_childviporsvip);
            viewHolder.tv_friendlist_childname = (TextView) childView.findViewById(R.id.tv_friendlist_childname);
            viewHolder.tv_friendlist_childstate = (TextView) childView.findViewById(R.id.tv_friendlist_childstate);
            viewHolder.tv_friendlist_childsignature = (TextView) childView.findViewById(R.id.tv_friendlist_childsignature);
            viewHolder.tv_friendlist_internettype = (TextView) childView.findViewById(R.id.tv_friendlist_internettype);
            childView.setTag(viewHolder);
        } else {
            childView = convertView;
            viewHolder = (ViewHolder) childView.getTag();
        }
        viewHolder.cpv_friendlist_childportrait.setImageResource(contactInfo.getPortraitId());
        viewHolder.tv_friendlist_childsignature.setText(contactInfo.getSignature());
        viewHolder.tv_friendlist_childname.setText(contactInfo.getName());
        viewHolder.tv_friendlist_internettype.setText(contactInfo.getInternetType());
        if (contactInfo.getOnline() == 1) {
            viewHolder.tv_friendlist_childstate.setText("[在线]");
            viewHolder.tv_friendlist_internettype.setVisibility(View.VISIBLE);//只有在线，才显示网络
            onlineNum += 1;
        } else if(contactInfo.getOnline() == 0) {
            viewHolder.tv_friendlist_childstate.setText("[离线]");
            viewHolder.tv_friendlist_internettype.setVisibility(View.GONE);
        }
        if (contactInfo.getMemberType().equals("QQ会员")) {
            viewHolder.iv_friendlist_childviporsvip.setImageResource(R.drawable.vip);
            viewHolder.tv_friendlist_childname.setTextColor(Color.parseColor("#f50410"));
        }else if (contactInfo.getMemberType().equals("超级QQ会员")) {
            viewHolder.iv_friendlist_childviporsvip.setImageResource(R.drawable.svip);
            viewHolder.tv_friendlist_childname.setTextColor(Color.parseColor("#f50410"));
        }else if (contactInfo.getMemberType().equals(" ")){
            viewHolder.iv_friendlist_childviporsvip.setImageResource(R.drawable.white);
            viewHolder.tv_friendlist_childname.setTextColor(Color.parseColor("#0a0a0a"));
        }
        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolder {
        TextView tv_friendlist_childname;
        TextView tv_friendlist_childstate;
        TextView tv_friendlist_childsignature;
        TextView tv_friendlist_internettype;
        CirclePortraitView cpv_friendlist_childportrait;
        ImageView iv_friendlist_childviporsvip;
    }
}
