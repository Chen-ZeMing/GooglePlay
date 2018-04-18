package com.example.czlgh.googleplay74.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.czlgh.googleplay74.R;
import com.example.czlgh.googleplay74.ui.View.LoadingPage;
import com.example.czlgh.googleplay74.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by CZLGH on 2018/2/11.
 */


///该BaseFragment的用处就是发现该软件每个界面的共同点，有两个   1：都有刷新布局  2：都有成功的布局，而且成功的布局触发需要
public abstract class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 使用textview显示当前类的类名
        // TextView view = new TextView(UIUtils.getContext());
        // view.setText(getClass().getSimpleName());
        mLoadingPage = new LoadingPage(UIUtils.getContext()) {

            @Override
            public View onCreateSuccessView() {
                // 注意:此处一定要调用BaseFragment的onCreateSuccessView, 否则栈溢出
                return BaseFragment.this.onCreateSuccessView();
            }

            @Override
            public ResultState onLoad() {
                return BaseFragment.this.onLoad();
            }

        };

        return mLoadingPage;
    }

    // 加载成功的布局, 必须由子类来实现
    public abstract View onCreateSuccessView();

    // 加载网络数据, 必须由子类来实现
    public abstract LoadingPage.ResultState onLoad();

    // 开始加载数据
    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }
    public LoadingPage.ResultState check(Object o){
        if(o!=null){
            if (o instanceof ArrayList) {
                //判断是否集合
                ArrayList list=(ArrayList) o;
                if (list.isEmpty()) {
                    return LoadingPage.ResultState.STATE_EMPTY;
                } else {
                    return LoadingPage.ResultState.STATE_SUCCESS;
                }
            }



        }
        return LoadingPage.ResultState.STATE_ERROR;
    }

}
