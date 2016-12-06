package hhzmy.bwei.com.hhzme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.dao.GwUser;
import hhzmy.bwei.com.hhzme.dao.GwUserDao;
import hhzmy.bwei.com.hhzme.shangpinxiangqing.QuerendingdanActivity;


public class GouwucheActivity extends Fragment {
    // 获取控件
    private GridView lv;
    private MyAdapter adapter;
    private List<GwUser> list;
    private GwUserDao dao;
    private View view;
    private TextView zongji;
    private Button jiesuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_gouwuche, null);
        init();
        jiesuan();
        return view;
    }

    public void jiesuan() {
        zongji = (TextView) view.findViewById(R.id.zongji);
        jiesuan = (Button) view.findViewById(R.id.jiesuan);
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuerendingdanActivity.class);
                startActivity(intent);
            }
        });
    }

    // 点击跳转到商品详情
    private void init() {
        list = new ArrayList<GwUser>();
        dao = new GwUserDao(getActivity());
        lv = (GridView) view.findViewById(R.id.gwGridView);
        xiba();
    }

    private void xiba() {
        list = dao.select();
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {

        public int getCount() {
            return list.size();
        }

        public Object getItem(int position) {
            return list.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_gwc, null);
                vh.name = (TextView) convertView.findViewById(R.id.name);
                vh.price = (TextView) convertView.findViewById(R.id.price);
                vh.image = (ImageView) convertView.findViewById(R.id.image);
                vh.del = (ImageView) convertView.findViewById(R.id.del);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.name.setText(list.get(position).getName());
            vh.price.setText("￥" + list.get(position).getPrice());

            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(list.get(position).getImage(), vh.image);
            vh.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "已删除", Toast.LENGTH_SHORT).show();
                    int id = list.get(position).getId();
                    dao.delete(id);
                    xiba();
                }
            });

            return convertView;
        }
    }

    class ViewHolder {
        TextView name;
        TextView price;
        ImageView image, del;
    }
}