package hhzmy.bwei.com.hhzme.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.activity.MainActivity;


public class AlphaActivity extends AppCompatActivity implements AnimationListener {

    ImageView welcomeimage;
    Animation alpahAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        welcomeimage = (ImageView) findViewById(R.id.welcomeimage);
        alpahAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
        welcomeimage.setAnimation(alpahAnimation);
        alpahAnimation.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "您好", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(this, "欢迎来到红孩子", Toast.LENGTH_LONG).show();
        // 判断是否加载过引导页
        if (isGuide()) {
            Intent intent = new Intent(AlphaActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(AlphaActivity.this, MainActivity.class);
            startActivity(intent);
        }
        //设置切换动画，从右边进入，左边退出,带动态效果
        overridePendingTransition(R.anim.enter_from_right,
                R.anim.out_exist_left);
    }

    /**
     * 判断是否引导过，如果引导过，则直接去主页main
     */
    private Boolean isGuide() {
        SharedPreferences preferences = getSharedPreferences(Utils.getGuideName(), Context.MODE_PRIVATE);
        return preferences.getBoolean(Utils.getGuideKey(), false);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub
        /**
         * android:repeatCount="1"
         */
        Toast.makeText(this, "重复加载动画", Toast.LENGTH_LONG).show();
    }
}
