package com.lam.smartbutlerv4.entry;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.entiry
 *  file name:          MyUser
 *  create date:        2018/10/7 21:26
 *  creator:            Luan Xiyuan
 *  description:        用户类
 */

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {

    private int age;
    private boolean sex;
    private String briefIntro;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }
}
