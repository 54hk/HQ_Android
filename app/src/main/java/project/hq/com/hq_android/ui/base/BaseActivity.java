package project.hq.com.hq_android.ui.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.hq.com.hq_android.MyApplication;
import project.hq.com.hq_android.R;
import project.hq.com.hq_android.utils.StatusBarUtil;
import project.hq.com.hq_android.utils.auto.AutoUtils;


/**
 * Created by 54hk on 2017/12/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean openCjsStatusBar = true;
    private Unbinder unbinder;

    public void setOpenCjsStatusBar(boolean openCjsStatusBar) {
        this.openCjsStatusBar = openCjsStatusBar;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        cjsStatusBar();

        if (Build.VERSION.SDK_INT > 22) {
            if (hodeWindow())
            {
                StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    StatusBarUtil.setStatusBarDarkMode(this);
                    StatusBarUtil.setColor(this, getResources().getColor(R.color.white),0);
                    StatusBarUtil.setStatusBarLightMode(this.getWindow());
                }
            }
        }
        MyApplication.baseActivity = this;
        setContentView(initLayout());
        unbinder = ButterKnife.bind(this);
        AutoUtils.auto(this);
        initView();
        initData();
        init();
        initListener();
//        initSystemBottomBar("#000000", this);

    }

    /**
     * 初始化布局
     */
    public abstract int initLayout();

    /**
     * 初始化一些的控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected abstract boolean hodeWindow();

    public void initListener() {
    }

    public void init() {

    }

    public void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarDarkMode(this);
            StatusBarUtil.setColor(this, Color.WHITE);
        }
    }

    //沉静式状态栏
    public void cjsStatusBar() {
        if (!openCjsStatusBar) return;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }
}
