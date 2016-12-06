package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.adapter.MyFragmentPagerAdapter;

public class XiangqingActivity extends Fragment {

    private View view;
    private List<Fragment> list;
    private ViewPager viewpager;
    private TextView tuwenxiangqing, guigecanshu, baozhuangshouhou;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_xiangqing, null);
        dianji();
        setviewpager();
        return view;
    }

    public void dianji() {
        tuwenxiangqing = (TextView) view.findViewById(R.id.tuwenxiangqing);
        guigecanshu = (TextView) view.findViewById(R.id.guigecanshu);
        baozhuangshouhou = (TextView) view.findViewById(R.id.baozhuangshouhou);
        tuwenxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(0);
                setcolor(0);
            }
        });
        guigecanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(1);
                setcolor(1);
            }
        });
        baozhuangshouhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(2);
                setcolor(2);
            }
        });
    }

    public void setcolor(int index) {
        switch (index) {
            case 0:
                tuwenxiangqing.setTextColor(Color.parseColor("#f29400"));
                guigecanshu.setTextColor(Color.BLACK);
                baozhuangshouhou.setTextColor(Color.BLACK);
                break;
            case 1:
                tuwenxiangqing.setTextColor(Color.BLACK);
                guigecanshu.setTextColor(Color.parseColor("#f29400"));
                baozhuangshouhou.setTextColor(Color.BLACK);
                break;
            case 2:
                tuwenxiangqing.setTextColor(Color.BLACK);
                guigecanshu.setTextColor(Color.BLACK);
                baozhuangshouhou.setTextColor(Color.parseColor("#f29400"));
                break;
        }
    }

    public void setviewpager() {
        viewpager = (ViewPager) view.findViewById(R.id.xviewpager);
        viewpager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), setfragment()));
    }

    public List<Fragment> setfragment() {
        list = new ArrayList<>();
        list.add(new TuwenActivity());
        list.add(new GuigeActivity());
        list.add(new ShouhouActivity());
        return list;
    }
}
