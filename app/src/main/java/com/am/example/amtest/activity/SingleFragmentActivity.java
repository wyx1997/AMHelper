package com.am.example.amtest.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.example.amtest.R;
import com.am.example.amtest.fragment.JindiaoListFragment;

import butterknife.BindView;

public class SingleFragmentActivity extends BaseActivity {

    public static final String FRAGMENT_START = "start_fragment";
    public static final int JINDIAO_LIST = 0x00;

    @BindView(R.id.single_fragment_toolbar)
    View toolbar;

    private int fragmentId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    protected void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.main_bottom_navi_bg, null));
            toolbar.setBackgroundColor(getResources().getColor(R.color.main_bottom_navi_bg, null));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.stroke_grey));
            toolbar.setBackgroundColor(Color.WHITE);
        }
        ((TextView)toolbar.findViewById(R.id.toolbar_common_middle_text)).setText(getText(R.string.jindiao_list_title));
        ImageView leftImage = toolbar.findViewById(R.id.toolbar_common_left_image);
        leftImage.setImageResource(R.drawable.back_icon);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)toolbar.findViewById(R.id.toolbar_common_right_image)).setImageResource(R.drawable.common_menu);
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        fragmentId = intent.getIntExtra(FRAGMENT_START, JINDIAO_LIST);
        handleFragmentId(fragmentId);
    }

    private void handleFragmentId(int fragmentId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (fragmentId){
            case JINDIAO_LIST:
                Fragment fragment = JindiaoListFragment.newInstance();
                transaction.replace(R.id.single_fragment_container, fragment);
        }
        transaction.commit();
    }
}
