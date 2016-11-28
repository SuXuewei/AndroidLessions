package com.suxw.androidlessions.layoutandview;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.suxw.androidlessions.R;

public class LayoutChangeAnimatorActivity extends Activity {

	private LinearLayout mContainer;
	static int newBtnID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_animator_layoutchange);

		//布局改变时的动画
		mContainer = (LinearLayout)findViewById(R.id.verticalContainer);
		LayoutTransition transition = new LayoutTransition();
		mContainer.setLayoutTransition(transition);

		//通过翻转进入的动画替换默认的出现动画
		Animator appearAnim = ObjectAnimator.ofFloat(null, 
				"rotationY", 90f, 0f).setDuration(
				transition.getDuration(LayoutTransition.APPEARING));
		transition.setAnimator(LayoutTransition.APPEARING, appearAnim);

		//通过翻转消失的动画替换默认退出动画
		Animator disappearAnim = ObjectAnimator.ofFloat(null, 
				"rotationX", 0f, 90f).setDuration(
				transition.getDuration(LayoutTransition.DISAPPEARING));
		transition.setAnimator(LayoutTransition.DISAPPEARING, disappearAnim);

		//
		PropertyValuesHolder pvhSlid = PropertyValuesHolder.ofFloat("y", 0, 1);
		PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("ScaleX", 1f, 0.5f, 1f);
		PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("ScaleY", 1f, 0.5f, 1f);

		Animator changingAppearingAnim = 
				ObjectAnimator.ofPropertyValuesHolder(
						this, pvhSlid, pvhScaleX, pvhScaleY);
		changingAppearingAnim.setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
		transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingAppearingAnim);
	}

	public void onAddClick(View v) {
		Button button = new Button(this);
		button.setText("Click To Remove " + newBtnID++);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mContainer.removeView(arg0);
			}
		});
		
		mContainer.addView(button, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
	}

}
