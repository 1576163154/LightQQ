package xion.lightqq.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xion.lightqq.R;
import xion.lightqq.activity.GroupManageActivity;
import xion.lightqq.activity.MainActivity;
import xion.lightqq.adapter.FriendListAdapter;
import xion.lightqq.entity.ContactInfo;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2016/10/20.
 */

public class ContactFragment extends Fragment {
    private List<String> groupList = new ArrayList<>();
    private List<List<ContactInfo>> childListList = new ArrayList<>();
    private List<ContactInfo> childList;
    private String[] groupArray = {"我的设备","我的好友","同学"};
    private String[][] childNameArray = {
            {"我的电脑"},
            {"小明","小刚","Xion"},
            { }
    };//通过该数组来特定每组添加的个数,每个contactInofo的内容都可以这样设置
    private int[][] childPortraitIdArray = {
            {R.drawable.portrait},
            {R.drawable.portrait,R.drawable.portrait,R.drawable.portrait}
    };
    private int[][] childStateArray = {
            {0},
            {1,0,0},
            {}
    };
    private String[][] childSignatureArray = {
            {"无需数据线，手机轻松传文件到电脑"},
            {"小明的签名","小刚的签名","Xion的签名名么么么么么么么么么么么么么么么么"},
            {}
    };
    private String[][] childInternetTypeArray = {
            {""},
            {"2G","3G","4G"},
            {}
    };
    private String[][] childMemberTypeArray = {
            {""},
            {"超级QQ会员","QQ会员"," "},
            {}
    };
    private ExpandableListView elv_friendlist;
    private FriendListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contactView = inflater.inflate(R.layout.fragment_contact,null);
        elv_friendlist = (ExpandableListView) contactView.findViewById(R.id.elv_friendlist);
        initData();
        setWidget();
        return contactView;
    }
    private void initData() {
        if(groupList.size() > 0){
            childList = new ArrayList<>();
            for (int i = 0; i < groupList.size(); i++) {
                List<ContactInfo> childList = new ArrayList<>();
                for (int j = 0; j < childNameArray[i].length; j++) {
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setName(childNameArray[i][j]);
                    contactInfo.setPortraitId(childPortraitIdArray[i][j]);
                    contactInfo.setSignature(childSignatureArray[i][j]);
                    contactInfo.setOnline(childStateArray[i][j]);
                    contactInfo.setMemberType(childMemberTypeArray[i][j]);
                    contactInfo.setInternetType(childInternetTypeArray[i][j]);
                    childList.add(contactInfo);
                }
                childListList.add(childList);
            }
        }else {
            childList = new ArrayList<>();
            for (int i = 0; i < groupArray.length; i++) {
                groupList.add(groupArray[i]);
                List<ContactInfo> childList = new ArrayList<>();
                for (int j = 0; j < childNameArray[i].length; j++) {
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setName(childNameArray[i][j]);
                    contactInfo.setPortraitId(childPortraitIdArray[i][j]);
                    contactInfo.setSignature(childSignatureArray[i][j]);
                    contactInfo.setOnline(childStateArray[i][j]);
                    contactInfo.setMemberType(childMemberTypeArray[i][j]);
                    contactInfo.setInternetType(childInternetTypeArray[i][j]);
                    childList.add(contactInfo);
                }
                childListList.add(childList);
            }
        }
        Log.d("groupList.size()", String.valueOf(groupList.size()));
    }
    private void setWidget() {
        adapter = new FriendListAdapter(groupList,childListList);
        elv_friendlist.setAdapter(adapter);
        elv_friendlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog contactDialog = new AlertDialog.Builder(getActivity()).create();
                contactDialog.show();
                Window window = contactDialog.getWindow();
                window.setContentView(R.layout.friendgroupdialog_layout);
                final Button btn_friendgroupmanager;
                btn_friendgroupmanager = (Button) window.findViewById(R.id.btn_friendgroupmanager);
                btn_friendgroupmanager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"点击了"+btn_friendgroupmanager.getText().toString(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),GroupManageActivity.class);
                        intent.putStringArrayListExtra("friendListName", (ArrayList<String>) groupList);
                        //考虑到是对ExpandableListView的修改，故暂时不考虑GroupManageActivity传数据回来
                        startActivityForResult(intent,1);//请求码 设为1
                        contactDialog.dismiss();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
     switch (requestCode){
         case 1:
             if ( resultCode == RESULT_OK){
                 List<String> newList;
                 newList = data.getStringArrayListExtra("modifyFriendListName");
                 //由于在GroupManageAcitivy中删除了list的第一个元素，故需要找回第一个元素
                 newList.set(0,"我的设备");
                 groupList = newList;
                 adapter = new FriendListAdapter(groupList,childListList);
                 elv_friendlist.setAdapter(adapter);
             }
             break;
         default:
             break;
     }
    }
}
