package hhzmy.bwei.com.hhzme.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.activity.MainActivity;

/**
 * Created by asus on 2016/11/8.
 */

public class MPagerAdapter extends PagerAdapter {
    private List<View> mViewList;
    private Activity activity;

    // 构造方法，参数是装有要展示页卡的集合
    public MPagerAdapter(List<View> mViewList, Activity activity) {
        this.mViewList = mViewList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        // 返回页卡的总数量
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 删除当前页卡
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 这个方法用来实例化页卡--添加页卡
        container.addView(mViewList.get(position), 0);
        if (position == mViewList.size() - 1) {
            // 已经到了最后一张
            Button btn = (Button) container.findViewById(R.id.start);
            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    haveGuided();
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });
        }
        return mViewList.get(position);
    }

    /**
     * 记录已经引导过了，下次进入可以直接进入主页面
     */
    protected void haveGuided() {
        SharedPreferences preferences = activity.getSharedPreferences(Utils.getGuideName(),
                Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(Utils.getGuideKey(), true);
        editor.commit();
    }
}
