package hhzmy.bwei.com.hhzme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.bean.Bean;

/**
 * Created by asus on 2016/11/15.
 */
public class MyzhutiAdapter4 extends RecyclerView.Adapter<MyzhutiAdapter4.MyViewHodler> {
    private Context context;
    private Bean bean;

    private static RecyclerView.Adapter Adapter;
    private static OnItemClickListener mOnItemClickListener;
    //设置点击和长按事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position); //设置点击事件

    }
    public static void setOnItemClickListener(OnItemClickListener listener)     {
        mOnItemClickListener = listener;
    }

    public MyzhutiAdapter4(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public MyzhutiAdapter4.MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler hodler = new MyViewHodler(LayoutInflater.from(context).inflate(R.layout.item_miaosha, parent, false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(final MyzhutiAdapter4.MyViewHodler holder, final int position) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("http://image1.suning.cn/" + bean.getData().get(21).getTag().get(position).getPicUrl(), holder.miaoshaImage);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() { //itemview是holder里的所有控件，可以单独设置比如ImageButton Button等
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bean.getData().get(21).getTag().size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        ImageView miaoshaImage;

        public MyViewHodler(View itemView) {
            super(itemView);
            miaoshaImage = (ImageView) itemView.findViewById(R.id.miaoshaImage);
        }
    }
}
