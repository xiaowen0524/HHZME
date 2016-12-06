package hhzmy.bwei.com.hhzme.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.adapter.MyPagerAdapter;
import hhzmy.bwei.com.hhzme.adapter.MyaojiaoAdapter;
import hhzmy.bwei.com.hhzme.adapter.MyaojiaoAdapter2;
import hhzmy.bwei.com.hhzme.adapter.MyaojiaoAdapter3;
import hhzmy.bwei.com.hhzme.adapter.MybageAdapter;
import hhzmy.bwei.com.hhzme.adapter.MymiaoshaAdapter;
import hhzmy.bwei.com.hhzme.adapter.MymuyingAdapter1;
import hhzmy.bwei.com.hhzme.adapter.MymuyingAdapter2;
import hhzmy.bwei.com.hhzme.adapter.MyzhutiAdapter1;
import hhzmy.bwei.com.hhzme.adapter.MyzhutiAdapter2;
import hhzmy.bwei.com.hhzme.adapter.MyzhutiAdapter3;
import hhzmy.bwei.com.hhzme.adapter.MyzhutiAdapter4;
import hhzmy.bwei.com.hhzme.bean.Bean;
import hhzmy.bwei.com.hhzme.erweima.ScanActivity;
import hhzmy.bwei.com.hhzme.okhttp.OkHttp;
import hhzmy.bwei.com.hhzme.shangpinxiangqing.Shangpinxiangqing;
import hhzmy.bwei.com.hhzme.sousuoyemian.SousuoyemianActivity;
import hhzmy.bwei.com.hhzme.webview.WebActivity;
import hhzmy.bwei.com.hhzme.zidingyi.FullyGridLayoutManager;
import hhzmy.bwei.com.hhzme.zidingyi.FullyLinearLayoutManager;
import okhttp3.Request;

public class ShouyeActivity extends Fragment {

    private View view;
    private ViewPager mViewPager;
    // 记录当前的页数
    private int mCount = 0;
    // 开始
    public static final int START = -1;
    // 停止
    public static final int STOP = -2;
    // 更新
    public static final int UPDATE = -3;
    // 接受传过来的当前页面数
    public static final int RECORD = -4;
    private List<ImageView> mList;
    private MyPagerAdapter mAdapter;
    private List<String> urlList;
    private ImageView dot1, dot2, dot3, dot0;
    private ImageView[] dots = new ImageView[4];
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case START:
                    handler.sendEmptyMessageDelayed(UPDATE, 3000);
                    break;
                case STOP:
                    handler.removeMessages(UPDATE);
                    break;
                case UPDATE:
                    mCount++;
                    mViewPager.setCurrentItem(mCount);
                    break;
                case RECORD:
                    mCount = msg.arg1;
                    break;

