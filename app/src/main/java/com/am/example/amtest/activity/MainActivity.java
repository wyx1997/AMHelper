package com.am.example.amtest.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.am.example.amtest.R;
import com.am.example.amtest.entity.ViewPagerEventMsg;
import com.am.example.amtest.fragment.GenzongFragment;
import com.am.example.amtest.fragment.JindiaoFragment;
import com.am.example.amtest.fragment.LuntanFragment;
import com.am.example.amtest.fragment.MyFragment;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.runtime.PermissionRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private static final int REQUEST_SCAN = 0;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_bottom_radio_group)
    RadioGroup radioGroup;

    private JindiaoFragment jindiaoFragment;

    private MyFragment myFragment;

    private LuntanFragment luntanFragment;

    private GenzongFragment genzongFragment;

    private View jindiaoToolbar;

    private View myToolbar;

    private View luntanToolbar;

    private View commonToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initToolbar(toolbar);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.main_radio_bn_jindiao);
        requestPermission(Permission.CAMERA)
                .start();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        jindiaoToolbar = getLayoutInflater().inflate(R.layout.toolbar_jindiao, null, false);
        myToolbar = getLayoutInflater().inflate(R.layout.toolbar_my, null, false);
        commonToolbar = getLayoutInflater().inflate(R.layout.toolbar_common, null, false);
        luntanToolbar = getLayoutInflater().inflate(R.layout.toolbar_luntan, null, false);

        ((TextView)commonToolbar.findViewById(R.id.toolbar_common_middle_text)).setText(getText(R.string.info_genzong));
        ((ImageView)commonToolbar.findViewById(R.id.toolbar_common_left_image)).setImageResource(R.drawable.common_search);
        ((ImageView)commonToolbar.findViewById(R.id.toolbar_common_right_image)).setImageResource(R.drawable.common_menu);

        jindiaoToolbar.findViewById(R.id.toolbar_jindiao_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AndPermission.hasPermissions(MainActivity.this, Permission.CAMERA)){
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_SCAN);
                } else {
                    requestPermission(Permission.CAMERA)
                            .onGranted(new Action<List<String>>() {
                                @Override
                                public void onAction(List<String> data) {
                                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                                    startActivityForResult(intent, REQUEST_SCAN);
                                }
                            })
                            .start();
                }
            }
        });

        RadioGroup radioGroup = luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_gp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            private ViewPagerEventMsg viewPagerEventMsg;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                clearCheckedTextColor();
                int position;
                switch (checkedId){
                    case R.id.toolbar_luntan_middle_radio_dongtai:
                        position = 0;
                        ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_dongtai))
                                .setTextColor(getResources().getColor(R.color.light_blue));
                        break;
                    case R.id.toolbar_luntan_middle_radio_heimingdan:
                        position = 1;
                        ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_heimingdan))
                                .setTextColor(getResources().getColor(R.color.light_blue));
                        break;
                    default:
                        position = 2;
                        ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_guanzhu))
                                .setTextColor(getResources().getColor(R.color.light_blue));
                }
                if(viewPagerEventMsg == null){
                    viewPagerEventMsg = new ViewPagerEventMsg(position);
                } else {
                    viewPagerEventMsg.setPosition(position);
                }
                EventBus.getDefault().post(position);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPosMsg(ViewPagerEventMsg msg){
        if(luntanToolbar == null) return;
        removeStickyEvent(msg);
        clearCheckedTextColor();
        switch (msg.getPosition()){
            case 0:
                ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_dongtai))
                        .setTextColor(getResources().getColor(R.color.light_blue));
                break;
            case 1:
                ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_heimingdan))
                        .setTextColor(getResources().getColor(R.color.light_blue));
                break;
            case 2:
                ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_guanzhu))
                        .setTextColor(getResources().getColor(R.color.light_blue));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId){
            case R.id.main_radio_bn_jindiao:
                if(jindiaoFragment == null){
                    jindiaoFragment = JindiaoFragment.newInstance();
                    transaction.add(R.id.main_fragment_container, jindiaoFragment);
                } else {
                    transaction.show(jindiaoFragment);
                }
                setJindiaoToolbar();
                break;
            case R.id.main_radio_bn_my:
                if(myFragment == null) {
                    myFragment = MyFragment.newInstance();
                    transaction.add(R.id.main_fragment_container, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                setMyToolbar();
                break;
            case R.id.main_radio_bn_genzong:
                if(genzongFragment == null) {
                    genzongFragment = GenzongFragment.newInstance();
                    transaction.add(R.id.main_fragment_container, genzongFragment);
                } else {
                    transaction.show(genzongFragment);
                }
                setGenzongToolbar();
                break;
            case R.id.main_radio_bn_luntan:
                if(luntanFragment == null) {
                    luntanFragment = LuntanFragment.newInstance();
                    transaction.add(R.id.main_fragment_container, luntanFragment);
                } else {
                    transaction.show(luntanFragment);
                }
                setLuntanToolbar();
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterEventBus(this);
    }

    private void hideAllFragment(FragmentTransaction transaction){
        if(jindiaoFragment != null){
            transaction.hide(jindiaoFragment);
        }
        if(genzongFragment != null){
            transaction.hide(genzongFragment);
        }
        if(luntanFragment != null){
            transaction.hide(luntanFragment);
        }
        if(myFragment != null){
            transaction.hide(myFragment);
        }
    }

    private void setAdapterToolbar(){
        toolbar.removeAllViews();
        toolbar.setBackgroundColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.WHITE);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.stroke_grey));
        }
    }

    private void setJindiaoToolbar(){
        setAdapterToolbar();
        toolbar.addView(jindiaoToolbar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void setMyToolbar(){
        setAdapterToolbar();
        toolbar.addView(myToolbar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void setCommonToolbar(){
        toolbar.removeAllViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.main_bottom_navi_bg, null));
            toolbar.setBackgroundColor(getResources().getColor(R.color.main_bottom_navi_bg, null));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.stroke_grey));
            toolbar.setBackgroundColor(Color.WHITE);
        }
    }

    private void setGenzongToolbar(){
        setCommonToolbar();
        toolbar.addView(commonToolbar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void setLuntanToolbar(){
        setCommonToolbar();
        toolbar.addView(luntanToolbar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RadioGroup radioGroup = luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_gp);
        radioGroup.check(R.id.toolbar_luntan_middle_radio_dongtai);
    }

    private void clearCheckedTextColor(){
        if(luntanToolbar != null){
            ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_dongtai))
                    .setTextColor(getResources().getColor(R.color.luntan_content_dark_grey));
            ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_heimingdan))
                    .setTextColor(getResources().getColor(R.color.luntan_content_dark_grey));
            ((RadioButton)luntanToolbar.findViewById(R.id.toolbar_luntan_middle_radio_guanzhu))
                    .setTextColor(getResources().getColor(R.color.luntan_content_dark_grey));
        }
    }

    private PermissionRequest requestPermission(String... permissions) {
        return AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
                            showSettingDialog(MainActivity.this, permissions);
                        }
                    }
                });
    }

    public void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.not_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
