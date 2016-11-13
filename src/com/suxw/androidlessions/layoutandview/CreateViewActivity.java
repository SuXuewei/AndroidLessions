package com.suxw.androidlessions.layoutandview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.suxw.androidlessions.R;

public class CreateViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_createview);

		//根据xml文件生成一个布局控件树
		LinearLayout layout = (LinearLayout)getLayoutInflater()
				.inflate(R.layout.activity_createview, null);

		//将布局树域activity窗体关联
		setContentView(layout);

		//动态在根目录下添加控件
		Button btnAddInRoot = new Button(this);
		btnAddInRoot.setText("视图根目录动态添加控件");
		layout.addView(btnAddInRoot, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,  
				LayoutParams.WRAP_CONTENT));

		//获取本activity布局目录树下的某个子节点，在该子节点下添加控件
		LinearLayout subLayout = (LinearLayout)findViewById(R.id.ll_subLinearLayout);
		Button btnCancel = new Button(this);
		btnCancel.setText("子节点下添加控件");
		subLayout.addView(btnCancel, 
				new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));

		//此时activity布局结构已经设置完毕待后续架构进行绘图操作
	}
}
