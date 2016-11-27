package com.suxw.androidlessions.layoutandview;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.suxw.androidlessions.R;

public class FlipperActivity extends Activity {

		private boolean mIsHeads;
		private ObjectAnimator mFliper;
		private Bitmap mHeadsImage, mTailsImage;
		private ImageView mFlipImage;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_simple_fliper);

			mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.coin_head);
			mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.coin_back);

			mFlipImage = (ImageView)findViewById(R.id.flip_image);
			mFlipImage.setImageBitmap(mHeadsImage);
			mIsHeads = true;

			mFliper = ObjectAnimator.ofFloat(mFlipImage, "rotationY", 0f, 360f);
			mFliper.setDuration(8000);	//动画持续时间，ms
			mFliper.setRepeatMode(ObjectAnimator.RESTART);	//RESTART:重新开始 RESERVE:往返（A-》B-》A，算动画效果执行两次，A->B算一次，B->A也算一次）
			mFliper.setRepeatCount(1);	//动画效果重复次数
			mFliper.addUpdateListener(new AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					if(animation.getAnimatedFraction() >= 0.25f && mIsHeads) {
						mFlipImage.setImageBitmap(mTailsImage);
						mIsHeads = false;
					}
					if ((animation.getAnimatedFraction() >= 0.75f) && (!mIsHeads)) {
						mFlipImage.setImageBitmap(mHeadsImage);
						mIsHeads = true;
					}
				}
			});
		}

		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				mFliper.start();
				return true;
			}
			return super.onTouchEvent(event);
		}

}
