package com.example.czlgh.googleplay74.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.czlgh.googleplay74.ui.holder.BaseHolder;
import com.example.czlgh.googleplay74.ui.holder.MoreHolder;
import com.example.czlgh.googleplay74.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by CZLGH on 2018/2/27.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private ArrayList<T> data;

    private static final int TYPE_NORMAL = 0;

    private static final int TYPE_MORE = 1;

    public MyBaseAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == getCount() - 1) {
            return TYPE_MORE;
        } else return getInnerType();

    }

    public int getInnerType() {
        return TYPE_NORMAL;//默认就是普通类型
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {

            //判断是否为加载跟多的布局
            if (getItemViewType(position) == TYPE_MORE) {
                holder = new MoreHolder(hasMore());
            } else {
                holder = getHolder();//
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
//根据数据刷新界面
        if (getItemViewType(position) != TYPE_MORE) {
            holder.setData(getItem(position));
        } else {
            MoreHolder moreHolder= (MoreHolder) holder;
            if(moreHolder.getData()==MoreHolder.STATE_MORE_MORE) {
                loadMore(moreHolder);

            }
        }


        return holder.getRootView();
    }

    //子类可以重写此方法
    public boolean hasMore() {
        return true;
    }

    public abstract BaseHolder<T> getHolder();
    private  boolean isLoadMore=false;

    //加载数据的逻辑
    public void loadMore(final MoreHolder holder){
        //请求网络

        if(!isLoadMore){
            isLoadMore=true;
            new Thread(){
                @Override
                public void run() {
                    //访问网络接口，请求数据
                    final  ArrayList<T> moreData = onLoadMore();
                    UIUtils.runOnUITHread(new Runnable() {

                        @Override
                        public void run() {
                            if (moreData != null) {
                                if (moreData.size() < 20) {
                                    holder.setData(MoreHolder.STATE_MORE_NONE);
                                    Toast.makeText(UIUtils.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                                } else {
                                    holder.setData(MoreHolder.STATE_MORE_MORE);
                                    data.addAll(moreData);
                                    // 刷新界面
                                    MyBaseAdapter.this.notifyDataSetChanged();
                                }
                            } else {
                                holder.setData(MoreHolder.STATE_MORE_ERROR);
                            }
                            isLoadMore=false;
                        }
                    });
                }
            }.start();


        }

    }
    //加载更多数据，必须有子类实现
    public abstract ArrayList<T> onLoadMore();

}
