package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.map.DituActivity;

public class ShangpinActivity extends Fragment {
    private TextView tvtime1, tvtime2, tvtime3;
    private LinearLayout dizhi;
    private long time = 40000;
    private View view;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    int[] imgRes = {R.mipmap.ic1, R.mipmap.ic2, R.mipmap.ic3, R.mipmap.ic4, R.mipmap.ic5, R.mipmap.ic6};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_shangpin, null);
        tvtime1 = (TextView) view.findViewById(R.id.tvtime1);
        tvtime2 = (TextView) view.findViewById(R.id.tvtime2);
        tvtime3 = (TextView) view.findViewById(R.id.tvtime3);
        handler.postDelayed(runnable, 1000);
        lunbo();
        ditu();
        return view;
    }

    public void ditu() {
        dizhi = (LinearLayout) view.findViewById(R.id.dizhi);
        dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DituActivity.class);
                startActivity(intent);
            }
        });
    }

    public void lunbo() {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setPageMargin(10);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter = new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(getActivity());
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
//                view.setText(position + ":" + view);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setBackgroundColor(Color.parseColor("#ffffff"));
                view.setImageResource(imgRes[position]);
                container.addView(view);
                view.setAdjustViewBounds(true);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }
        });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            String formatLongToTimeStr = formatLongToTimeStr(time);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    tvtime1.setText(split[0] + "");
                }
                if (i == 1) {
                    tvtime2.setText(split[1] + "");
                }
                if (i == 2) {
                    tvtime3.setText(split[2] + "");
                }

            }
            if (time > 0) {
                handler.postDelayed(this, 1000);
            }
        }
    };

    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;

    }
}

