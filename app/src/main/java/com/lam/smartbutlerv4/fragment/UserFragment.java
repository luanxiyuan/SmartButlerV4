package com.lam.smartbutlerv4.fragment;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.fragment
 *  file name:          UserFragment
 *  create date:        2018/10/1 13:17
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lam.smartbutlerv4.R;

public class UserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        return view;
    }
}
