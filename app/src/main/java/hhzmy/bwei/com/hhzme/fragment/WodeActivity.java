package hhzmy.bwei.com.hhzme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.wodeyemian.DengluActivity;
import hhzmy.bwei.com.hhzme.wodeyemian.ShoucangActivity;

public class WodeActivity extends Fragment {

    private View view;
    private LinearLayout shangpinshoucang, chakanyunzuan, zuji, youhuiquan, bangdingshezhi, zhanghuanquan, daizhifu, daishouhuo, daipingjia, tuihuanweixiu;
    private ImageView xiaoxi, touxiang;
    private TextView zhanghuguanli, shezhi, shoujihao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_wode, null);
        daizhifu();
        daishouhuo();
        daipingjia();
        tuihuanweixiu();
        shangpinshoucang();
        chakanyunzuan();
        zuji();
        youhuiquan();
        bangdingshezhi();
        zhanghuanquan();
        xiaoxi();
        zhanghuguanli();
        shezhi();
        shoujihao();
        touxiang();
        return view;
    }

    /*头像*/
    public void touxiang() {
        touxiang = (ImageView) view.findViewById(R.id.touxiang);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), DengluActivity.class));
            }
        });
    }


    /*手机号*/
    public void shoujihao() {
        shoujihao = (TextView) view.findViewById(R.id.shoujihao);
        shoujihao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "手机号", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*设置*/
    public void shezhi() {
        shezhi = (TextView) view.findViewById(R.id.shezhi);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*账户管理*/
    public void zhanghuguanli() {
        zhanghuguanli = (TextView) view.findViewById(R.id.zhanghuguanli);
        zhanghuguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "账户管理", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*消息（设置后面的图标）*/
    public void xiaoxi() {
        xiaoxi = (ImageView) view.findViewById(R.id.xiaoxi);
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*待支付*/
    public void daizhifu() {
        daizhifu = (LinearLayout) view.findViewById(R.id.daizhifu);
        daizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "待支付", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*待收货*/
    public void daishouhuo() {
        daishouhuo = (LinearLayout) view.findViewById(R.id.daishouhuo);
        daishouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "待收货", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*待评价*/
    public void daipingjia() {
        daipingjia = (LinearLayout) view.findViewById(R.id.daipingjia);
        daipingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "待评价", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*退换/维修*/
    public void tuihuanweixiu() {
        tuihuanweixiu = (LinearLayout) view.findViewById(R.id.tuihuanweixiu);
        tuihuanweixiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "退换/维修", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*商品收藏*/
    public void shangpinshoucang() {
        shangpinshoucang = (LinearLayout) view.findViewById(R.id.shangpinshoucang);
        shangpinshoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShoucangActivity.class);
                startActivity(intent);
            }
        });
    }

    /*查看云钻*/
    public void chakanyunzuan() {
        chakanyunzuan = (LinearLayout) view.findViewById(R.id.chakanyunzuan);
        chakanyunzuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "查看云钻", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*足迹*/
    public void zuji() {
        zuji = (LinearLayout) view.findViewById(R.id.zuji);
        zuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "足迹", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*优惠券*/
    public void youhuiquan() {
        youhuiquan = (LinearLayout) view.findViewById(R.id.youhuiquan);
        youhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "优惠券", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*绑定设置*/
    public void bangdingshezhi() {
        bangdingshezhi = (LinearLayout) view.findViewById(R.id.bangdingshezhi);
        bangdingshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "绑定设置", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*账户安全*/
    public void zhanghuanquan() {
        zhanghuanquan = (LinearLayout) view.findViewById(R.id.zhanghuanquan);
        zhanghuanquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "账户安全", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
