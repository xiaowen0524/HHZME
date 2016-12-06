package hhzmy.bwei.com.hhzme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.adapter.MyFragmentPagerAdapter;
import hhzmy.bwei.com.hhzme.fragment.FenleiActivity;
import hhzmy.bwei.com.hhzme.fragment.GouwucheActivity;
import hhzmy.bwei.com.hhzme.fragment.ShouyeActivity;
import hhzmy.bwei.com.hhzme.fragment.WodeActivity;

public class MainActivity extends FragmentActivity {

    private ViewPager viewpager;
    private ImageView mainImage1, mainImage2, mainImage3, mainImage4;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPagers();
        setImageView();
        Intents();
    }

    public void Intents() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("fragment", -1);
        System.out.println("this is fragment to" + id);
        viewpager.setCurrentItem(id);
        setColor(id);
    }

    public void setViewPagers() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), setFragment()));
    }

    public void setImageView() {
        mainImage1 = (ImageView) findViewById(R.id.mainImage1);
        mainImage2 = (ImageView) findViewById(R.id.mainImage2);
        mainImage3 = (ImageView) findViewById(R.id.mainImage3);
        mainImage4 = (ImageView) findViewById(R.id.mainImage4);
        mainImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(0);
                setColor(0);
            }
        });
        mainImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(1);
                setColor(1);
            }
        });
        mainImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(2);
                setColor(2);
            }
        });
        mainImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(3);
                setColor(3);
            }
        });
    }

    public void setColor(int index) {
        switch (index) {
            case 0:
                mainImage1.setImageResource(R.mipmap.tab_home_pressed);
                mainImage2.setImageResource(R.mipmap.tab_class_normal);
                mainImage3.setImageResource(R.mipmap.tab_shopping_normal);
                mainImage4.setImageResource(R.mipmap.tab_myebuy_normal);
                break;
            case 1:
                mainImage1.setImageResource(R.mipmap.tab_home_normal);
                mainImage2.setImageResource(R.mipmap.tab_class_pressed);
                mainImage3.setImageResource(R.mipmap.tab_shopping_normal);
                mainImage4.setImageResource(R.mipmap.tab_myebuy_normal);
                break;
            case 2:
                mainImage1.setImageResource(R.mipmap.tab_home_normal);
                mainImage2.setImageResource(R.mipmap.tab_class_normal);
                mainImage3.setImageResource(R.mipmap.tab_shopping_pressed);
                mainImage4.setImageResource(R.mipmap.tab_myebuy_normal);
                break;
            case 3:
                mainImage1.setImageResource(R.mipmap.tab_home_normal);
                mainImage2.setImageResource(R.mipmap.tab_class_normal);
                mainImage3.setImageResource(R.mipmap.tab_shopping_normal);
                mainImage4.setImageResource(R.mipmap.tab_myebuy_pressed);
                break;
        }
    }

    public List<Fragment> setFragment() {
        list = new ArrayList<>();
        list.add(new ShouyeActivity());
        list.add(new FenleiActivity());
        list.add(new GouwucheActivity());
        list.add(new WodeActivity());
        return list;
    }
}
