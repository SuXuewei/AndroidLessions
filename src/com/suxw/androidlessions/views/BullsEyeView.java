package com.suxw.androidlessions.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

//��ͬ��Բ
public class BullsEyeView extends View {
	public static final String TAG = "BullsEyeView";

	private Paint mPaint;
	private Point mCenter;
	private float mRadius;

	//Java���캯��
	public BullsEyeView(Context context) {
		this(context, null);
	}

	//XML���캯��
	public BullsEyeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	//������ʽ��XML���캯��
	public BullsEyeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		//�ڴ˹��캯��������ʼ������,�����������캯�����ñ����캯��������ʵ��
		//�������ڻ��ƵĻ�ˢ
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Style.FILL);
		//����Բ����
		mCenter =new Point();
	}

	//ϵͳ�ڸ�������ռ�󣬵���view����onMeasure�������ڽ�������������viewռ�еĿռ�
	//�����Ŀؼ����ͨ�����δ��������view������ݵ�ǰ���ֵ�ģʽ�������Ŀ�ߣ��������ʺ�����Ŀ��
	//��ͨ������seMeasuredDimension��������֪�ܹ�������ͻ㱨����
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width, height;
		
		//ȷ�����ݵ������С���ٶ����κ�Լ���������£�
		int contentWidth = 100;
		int contentHeight = 100;

		width = getMeasurement(widthMeasureSpec, contentWidth);
		height = getMeasurement(heightMeasureSpec, contentHeight);

		setMeasuredDimension(width, height);
	}

	//���ڲ�����Ⱥ͸߶ȵĸ�������
	private int getMeasurement(int measureSpec, int contentSize) {
		int specSize = MeasureSpec.getSize(measureSpec);
		switch(MeasureSpec.getMode(measureSpec)) {
		//ͨ����View���ó�match_parent������ģʽ��С���ߵ�ģʽ��ֻҪ�����������Ŀռ����
		//���Դ�ʱ����������ֵ���޶���ֵ�����е���Сֵ����view������������������
		case MeasureSpec.AT_MOST:
			return Math.min(specSize, contentSize);
		//�������ƣ��������ó�wrap_contentģʽ�����Դ˴������ݴ�С����
		case MeasureSpec.UNSPECIFIED:
			return contentSize;
		//���ǲ��ù̶����ش�С�ķ�ʽ���������ÿ��Ϊ50���أ���ʱ����viewʱ���ǲ������ģʽ�����Դ˴�view���ظ����Ĵ�С
		//��Ȼ��Щ������view����ʵ�־����ģ�Ҳ���Է���contentSize���Ϳ�view������ô�����ˣ��ܹ�ֻ���ṩ�˱�Ҫ�Ĳο�����
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
		//����һϵ�д�С��������ɫ����任��ͬ��Բ
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
