package com.example.czlgh.googleplay74.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.czlgh.googleplay74.R;
import com.example.czlgh.googleplay74.ui.holder.BaseHolder;
import com.example.czlgh.googleplay74.utils.UIUtils;

import org.w3c.dom.Text;

/**
 * Created by CZLGH on 2018/2/28.
 */
//加载几种不同的状态的低端布局
    /*
    1 .可以加载更多
    2.加载更多失败
    3.没有更多数据
     */



public class MoreHolder extends BaseHolder<Integer> {


    public static final int STATE_MORE_MORE = 1;
    public static final int STATE_MORE_ERROR = 2;
    public static final int STATE_MORE_NONE = 3;
    private View view;
    private LinearLayout loadMore;
    private TextView tvLoadError;

    public MoreHolder(boolean hasmore) {
        if (hasmore) {
            setData(STATE_MORE_MORE);
        } else {
            setData(STATE_MORE_NONE);
        }
    }


    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.list_item_more);
        loadMore = view.findViewById(R.id.ll_load_more);
        tvLoadError = view.findViewById(R.id.tv_load_error);

        return view;
    }

    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case STATE_MORE_MORE:
                loadMore.setVisibility(View.VISIBLE);
                tvLoadError.setVisibility(View.GONE);
                break;

            case STATE_MORE_NONE:
                loadMore.setVisibility(View.GONE);
                tvLoadError.setVisibility(View.GONE);
                break;
            case STATE_MORE_ERROR:
                //
                loadMore.setVisibility(View.GONE);
                tvLoadError.setVisibility(View.VISIBLE);

                break;

            default:
                break;
        }
    }
}
