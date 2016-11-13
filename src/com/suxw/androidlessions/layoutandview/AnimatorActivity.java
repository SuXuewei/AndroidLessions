package com.suxw.androidlessions.layoutandview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.suxw.androidlessions.R;

public class AnimatorActivity extends Activity implements View.OnClickListener{

	View viewToAnimate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animater);
		Button btnStart = (Button)findViewById(R.id.btn_animate);
		btnStart.setOnClickListener(this);
		viewToAnimate = (View)findViewById(R.id.tv_content);
	}

	@Override
	public void onClick(View arg0) {
		//�ж�view��ǰ��͸���ȣ������Ϊ0
		if(viewToAnimate.getAlpha() > 0f) {
			//����view�Ķ���Ч������view��������x����Ϊ1000f�ĵط���͸���Ƚ���Ϊ0
			//�����еĸ������Ա仯Ƶ����animate���о������������ԵĽ���Ч��ͬʱ����
			//�˴�ֻ�����ñ仯���Ե�������ֵ
			viewToAnimate.animate().translationX(1000f).alpha(0f);
		} else {
			//��view���û�ԭ����λ��
			viewToAnimate.setTranslationX(0f);
			//�������Խ���Ч����͸���Ƚ���Ϊ1
			viewToAnimate.animate().alpha(1f);
		}
	}
}
