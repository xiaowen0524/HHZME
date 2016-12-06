package hhzmy.bwei.com.hhzme.wodeyemian;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hhzmy.bwei.com.hhzme.R;

public class ZhuceActivity extends AppCompatActivity {

    private TextView zhangcheng;
    private SpannableString spanText;
    private ImageView yzsjhfanhui;
    private Button xiayibu;
    private EditText shoujihao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        shoujihao = (EditText) findViewById(R.id.shoujihao);
        zhangcheng();
        yzsjhfanhui();
        xiayibu();
    }


    /*返回*/
    public void yzsjhfanhui() {
        yzsjhfanhui = (ImageView) findViewById(R.id.yzsjhfanhui);
        yzsjhfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*章程*/
    public void zhangcheng() {
        zhangcheng = (TextView) findViewById(R.id.zhangcheng);
        spanText = new SpannableString("同意苏宁易购章程和易付宝协议");
        spanText.setSpan(new URLSpan("http://www.suning.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=%E5%93%81%E7%89%8C%E8%AF%8D-%E8%8B%8F%E5%AE%81%E6%98%93%E8%B4%AD&&utm_content=0biaoti&utm_term=u9922807.c0.g0.k21628438937.a7672144346.pb"), 2, 8,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(new URLSpan("http://www.suning.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=%E5%93%81%E7%89%8C%E8%AF%8D-%E8%8B%8F%E5%AE%81%E6%98%93%E8%B4%AD&&utm_content=0biaoti&utm_term=u9922807.c0.g0.k21628438937.a7672144346.pb"), 9, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(new ForegroundColorSpan(Color.parseColor("#eda200")), 2, 8,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(new ForegroundColorSpan(Color.parseColor("#eda200")), 9, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        zhangcheng.append(spanText);
        //让URLSpan可以点击
        zhangcheng.setMovementMethod(new LinkMovementMethod());
    }

    /*下一步*/
    public void xiayibu() {
        xiayibu = (Button) findViewById(R.id.xiayibu);
        xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhuceActivity.this, YanzhengActivity.class);
                intent.putExtra("shoujihao", shoujihao.toString());
                startActivity(intent);
            }
        });
    }


}
