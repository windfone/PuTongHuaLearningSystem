package com.hlxyedu.putonghualearningsystem.weight.jzvd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.R;

public class CustomUIJzvdStd extends JzvdStd {

    private JzvdStdImp jzvdStdImp;

    public void setJzvdStdImp(JzvdStdImp jzvdStdImp) {
        this.jzvdStdImp = jzvdStdImp;
    }

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

   /* @Override
    public void onStatePause() {
        super.onStatePause();
        if(jzvdStdImp != null){
            jzvdStdImp.videoPause();
        }
    }*/

    @Override
    public void onVideoSizeChanged(int width, int height) {
//        super.onVideoSizeChanged(width, height);
        /*if (JZMediaManager.textureView !=null) {

            JZMediaManager.textureView.setVideoSize(textureViewContainer.getWidth(),textureViewContainer.getHeight());//视频大小与控件大小一致

        }*/
        JzvdStd.setVideoImageDisplayType(JzvdStd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
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
