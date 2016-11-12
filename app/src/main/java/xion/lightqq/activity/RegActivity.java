package xion.lightqq.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import xion.lightqq.R;


/**
 * Created by Administrator on 2016/10/18.
 */

public class RegActivity extends Activity {
    private final int REQ_COUNTRY = 1;
    private EditText et_input;
    private Button btn_next;
    private CheckBox cb_term;
    private boolean cb_termChecked = true;//按钮默认选中，便于控制按钮状态
    private LinearLayout ll_reg;
    private TextView tv_country,tv_countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        initWidget();
        setWidget();


        ll_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(RegActivity.this,ChooseCountryActivity.class),REQ_COUNTRY);
            }
        });

        //只有复选按钮选中并且满足文本输入条件时
        cb_term.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_termChecked = true;
                    if (et_input.getText().length() >= 3) {
                        //把et_input.getText.length()放在一起判断，造成下面只要不是3个Checked就一直为false
                        btn_next.setEnabled(true);
                        btn_next.setBackgroundResource(R.drawable.btn);
                    }
//                            Log.d("cb_termChecked", String.valueOf(cb_termChecked));
                } else {
                    cb_termChecked = false;
                    btn_next.setEnabled(false);
                    btn_next.setBackgroundResource(R.drawable.btn_grayshape);
//                            Log.d("cb_termChecked", String.valueOf(cb_termChecked));
                }

            }
        });
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(getApplicationContext(), "beforeTextChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(getApplicationContext(), "onTextChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getApplicationContext(), "afterTextChanged", Toast.LENGTH_SHORT).show();
                if (et_input.getText().length() >= 3 && cb_termChecked) {
                    btn_next.setEnabled(true);
                    btn_next.setBackgroundResource(R.drawable.btn);
                } else {
                    btn_next.setEnabled(false);
                    btn_next.setBackgroundResource(R.drawable.btn_grayshape);
                }
            }
        });
    }
    private void initWidget() {
        et_input = (EditText) findViewById(R.id.et_input);
        btn_next = (Button) findViewById(R.id.btn_regnext);
        cb_term = (CheckBox) findViewById(R.id.cb_term);
        ll_reg = (LinearLayout) findViewById(R.id.ll_countryandcode);
        tv_country = (TextView) findViewById(R.id.tv_country);
        tv_countryCode = (TextView) findViewById(R.id.tv_countrycode);
    }
    private void setWidget() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this,ChooseCountryActivity.class));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_COUNTRY){
            if (resultCode == RESULT_OK){
                tv_country.setText(data.getStringExtra("国家名"));
                tv_countryCode.setText(data.getStringExtra("国家代码"));
            }
        }
    }
}
