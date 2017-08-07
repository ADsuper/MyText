package io.github.adsuper.mytext1.statebarandnavigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.adsuper.mytext1.R;

public class StatesBarAndNavigationBarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.color_bar)
    Button mColorBar;
    @BindView(R.id.immersion_bar)
    Button mImmersionBar;
    @BindView(R.id.transparent_bar_1)
    Button mTransparentBar1;
    @BindView(R.id.transparent_bar_2)
    Button mTransparentBar2;
    @BindView(R.id.hint_bar_1)
    Button mHintBar1;
    @BindView(R.id.hint_bar_2)
    Button mHintBar2;
    @BindView(R.id.drawer_color_bar)
    Button mDrawerColorBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_bar_and_navigation_bar);
        ButterKnife.bind(this);


        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this,R.color.DeepSkyBlue),100);
//        ultimateBar.setImmersionBar();


        mToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.DeepSkyBlue));
        setSupportActionBar(mToolbar);

    }

    @OnClick({R.id.color_bar, R.id.immersion_bar, R.id.transparent_bar_1, R.id.transparent_bar_2, R.id.hint_bar_1, R.id.hint_bar_2, R.id.drawer_color_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.color_bar:
                startActivity(new Intent(this, ColorBarActivity.class));
                break;

            case R.id.immersion_bar:
                startActivity(new Intent(this, ImmersionBarActivity.class));
                break;

            case R.id.transparent_bar_1:
                startActivity(new Intent(this, TransparentBarActivity1.class));
                break;

            case R.id.transparent_bar_2:
                startActivity(new Intent(this, TransparentBarActivity2.class));
                break;

            case R.id.hint_bar_1:
                startActivity(new Intent(this, HintBarActivity1.class));
                break;

            case R.id.hint_bar_2:
                startActivity(new Intent(this, HintBarActivity2.class));
                break;

            case R.id.drawer_color_bar:
                startActivity(new Intent(this, DrawerActivity.class));
            default:

                break;
        }
    }
}
