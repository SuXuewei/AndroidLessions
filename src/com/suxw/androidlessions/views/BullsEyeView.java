package com.suxw.androidlessions.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

//画同心圆
public class BullsEyeView extends View {
	public static final String TAG = "BullsEyeView";

	private Paint mPaint;
	private Point mCenter;
	private float mRadius;

	//Java构造函数
	public BullsEyeView(Context context) {
		this(context, null);
	}

	//XML构造函数
	public BullsEyeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	//带有样式的XML构造函数
	public BullsEyeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		//在此构造函数中做初始化操作,其它两个构造函数调用本构造函数做具体实现
		//创建用于绘制的画刷
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Style.FILL);
		//创建圆中心
		mCenter =new Point();
	}

	//系统在给定区域空间后，调用view自身onMeasure方法，在进行自行评估，view占有的空间
	//给定的控件宽高通过函参传入进来，view自身根据当前布局的模式，给定的宽高，来计算适合自身的宽高
	//并通过调用seMeasuredDimension方法，告知架构。否则就汇报错误。
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width, height;
		
		//确定内容的理想大小（假定无任何约束的条件下）
		int contentWidth = 100;
		int contentHeight = 100;

		width = getMeasurement(widthMeasureSpec, contentWidth);
		height = getMeasurement(heightMeasureSpec, contentHeight);

		setMeasuredDimension(width, height);
	}

	//用于测量宽度和高度的辅助方法
	private int getMeasurement(int measureSpec, int contentSize) {
		int specSize = MeasureSpec.getSize(measureSpec);
		switch(MeasureSpec.getMode(measureSpec)) {
		//通常将View设置成match_parent或其它模式大小上线的模式，只要不超出给定的空间就行
		//所以此时采用理想数值和限定数值两者中的最小值，是view尽量填满给定的区域
		case MeasureSpec.AT_MOST:
			return Math.min(specSize, contentSize);
		//不做限制，例如设置成wrap_content模式，所以此处以内容大小返回
		case MeasureSpec.UNSPECIFIED:
			return contentSize;
		//就是采用固定像素大小的方式，比如设置宽度为50像素，此时绘制view时就是采用这个模式，所以此处view返回给定的大小
		//当然这些都是有view自身实现决定的，也可以返回contentSize，就看view自身怎么定义了，架构只是提供了必要的参考条件
		case MeasureSpec.EXACTLY:
			return specSize;
		default:
			return 0;
		}
	}

	protected void onSizeChanged(int w, int h, int oldW, int oldH) {
		if((w != oldW) || (h != oldH)){
			mCenter.x = w / 2;
			mCenter.y = h / 2;
			mRadius = Math.min(mCenter.x, mCenter.y);
		}
	}

	protected void onDraw(Canvas canvas) {
		//绘制一系列从小到大且颜色交替变换的同心圆
		mPaint.setColor(Color.RED);
		canvas.drawCircle(mCenter.x, mCenter.y, mRadius, mPaint);

		mPaint.setColor(Color.WHITE);
		canvas.drawCircle(mCenter.x, mCenter.y, mRadius*0.8f, mPaint);

		mPaint.setColor(Color.BLUE);
		canvas.drawCircle(mCenter.x, mCenter.y, mRadius*0.6f, mPaint);

		mPaint.setColor(Color.WHITE);
		canvas.drawCircle(mCenter.x, mCenter.y, mRadius*0.4f, mPaint);

		mPaint.setColor(Color.RED);
		canvas.drawCircle(mCenter.x, mCenter.y, mRadius*0.2f, mPaint);
	}
}
