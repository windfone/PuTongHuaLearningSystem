package com.hlxyedu.putonghualearningsystem.weight.jzvd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.R;

public class CustomUIJzvdStd extends JzvdStd {


    public CustomUIJzvdStd(Context context) {
        super(context);
    }

    public CustomUIJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
//        shareButton = findViewById(R.id.share);
//        shareButton.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_std_with_custom_ui;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
//        if (v.getId() == R.id.share) {
//            Toast.makeText(getContext(), "Whatever the icon means", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void setScreenNormal() {
        super.setScreenNormal();
//        shareButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setScreenFullscreen() {
        super.setScreenFullscreen();

    }

}
