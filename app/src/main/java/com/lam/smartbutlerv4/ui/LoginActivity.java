package com.lam.smartbutlerv4.ui;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          LoginActivity
 *  create date:        2018/10/7 18:13
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lam.smartbutlerv4.MainActivity;
import com.lam.smartbutlerv4.R;
import com.lam.smartbutlerv4.entry.MyUser;
import com.lam.smartbutlerv4.utils.L;
import com.lam.smartbutlerv4.utils.SPUtil;
import com.lam.smartbutlerv4.utils.StaticClass;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_register, btn_login;
    private EditText et_username, et_password;
    private CheckBox chk_remember_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        chk_remember_password = findViewById(R.id.chk_remember_password);
        //设置选中状态
        boolean isChecked = SPUtil.getBoolean(this, StaticClass.REMEMBER_PASSWORD_FLG, false);
        chk_remember_password.setChecked(isChecked);
        if(isChecked) {
            et_username.setText(SPUtil.getString(this, StaticClass.USERNAME, ""));
            et_password.setText(SPUtil.getString(this, StaticClass.PASSWORD, ""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(password)) {
                    MyUser myUser = new MyUser();
                    myUser.setUsername(username);
                    myUser.setPassword(password);

                    myUser.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser bmobUser, BmobException e) {
                            if(e==null){
                                Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }else{
                                L.d(e.toString());
                                Toast.makeText(LoginActivity.this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, getString(R.string.input_not_null), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //重写distroy事件，只有当用户点击登录才记录登录状态，点击退出不记录
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存状态
        SPUtil.putBoolean(this, StaticClass.REMEMBER_PASSWORD_FLG, chk_remember_password.isChecked());
        if(chk_remember_password.isChecked()) {
            SPUtil.putString(this, StaticClass.USERNAME, et_username.getText().toString().trim());
            SPUtil.putString(this, StaticClass.PASSWORD, et_password.getText().toString().trim());
        } else {
            SPUtil.deleteOne(this, StaticClass.USERNAME);
            SPUtil.deleteOne(this, StaticClass.PASSWORD);
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
