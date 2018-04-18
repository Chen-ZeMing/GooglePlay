package com.example.czlgh.googleplay74.ui.fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.czlgh.googleplay74.domain.AppInfo;
import com.example.czlgh.googleplay74.http.protocol.HomeProtocol;
import com.example.czlgh.googleplay74.ui.View.LoadingPage;
import com.example.czlgh.googleplay74.ui.adapter.MyBaseAdapter;
import com.example.czlgh.googleplay74.ui.holder.BaseHolder;
import com.example.czlgh.googleplay74.ui.holder.HomeHolder;
import com.example.czlgh.googleplay74.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by CZLGH on 2018/2/11.
 */

public class HomeFragment extends BaseFragment {


    private ArrayList<AppInfo> data;

    //如果加载数据成功 ，就回掉此方法,在主线程运行

    //此方法在LoadingPage里面调用 而
    @Override
    public View onCreateSuccessView() {
        ListView view = new ListView(UIUtils.getContext());
        view.setAdapter(new HomeAdapter(data));

        return view;


    }



    @Override

    //加载网咯数据必须有子类实现，但是父类可以帮主封装

    //而onLoad方法却必须要在
    //此方法运行在子线程
//实现了loadingpage的loadData，但是没有调用
    public LoadingPage.ResultState onLoad() {

//        for (int i = 0; i < 20 ;i++){
//            data.add("测试数据:"+i);
//        }

        HomeProtocol protocol=new HomeProtocol();
        data=protocol.getData(0);


        Log.d("MainActivity", "onLoad: 实现");
//        return LoadingPage.ResultState.STATE_SUCCESS;

        return check(data);//校验数据并且返回
    }



    class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> data){
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder() {
            return new HomeHolder();
        }
//在子线程里调用
        @Override
        public ArrayList<AppInfo> onLoadMore() {
//            ArrayList<String > moreData=new ArrayList<>();
//            for(int i=0; i<20; i++){
//                moreData.add("测试更多数据:"+i);
//            }
//            SystemClock.sleep(2000);
            return null;
        }

//        @Override
//        public boolean hasMore(){
//            return false;
//        }
    }
    static class ViewHolder{
        public TextView tvContent;
    }
}