                default:
                    break;
            }

        }
    };
    /*************************************************************************************************/

    private RecyclerView zhutirecyclerview1, zhutirecyclerview2, zhutirecyclerview3, zhutirecyclerview4, muyingrecyclerview1, muyingrecyclerview2, bagerecyclerview, miaosharecyclerview, aojiaorecyclerview1, aojiaorecyclerview2, aojiaorecyclerview3;
    private String path = "http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";
    private MybageAdapter mybageAdapter;
    private MymiaoshaAdapter mymiaoshaAdapter;
    private MyaojiaoAdapter myaojiaoAdapter;
    private MyaojiaoAdapter2 myaojiaoAdapter2;
    private MyaojiaoAdapter3 myaojiaoAdapter3;
    private MymuyingAdapter1 mymuyingAdapter1;
    private MymuyingAdapter2 mymuyingAdapter2;
    private MyzhutiAdapter1 myzhutiAdapter1;
    private MyzhutiAdapter2 myzhutiAdapter2;
    private MyzhutiAdapter3 myzhutiAdapter3;
    private MyzhutiAdapter4 myzhutiAdapter4;
    private ImageView xiangqing;
    private Bean bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_shouye, null);
        //跳转到搜索页面
        SousuoyemianTiaozhuan();
        //无限轮播
        wuxian();
        //二维码
        erweima();
        //网络请求
        OkhttpDemo();
        xiangqing = (ImageView) view.findViewById(R.id.xiangqing);
        xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    /*二维码*/
    public void erweima() {
        ImageView erweima = (ImageView) view.findViewById(R.id.erweima);
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScanActivity.class));
            }
        });
    }

    /*八个按钮RecyclerView的使用*/
    public void setRecyclerView() {
        bagerecyclerview = (RecyclerView) view.findViewById(R.id.bagerecyclerview);
        //设置线性布局效果
        FullyGridLayoutManager linearLayoutManager = new FullyGridLayoutManager(getActivity(), 4);
        bagerecyclerview.setLayoutManager(linearLayoutManager);
        mybageAdapter = new MybageAdapter(getActivity(), bean);
        bagerecyclerview.setAdapter(mybageAdapter);
        mybageAdapter.setOnItemClickListener(new MybageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "八个按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", bean.getData().get(1).getTag().get(position).getLinkUrl());
                intent.putExtra("name", bean.getData().get(1).getTag().get(position).getElementName());
                startActivity(intent);
            }
        });
    }

    /*十点秒杀*/
    public void shidianmiaosha() {
        miaosharecyclerview = (RecyclerView) view.findViewById(R.id.miaosharecyclerview);
        //设置线性布局效果
        FullyLinearLayoutManager linearLayoutManagers = new FullyLinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(FullyLinearLayoutManager.HORIZONTAL);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        miaosharecyclerview.setLayoutManager(linearLayoutManagers);
        mymiaoshaAdapter = new MymiaoshaAdapter(getActivity(), bean);
        miaosharecyclerview.setAdapter(mymiaoshaAdapter);
        mymiaoshaAdapter.setOnItemClickListener(new MymiaoshaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), Shangpinxiangqing.class));
            }
        });

    }

    /*主题特卖1*/
    public void zhutitemai1() {
        zhutirecyclerview1 = (RecyclerView) view.findViewById(R.id.zhutirecyclerview1);
        //设置线性布局效果
        FullyLinearLayoutManager linearLayoutManagers = new FullyLinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(FullyLinearLayoutManager.HORIZONTAL);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        zhutirecyclerview1.setLayoutManager(linearLayoutManagers);
        myzhutiAdapter1 = new MyzhutiAdapter1(getActivity(), bean);
        zhutirecyclerview1.setAdapter(myzhutiAdapter1);
        myzhutiAdapter1.setOnItemClickListener(new MyzhutiAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), Shangpinxiangqing.class));
            }
        });
    }

    /*主题特卖2*/
    public void zhutitemai2() {
        zhutirecyclerview2 = (RecyclerView) view.findViewById(R.id.zhutirecyclerview2);
        //设置线性布局效果
        FullyLinearLayoutManager linearLayoutManagers = new FullyLinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(FullyLinearLayoutManager.HORIZONTAL);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        zhutirecyclerview2.setLayoutManager(linearLayoutManagers);
        myzhutiAdapter2 = new MyzhutiAdapter2(getActivity(), bean);
        zhutirecyclerview2.setAdapter(myzhutiAdapter2);
        myzhutiAdapter2.setOnItemClickListener(new MyzhutiAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), Shangpinxiangqing.class));
            }
        });
    }

    /*主题特卖3*/
    public void zhutitemai3() {
        zhutirecyclerview3 = (RecyclerView) view.findViewById(R.id.zhutirecyclerview3);
        //设置线性布局效果
        FullyLinearLayoutManager linearLayoutManagers = new FullyLinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(FullyLinearLayoutManager.HORIZONTAL);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        zhutirecyclerview3.setLayoutManager(linearLayoutManagers);
        myzhutiAdapter3 = new MyzhutiAdapter3(getActivity(), bean);
        zhutirecyclerview3.setAdapter(myzhutiAdapter3);
        myzhutiAdapter3.setOnItemClickListener(new MyzhutiAdapter3.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), Shangpinxiangqing.class));
            }
        });
    }

    /*主题特卖4*/
    public void zhutitemai4() {
        zhutirecyclerview4 = (RecyclerView) view.findViewById(R.id.zhutirecyclerview4);
        //设置线性布局效果
        FullyLinearLayoutManager linearLayoutManagers = new FullyLinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(FullyLinearLayoutManager.HORIZONTAL);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        zhutirecyclerview4.setLayoutManager(linearLayoutManagers);
        myzhutiAdapter4 = new MyzhutiAdapter4(getActivity(), bean);
        zhutirecyclerview4.setAdapter(myzhutiAdapter4);
        myzhutiAdapter4.setOnItemClickListener(new MyzhutiAdapter4.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), Shangpinxiangqing.class));
            }
        });
    }

    //母婴专区1
    public void muyingzhuanqu1() {
        muyingrecyclerview1 = (RecyclerView) view.findViewById(R.id.muyingrecyclerview1);
        //设置线性布局效果
        FullyGridLayoutManager linearLayoutManagers = new FullyGridLayoutManager(getActivity(), 2);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        muyingrecyclerview1.setLayoutManager(linearLayoutManagers);
        mymuyingAdapter1 = new MymuyingAdapter1(getActivity(), bean);
        muyingrecyclerview1.setAdapter(mymuyingAdapter1);
        mymuyingAdapter1.setOnItemClickListener(new MymuyingAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", bean.getData().get(10).getTag().get(position).getLinkUrl());
                intent.putExtra("name", bean.getData().get(10).getTag().get(position).getElementName());
                startActivity(intent);
            }
        });
    }

    //母婴专区2
    public void muyingzhuanqu2() {
        muyingrecyclerview2 = (RecyclerView) view.findViewById(R.id.muyingrecyclerview2);
        //设置线性布局效果
        FullyGridLayoutManager linearLayoutManagers = new FullyGridLayoutManager(getActivity(), 4);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        muyingrecyclerview2.setLayoutManager(linearLayoutManagers);
        mymuyingAdapter2 = new MymuyingAdapter2(getActivity(), bean);
        muyingrecyclerview2.setAdapter(mymuyingAdapter2);
        mymuyingAdapter2.setOnItemClickListener(new MymuyingAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", bean.getData().get(11).getTag().get(position).getLinkUrl());
                intent.putExtra("name", bean.getData().get(11).getTag().get(position).getElementName());
                startActivity(intent);
            }
        });
    }

    /*傲娇品牌*/
    public void aojiaopinpai() {
        aojiaorecyclerview1 = (RecyclerView) view.findViewById(R.id.aojiaorecyclerview1);
        //设置线性布局效果
        FullyGridLayoutManager linearLayoutManagers = new FullyGridLayoutManager(getActivity(), 2);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        aojiaorecyclerview1.setLayoutManager(linearLayoutManagers);
        myaojiaoAdapter = new MyaojiaoAdapter(getActivity(), bean);
        aojiaorecyclerview1.setAdapter(myaojiaoAdapter);
        myaojiaoAdapter.setOnItemClickListener(new MyaojiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", bean.getData().get(5).getTag().get(position).getLinkUrl());
                intent.putExtra("name", bean.getData().get(5).getTag().get(position).getElementName());
                startActivity(intent);
            }
        });
    }

    /*傲娇品牌*/
    public void aojiaopinpai2() {
        aojiaorecyclerview2 = (RecyclerView) view.findViewById(R.id.aojiaorecyclerview2);
        //设置线性布局效果
        FullyGridLayoutManager linearLayoutManagers = new FullyGridLayoutManager(getActivity(), 2);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        aojiaorecyclerview2.setLayoutManager(linearLayoutManagers);
        myaojiaoAdapter2 = new MyaojiaoAdapter2(getActivity(), bean);
        aojiaorecyclerview2.setAdapter(myaojiaoAdapter2);
        myaojiaoAdapter2.setOnItemClickListener(new MyaojiaoAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", bean.getData().get(6).getTag().get(position).getLinkUrl());
                intent.putExtra("name", bean.getData().get(6).getTag().get(position).getElementName());
                startActivity(intent);
            }
        });
    }

    /*傲娇品牌*/
    public void aojiaopinpai3() {
        aojiaorecyclerview3 = (RecyclerView) view.findViewById(R.id.aojiaorecyclerview3);
        //设置线性布局效果
        FullyGridLayoutManager linearLayoutManagers = new FullyGridLayoutManager(getActivity(), 2);
//        bagerecyclerview.setNestedScrollingEnabled(false);
        aojiaorecyclerview3.setLayoutManager(linearLayoutManagers);
        myaojiaoAdapter3 = new MyaojiaoAdapter3(getActivity(), bean);
        aojiaorecyclerview3.setAdapter(myaojiaoAdapter3);
        myaojiaoAdapter3.setOnItemClickListener(new MyaojiaoAdapter3.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", bean.getData().get(7).getTag().get(position).getLinkUrl());
                intent.putExtra("name", bean.getData().get(7).getTag().get(position).getElementName());
                startActivity(intent);
            }
        });
    }

    /*跳转到搜索页面*/
    public void SousuoyemianTiaozhuan() {
        LinearLayout sousuoyemianT = (LinearLayout) view.findViewById(R.id.shousuoyemianT);
        sousuoyemianT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SousuoyemianActivity.class);
                startActivity(intent);
            }
        });
    }

    /*网络数据请求*/
    public void OkhttpDemo() {
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                bean = gson.fromJson(result, Bean.class);
                //八个按钮
                setRecyclerView();
                //十点秒杀
                shidianmiaosha();
                //主题特卖
                zhutitemai1();
                zhutitemai2();
                zhutitemai3();
                zhutitemai4();
                //母婴专区
                muyingzhuanqu1();
                muyingzhuanqu2();
                //傲娇品牌
                aojiaopinpai();
                aojiaopinpai2();
                aojiaopinpai3();


                ImageView miaoshabiaoti = (ImageView) view.findViewById(R.id.miaoshabiaoti);
                ImageView lamaImage1 = (ImageView) view.findViewById(R.id.lamaImage1);
                ImageView lamaImage2 = (ImageView) view.findViewById(R.id.lamaImage2);
                ImageView lamaImage3 = (ImageView) view.findViewById(R.id.lamaImage3);
                ImageView lamaImage4 = (ImageView) view.findViewById(R.id.lamaImage4);
                ImageView lamaImage5 = (ImageView) view.findViewById(R.id.lamaImage5);
                ImageView chakangengduo = (ImageView) view.findViewById(R.id.chakangengduo);
                ImageView aojiaopinpai = (ImageView) view.findViewById(R.id.aojiaopinpai);
                ImageView muyingzhuanqu = (ImageView) view.findViewById(R.id.muyingzhuanqu);
                ImageView zhutitemai = (ImageView) view.findViewById(R.id.zhutitemai);
                ImageView zhutitemai1 = (ImageView) view.findViewById(R.id.zhutitemai1);
                ImageView zhutitemai2 = (ImageView) view.findViewById(R.id.zhutitemai2);
                ImageView zhutitemai3 = (ImageView) view.findViewById(R.id.zhutitemai3);
                ImageView zhutitemai4 = (ImageView) view.findViewById(R.id.zhutitemai4);
                ImageView lamaImage = (ImageView) view.findViewById(R.id.lamaImage);

                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(2).getTag().get(0).getPicUrl(), miaoshabiaoti);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(23).getTag().get(0).getPicUrl(), lamaImage);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(24).getTag().get(0).getPicUrl(), lamaImage1);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(26).getTag().get(0).getPicUrl(), lamaImage2);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(28).getTag().get(0).getPicUrl(), lamaImage3);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(30).getTag().get(0).getPicUrl(), lamaImage4);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(32).getTag().get(0).getPicUrl(), lamaImage5);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(33).getTag().get(0).getPicUrl(), chakangengduo);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(4).getTag().get(0).getPicUrl(), aojiaopinpai);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(9).getTag().get(0).getPicUrl(), muyingzhuanqu);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(13).getTag().get(0).getPicUrl(), zhutitemai);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(14).getTag().get(0).getPicUrl(), zhutitemai1);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(16).getTag().get(0).getPicUrl(), zhutitemai2);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(18).getTag().get(0).getPicUrl(), zhutitemai3);
                imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(20).getTag().get(0).getPicUrl(), zhutitemai4);

                lamaImage1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://pin.m.suning.com/pgs/product/90ab6268.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                lamaImage2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://pin.m.suning.com/pgs/product/a8ad308c.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                lamaImage3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://pin.m.suning.com/pgs/product/34360549.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                lamaImage4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://pin.m.suning.com/pgs/product/40fec45c.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                lamaImage5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://pin.m.suning.com/pgs/product/783494a7.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                zhutitemai1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://c.m.suning.com/hwg140516.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                zhutitemai2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://ju.m.suning.com/?adTypeCode=1109&adId=0";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                zhutitemai3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://c.m.suning.com/redbaby_lfh.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                zhutitemai4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String url = "http://c.m.suning.com/honghaiziqiang.html";
                        String name = "商品详情";
                        intent.putExtra("name", name);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    /*无限轮播*/
    public void wuxian() {
        initView();
        init();
        setListener();
        mAdapter = new MyPagerAdapter(mList);
        mViewPager.setAdapter(mAdapter);
        int i = Integer.MAX_VALUE / 2 % mList.size();
        // 默认在中间，让用户看不到边界
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - i);
        handler.sendEmptyMessage(START);
    }

    /*无限轮播方法*/
    private void init() {
        // TODO Auto-generated method stub
        urlList = new ArrayList<String>();
        mList = new ArrayList<ImageView>();
        urlList.add("http://image1.suning.cn//uimg/cms/img/147894494764004158.png");
        urlList.add("http://image1.suning.cn//uimg/cms/img/147884585000874489.jpg");
        urlList.add("http://image1.suning.cn//uimg/cms/img/147704410779295661.png");
        urlList.add("http://image1.suning.cn//uimg/cms/img/147877125414433459.jpg");
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).displayer(new RoundedBitmapDisplayer(50))
                .displayer(new FadeInBitmapDisplayer(100)).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageView imageView;
        for (int i = 0; i < 4; i++) {
            imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 使用的ImageLoader网络加载图片，需先在Application和清单文件中配置在使用
            ImageLoader.getInstance().displayImage(urlList.get(i), imageView,
                    options);
            mList.add(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("==========m==" + mCount % mList.size());
                    // 这里写点击图片的操作 mCount % mList.size()这个点击的第几个图片
                }
            });
        }
    }

    /*无限*/
    private void initView() {
        // TODO Auto-generated method stub
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        dot0 = (ImageView) view.findViewById(R.id.dot1);
        dot1 = (ImageView) view.findViewById(R.id.dot2);
        dot2 = (ImageView) view.findViewById(R.id.dot3);
        dot3 = (ImageView) view.findViewById(R.id.dot4);
        dots[0] = dot0;
        dots[1] = dot1;
        dots[2] = dot2;
        dots[3] = dot3;
        dot0.setSelected(true);
    }

    /*无限*/
    private void setListener() {
        // TODO Auto-generated method stub
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                handler.sendMessage(Message.obtain(handler, RECORD, arg0, 0));
                // 下面是控制点的变化
                int j = arg0 % mList.size();
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setSelected(false);
                }
                dots[j].setSelected(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                switch (arg0) {
                    // 当滑动时让当前轮播停止
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(STOP);
                        break;
                    // 滑动停止时继续轮播
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessage(START);
                        break;
                }
            }
        });

    }
}