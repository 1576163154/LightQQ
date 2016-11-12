package xion.lightqq.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/18.
 */

public class ChooseCountryActivity extends Activity {
    private SearchView sv_choose;
    private ExpandableListView elv_reg;
    private String[] groupArray = {"最长选择的国家和地区",
            "A", "B", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "W", "X", "Y", "Z"};
    private String[][] countryArray = {
            {"中国", "中国(香港)"},
            {"阿尔及利亚"},
            {"波兰"},
            {"德国"},
            {"俄罗斯"},
            {"芬兰"},
            {"刚果"},
            {"韩国"},
            {"吉尔吉斯斯坦"},
            {"科威特"},
            {"老挝"},
            {"马达加斯加"},
            {"尼日利亚"},
            {"葡萄牙"},
            {"日本"},
            {"沙特阿拉伯"},
            {"土耳其"},
            {"乌克兰"},
            {"西班牙"},
            {"牙买加"},
            {"中国"}
    };
    private String[][] countryTelCodeArray = {
            {"+86", "+852"},
            {"+213"},
            {"+48"},
            {"+49"},
            {"+7"},
            {"+358"},
            {"+242"},
            {"+82"},
            {"+996"},
            {"+965"},
            {"+856"},
            {"+261"},
            {"+234"},
            {"+351"},
            {"+81"},
            {"+966"},
            {"+90"},
            {"+380"},
            {"+34"},
            {"+1876"},
            {"+86"}
    };
    private List<Map<String, ?>> groupList = new ArrayList<>();//问号 表明该list只读
    private List<List<Map<String, ?>>> childListList = new ArrayList<>();//child存储类型为Country
    private ImageView searchButton;//用来存储并修改searcView里面的icon

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosecountry);
        elv_reg = (ExpandableListView) findViewById(R.id.elv_regchoosecountry);
        sv_choose = (SearchView) findViewById(R.id.sv_regchoose);
        initData();
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,
                groupList, R.layout.choosecountry_group_item,
                new String[]{"groupName"}, new int[]{R.id.tv_groupname},
                childListList, R.layout.choosecountry_child_item,
                new String[]{"childName", "childCode"}, new int[]{R.id.tv_childname, R.id.tv_childcode});
        elv_reg.setAdapter(adapter);
//        elv_reg.setTextFilterEnabled(true);
        elv_reg.setGroupIndicator(null);
        for (int k = 0; k < groupList.size(); k++) {
            elv_reg.expandGroup(k);
        }
        elv_reg.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String childName = (String) childListList.get(groupPosition).get(childPosition).get("childName");
                String childCode = (String) childListList.get(groupPosition).get(childPosition).get("childCode");
                Intent intent = new Intent(ChooseCountryActivity.this, RegActivity.class);
                intent.putExtra("国家名", childName);
                intent.putExtra("国家代码", childCode);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        });
        elv_reg.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        //要实现expandablelistview的搜索过滤功能，adapter需继承至Base。。，并实现Filter接口


    }


    private void initData() {
        for (int i = 0; i < groupArray.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("groupName", groupArray[i]);
            groupList.add(map);
            //为方便，只设置每组只有一个国家
            List<Map<String, ?>> childList = new ArrayList<>();
            for (int j = 0; j < countryArray[i].length; j++) {
                Map<String, String> childMap = new HashMap<>();
                childMap.put("childName", countryArray[i][j]);
                childMap.put("childCode", countryTelCodeArray[i][j]);
                childList.add(childMap);
            }
            childListList.add(childList);
        }

    }
}



