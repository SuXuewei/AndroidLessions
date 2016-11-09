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
		//����������ԣ����״̬ ���ͻ���������
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_fullscreen);
	}

	public void onToggleClick(View v)
	{
		fullScreen(v);
	}

	private void fullScreen(View v){
		Log.i(TAG, "fullScreen");
		//ȫ����ʾ���ٵ����Ļ��ϵͳ���Զ���ʾ���ص�ϵͳԪ�أ���������״̬����
		v.setSystemUiVisibility(
				//ͨ���ı䴰���С�����ػ���ʾϵͳԪ�أ����Ҳ�Ҫ�ƶ��Ѿ��趨�õ�layout����
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				//ȫ����ʾ������״̬��
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				//���ص�����
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

	//���ص���������������back��home���������غ��ٴε����Ļ����ʾ��������
	private void hideNavagition(View v){
		Log.i(TAG, "hideNavagition");
		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

	//�л�ҹ��ģʽ
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
