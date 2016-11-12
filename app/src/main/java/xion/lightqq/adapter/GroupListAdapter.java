package xion.lightqq.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import xion.lightqq.R;
import xion.lightqq.activity.GroupManageActivity;
import xion.lightqq.widget.ClearEditText;


/**
 * Created by Administrator on 2016/11/3.
 */

public class GroupListAdapter extends BaseAdapter {
    private List<String> groupList;
    private Context context;
    public GroupListAdapter(List<String> groupList,Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        String groupName = (String) getItem(position);
        View groupView = null;
        ViewHolder viewHolder = null;
        if (convertView == null){
            groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.groupmanage_grouplist_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_groupname = (TextView) groupView.findViewById(R.id.tv_groupmanage_groupname);
            viewHolder.iv_groupdelete = (ImageView) groupView.findViewById(R.id.iv_groupmanage_deletegroup);
            groupView.setTag(viewHolder);
        }else {
            groupView = convertView;
            viewHolder = (ViewHolder) groupView.getTag();
        }
        viewHolder.tv_groupname.setText(groupName);
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.iv_groupdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了删除按钮",Toast.LENGTH_SHORT).show();
                groupList.remove(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.tv_groupname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了"+ finalViewHolder.tv_groupname.getText().toString(),Toast.LENGTH_SHORT).show();
                final AlertDialog editDialog = new AlertDialog.Builder(context).create();
                editDialog.show();
                Window window = editDialog.getWindow();
                window.setContentView(R.layout.groupmanage_edit_dialog);
                editDialog.getWindow().
                        clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                Button btn_grouopmanage_edit_cancel;
                Button btn_grouopmanage_edit_ok;
                final ClearEditText cet_groupmanage_edit;//编辑框
                btn_grouopmanage_edit_cancel = (Button) window.findViewById(R.id.btn_grouopmanage_edit_cancel);
                btn_grouopmanage_edit_ok = (Button) window.findViewById(R.id.btn_grouopmanage_edit_ok);
                cet_groupmanage_edit = (ClearEditText) window.findViewById(R.id.cet_groupmanage_edit);
                cet_groupmanage_edit.setText(groupList.get(position));
                cet_groupmanage_edit.setSelection((groupList.get(position)).toString().length());
                btn_grouopmanage_edit_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editDialog.dismiss();
                    }
                });
                btn_grouopmanage_edit_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       groupList.set(position,cet_groupmanage_edit.getText().toString());
                        notifyDataSetChanged();
                        editDialog.dismiss();
                    }
                });
            }
        });
        return groupView;
    }
    class ViewHolder{
        TextView tv_groupname;
        ImageView iv_groupdelete;
}
}
