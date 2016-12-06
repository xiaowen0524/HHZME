package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hhzmy.bwei.com.hhzme.R;
import hhzmy.bwei.com.hhzme.pay.PayDemoActivity;

public class QuerendingdanActivity extends AppCompatActivity {

    private ImageView ddFanhui;
    private TextView tijiaodingdan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querendingdan);
        tijiaodingdan();
        fanhui();
    }

    public void tijiaodingdan() {
        tijiaodingdan = (TextView) findViewById(R.id.tijiaodingdan);
        tijiaodingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuerendingdanActivity.this, PayDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    /*返回*/
    public void fanhui() {
        ddFanhui = (ImageView) findViewById(R.id.ddFanhui);
        ddFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
