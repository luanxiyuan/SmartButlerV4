package com.lam.smartbutlerv4.ui;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          RegisterActivity
 *  create date:        2018/10/7 20:29
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lam.smartbutlerv4.R;
import com.lam.smartbutlerv4.entry.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username, et_age, et_brief_intro, et_password, et_retype_password, et_email;
    private RadioGroup rg_sex;
    private RadioButton chk_mail, chk_femail;
    private Button btn_register;
    //默认是男：true
    private boolean genderFlg = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_age = findViewById(R.id.et_age);
        et_brief_intro = findViewById(R.id.et_brief_intro);
        et_retype_password = findViewById(R.id.et_retype_password);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        rg_sex = findViewById(R.id.rg_sex);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String username = et_username.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String briefIntro = et_brief_intro.getText().toString().trim();
                String retypePassword = et_retype_password.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                //判断是否为空
                if (!TextUtils.isEmpty(username) &
                        !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(retypePassword) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)) {
                    //判断密码是否一致
                    if (password.equals(retypePassword)) {
                        //判断性别
                        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rd_mail) {
                                    genderFlg = true;
                                } else {
                                    genderFlg = false;
                                }
                            }
                        });
                        //判断简介是否为空
                        if (TextUtils.isEmpty(briefIntro)) {
                            briefIntro = getString(R.string.leave_nothing);
                        }

                        //注册
                        MyUser myUser = new MyUser();
                        myUser.setUsername(username);
                        myUser.setPassword(password);
                        myUser.setEmail(email);
                        myUser.setAge(Integer.parseInt(age));
                        myUser.setBriefIntro(briefIntro);
                        myUser.setSex(genderFlg);

                        myUser.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser s, BmobException e) {
                                if(e==null){
                                    Toast.makeText(RegisterActivity.this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this, getString(R.string.register_fail) + e.toString(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, getString(R.string.password_not_same), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.input_not_null), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
