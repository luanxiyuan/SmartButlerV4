package com.lam.smartbutlerv4.ui;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          GuideActivity
 *  create date:        2018/10/3 21:04
 *  creator:            Luan Xiyuan
 *  description:        引导页
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lam.smartbutlerv4.MainActivity;
import com.lam.smartbutlerv4.R;
import com.lam.smartbutlerv4.utils.L;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;

    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3;
    private ImageView point1, point2, point3;
    private ImageView iv_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        iv_skip = findViewById(R.id.iv_skip);

        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        //设置默认图片
        setPointImg(true, false, false);

        mViewPager = findViewById(R.id.mViewPager);

        view1 = View.inflate(this, R.layout.start_item1, null);
        view2 = View.inflate(this, R.layout.start_item2, null);
        view3 = View.inflate(this, R.layout.start_item3, null);
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        //设置进入主页点击事件
        view3.findViewById(R.id.btn_start).setOnClickListener(this);
        //设置跳过点击事件
        iv_skip.findViewById(R.id.iv_skip).setOnClickListener(this);

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());

        //监听viewpager滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            //page切换回调
            @Override
            public void onPageSelected(int i) {
                L.i("position:" + i);
                switch (i) {
                    case 0:
                        iv_skip.setVisibility(View.VISIBLE);
                        setPointImg(true, false, false);
                        break;
                    case 1:
                        iv_skip.setVisibility(View.VISIBLE);
                        setPointImg(false, true, false);
                        break;
                    case 2:
                        iv_skip.setVisibility(View.GONE);
                        setPointImg(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    //设置小圆点的选中效果
    private void setPointImg(boolean isChecked1, boolean isChecked2, boolean isChecked3) {
        if (isChecked1) {
            point1.setBackgroundResource(R.drawable.point_checked);
        } else {
            point1.setBackgroundResource(R.drawable.point_unchecked);
        }

        if (isChecked2) {
            point2.setBackgroundResource(R.drawable.point_checked);
        } else {
            point2.setBackgroundResource(R.drawable.point_unchecked);
        }

        if (isChecked3) {
            point3.setBackgroundResource(R.drawable.point_checked);
        } else {
            point3.setBackgroundResource(R.drawable.point_unchecked);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
            case R.id.iv_skip:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mList.get(position));
//            super.destroyItem(container, position, object);
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
