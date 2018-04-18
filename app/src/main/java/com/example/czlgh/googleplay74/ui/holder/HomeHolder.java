package com.example.czlgh.googleplay74.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.example.czlgh.googleplay74.R;
import com.example.czlgh.googleplay74.domain.AppInfo;
import com.example.czlgh.googleplay74.utils.UIUtils;

/**
 * Created by CZLGH on 2018/2/27.
 */

public class HomeHolder extends BaseHolder<AppInfo> {


    private TextView tvcontent;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item);
        tvcontent = view.findViewById(R.id.tv_content);
        return view;

    }

    @Override
    public void refreshView(AppInfo data) {
        tvcontent.setText(data.name);
    }
}
