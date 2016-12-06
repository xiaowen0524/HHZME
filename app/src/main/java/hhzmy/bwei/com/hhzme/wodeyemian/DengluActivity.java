package hhzmy.bwei.com.hhzme.wodeyemian;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import hhzmy.bwei.com.hhzme.R;

public class DengluActivity extends AppCompatActivity implements View.OnClickListener, Handler.Callback, PlatformActionListener {

    private ImageView dlfanhui, mianfeizhuce, qqImage, sinaImage, weixinImage, yinxian;
    private Button dengluanniu;
    private TextView wangjimima;
    private EditText username, password;
    ImageView vc_image; //图标
    String getCode = null; //获取验证码的值  a
    EditText vc_code; //文本框的值

    private ImageView ivQQ, ivWeChat, ivSinaWeibo;

    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        dlfanhui();
        mianfeizhuce();
//        qqImage();
//        sinaImage();
//        weixinImage();
        wangjimima();
        dengluanniu();
        zhanghaomima();
        yanzhengma();
        mimadeyinxian();
        disanfang();
        //初始化
        ShareSDK.initSDK(this);
        ivQQ = (ImageView) findViewById(R.id.qqImage);
        ivWeChat = (ImageView) findViewById(R.id.weixinImage);
        ivSinaWeibo = (ImageView) findViewById(R.id.sinaImage);

        ivQQ.setOnClickListener(this);
        ivWeChat.setOnClickListener(this);
        ivSinaWeibo.setOnClickListener(this);
    }

    /*第三方登陆*/
    public void disanfang() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qqImage:
                //执行授权,获取用户信息
                authorize(new QQ(DengluActivity.this));
                break;
            case R.id.weixinImage:
                authorize(new Wechat(DengluActivity.this));
                break;
            case R.id.sinaImage:
                authorize(new SinaWeibo(DengluActivity.this));
                break;

        }
    }

    //执行授权,获取用户信息
    private void authorize(Platform plat) {
        if (plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(DengluActivity.this);
        //true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    //发送登陆信息
    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    protected void onDestroy() {
        //释放资源
        ShareSDK.stopSDK(DengluActivity.this);
        super.onDestroy();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_LOGIN: {
                String text = getString(R.string.logining, msg.obj);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            //登录成功,获取需要的信息
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            login(platform.getName(), platform.getDb().getUserId(), res);
            Log.e("asd", "platform.getName():" + platform.getName());
            Log.e("asd", "platform.getDb().getUserId()" + platform.getDb().getUserId());
            String openid = platform.getDb().getUserId() + "";
            String gender = platform.getDb().getUserGender();
            String head_url = platform.getDb().getUserIcon();
            String nickname = platform.getDb().getUserName();

            Log.e("asd", "openid:" + openid);
            Log.e("asd", "gender:" + gender);
            Log.e("asd", "head_url:" + head_url);
            Log.e("asd", "nickname:" + nickname);
        }
    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        t.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }


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

    /*验证码*/
    public void yanzhengma() {
        vc_image = (ImageView) findViewById(R.id.vc_image);
        vc_image.setImageBitmap(Code.getInstance().getBitmap());
        vc_code = (EditText) findViewById(R.id.vc_code);

        getCode = Code.getInstance().getCode(); //获取显示的验证码
        Log.e("info", getCode + "----");
        vc_image = (ImageView) findViewById(R.id.vc_image);
        vc_image.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                vc_image.setImageBitmap(Code.getInstance().getBitmap());
                getCode = Code.getInstance().getCode();
            }
        });

    }

    /*账号密码的点击事件*/
    public void zhanghaomima() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        username.addTextChangedListener(new PhoneTextWatcher(username));

    }

    /*返回*/
    public void dlfanhui() {
        dlfanhui = (ImageView) findViewById(R.id.dlfanhui);
        dlfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*登陆按钮*/
    public void dengluanniu() {
        dengluanniu = (Button) findViewById(R.id.dengluanniu);
        dengluanniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String v_code = vc_code.getText().toString().trim();
                if (v_code == null || v_code.equals("")) {
                    Toast.makeText(DengluActivity.this, "没有填写验证码", Toast.LENGTH_SHORT).show();
                } else if (!v_code.equals(getCode)) {
                    Toast.makeText(DengluActivity.this, "验证码填写不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DengluActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*忘记密码*/
    public void wangjimima() {
        wangjimima = (TextView) findViewById(R.id.wangjimima);
        wangjimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DengluActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    /*QQ*/
//    public void qqImage() {
//        qqImage = (ImageView) findViewById(R.id.qqImage);
//        qqImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DengluActivity.this, "QQ", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    /*新浪*/
//    public void sinaImage() {
//        sinaImage = (ImageView) findViewById(R.id.sinaImage);
//        sinaImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DengluActivity.this, "新浪", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    /*微信*/
//    public void weixinImage() {
//        weixinImage = (ImageView) findViewById(R.id.weixinImage);
//        weixinImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DengluActivity.this, "微信", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    /*免费注册*/
    public void mianfeizhuce() {
        mianfeizhuce = (ImageView) findViewById(R.id.mianfeizhuce);
        mianfeizhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DengluActivity.this, ZhuceActivity.class));
            }
        });
    }
}
