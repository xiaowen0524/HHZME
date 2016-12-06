package hhzmy.bwei.com.hhzme.wodeyemian;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hhzmy.bwei.com.hhzme.R;

public class YanzhengActivity extends AppCompatActivity {
    private SpannableString spanText;
    private ImageView szmmfanhui;
    private TextView yuyinyanzhengma, jieshoushoujihao;
    private ImageView yinxian;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanzheng);
        szmmfanhui();
        yuyinyanzhengma();
        mimadeyinxian();
        shouji();
    }

    /*接收上个页面传过来的手机号*/
    public void shouji() {
        jieshoushoujihao = (TextView) findViewById(R.id.jieshoushoujihao);
        Intent intent = getIntent();
        String shouji = intent.getStringExtra("shouji");
        jieshoushoujihao.setText("验证码已发送至" + shouji);
    }

    /*密码的隐藏和显示调用isshow*/
    public void mimadeyinxian() {
        password = (EditText) findViewById(R.id.password);
        yinxian = (ImageView) findViewById(R.id.yinxian);
        yinxian.setImageResource(R.mipmap.icon_hidden);
        yinxian.setTag(1);
        yinxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isshow();
            }
        });
    }

    /*密码的隐藏和显示*/
    private void isshow() {
        if (yinxian.getTag().equals(1)) {
            password.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());
            yinxian.setImageResource(R.mipmap.icon_display);
            yinxian.setTag(2);
        } else {
            yinxian.setTag(1);
            password.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            yinxian.setImageResource(R.mipmap.icon_hidden);
        }
    }

    /*返回*/
    public void szmmfanhui() {
        szmmfanhui = (ImageView) findViewById(R.id.szmmfanhui);
        szmmfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*章程*/
    public void yuyinyanzhengma() {
        yuyinyanzhengma = (TextView) findViewById(R.id.yuyinyanzhengma);
        spanText = new SpannableString("您也可以尝试1获取语音验证码");
        spanText.setSpan(new URLSpan("http://www.suning.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=%E5%93%81%E7%89%8C%E8%AF%8D-%E8%8B%8F%E5%AE%81%E6%98%93%E8%B4%AD&&utm_content=0biaoti&utm_term=u9922807.c0.g0.k21628438937.a7672144346.pb"), 6, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(new ForegroundColorSpan(Color.parseColor("#eda200")), 6, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        Drawable d = getResources().getDrawable(R.mipmap.get_voice_verifycode_icon);
        d.setBounds(0, 0, 30, 30);
        spanText.setSpan(new ImageSpan(d), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        yuyinyanzhengma.append(spanText);
        //让URLSpan可以点击
        yuyinyanzhengma.setMovementMethod(new LinkMovementMethod());
    }
}
