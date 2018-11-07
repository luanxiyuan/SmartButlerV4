package com.lam.smartbutlerv4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.lam.smartbutlerv4.fragment.ButlerFragment;
import com.lam.smartbutlerv4.fragment.ImageFragment;
import com.lam.smartbutlerv4.fragment.UserFragment;
import com.lam.smartbutlerv4.fragment.WechatFragment;
import com.lam.smartbutlerv4.ui.LocationActivity;
import com.lam.smartbutlerv4.ui.SettingActivity;
import com.lam.smartbutlerv4.utils.StatusBarUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TabLayout
//    private TabLayout mTabLayout;
    //ViewPager
//    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗
    private FloatingActionButton fab_setting;
//    private FloatingActionButton fab_location;
    //卡片视图
    private CardView cardView;

    private SliderLayout sliderLayout;
    private PagerIndicator indicator;
    private Button toolbar_right_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.bg_toolbar);
        setContentView(R.layout.activity_main);

        //去掉阴影
//        getSupportActionBar().setElevation(0);

        initDate();
        initView();

        //Bugly test
//        CrashReport.testJavaCrash();
    }

    //初始化数据
    private void initDate() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.title_1));
        mTitle.add(getString(R.string.title_2));
        mTitle.add(getString(R.string.title_3));
        mTitle.add(getString(R.string.title_4));

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new ImageFragment());
        mFragment.add(new UserFragment());
    }

    //初始化视图
    @SuppressLint("RestrictedApi")
    private void initView() {
//        mTabLayout = findViewById(R.id.mTabLayout);
//        mViewPager = findViewById(R.id.mViewPager);
        fab_setting = findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        toolbar_right_btn = findViewById(R.id.toolbar_right_btn);
        toolbar_right_btn.setOnClickListener(this);
//        fab_location = findViewById(R.id.fab_location);
        //悬浮窗默认隐藏
//        fab_setting.setVisibility(View.GONE);
        //预加载
//        mViewPager.setOffscreenPageLimit(mFragment.size());
        //卡片
//        cardView = (CardView)findViewById(R.id.cardView);
//        cardView.setRadius(1);//设置图片圆角的半径大小
//        cardView.setCardElevation(3);//设置阴影部分大小
//        cardView.setContentPadding(3,3,3,3);//设置图片距离阴影大小
        //初始化banner
        initImageSlider();

        //滑动监听
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                if(0 == i) {
//                    fab_setting.setVisibility(View.GONE);
//                } else {
//                    fab_setting.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//
//        //设置适配器
//        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return mFragment.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return mFragment.size();
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return mTitle.get(position);
//            }
//        });
        //绑定
//        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.toolbar_right_btn:
                startActivity(new Intent(this, LocationActivity.class));
                break;
        }
    }

    /**
     * 初始化首页的商品广告条
     */
    private void initImageSlider() {

        sliderLayout = findViewById(R.id.slider);
        indicator = findViewById(R.id.custom_indicator);

        //准备好要显示的数据
        List<Integer> imageUrls = new ArrayList<>();
        List<File> imageFiles = new ArrayList<>();
        final List<String> descriptions = new ArrayList<>();
        imageUrls.add(R.drawable.banner1);
        imageUrls.add(R.drawable.banner2);
        imageUrls.add(R.drawable.banner3);
        descriptions.add("美元信用卡，享最高10%刷卡金返还");
        descriptions.add("周末刷卡，享受5倍礼赏");
        descriptions.add("天天花旗日，看电影巨划算");

        for (int i = 0; i < imageUrls.size(); i++) {
            //新建三个展示View，并且添加到SliderLayout
            TextSliderView tsv = new TextSliderView(this);
            tsv.image(imageUrls.get(i)).description(descriptions.get(i));
            final int finalI = i;
            tsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Toast.makeText(MainActivity.this, descriptions.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            sliderLayout.addSlider(tsv);
        }

        //对SliderLayout进行一些自定义的配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);//图片轮播时候的动画，有兴趣的话可以都亲自试一下；；
        sliderLayout.setDuration(7000);
        //      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomIndicator(indicator);
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
