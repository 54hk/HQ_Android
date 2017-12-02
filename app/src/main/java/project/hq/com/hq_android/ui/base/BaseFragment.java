package project.hq.com.hq_android.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.hq.com.hq_android.utils.auto.AutoUtils;

/**
 * Created by 54hk on 2017/12/2
 */

public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), null);
        AutoUtils.auto(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
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

    protected void initListener() {

    }
    public void refreshData(){

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }
}
