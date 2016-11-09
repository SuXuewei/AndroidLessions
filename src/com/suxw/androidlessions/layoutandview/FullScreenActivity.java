package com.suxw.androidlessions.layoutandview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.suxw.androidlessions.R;

public class FullScreenActivity extends Activity {
	private static final String TAG = "FullScreenActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//请求这个特性，这个状态 栏就会隐藏起来
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_fullscreen);
	}

	public void onToggleClick(View v)
	{
		fullScreen(v);
	}

	private void fullScreen(View v){
		Log.i(TAG, "fullScreen");
		//全屏显示后，再点击屏幕，系统会自动显示隐藏的系统元素（导航栏和状态栏）
		v.setSystemUiVisibility(
				//通过改变窗体大小来隐藏或显示系统元素，并且不要移动已经设定好的layout布局
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				//全屏显示，隐藏状态栏
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				//隐藏导航栏
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

	//隐藏导航栏（隐藏虚拟back和home键），隐藏后再次点击屏幕会显示出导航栏
	private void hideNavagition(View v){
		Log.i(TAG, "hideNavagition");
		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

	//切换夜间模式
	private void switchLowProfile(View v){
		int nCurrentVis = v.getSystemUiVisibility();
		int nNewVis = 0;

		Log.i(TAG, "onToggleClick nCurrentVis: "+nCurrentVis);
		if((nCurrentVis & View.SYSTEM_UI_FLAG_LOW_PROFILE)
				== View.SYSTEM_UI_FLAG_LOW_PROFILE) {
			Log.i(TAG, "onToggleClick current is low profile");
			nNewVis	= View.SYSTEM_UI_FLAG_VISIBLE;
		} else {
			Log.i(TAG, "onToggleClick current is visible");
			nNewVis	= View.SYSTEM_UI_FLAG_LOW_PROFILE;
		}
		v.setSystemUiVisibility(nNewVis);
	}
}
