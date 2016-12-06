package hhzmy.bwei.com.hhzme.welcome;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;

public class MGuideAction extends AppCompatActivity {

    ViewPager viewPager;
    View view1, view2, view3;
    View view11, view22, view33;
    List<View> viewList;
    LinearLayout linearLayout;
    private ImageView[] points;// 存放小横条的图片控件集合
    int currentPoint = 0;// 当前选中的导航页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mguide_action);

        initView();

        initPoint();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化指示当前页的横条
     */
    private void initPoint() {
        // 获得横条所在的布局
        linearLayout = (LinearLayout) findViewById(R.id.point_bar_id);

        points = new ImageView[viewList.size()];

        // 将小横条放到集合中
        for (int i = 0; i < viewList.size(); i++) {
            points[i] = (ImageView) linearLayout.getChildAt(i);
            points[i].setEnabled(true); // 设置为灰色，未选中状态
        }

        currentPoint = 0;

        points[currentPoint].setEnabled(false); // 选中状态

    }

    /**
     * 初始化view
     */
    private void initView() {
        // 实例化ViewPager组件
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // 加载要显示的页卡组件
        LayoutInflater inflater = LayoutInflater.from(MGuideAction.this);
        view1 = inflater.inflate(R.layout.guide_layout1, null);
        view2 = inflater.inflate(R.layout.guide_layout2, null);
        view3 = inflater.inflate(R.layout.guide_layout3, null);
        // 将要滑动显示的组件View放入集合中
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        // 实例化适配器,将页卡集合传入适配器中
        MPagerAdapter mPagerAdapter = new MPagerAdapter(viewList, this);

        // 为ViewPager设置适配器
        viewPager.setAdapter(mPagerAdapter);

        /**
         * ViewPager在处理滑动事件的时候要用到OnPageChangeListener
         * OnPageChangeListener这个接口需要实现三个方法：
         * （onPageScrollStateChanged，onPageScrolled ，onPageSelected）
         */

//        viewPager.setOnPageChangeListener(this);

    }

    /**
     * 三个方法的执行顺序为：用手指拖动翻页时，最先执行一遍onPageScrollStateChanged（1），
     * 然后不断执行onPageScrolled
     * ，放手指的时候，直接立即执行一次onPageScrollStateChanged（2），然后立即执行一次onPageSelected
     * ，然后再不断执行onPageScrollStateChanged，最后执行一次onPageScrollStateChanged（0）。
     */

    /**
     * 这个方法在手指操作屏幕的时候发生变化。有三个值：0（END）,1(PRESS) , 2(UP) 。
     * 当用手指滑动翻页时，手指按下去的时候会触发这个方法，state值为1，手指抬起时，如果发生了滑动（即使很小），这个值会变为2，然后最后变为0
     * 。总共执行这个方法三次。一种特殊情况是手指按下去以后一点滑动也没有发生，这个时候只会调用这个方法两次，state值分别是1,0 。
     * 当setCurrentItem翻页时，会执行这个方法两次，state值分别为2 , 0 。
     */
//    @Override
//    public void onPageScrollStateChanged(int arg0) {
//        // TODO Auto-generated method stub
//
//    }

    /**
     * 这个方法会在屏幕滚动过程中不断被调用。 有三个参数，第一个position，这个参数要特别注意一下。当用手指滑动时，如果手指按在页面上不动，
     * position和当前页面index是一致的
     * ；如果手指向左拖动（相应页面向右翻动），这时候position大部分时间和当前页面是一致的，只有翻页成功的情况下最后一次调用才会变为目标页面
     * ；如果手指向右拖动（相应页面向左翻动），这时候position大部分时间和目标页面是一致的，只有翻页不成功的情况下最后一次调用才会变为原页面。
     * 当直接设置setCurrentItem翻页时
     * ，如果是相邻的情况（比如现在是第二个页面，跳到第一或者第三个页面），如果页面向右翻动，大部分时间是和当前页面是一致的
     * ，只有最后才变成目标页面；如果向左翻动，position和目标页面是一致的。这和用手指拖动页面翻动是基本一致的。
     * 如果不是相邻的情况，比如我从第一个页面跳到第三个页面
     * ，position先是0，然后逐步变成1，然后逐步变成2；我从第三个页面跳到第一个页面，position先是1
     * ，然后逐步变成0，并没有出现为2的情况。
     * positionOffset是当前页面滑动比例，如果页面向右翻动，这个值不断变大，最后在趋近1的情况后突变为0
     * 。如果页面向左翻动，这个值不断变小，最后变为0。
     * positionOffsetPixels是当前页面滑动像素，变化情况和positionOffset一致。
     */
//    @Override
//    public void onPageScrolled(int arg0, float arg1, int arg2) {
//        // TODO Auto-generated method stub
//
//    }

    /**
     * 这个方法有一个参数position，代表哪个页面被选中。
     * 当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），手指抬起来就会立即执行这个方法, position就是当前滑动到的页面。
     * 如果直接setCurrentItem翻页，那position就和setCurrentItem的参数一致，
     * 这种情况在onPageScrolled执行方法前就会立即执行。
     */
    /*@Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        setCurrentPoint(arg0);
    }*/

    /**
     * @param arg0 设置当前页面对应的小横条
     */
    private void setCurrentPoint(int arg0) {
        points[arg0].setEnabled(false); // 选中状态
        points[currentPoint].setEnabled(true); //未选中状态
        currentPoint = arg0;
    }
}