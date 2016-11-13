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
		//判定view当前的透明度，如果不为0
		if(viewToAnimate.getAlpha() > 0f) {
			//调用view的动画效果，将view坐标移至x坐标为1000f的地方，透明度渐变为0
			//这其中的各个属性变化频率有animate自行决定，各个属性的渐变效果同时进行
			//此处只是设置变化属性的最终数值
			viewToAnimate.animate().translationX(1000f).alpha(0f);
		} else {
			//将view设置回原来的位置
			viewToAnimate.setTranslationX(0f);
			//设置属性渐变效果，透明度渐变为1
			viewToAnimate.animate().alpha(1f);
		}
	}
}
