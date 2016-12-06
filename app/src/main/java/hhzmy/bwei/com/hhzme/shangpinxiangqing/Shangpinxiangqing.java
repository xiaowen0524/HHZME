package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.activity.MainActivity;
import hhzmy.bwei.com.hhzme.adapter.MyFragmentPagerAdapter;
import hhzmy.bwei.com.hhzme.dao.GwUserDao;
import hhzmy.bwei.com.hhzme.dao.ScUserDao;
import hhzmy.bwei.com.hhzme.map.DituActivity;

public class Shangpinxiangqing extends FragmentActivity {
    private GwUserDao dao;
    private ScUserDao scdao;
    private List<Fragment> list;
    private ViewPager xqViewPager;
    private ImageView xqFanhui, xqKefu, xqShoucang, xqDianpu, xqGengduo, xqGouwuche;
    private TextView xqShangpin, xqXiangqing, xqPingjia, xqLijigoumai, xqJiarugouwuche;
    final String huawang = "http://image1.suning.cn//uimg/cms/img/147921138033954078.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpinxiangqing);
        zhaokongjian();
        xuandianji();
        setviewpager();
    }

    /*商品详情里面的点击事件*/
    public void zhaokongjian() {
        xqFanhui = (ImageView) findViewById(R.id.xqFanhui);
        xqKefu = (ImageView) findViewById(R.id.xqKefu);
        xqShoucang = (ImageView) findViewById(R.id.xqShoucang);
        xqDianpu = (ImageView) findViewById(R.id.xqDianpu);
        xqGengduo = (ImageView) findViewById(R.id.xqGengduo);
        xqGouwuche = (ImageView) findViewById(R.id.xqGouwuche);
        xqLijigoumai = (TextView) findViewById(R.id.xqLijigoumai);
        xqJiarugouwuche = (TextView) findViewById(R.id.xqJiarugouwuche);
        xqFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*客服-拨打电话*/
        xqKefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "400-688-0900"));
                // 跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                Toast.makeText(Shangpinxiangqing.this, "客服", Toast.LENGTH_SHORT).show();
            }
        });
        /*收藏*/
        scdao = new ScUserDao(Shangpinxiangqing.this);
        xqShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserts = scdao.insert("花王（Merries）妙而舒 婴幼儿纸尿裤 中号M64片（6-11kg）", "109.00", huawang);
                Toast.makeText(Shangpinxiangqing.this, "收藏", Toast.LENGTH_SHORT).show();
            }
        });
        /*店铺*/
        xqDianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shangpinxiangqing.this, DianpuActivity.class);
                startActivity(intent);
                Toast.makeText(Shangpinxiangqing.this, "店铺", Toast.LENGTH_SHORT).show();
            }
        });
        /*更多*/
        xqGengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Shangpinxiangqing.this, "更多", Toast.LENGTH_SHORT).show();
            }
        });
        /*购物车*/
        xqGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shangpinxiangqing.this, MainActivity.class);
                intent.putExtra("fragment", 2);
                startActivity(intent);
                Toast.makeText(Shangpinxiangqing.this, "购物车", Toast.LENGTH_SHORT).show();
            }
        });
        /*立即购买*/
        xqLijigoumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shangpinxiangqing.this, QuerendingdanActivity.class);
                startActivity(intent);
            }
        });
        /*加入购物车*/

        dao = new GwUserDao(Shangpinxiangqing.this);
        xqJiarugouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserts = dao.insert("花王（Merries）妙而舒 婴幼儿纸尿裤 中号M64片（6-11kg）", "109.00", huawang);
                Toast.makeText(Shangpinxiangqing.this, "加入购物车", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*三个按钮的点击事件*/
    public void xuandianji() {
        xqShangpin = (TextView) findViewById(R.id.xqShangpin);
        xqXiangqing = (TextView) findViewById(R.id.xqXiangqing);
        xqPingjia = (TextView) findViewById(R.id.xqPingjia);
        xqShangpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xqViewPager.setCurrentItem(0);
                setcolor(0);
            }
        });
        xqXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xqViewPager.setCurrentItem(1);
                setcolor(1);
            }
        });
        xqPingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xqViewPager.setCurrentItem(2);
                setcolor(2);
            }
        });
    }

    /*三个页面的切换*/
    public void setviewpager() {
        xqViewPager = (ViewPager) findViewById(R.id.xqViewPager);
        xqViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), setFragment()));
        xqViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setcolor(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*点击字体变颜色*/
    public void setcolor(int index) {
        switch (index) {
            case 0:
                xqShangpin.setTextColor(Color.parseColor("#f29400"));
                xqXiangqing.setTextColor(Color.BLACK);
                xqPingjia.setTextColor(Color.BLACK);
                break;
            case 1:
                xqShangpin.setTextColor(Color.BLACK);
                xqXiangqing.setTextColor(Color.parseColor("#f29400"));
                xqPingjia.setTextColor(Color.BLACK);
                break;
            case 2:
                xqShangpin.setTextColor(Color.BLACK);
                xqXiangqing.setTextColor(Color.BLACK);
                xqPingjia.setTextColor(Color.parseColor("#f29400"));
                break;
        }
    }

    /*将三个页面加入集合*/
    public List<Fragment> setFragment() {
        list = new ArrayList<>();
        list.add(new ShangpinActivity());
        list.add(new XiangqingActivity());
        list.add(new PingjiaActivity());
        return list;
    }
}
