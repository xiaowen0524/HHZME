package hhzmy.bwei.com.hhzme.wodeyemian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import hhzmy.bwei.com.hhzme.dao.ScUser;
import hhzmy.bwei.com.hhzme.dao.ScUserDao;

public class ShoucangActivity extends AppCompatActivity {
    // 获取控件
    private GridView lv;
    private MyAdapter adapter;
    private List<ScUser> list;
    private ScUserDao dao;
    private TextView zongji;
    private Button jiesuan;
    private ImageView scFanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        fanhui();
        init();
    }

    public void fanhui() {
        scFanhui = (ImageView) findViewById(R.id.scFanhui);
        scFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    // 点击跳转到商品详情
    private void init() {
        list = new ArrayList<ScUser>();
        dao = new ScUserDao(ShoucangActivity.this);
        lv = (GridView) findViewById(R.id.gwGridView);
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
                convertView = LayoutInflater.from(ShoucangActivity.this).inflate(R.layout.item_gwc, null);
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
                    Toast.makeText(ShoucangActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
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
