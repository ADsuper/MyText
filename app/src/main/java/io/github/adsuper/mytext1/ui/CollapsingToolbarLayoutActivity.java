package io.github.adsuper.mytext1.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.adsuper.mytext1.R;

import static io.github.adsuper.mytext1.R.id.imageview;

/**
 * 可折叠式标题栏
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    @BindView(imageview)
    ImageView mImageview;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout mCollapsingtoolbarlayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.textview_card)
    TextView mTextviewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsingtoolbarlayout);
        ButterKnife.bind(this);

        mToolbar.setTitle("可折叠式标题栏");

        try {
            Field field = mToolbar.getClass().getField("mTitleTextView");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        Glide.with(this).load(R.drawable.guide_bg_me).into(mImageview);



    }
}
